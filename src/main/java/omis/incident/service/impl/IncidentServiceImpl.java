package omis.incident.service.impl;

import java.util.List;

import omis.incident.domain.Jurisdiction;
import omis.incident.service.IncidentService;
import omis.person.domain.Person;

/**
 * Incident service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 4, 2015)
 * @since OMIS 3.0
 */
public class IncidentServiceImpl implements IncidentService {

	/** Instantiates a default instance of incident service. */
	public IncidentServiceImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public List<Jurisdiction> findJurisdictionsByStaff(Person person) {
		// TODO Implement this method - JN
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
}