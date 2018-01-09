package omis.incident.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.InvolvedParty;
import omis.person.domain.Person;

/**
 * Data access object for involved party.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @version 0.1.0 (June 28, 2015)
 * @since OMIS 3.0
 */
public interface InvolvedPartyDao
	extends GenericDao<InvolvedParty> {
	
	/**
	 * Returns the incident report.
	 * 
	 * @param report
	 * @param person
	 * @return matching involved party
	 */
	InvolvedParty find(IncidentStatement report, Person person);
	
	/**
	 * Find corresponding involved party.
	 * 
	 * @param report incident report
	 * @param person person
	 * @param involvedParty involved part
	 * @return matching involved party in DB
	 */
	InvolvedParty findExcluding(IncidentStatement report,
			Person person, InvolvedParty involvedParty);
	
	/**
	 * Find involved parties.
	 * @param report incident report
	 * @return list of involved parties
	 */
	List<InvolvedParty> findInvolvedParties(IncidentStatement report);
}