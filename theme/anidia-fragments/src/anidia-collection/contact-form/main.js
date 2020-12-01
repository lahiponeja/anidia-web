const selects = document.querySelectorAll('.data-select-container');

function customSelect() {
  const elSelectContainer = this;
  const elSelectNative = elSelectContainer.querySelector('.data-select-native');
  const elSelectCustom = elSelectContainer.querySelector('.data-select-custom');
  const elSelectCustomBox = elSelectCustom.querySelector('.data-select-custom-trigger');
  const elSelectCustomOpts = elSelectCustom.querySelector('.data-select-custom-options');
  const customOptsList = Array.from(elSelectCustomOpts.children);
  const optionsCount = customOptsList.length;

  let optionChecked = "";
  let optionHoveredIndex = -1;

  elSelectCustomBox.addEventListener("click", (e) => {
    const isClosed = !elSelectCustom.classList.contains("is-active");

    if (isClosed) {
      openSelectCustom();
    } else {
      closeSelectCustom();
    }
  });

  function openSelectCustom() {
  elSelectCustom.classList.add("is-active");
  elSelectContainer.classList.add("is-active");
    // Remove aria-hidden in case this was opened by a user
    // who uses AT (e.g. Screen Reader) and a mouse at the same time.
    elSelectCustom.setAttribute("aria-hidden", false);

    if (optionChecked) {
      const optionCheckedIndex = customOptsList.findIndex(
        (el) => el.getAttribute("data-value") === optionChecked
      );
      updateCustomSelectHovered(optionCheckedIndex);
    }

    // Add related event listeners
    document.addEventListener("click", watchClickOutside);
    document.addEventListener("keydown", supportKeyboardNavigation);
  }

  function closeSelectCustom() {
    elSelectCustom.classList.remove("is-active");
    elSelectContainer.classList.remove("is-active");

    elSelectCustom.setAttribute("aria-hidden", true);

    // updateCustomSelectHovered(-1);

    // Remove related event listeners

    document.removeEventListener("click", watchClickOutside);
    document.removeEventListener("keydown", supportKeyboardNavigation);
  }

  function watchClickOutside(e) {
    const didClickedOutside = !elSelectCustom.contains(event.target);
    if (didClickedOutside) {
      closeSelectCustom();
    }
  }

  function updateCustomSelectHovered(newIndex) {
    const prevOption = elSelectCustomOpts.children[optionHoveredIndex];
    const option = elSelectCustomOpts.children[newIndex];

    if (prevOption) {
      prevOption.classList.remove("is-hover");
    }
    if (option) {
      option.classList.add("is-hover");
    }

    optionHoveredIndex = newIndex;
  }

  function updateCustomSelectChecked(value, text) {
    const prevValue = optionChecked;

    const elPrevOption = elSelectCustomOpts.querySelector(`[data-value="${prevValue}"`);
    const elOption = elSelectCustomOpts.querySelector(`[data-value="${value}"`);

    if (elPrevOption) {
      elPrevOption.classList.remove("is-active");
    }

    if (elOption) {
      elOption.classList.add("is-active");
    }

    elSelectCustomBox.querySelector('span').textContent = text;
    optionChecked = value;
  }

  function supportKeyboardNavigation(e) {
    // press down -> go next
    if (event.keyCode === 40 && optionHoveredIndex < optionsCount - 1) {
      // let index = optionHoveredIndex;
      e.preventDefault(); // prevent page scrolling
      updateCustomSelectHovered(optionHoveredIndex + 1);
    }

    // press up -> go previous
    if (event.keyCode === 38 && optionHoveredIndex > 0) {
      e.preventDefault(); // prevent page scrolling
      updateCustomSelectHovered(optionHoveredIndex - 1);
    }

    // press Enter or space -> select the option
    if (event.keyCode === 13 || event.keyCode === 32) {
      e.preventDefault();

      const option = elSelectCustomOpts.children[optionHoveredIndex];
      const value = option && option.getAttribute("data-value");

      if (value) {
        elSelectNative.value = value;
        updateCustomSelectChecked(value, option.textContent);
        setFlag(value);
      }
      closeSelectCustom();
    }

    // press ESC -> close selectCustom
    if (event.keyCode === 27) {
      closeSelectCustom();
    }
  }

  // Update selectCustom value when selectNative is changed.
  elSelectNative.addEventListener("change", (e) => {
    const value = e.target.value;
    const elRespectiveCustomOption = elSelectCustomOpts.querySelector(
      `[data-value="${value}"]`
    );

    updateCustomSelectChecked(value, elRespectiveCustomOption.textContent);
  });

  // Update selectCustom value when an option is clicked or hovered
  customOptsList.forEach(function (elOption, index) {
    elOption.addEventListener("click", (e) => {
      const value = e.target.getAttribute("data-value");

      // Sync native select to have the same value
      elSelectNative.value = value;
      updateCustomSelectChecked(value, e.target.textContent);
      closeSelectCustom();
      setFlag(value);
    });

    elOption.addEventListener("mouseenter", (e) => {
      updateCustomSelectHovered(index);
    });
  });
}


let locationOrigin = window.location.origin,
    prefixSelect = document.querySelector(".prefix-select"),
    flagImgArr = document.querySelectorAll(".an-select__flag"),
    prefixSelectValue = prefixSelect.value;

prefixSelect.addEventListener("change", function() {
  prefixSelectValue = prefixSelect.value;
  setFlag(prefixSelect.value);
})

function setFlag(prefixSelectValue) {
  flagImgArr.forEach((flagImg) => {
    switch(prefixSelectValue) {
      case "+34":
        flagImg.src = locationOrigin + "/o/anidia-theme/images/flags/flag-spain.svg";
        break;
      case "+44":
        flagImg.src = locationOrigin + "/o/anidia-theme/images/flags/flag-uk.svg";
        break;
      case "+49":
        flagImg.src = locationOrigin + "/o/anidia-theme/images/flags/flag-germany.svg";
        break;
    }
  });
}

if(selects.length) {
  selects.forEach((elem)=> customSelect.call(elem));
}

setFlag("+34");
