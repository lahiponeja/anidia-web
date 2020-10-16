let elementWithSubmenu = document.querySelectorAll("ul .dropdown");

for (let i = 0; i < elementWithSubmenu.length; i++) {
    openSubmenu(elementWithSubmenu[i].getElementsByTagName("a")[0]);
    returnLink(elementWithSubmenu[i]);
}

function returnLink(menuItem) {
    let link = menuItem.getElementsByTagName("a")[0],
        submenu = menuItem.getElementsByTagName("ul")[0],
        backItem = document.createElement("li"),
        backLink = document.createElement("a");

    backLink.href = link.href;
    backLink.text = link.text;
    backItem.classList.add("anidia-header__back");
    backItem.appendChild(backLink);
    submenu.insertAdjacentElement("afterbegin", backItem);
    closeSubmenu(backLink);
}

function openSubmenu(link) {
    link.addEventListener("click", (event) => {
      event.preventDefault();
      link.classList.add("active");
      link.nextElementSibling.classList.add("active");
    });
}

function closeSubmenu(link) {
    link.addEventListener("click", (event) => {
      event.preventDefault();
      let submenu = link.parentElement.parentElement;
      submenu.classList.remove("active");
      submenu.previousElementSibling.classList.remove("active");
    });
}
