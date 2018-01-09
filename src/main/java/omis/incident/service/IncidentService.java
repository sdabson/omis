package omis.incident.service;

import java.util.List;

import omis.incident.domain.Jurisdiction;
import omis.person.domain.Person;

/**
 * Incident service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 3, 2015)
 * @since OMIS 3.0
 */
public interface IncidentService {

	List<Jurisdiction> findJurisdictionsByStaff(Person person);
}