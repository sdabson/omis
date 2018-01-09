package omis.commitstatus.web.validator.testng;

import java.beans.PropertyEditor;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.web.form.CommitStatusForm;
import omis.commitstatus.web.validator.CommitStatusFormValidator;
import omis.instance.factory.InstanceFactory;
import omis.testng.AbstractNonTransactionalTestNGSpringContextTests;

/**
 * Tests for validator for commit status form.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Oct 27, 2017)
 * @since OMIS 3.0
 */
@Test(groups = {"commitStatus", "validator"})
public class CommitStatusFormValidatorTests
			extends AbstractNonTransactionalTestNGSpringContextTests {

	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Instance factories. */
	
	@Autowired
	@Qualifier("commitStatusInstanceFactory")
	private InstanceFactory<CommitStatus> commitStatusInstanceFactory;
	
	/* Validator. */
	
	private CommitStatusFormValidator validator
			= new CommitStatusFormValidator();
	
	/* Tests. */
	
	/** Tests that start date is required. */
	public void testStartDateRequired() {
		
		// Arrangements
		CommitStatus commitStatus = this.createCommitStatus(
				"DOC commit", true);
		Date endDate = this.parseDateText("01/23/2017");
		CommitStatusForm commitStatusForm = this.createCommitStatusForm(
				commitStatus, null, endDate);
		Errors errors = new BeanPropertyBindingResult(
				commitStatusForm, "commitStatusForm");
		
		// Action
		this.validator.validate(commitStatusForm, errors);
		
		// Assertions
		assert this.hasFieldErrorCode(errors, "startDate", "startDateEmpty");
	}
	
	/** Tests that commit status is required. */
	public void testCommitStatusRequired() {
		
		// Arrangements
		Date startDate = this.parseDateText("12/07/2017");
		CommitStatusForm commitStatusForm = this.createCommitStatusForm(
				null, startDate, null);
		Errors errors = new BeanPropertyBindingResult(
				commitStatusForm, "commitStatusForm");
		
		// Action
		this.validator.validate(commitStatusForm, errors);
		
		// Assertions
		assert this.hasFieldErrorCode(errors, "commitStatus", "statusEmpty");
	}
	
	/** Tests that start date cannot be greater than end date. */
	public void testStartDateGreaterThanEndDate() {
		
		// Arrangements
		CommitStatus commitStatus = this.createCommitStatus(
				"DOC Commit", true);
		Date startDate = this.parseDateText("12/31/2017");
		Date endDate = this.parseDateText("01/31/2017");
		CommitStatusForm commitStatusForm = this.createCommitStatusForm(
				commitStatus, startDate, endDate);
		Errors errors = new BeanPropertyBindingResult(
				commitStatusForm, "commitStatusForm");
		
		// Action
		this.validator.validate(commitStatusForm, errors);
		
		// Assertions
		assert this.hasFieldErrorCode(
				errors, "startDate", "startDateGreaterThanEndDate")
			: "Start date greater than end date is allowed";
	}
	
	/* Helper methods. */
	
	// Returns commit status form
	private CommitStatusForm createCommitStatusForm(
			final CommitStatus commitStatus,
			final Date startDate,
			final Date endDate) {
		CommitStatusForm commitStatusForm = new CommitStatusForm();
		commitStatusForm.setCommitStatus(commitStatus);
		commitStatusForm.setStartDate(startDate);
		commitStatusForm.setEndDate(endDate);
		return commitStatusForm;
	}
	
	// Returns commit status
	private CommitStatus createCommitStatus(
			final String name, final Boolean valid) {
		CommitStatus commitStatus = this.commitStatusInstanceFactory
				.createInstance();
		commitStatus.setName(name);
		commitStatus.setValid(valid);
		return commitStatus;
	}
	
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
}