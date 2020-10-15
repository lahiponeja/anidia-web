package ContactFormPortlet.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * @author danieldelapena
 */
public class LeadDTO implements Serializable {

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Boolean getPrivacyAccepted() {
		return privacyAccepted;
	}

	public Boolean getCommunicationAccepted() {
		return communicationAccepted;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCommunicationAccepted(Boolean communicationAccepted) {
		this.communicationAccepted = communicationAccepted;
	}

	public void setPrivacyAccepted(Boolean privacyAccepted) {
		this.privacyAccepted = privacyAccepted;
	}


	private static final long serialVersionUID = 1113488483222411111L;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String email;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	private Boolean privacyAccepted;

	@NotBlank
	private Boolean communicationAccepted;


}