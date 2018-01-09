package omis.guardianship.service;

import omis.exception.DuplicateEntityFoundException;
import omis.guardianship.domain.Guardianship;
import omis.offender.domain.Offender;
import omis.person.domain.Person;

/**
 * Services for guardianship.
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (June 8, 2013)
 * @since OMIS 3.0
 */
public interface GuardianshipService {
	/**
	 * Associate the specified guardianship.
	 * 
	 * @param offender offender
	 * @return guardian guardian
	 */
	Guardianship associateAsGuardian(Offender offender, Person guardian)
		throws DuplicateEntityFoundException;
	
	/**
	 * Update the specified guardianship.
	 * 
	 * @param offender offender
	 * @return guardian guardian
	 */
	Guardianship associateAsGuardianee(Person guardian, Offender offender)
		throws DuplicateEntityFoundException;
	
	/**
	 * Remove the specified guardianship.
	 * 
	 * @param guardianship guardianship
	 */
	void remove(Guardianship guardianship);
	
	/**
	 * Save the specified guardianship.
	 * 
	 * @param guardianship guardianship
	 * @return guardianship
	 */
	Guardianship save(Guardianship guardianship);
}