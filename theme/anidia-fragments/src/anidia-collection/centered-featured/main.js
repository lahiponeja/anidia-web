function centeredFeatured() {
  const container = document.querySelector(".an-centered-featured");
  const src = document.querySelector("#ImagenFondo img").src;
  container.style.backgroundImage = 'url(' + src + ')';
}


if (document.querySelector(".an-centered-featured")) {
  centeredFeatured();
}
