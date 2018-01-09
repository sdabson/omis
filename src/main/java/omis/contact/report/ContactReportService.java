package omis.contact.report;

import omis.person.domain.Person;

/**
 * Report service for contacts.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 12, 2016)
 * @since OMIS 3.0
 */
public interface ContactReportService {

	/**
	 * Summarizes contact by person.
	 * 
	 * @param person person
	 * @return summary of contact by person
	 */
	ContactSummary summarizeByPerson(Person person);
}