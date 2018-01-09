package omis.contact.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.contact.web.form.OnlineAccountFields;

/**
 * Online account fields validator delegate.
 * 
 * @author Sheronda Vaughn
 * @author Yidong Li
 * @version 0.1.0 (Nov 6, 2017)
 * @since OMIS 3.0
 */
public class OnlineAccountFieldsValidatorDelegate {

	/* Constructor. */
	
	/**
	 * Instantiates a default instance of online account fields validator.
	 */
	public OnlineAccountFieldsValidatorDelegate() {
		// Default constructor.
	}

	/* Validation method. */
	
	public Errors validateOnlineAccountFields(
			final OnlineAccountFields onlineAccountFields, 
			final String onlineAccountFieldsVariableName,
			final Errors errors) {
		if (onlineAccountFields.getName()==null
			||onlineAccountFields.getName().length() == 0) {
			errors.rejectValue(onlineAccountFieldsVariableName + ".name", 
					"onlineAccountFields.name.empty");
		}

		if (onlineAccountFields.getHost() == null) {
			errors.rejectValue(onlineAccountFieldsVariableName + ".host", 
					"onlineAccountFields.host.empty");
		}
		return errors;
	}
}