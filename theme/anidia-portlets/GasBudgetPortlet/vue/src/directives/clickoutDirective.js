const clickOutside = {
  bind(el, binding){
    const idString = "#" + binding.arg
    el.__ClickOutSideCB__ = (event) => {
      if(!(el === event.target || el.contains(event.target))) {
        binding.value(idString)
      }
    }
    
    document.body.addEventListener("click", el.__ClickOutSideCB__)
  },
  unbind(el) {
    document.body.removeEventListener("click", el.__ClickOutSideCB__)
  }
}

export default clickOutside