function centeredFeatured(fragmentElement) {
  const container = fragmentElement.querySelector(".an-centered-featured");
  const src = fragmentElement.querySelector(".background-image").src;
  container.style.backgroundImage = 'url(' + src + ')';
}


if (fragmentElement.querySelector(".an-centered-featured")) {
  centeredFeatured(fragmentElement);
}