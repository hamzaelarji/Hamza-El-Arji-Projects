document.addEventListener("DOMContentLoaded", function() {
    loadSlides();
});

var currentIndex = -1;
var slideshowInterval;
var slidesData;

function loadSlides() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "slides.json");
    xhr.onload = function() {
        if (xhr.status === 200) {
            slidesData = JSON.parse(xhr.responseText).slides;
            renderSlides(slidesData);
            setupButtons(slidesData);
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

function showSlide(slides, index) {
    var slshContainer = document.getElementById("SLSH");
    slshContainer.innerHTML = ""; // Effacez la div

    var slide = slides[index];
    if (slide.url !== "") {
        var iframe = document.createElement("iframe");
        iframe.src = slide.url;
        iframe.width = "100%";
        iframe.height = "100%";
        slshContainer.appendChild(iframe);
    }
}

function pauseContinueSlideshow() {
    if (slideshowInterval) {
        clearInterval(slideshowInterval);
        slideshowInterval = null; // Set to null to indicate paused state
    } else {
        // If paused, resume from the current slide
        playSlideshow(slidesData);
    }
}

function setupButtons(slides) {
    var pauseButton = document.getElementById("pauseButton");
    var precedentButton = document.getElementById("precedent");
    var suivButton = document.getElementById("suiv");

    if (pauseButton) {
        pauseButton.addEventListener("click", function() {
            pauseContinueSlideshow();
        });
    }

    precedentButton.addEventListener("click", function() {
        currentIndex = (currentIndex - 1 + slides.length) % slides.length;
        showSlide(slides, currentIndex);
        clearInterval(slideshowInterval);
    });

    suivButton.addEventListener("click", function() {
        currentIndex = (currentIndex + 1) % slides.length;
        showSlide(slides, currentIndex);
        clearInterval(slideshowInterval);
    });

    // Ensure the slideshow starts from the first slide on initial load
    showSlide(slides, 0);
}
