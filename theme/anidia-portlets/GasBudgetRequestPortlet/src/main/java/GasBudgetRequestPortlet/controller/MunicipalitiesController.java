package GasBudgetRequestPortlet.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;


import com.liferay.portletmvc4spring.bind.annotation.ActionMapping;
import com.liferay.portletmvc4spring.bind.annotation.RenderMapping;

import java.io.IOException;
import java.util.ArrayList;

import javax.portlet.ActionResponse;
import javax.portlet.MutableRenderParameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import GasBudgetRequestPortlet.dto.AddressDTO;
import GasBudgetRequestPortlet.services.AddressService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@Controller
@RequestMapping(value = "VIEW")
public class MunicipalitiesController {

  private Log log = LogFactoryUtil.getLog(MunicipalitiesController.class.getName());

  @ModelAttribute("address")
	public AddressDTO getAddressDTOModelAttribute() {
		return new AddressDTO();
  }

  @RenderMapping
  public String viewIndex(ModelMap modelMap) {

    AddressService addressService = new AddressService();
    log.info("#############################Calling viewIndex##################################");
    try {
      modelMap.put("municipalities", addressService.getMunicipalties());
    } catch (IOException e) {
      modelMap.put("municipalities", new ArrayList());
    }

    return "index";

  }
}