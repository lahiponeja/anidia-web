(function(global) {
  var UtagLayerHandler = {};
  global.UtagLayerHandler = UtagLayerHandler;

  UtagLayerHandler.init = function() {
    this.bindEvents();
  };

   UtagLayerHandler.pushData = function(data) {
    if(data !== null) {
      var parsed_data = JSON.parse(data),
          temp = {};
      delete parsed_data.only_checked;
      for(var key in parsed_data) {
        temp[key] = parsed_data[key];
      }
      utag.link(temp);
    }
  };

  UtagLayerHandler.bindEvents = function() {
    var events = ['click', 'contextmenu', 'dblclick', 'mousedown',
                  'mouseenter', 'mouseleave', 'mousemove', 'mouseover',
                  'mouseout', 'mouseup', 'keydown', 'keypress', 'keyup',
                  'abort', 'beforeunload', 'error', 'hashchange', 'load',
                  'pageshow', 'pagehide', 'resize', 'scroll', 'unload',
                  'blur', 'change', 'focus', 'focusin', 'focusout',
                  'input', 'invalid', 'reset', 'search', 'select', 'submit',
                  'one'];
    for(var i = 0; i < events.length; i++) {
      this.bind(events[i]);
    }
  };

  UtagLayerHandler.bind = function(eventToBind) {
      document.body.addEventListener(eventToBind, function(event) {
        var element = event.target,
            $element_input = ($(element).find("input") != null ? $(element) : $(element).find("input")),
            data;
        do {

          data = element.getAttribute('data-gtm-' + eventToBind);

          element = element.parentElement;
        } while (element && element.parentElement && data === null);
        if (data !== null) {
          var dataFormatted = JSON.parse(data);
          if(dataFormatted["only_checked"] == "true"){
            if($element_input.is(":checked") == true)
              this.pushData(data);
          }
          else
            this.pushData(data);
        }
      }.bind(this));
    };

})(window);
