function header() {
  if (Math.max(document.documentElement.clientWidth, window.innerWidth || 0) < 1024){
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
          link.parentElement.classList.add('hide-border')
          link.classList.add("active");
          document.querySelector('.nav-item.dropdown:not(.active)').classList.add('hide')
          link.nextElementSibling.classList.add("active");
        });
    }

    function closeSubmenu(link) {
        link.addEventListener("click", (event) => {
          event.preventDefault();
          let submenu = link.parentElement.parentElement;
          submenu.classList.remove("active");
          submenu.previousElementSibling.classList.remove("active");
          document.querySelectorAll('.nav-item.dropdown').forEach(e => {
            e.classList.remove('hide');
            e.classList.remove('hide-border');
          })
        });
    }

    document.querySelector('.anidia-header__input').addEventListener("click", (event) => {
      document.body.classList.toggle('overflow-hidden');
      document.querySelector('.anidia-header').classList.toggle('active');
      document.querySelector('.site-title .anidia-logo-header--mobile--white').classList.toggle('hide')
      document.querySelector('.site-title .anidia-logo-header--mobile--green').classList.toggle('hide')
    })
  }
}

if (document.querySelector(".anidia-header__input")) {
  header();
}

