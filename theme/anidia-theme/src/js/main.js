
var customValidationInputs = document.querySelectorAll("input[data-validation-message]");

if(customValidationInputs.length > 0) {
  customValidationInputs.forEach(input => {
    if(input.setCustomValidity) {
      input.addEventListener('input', (e) => {
        input.reportValidity();
      });

      input.addEventListener('invalid', (e) => {
          input.setCustomValidity('');
          if(!input.validity.valid) {
            input.setCustomValidity(input.dataset.validationMessage);
          }
      })
    }
  });
}
