package omis.incident.report;

import java.util.Date;
import java.util.List;

import omis.incident.domain.IncidentStatementCategory;
import omis.incident.domain.Jurisdiction;
import omis.location.domain.Location;
import omis.person.domain.Person;

/**
 * Report service for incident.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @version 0.1.1 (Oct 8, 2015)
 * @since OMIS 3.0
 */
public interface IncidentStatementSummaryService {

	/**
	 * Returns the list of involved parties summaries.
	 * 
	 * @param statement incident statement
	 * @return list of involved party summaries.
	 */
	List<InvolvedPartySummary> findInvolvedParties(Long id);
	
	/**
	 * Returns a list of incident statement summaries for the specified start date,
	 * end date, and location.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @param jurisdiction jurisdiction
	 * @param location location
	 * @return list of matching incident statement summaries
	 */
	List<IncidentStatementSummary> findByLocation(Date startDate, Date endDate,
			Jurisdiction jurisdiction, Location location);

	/**
	 * Returns a list of incident statement summaries for the specified start date,
	 * end date, location, and list of involved people.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @param jurisdiction jurisdiction
	 * @param location location
	 * @param involvedPeople involved people
	 * @return list of incident statement summaries
	 */
	List<IncidentStatementSummary> findByInvolvedPeople(Date startDate,
			Date endDate, Jurisdiction jurisdiction, 
			Location location, List<Person> involvedPeople);

	/**
	 * Returns incident statement summaries that have been documented by
	 * the current user.
	 * 
	 * @return list of incident reports
	 */
	List<IncidentStatementSummary> findByCurrentUser();
	
	/**
	 * Returns incident statement summaries that meet the specified search criteria.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @param jurisdiction jurisdiction
	 * @param category incident statement category
	 * @param reporter reporting staff
	 * @param title title
	 * @param keywords list of keywords
	 * @param involvedParty involved party
	 * @param involvedPartyName name of involved party
	 * @return list of incident statement summaries
	 */
	List<IncidentStatementSummary> findByCriteria(Date startDate, Date endDate, Jurisdiction jurisdiction,
			IncidentStatementCategory category, Person reporter, String title, List<String> keywords,
			Person involvedParty, String involvedPartyName);
}