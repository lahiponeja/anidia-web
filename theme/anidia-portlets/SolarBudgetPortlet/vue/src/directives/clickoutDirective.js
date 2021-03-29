const clickOutside = {
  bind(el, binding){
    if(binding.arg) {
      const idString = "#" + binding.arg
      el.__ClickOutSideCB__ = (event) => {
        if(!(el === event.target || el.contains(event.target))) {
          binding.value(idString)
        }
      }
    } else {
      el.__ClickOutSideCB__ = (event) => {
        if(!(el === event.target || el.contains(event.target))) {
          binding.value(el)
        }
      }
    }

    document.body.addEventListener("click", el.__ClickOutSideCB__)
  },
  unbind(el) {
    document.body.removeEventListener("click", el.__ClickOutSideCB__)
  }
}

export default clickOutside