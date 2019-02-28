package omis.incident.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.datatype.DateRange;
import omis.incident.web.form.InvolvedPartyOption;
import omis.incident.web.form.SearchIncidentStatementForm;

/**
 * Search incident statement form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (February 7, 2019)
 * @since OMIS 3.0
 */
public class SearchIncidentStatementFormValidator implements Validator {
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SearchIncidentStatementForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		SearchIncidentStatementForm form = (SearchIncidentStatementForm) target;
		if(form.getStartDate() != null && form.getEndDate() != null) {
			if(form.getStartDate().getTime() > form.getEndDate().getTime()) {
				errors.rejectValue("startDate", "searchIncidentStatementForm.search.startDateAfterEndDate");
			} else {
				if(DateRange.countDaysExactly(form.getStartDate(), form.getEndDate()) > 30) {
					errors.rejectValue("startDate",
							"searchIncidentStatementForm.startDate.distanceFromEndDate");
				}
			}
		} else {
			if((form.getTitle() == null || form.getTitle().isEmpty())&& form.getReporter() == null
					&& (form.getKeywords() == null||form.getKeywords().isEmpty())
					&& form.getCategory() == null && form.getJurisdiction() == null) {
				if(form.getStartDate() == null) {
					errors.rejectValue("startDate", "searchIncidentStatementForm.startDate.empty");
				}
				if(form.getEndDate() == null) {
					errors.rejectValue("endDate", "searchIncidentStatementForm.endDate.empty");
				}
			}
		}
		if(InvolvedPartyOption.OFFENDER.equals(form.getInvolvedPartyOption())
				|| InvolvedPartyOption.STAFF.equals(form.getInvolvedPartyOption())) {
			if(form.getInvolvedParty() == null) {
				errors.rejectValue("involvedParty", "searchIncidentStatementForm.involvedParty.empty");
			}
		} else if (InvolvedPartyOption.OTHER.equals(form.getInvolvedPartyOption())) {
			if(form.getInvolvedPartyName() == null || form.getInvolvedPartyName().isEmpty()) {
				errors.rejectValue("involvedPartyName", "searchIncidentStatementForm.involvedPartyName.empty");
			}
		}
	}
}
