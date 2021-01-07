<script defer>
  (function() {
    let currentDropdownActiveIndex = null
    const lfrNavItems = Array.from(document.querySelectorAll('.lfr-nav-item'))
    const UlNavBar = document.querySelector(".navbar-blank.navbar-nav.navbar-site")

    function checkItemOnMouseEnter(e) {
      const lfrNavItem = e.target
      const itemHasDropdown = lfrNavItem.classList.contains('dropdown')

      hideAllDropdowns();

      if(itemHasDropdown) {
        currentDropdownActiveIndex = lfrNavItems.indexOf(lfrNavItem)
      } 

      if(!Object.is(currentDropdownActiveIndex, null)) {
        lfrNavItems[currentDropdownActiveIndex].classList.add("hover", "open")
      }
    }

    function setIndexToNull() {
      hideAllDropdowns()
      currentDropdownActiveIndex = null
    }

    function hideAllDropdowns() {
      lfrNavItems.forEach(function(lfrNavItem) {
        lfrNavItem.classList.remove("hover", "open")
        // console.log(lfrNavItem)
      })
    }

    function addEventListeners(bool) {
      if(bool) {
        if(lfrNavItems.length) {
          lfrNavItems.forEach(function(lfrNavItem) {
            lfrNavItem.addEventListener('mouseenter', checkItemOnMouseEnter)
          })
        }
        if(UlNavBar) {
          // console.log(UlNavBar)
          UlNavBar.addEventListener('mouseleave', setIndexToNull)
        }
      } else {
        if(lfrNavItems.length) {
          lfrNavItems.forEach(function(lfrNavItem) {
            lfrNavItem.removeEventListener('mouseenter', checkItemOnMouseEnter)
          })
        }
        if(UlNavBar) {
          UlNavBar.removeEventListener('mouseleave', setIndexToNull)
        }
      }
    }

    function debounce(fn, time) {
      let timeout;
      return function(...args) {
        callback = () => fn.apply(this, arguments);
        clearTimeout(timeout);
        timeout = setTimeout(callback, time);
      }
    }

    function keepMenuDropdownOpen() {
      if(window.innerWidth <= 768) { 
        addEventListeners(false)
      } else {
        addEventListeners(true)
      }
    }

    keepMenuDropdownOpen()

    window.onresize = debounce(keepMenuDropdownOpen, 500)

  })()
</script>