function header() {
  if (Math.max(document.documentElement.clientWidth, window.innerWidth || 0) < 1024){
    let elementWithSubmenu = document.querySelectorAll("ul .dropdown");

    for (let i = 0; i < elementWithSubmenu.length; i++) {
        openSubmenu(elementWithSubmenu[i].getElementsByClassName("lfr-nav-child-toggle")[0]);
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
          link.parentElement.parentElement.parentElement.classList.add('hide-border');
          link.classList.add("active");
          if (document.querySelectorAll('.nav-item:not(.open)').length) {
            document.querySelectorAll('.nav-item:not(.open)').forEach(e => {
              e.classList.add('hide');
            });
          }
          link.nextElementSibling.classList.add("active");
        });
    }

    function closeSubmenu(link) {
        link.addEventListener("click", (event) => {
          event.preventDefault();
          let submenu = link.parentElement;
          submenu.parentElement.parentElement.classList.remove("open");
          document.querySelectorAll('.nav-item.dropdown').forEach(e => {
            e.classList.remove('hide');
            e.classList.remove('hide-border');
          });
        });
    }
    document.querySelector('.anidia-header__input').addEventListener('change', () => {
      document.body.classList.toggle('overflow-hidden');
      document.querySelector('.anidia-header').classList.toggle('active');
      document.querySelector('.site-title .anidia-logo-header--mobile--white').classList.toggle('hide');
      document.querySelector('.site-title .anidia-logo-header--mobile--green').classList.toggle('hide');
    });
  }
}

if (document.querySelector('.anidia-header__input')) {
  header();
}

if (document.querySelectorAll(".an-checkbox__label a").length && document.querySelectorAll(".an-modal").length) {
  var buttons = document.querySelectorAll(".an-checkbox__label a");

  buttons.forEach(button => {
    button.addEventListener('click', (e) => {
      e.preventDefault();
      document.getElementById(button.dataset.modal).style.display = 'block';
    });
  });

  var close = document.querySelectorAll(".an-modal__close");

  close.forEach(c => {
    c.addEventListener('click', (e) => {
      e.preventDefault();
      c.parentElement.parentElement.style.display = "none";
    });
  });
}

svg4everybody(
  {
    attributeName: 'data-href',
    polyfill: true,
    validate: function (src, svg, use) {
      return !src || !src.startsWith('#');
    }
  }
);
