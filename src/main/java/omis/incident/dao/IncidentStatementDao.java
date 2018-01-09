package omis.incident.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.component.IncidentScene;
import omis.person.domain.Person;

/**
 * Data access object for incident report.
 * @author Yidong Li
 * @author Joel Norris
 * @version 0.1.0 (June 28, 2015)
 * @since OMIS 3.0
 */
public interface IncidentStatementDao
	extends GenericDao<IncidentStatement> {

	/**
	 * Find corresponding incident report.
	 * @param reporter reporter
	 * @param statementDate statement date
	 * @param incidentDate incident date
	 * @param summary summary
	 * @param scene incident scene
	 * @return same incident report in DB
	 */
	IncidentStatement find(Person reporter, Date statementDate,
		Date incidentDate, String summary, IncidentScene scene);
	
	/**
	 * Find corresponding incident statement.
	 * @param statement incident statement
	 * @param reporter reporter
	 * @param reportDate date on which the incident is reported.
	 * @param incidentDate the date on which the incident happened.
	 * @param summary summary
	 * @param scene incident scene
	 * @return same incident report 
	 */
	IncidentStatement findExcluding(IncidentStatement statement, 
		Person reporter, Date reportDate, Date incidentDate, String summary,
		IncidentScene scene);

	/**
	 * Returns the generated number for an incident report.
	 * 
	 * @return incident report number
	 */
	String generateReportNumber();
}