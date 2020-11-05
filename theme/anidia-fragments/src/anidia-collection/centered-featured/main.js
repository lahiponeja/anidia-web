function centeredFeatured(fragmentElement) {
  const container = fragmentElement.querySelector(".an-centered-featured");
  const src = fragmentElement.querySelector(".background-image").src;
  container.style.backgroundImage = 'url(' + src + ')';

  if (!document.querySelector('.an-centered-featured__left-img') || !document.querySelector('.an-centered-featured__right-img')) {
    document.querySelector('.bg-an-theme--centered-featured').classList.add('no-img');
  }
}


if (fragmentElement.querySelector(".an-centered-featured")) {
  centeredFeatured(fragmentElement);
}
