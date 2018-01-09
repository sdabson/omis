package omis.victim.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.person.domain.Person;
import omis.victim.domain.VictimDocketAssociation;
import omis.victim.domain.VictimDocketDocumentAssociation;

/**
 * Victim docket document association data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 08, 2017)
 * @since OMIS 3.0
 */
public interface VictimDocketDocumentAssociationDao extends GenericDao<VictimDocketDocumentAssociation> {

	/**
	 * Returns the victim docket document association with the specified victim docket association and document.
	 * 
	 * @param victimDocketAssociation victim docket association
	 * @param document document
	 * @return matching victim docket document association
	 */
	VictimDocketDocumentAssociation find(VictimDocketAssociation victimDocketAssociation, Document document);
	
	/**
	 * Returns the victim docket document association with the specified victim docket association and document,
	 * excluding the specified victim docket document association.
	 * 
	 * @param victimDocketDocumentAssociation victim docket document association
	 * @param victimDocketAssociation victim docket association
	 * @param document document
	 * @return matching victim docket document association
	 */
	VictimDocketDocumentAssociation findExcluding(VictimDocketDocumentAssociation victimDocketDocumentAssociation,
			VictimDocketAssociation victimDocketAssociation, Document document);
	
	/**
	 * Returns all victim docket document associations for the specified victim.
	 * 
	 * @param victim victim
	 * @return list of victim docket document associations
	 */
	List<VictimDocketDocumentAssociation> findByVictim(Person victim);
	
	/**
	 * Returns all victim docket document associations for the specified victim docket association.
	 * 
	 * @param victimDocketAssociation victim docket association
	 * @return list of victim docket document associations
	 */
	List<VictimDocketDocumentAssociation> findByVictimDocketAssociation(
			VictimDocketAssociation victimDocketAssociation);
}