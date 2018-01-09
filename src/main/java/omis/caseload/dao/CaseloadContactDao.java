package omis.caseload.dao;

import java.util.Date;

import omis.caseload.domain.CaseloadContact;
import omis.caseload.domain.ContactCategory;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.dao.GenericDao;
import omis.person.domain.Person;

/**
 * Data access object for contact.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 28, 2016)
 * @since OMIS 3.0
 */
public interface CaseloadContactDao extends GenericDao<CaseloadContact> {

	/**
	 * Returns the contact.
	 *
	 * @param offenderCaseAssignment offender case assignment
	 * @param date date
	 * @param contactBy contact by
	 * @param category category
	 * @return contact
	 */
	CaseloadContact find(OffenderCaseAssignment offenderCaseAssignment, 
			Date contactDate, Person contactBy, ContactCategory category);

	/**
	 * Returns the contact excluding the one in view.
	 *
	 * @param offenderCaseAssignment offender case assignment
	 * @param date date
	 * @param contactBy contact by
	 * @param category category
	 * @param contact contact
	 * @return contact
	 */
	CaseloadContact findExcluding(OffenderCaseAssignment offenderCaseAssignment, 
			Date contactDate, Person contactBy, ContactCategory category, 
			CaseloadContact contact);	
}