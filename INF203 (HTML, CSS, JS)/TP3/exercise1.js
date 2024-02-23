
function loadDoc() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "text.txt");
    xhr.onload = function() {
        if (xhr.status === 200) {
            document.getElementById("texta").value = xhr.responseText;
        }
    };
    xhr.send();
}

function loadDoc2() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "text.txt");
    xhr.onload = function() {
        if (xhr.status === 200) {
            let text = xhr.responseText.split('\n'); 
            let textContainer = document.getElementById("texta2");
            text.forEach((line, index) => {
                let paragraph = document.createElement("p");
                paragraph.textContent = line;
                let color = index % 2 === 0 ? "blue" : "white"; 
                paragraph.style.color = color;
                textContainer.appendChild(paragraph);
            });
        }
    };
    xhr.send();
}