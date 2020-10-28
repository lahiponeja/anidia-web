package ContactFormPortlet.controller;

import ContactFormPortlet.dto.*;
import ContactFormPortlet.exception.PortletException;
import ContactFormPortlet.exception.*;
import ContactFormPortlet.services.*;
import com.liferay.portal.kernel.log.*;
import com.liferay.portletmvc4spring.bind.annotation.*;
import java.util.*;
import javax.portlet.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.validation.beanvalidation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author danieldelapena
 */
@Controller
@RequestMapping("VIEW")
public class LeadController {
	private Log log = LogFactoryUtil.getLog(LeadController.class.getName());

	@ModelAttribute("lead")
	public LeadDTO getLeadModelAttribute() {
		return new LeadDTO();
	}

	@ModelAttribute("prefixes")
	public List<String> getPrefixesList() {
		List<String> prefixes = new ArrayList<String>();
		prefixes.add("+34");
		prefixes.add("+49");
		prefixes.add("+44");
		return prefixes;
	}

	@RenderMapping
	public String prepareView() {
		return "form";
	}

	@RenderMapping(params = "javax.portlet.action=success")
	public String showData(ModelMap modelMap) throws ValidationException, PortletException {

		SalesforceService salesforceService = new SalesforceService();

		LeadDTO lead = (LeadDTO) modelMap.get("lead");
		log.info("Sending Lead" );
		log.info(" >  firstName=" + lead.getFirstName());
		log.info(" >  lastName=" + lead.getLastName());
		log.info(" >  email=" + lead.getEmail());
		log.info(" >  phoneNumber=" + lead.getPhoneNumber());

		salesforceService.sendLead(lead);

		//log.info(" >  Result =" + result);

		return "form";
	}

	@ActionMapping
	public void submitApplicant(
		@ModelAttribute("lead") LeadDTO Lead, BindingResult bindingResult,
		ModelMap modelMap, Locale locale, ActionResponse actionResponse,
		SessionStatus sessionStatus) {
		//_localValidatorFactoryBean.validate(Lead, bindingResult);

		if (!bindingResult.hasErrors()) {
			if (_logger.isDebugEnabled()) {
				_logger.debug("firstName=" + Lead.getFirstName());
				_logger.debug("lastName=" + Lead.getLastName());
				_logger.debug("email=" + Lead.getEmail());
				_logger.debug("phoneNumber=" + Lead.getPhoneNumber());
			}

			MutableRenderParameters mutableRenderParameters =
				actionResponse.getRenderParameters();

			mutableRenderParameters.setValue("javax.portlet.action", "success");

			sessionStatus.setComplete();
		}
		else {
			bindingResult.addError(
				new ObjectError(
					"Lead",
					_messageSource.getMessage(
						"please-correct-the-following-errors", null, locale)));
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		LeadController.class);

	@Autowired
	private LocalValidatorFactoryBean _localValidatorFactoryBean;

	@Autowired
	private MessageSource _messageSource;

}