<script>
(function(){
  const navItem = Array.from(document.querySelectorAll(".nav-item"));

  if(navItem) {
    const pathNameSplit = window.location.pathname.split("/");
    const pathNameSplitLastItem = pathNameSplit[pathNameSplit.length-1];

    function findLinkCB(currentValue, index) {
        const navLink = currentValue.querySelector(".nav-link");
        const linkUrl = navLink.getAttribute("href");
        const linkSplit = linkUrl.split("/")
        const linkSplitLastItem = linkSplit[linkSplit.length-1]
        return linkSplitLastItem === pathNameSplitLastItem;
    }

    const activeItem = navItem.find(findLinkCB);

    if(activeItem) {
      activeItem.classList.add("active-section");
    }
  }
})();
</script>