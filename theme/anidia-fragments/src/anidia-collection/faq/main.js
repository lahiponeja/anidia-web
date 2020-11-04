(function() {
  function accordeon() {
    const accList = this.querySelector("[data-accordeon-list]");
    const accItems = Array.from(accList.children);

    function closeOpenItems() {
      const accFilteredItems = accItems.filter((item) => item != this);
      accFilteredItems.forEach(function(filteredItem) {
        const headItem = filteredItem.querySelector("[data-accordeon-head]");
        const parent = headItem.parentElement;
        if(parent.classList.contains("an-accordeon__item--open")) {
          parent.classList.remove("an-accordeon__item--open");
        }
      });
    }

    function toggleActive() {
      closeOpenItems.call(this);
      this.classList.toggle("an-accordeon__item--open");
    }

    function addEventListeners() {
      this.forEach(function(accItem) {
        const headItem = accItem.querySelector("[data-accordeon-head]");
        headItem.addEventListener("click", toggleActive.bind(accItem));
      });
    }

    function initAccordeon() {
      addEventListeners.call(accItems);
    }

    initAccordeon();
  }

  if(this) {
    this.forEach(function(acc){accordeon.call(acc);});
  }
}).call(document.querySelectorAll("[data-accordeon]"));
