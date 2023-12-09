const hamburger = document.querySelector(".hamburger");
hamburger.addEventListener("click", clickHamburger);
const hamburgerDiv = document.getElementById("hamburger_click");
function clickHamburger() {
    hamburger.classList.toggle("active");

    if (hamburgerDiv.style.display === "block") {
        hamburgerDiv.style.display = "none";
    } else {
        hamburgerDiv.style.display = "block";
    }
}