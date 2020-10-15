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

	private static final long serialVersionUID = 1113488483222411111L;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String email;

	@NotBlank
	private String phoneNumber;

}