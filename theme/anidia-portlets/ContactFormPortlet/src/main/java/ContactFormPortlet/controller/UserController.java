package ContactFormPortlet.controller;

import ContactFormPortlet.dto.UserDTO;

import com.liferay.portletmvc4spring.bind.annotation.ActionMapping;
import com.liferay.portletmvc4spring.bind.annotation.RenderMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Locale;

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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import ContactFormPortlet.services.SalesforceService;
/**
 * @author danieldelapena
 */
@Controller
@RequestMapping("VIEW")
public class UserController {
	private Log log = LogFactoryUtil.getLog(UserController.class.getName());

	@ModelAttribute("user")
	public UserDTO getUserModelAttribute() {
		return new UserDTO();
	}

	@RenderMapping
	public String prepareView() {
		log.info("#############################PREPARE VIEW##################################");

		return "user";
	}

	@RenderMapping(params = "javax.portlet.action=success")
	public String showData(ModelMap modelMap) {
		SalesforceService salesforceService = new SalesforceService();
		modelMap.put("testVar",salesforceService.sendContactData());
		return "summary";
	}

	@ActionMapping
	public void submitApplicant(
		@ModelAttribute("user") UserDTO user, BindingResult bindingResult,
		ModelMap modelMap, Locale locale, ActionResponse actionResponse,
		SessionStatus sessionStatus) {
		//_localValidatorFactoryBean.validate(user, bindingResult);
		log.info("#############################SUBMIT APPLICANT#############################");
		
		if (!bindingResult.hasErrors()) {
			if (_logger.isDebugEnabled()) {
				_logger.debug("firstName=" + user.getFirstName());
				_logger.debug("lastName=" + user.getLastName());
				_logger.debug("email=" + user.getEmail());
				_logger.debug("phoneNumber=" + user.getPhoneNumber());
			}

			MutableRenderParameters mutableRenderParameters =
				actionResponse.getRenderParameters();

			mutableRenderParameters.setValue("javax.portlet.action", "success");

			sessionStatus.setComplete();
		}
		else {
			bindingResult.addError(
				new ObjectError(
					"user",
					_messageSource.getMessage(
						"please-correct-the-following-errors", null, locale)));
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		UserController.class);

	@Autowired
	private LocalValidatorFactoryBean _localValidatorFactoryBean;

	@Autowired
	private MessageSource _messageSource;

}