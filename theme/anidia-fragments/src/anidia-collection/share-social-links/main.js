function socialShare() {
  const blogType = document.querySelector(".anidia-single-post-page .component-html");
  if (blogType) {
    blogType.classList.add('hide');
    const text = blogType.innerText.trim();
    let button = document.querySelector(".share-social-links .an-btn")
    text === 'gas' ? button.innerHTML = "Descubre Anidia Gas" : button.innerHTML = "Descubre Anidia Solar"
    text === 'gas' ? button.href = window.location.origin + "/es/web/guest/gas" : button.href = window.location.origin + "/es/web/guest/solar"
  }
}

if (document.querySelector(".share-social-links")) {
  socialShare();
}
