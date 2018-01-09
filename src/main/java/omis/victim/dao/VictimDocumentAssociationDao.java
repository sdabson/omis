package omis.victim.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.person.domain.Person;
import omis.victim.domain.VictimDocumentAssociation;

/**
 * Victim document association data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 22, 2017)
 * @since OMIS 3.0
 */
public interface VictimDocumentAssociationDao extends GenericDao<VictimDocumentAssociation> {

	/**
	 * Returns the victim document association with the specified victim and document.
	 * 
	 * @param victim victim
	 * @param document document
	 * @return matching victim document association
	 */
	VictimDocumentAssociation find(Person victim, Document document);
	
	/**
	 * Returns the victim document association with tbhe specified victim and document,
	 * excluding the specified victim document association.
	 * 
	 * @param victim victim
	 * @param document document
	 * @param documentAssociation document associaiton
	 * @return matching victim document association
	 */
	VictimDocumentAssociation findExcluding(Person victim, Document document,
			VictimDocumentAssociation documentAssociation);
	
	/**
	 * Returns all victim document associations for the specified docket.
	 * 
	 * @param docket docket
	 * @return list of victim document associations
	 */
	List<VictimDocumentAssociation> findByDocket(Docket docket);
	
	/**
	 * Returns all victim document associations for the specified victim.
	 * 
	 * @param victim victim
	 * @return list of victim document associations
	 */
	List<VictimDocumentAssociation> findByVictim(Person victim);

	/**
	 * Returns all victim document associations for the specified docket and victim.
	 * 
	 * @param docket docket
	 * @param victim victim
	 * @return list of victim document associations
	 */
	List<VictimDocumentAssociation> findByDocketAndVictim(Docket docket, Person victim);
}