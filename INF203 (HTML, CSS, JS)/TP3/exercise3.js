document.addEventListener("DOMContentLoaded", function() {
    loadSlides();
});

function loadSlides() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "slides.json");
    xhr.onload = function() {
        if (xhr.status === 200) {
            var slides = JSON.parse(xhr.responseText).slides;
            renderSlides(slides);
            setupPlayButton(slides);
        }
    };
    xhr.send();
}

function renderSlides(slides) {
    var slshContainer = document.getElementById("SLSH");

    slides.forEach(function(slide) {
        var slideDiv = document.createElement("div");
        slideDiv.textContent = "Time: " + slide.time + "s, URL: " + slide.url;
        slshContainer.appendChild(slideDiv);
    });
}

function playSlideshow(slides) {
    slides.forEach(function(slide) {
        setTimeout(function() {
            var slshContainer = document.getElementById("SLSH");
            slshContainer.innerHTML = ""; // Clear the div

            if (slide.url !== "") {
                var iframe = document.createElement("iframe");
                iframe.src = slide.url;
                iframe.width = "100%";
                iframe.height = "100%";
                slshContainer.appendChild(iframe);
            }
        }, slide.time * 1000);
    });
}

function setupPlayButton(slides) {
    var playButton = document.getElementById("pl");
    playButton.addEventListener("click", function() {
        playSlideshow(slides);
    });
}
