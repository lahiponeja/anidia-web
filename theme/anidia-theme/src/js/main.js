let menuItemsWithSubmenu = document.querySelectorAll("ul .dropdown");

for (let i = 0; i < menuItemsWithSubmenu.length; i++) {
    _addDrillDown(menuItemsWithSubmenu[i].getElementsByTagName("a")[0]);
    _insertBackLinks(menuItemsWithSubmenu[i]);
}

function _insertBackLinks(menuItem) {
    let link = menuItem.getElementsByTagName("a")[0],
        submenu = menuItem.getElementsByTagName("ul")[0],
        backItem = document.createElement("li"),
        backLink = document.createElement("a");

    backLink.href = link.href;
    backLink.text = link.text;
    backItem.classList.add("back");
    backItem.appendChild(backLink);
    submenu.insertAdjacentElement("afterbegin", backItem);
    _addDrillUp(backLink);
}

function _addDrillDown(link) {
    link.addEventListener("click", (event) => {
      event.preventDefault();
      link.classList.add("active");
      link.nextElementSibling.classList.add("active");
    });
}

function _addDrillUp(link) {
    link.addEventListener("click", (event) => {
      let submenu = link.parentElement.parentElement;
      submenu.classList.remove("active");
      submenu.previousElementSibling.classList.remove("active");
    });
}
