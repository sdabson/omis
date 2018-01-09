package omis.alert.web.validator.testng;

import java.beans.PropertyEditor;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import omis.alert.web.form.AlertForm;
import omis.alert.web.validator.AlertFormValidator;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.person.domain.Person;
import omis.testng.AbstractNonTransactionalTestNGSpringContextTests;
import omis.web.validator.StringLengthChecks;

/**
 * Tests validator for alert form.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 29, 2017)
 * @since OMIS 3.0
 */
@Test(groups = {"alert", "validator"})
public class AlertFormValidatorTests
		extends AbstractNonTransactionalTestNGSpringContextTests {

	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* String length checks. */
	
	@Autowired
	@Qualifier("stringLengthChecks")
	private  StringLengthChecks stringLengthChecks;
	
	/* Validators. */
	
	private AlertFormValidator alertFormValidator;
	
	/* Class preparation methods. */
	
	/** Prepares test class. */
	@BeforeClass
	public void prepareClass() {
		this.alertFormValidator = new AlertFormValidator(stringLengthChecks);
	}
	
	/* Test methods. */
	
	/** Tests that description is required. */
	public void testDescriptionRequired() {
		
		// Arrangements
		AlertForm alertForm = this.createAlertForm("",
				null, null, null, this.parseDateText("12/13/2014"));
		Errors errors = new BeanPropertyBindingResult(alertForm, "alertForm");
		
		// Action
		this.alertFormValidator.validate(alertForm, errors);
		
		// Assertions
		assert this.hasFieldErrorCode(
				errors, "description", "alert.description.empty")
			: "Empty description allowed";
	}
	
	/** Tests that expire date is required. */
	public void testExpireDateRequired() {
		
		// Arrangements
		AlertForm alertForm = this.createAlertForm(
				"description", null, null, null, null);
		Errors errors = new BeanPropertyBindingResult(alertForm, "alertForm");
		
		// Action
		this.alertFormValidator.validate(alertForm, errors);
		
		// Assertions
		assert this.hasFieldErrorCode(
				errors, "expireDate", "alert.expireDate.empty");
	}
	
	/** Tests description length limit. */
	public void testDescriptionLengthLimit() {
		
		// Arrangements
		int length = this.stringLengthChecks.getLargeCheck().getLength();
		String description = String.format(
				"%1$" + (length + 1) + "s", "large description");
		AlertForm alertForm = this.createAlertForm(description,
				null, null, null, this.parseDateText("12/12/2012"));
		Errors errors = new BeanPropertyBindingResult(alertForm, "alertForm");
		
		// Action
		this.alertFormValidator.validate(alertForm, errors);
		
		// Assertions
		assert this.hasFieldErrorCode(
				errors, "description", "largeStringLength.exceeded")
			: String.format(
					"Description \"%s\" must be %d characters or less",
					description, length);
	}
	
	/** Tests resolve description length limit. */
	public void testResolveDescriptionLengthLimit() {
		
		// Arrangements
		int length = this.stringLengthChecks.getLargeCheck().getLength();
		String resolveDescription = String.format(
				"%1$" + (length + 1) + "s", " large resolve description");
		AlertForm alertForm = this.createAlertForm("description",
				null, resolveDescription, null,
				this.parseDateText("11/11/2011"));
		Errors errors = new BeanPropertyBindingResult(alertForm, "alertForm");
		
		// Action
		this.alertFormValidator.validate(alertForm, errors);
		
		// Assertions
		assert this.hasFieldErrorCode(errors, "resolveDescription",
				"largeStringLength.exceeded")
			: String.format(
					"Resolve description \"%s\" must be %d characters or less",
					resolveDescription, length);
	}
	
	/* Helper methods. */
	
	// Parse date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
	
	// Returns whether error field has error with code
	private boolean hasFieldErrorCode(
			final Errors errors, final String field, final String code) {
		List<FieldError> fieldErrors = errors.getFieldErrors(field);
		for(FieldError fieldError : fieldErrors) {
			if (fieldError.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	// Returns alert form
	private AlertForm createAlertForm(
			final String description, final Date resolveDate,
			final String resolveDescription, final Person resolveByPerson,
			final Date expireDate) {
		AlertForm alertForm = new AlertForm();
		alertForm.setDescription(description);
		alertForm.setResolveDate(resolveDate);
		alertForm.setResolveDescription(resolveDescription);
		alertForm.setResolveByPerson(resolveByPerson);
		alertForm.setExpireDate(expireDate);
		return alertForm;
	}
}