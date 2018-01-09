package omis.victim.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.person.domain.Person;
import omis.victim.domain.VictimDocketAssociation;

/**
 * Victim docket association data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 08, 2017)
 * @since OMIS 3.0
 */
public interface VictimDocketAssociationDao extends GenericDao<VictimDocketAssociation> {

	/**
	 * Returns the victim docket association with the specified victim and docket.
	 * 
	 * @param victim person
	 * @param docket docket
	 * @return matching victim docket association
	 */
	VictimDocketAssociation find(Person victim, Docket docket);
	
	/**
	 * Returns the victim docket association with the specified victim and docket, excluding the specified
	 * victim docket associaiton.
	 * 
	 * @param victimDocketAssociation victim docket association
	 * @param victim person
	 * @param docket docket
	 * @return matching victim docket association
	 */
	VictimDocketAssociation findExcluding(
			VictimDocketAssociation victimDocketAssociation, Person victim,
			Docket docket);
	/**
	 * Returns all victim docket associations for the specified victim.
	 * 
	 * @param victim person
	 * @return list of victim docket associations
	 */
	List<VictimDocketAssociation> findByVictim(Person victim);
	
	/**
	 * Returns all victim docket associations for the specified docket.
	 * 
	 * @param docket docket
	 * @return list of victim docket associations
	 */
	List<VictimDocketAssociation> findByDocket(Docket docket);
}