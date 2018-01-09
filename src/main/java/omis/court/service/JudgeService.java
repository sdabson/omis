package omis.court.service;

import java.util.Date;
import java.util.List;

import omis.court.domain.Court;
import omis.person.domain.Person;

/**
 * Service for judges.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 12, 2013)
 * @since OMIS 3.0
 */
public interface JudgeService {

	/**
	 * Returns judges for a court on a date.
	 * 
	 * @param court court
	 * @param date date
	 * @return judges for court on date
	 */
	List<Person> findByCourtOnDate(Court court, Date date);
}