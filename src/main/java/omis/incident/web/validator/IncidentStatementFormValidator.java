package omis.incident.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.incident.domain.InformationSourceCategory;
import omis.incident.domain.InvolvedPartyCategory;
import omis.incident.web.form.IncidentStatementForm;
import omis.incident.web.form.IncidentStatementNoteItem;
import omis.incident.web.form.InvolvedPartyItem;
import omis.incident.web.form.InvolvedPartyItemOperation;
import omis.web.validator.StringLengthChecks;

/**
 * Incident report form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 7, 2015)
 * @since OMIS 3.0
 */
public class IncidentStatementFormValidator implements Validator {

	private final StringLengthChecks stringLengthChecks;
	
	/**
	 * Instantiates a default instance of incident statement form validator
	 * with the specified string length checks.
	 * 
	 * @param stringLengthChecks string length checks
	 */
	public IncidentStatementFormValidator(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return IncidentStatementForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		IncidentStatementForm form = (IncidentStatementForm) target;
		
		if (form.getTitle() == null || form.getTitle().isEmpty()) {
			errors.rejectValue("title", "incidentStatement.title.empty");
		}
		if (form.getCategory() == null) {
			errors.rejectValue("category", "incidentStatement.category.empty");
		}
		if (form.getStatementDate() == null) {
			errors.rejectValue("statementDate",
					"incidentStatement.reportDate.empty");
		}
		if (form.getJurisdiction() == null) {
			errors.rejectValue("jurisdiction",
					"incidentStatement.jurisdication.empty");
		}
		if (form.getIncidentDate() == null) {
			errors.rejectValue("incidentDate",
					"incidentStatement.incidentDate.empty");
		}
		if (form.getIncidentTime() == null) {
			errors.rejectValue("incidentTime",
					"incidentStatement.incidentTime.empty");
		}
		if (form.getDocumenter() == null) {
			errors.rejectValue("documenter",
					"incidentStatement.documenter.empty");
		}
		if (form.getSummary() == null || form.getSummary().isEmpty()) {
			errors.rejectValue("summary", "incidentStatement.summary.empty");
		} else {
			this.stringLengthChecks.getDocumentCheck().check(
					"summary", form.getSummary(), errors);
		}
		this.stringLengthChecks.getHumongousCheck().check("location",
				form.getLocation(), errors);
		
		if(form.getInformationSourceCategory() == null) {
			errors.rejectValue("informationSourceCategory",
					"incidentStatement.informationSourceCategory.empty");
		} else if(InformationSourceCategory.OTHER.equals(
				form.getInformationSourceCategory())) {
			if (form.getInformationSourceName() == null
					|| form.getInformationSourceName().isEmpty()) {
				errors.rejectValue("informationSourceName",
						"incidentStatement.informationSourceName.empty");
			}
		} else if (!InformationSourceCategory.ANONYMOUS.equals(
				form.getInformationSourceCategory())) {
			if(form.getInformant() == null) {
				errors.rejectValue("informant",
						"incidentStatement.informant.empty");
			}
		}
		if(form.getReporter() == null) {
			errors.rejectValue("reporter", "incidentStatement.reporter.empty");
		}
		int involvedPartyIndex = 0;
		if (form.getInvolvedPartyItems() != null) {
			for (InvolvedPartyItem item : form.getInvolvedPartyItems()) {
				if (item.getCategory() == null) {
					errors.rejectValue(
						"involvedPartyItems[" + involvedPartyIndex 
							+ "].category",
						"incidentStatement.involvedParty.category.empty");
				} else {
					if (InvolvedPartyItemOperation.CREATE.equals(
							item.getOperation())
							|| InvolvedPartyItemOperation.UPDATE
							.equals(item.getOperation())) {
						if (InvolvedPartyCategory.OTHER.equals(
								item.getCategory())) {
							if(item.getName() == null
									|| item.getName().isEmpty()) {
								errors.rejectValue("involvedPartyItems["
									+ involvedPartyIndex + "].name",
								"incidentStatement.involvedParty.name.empty");
							}
						} else if (item.getPerson() == null) {
							errors.rejectValue(
								"involvedPartyItems[" + involvedPartyIndex
									+ "].person",
								"incidentStatement.involvedParty.person.empty");
						}
					}
				}
				involvedPartyIndex++;
			}
		}
		int noteIndex = 0;
		if (form.getIncidentStatementNoteItems() != null) {
			for (IncidentStatementNoteItem item 
					: form.getIncidentStatementNoteItems()) {
				if (item.getValue() == null || item.getValue().isEmpty()) {
					errors.rejectValue(
						"incidentStatementNoteItems[" + noteIndex + "].value",
						"incidentStatement.note.value.empty");
				}
				if (item.getDate() == null) {
					errors.rejectValue(
						"incidentStatementNoteItems[" + noteIndex + "].date",
						"incidentStatement.note.date.empty");
				}
				noteIndex++;
			}
		}
	}
}