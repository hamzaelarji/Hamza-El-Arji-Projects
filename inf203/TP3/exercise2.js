function sendMessage() {
    // Get the text from the textedit field
    var sentence = document.getElementById("textedit").value;

    // Call the chat.php file with the sentence parameter
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "chat.php?phrase=" + encodeURIComponent(sentence));
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Clear the textedit field after successful sending
            document.getElementById("textedit").value = "";
            // Reload the chat after sending a message
            reloadChat();
        }
    };
    xhr.send();
}

function reloadChat() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "chatlog.txt");
    xhr.onload = function() {
        if (xhr.status === 200) {
            var chatLines = xhr.responseText.split('\n');
            var textContainer = document.getElementById("texta");

            // Display the last 10 non-empty messages only
            var messagesToDisplay = chatLines
                .filter(message => message.trim() !== '')
                .slice(-10)
                .reverse();

            // Clear existing content in the texta div
            textContainer.innerHTML = "";

            // Display messages in reverse order (latest at the top)
            messagesToDisplay.forEach(message => {
                var paragraph = document.createElement("p");
                paragraph.textContent = message;
                textContainer.appendChild(paragraph);
            });
        }
    };
    xhr.send();
}

// Initial call to load the chat
reloadChat();

// Set up a loop to reload the chat every second
setInterval(function() {
    reloadChat();
}, 1000);
