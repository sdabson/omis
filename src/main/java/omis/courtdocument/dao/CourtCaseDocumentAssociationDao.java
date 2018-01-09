package omis.courtdocument.dao;

import java.util.List;

import omis.courtcase.domain.CourtCase;
import omis.courtdocument.domain.CourtCaseDocumentAssociation;
import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.offender.domain.Offender;

/** Dao for Court case documents.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 18, 2015)
 * @since OMIS 3.0 */
public interface CourtCaseDocumentAssociationDao 
	extends GenericDao<CourtCaseDocumentAssociation> {
	/** finds court document association by document and court case.
	 * @param document document.
	 * @param courtCase - court case. 
	 * @return list of court document association. */
	List<CourtCaseDocumentAssociation> findByDocumentAndCourtCase(
			final Document document, final CourtCase courtCase);
	
	/** finds court document associations by document and court case
	 * excluding.
	 * @param document - document.
	 * @param courtCase - court case.
	 * @param excluding - court document associations.
	 * @return list of court document association. */
	List<CourtCaseDocumentAssociation> findByDocumentAndCourtCaseExcluding(
			final Document document, final CourtCase courtCase, 
			final CourtCaseDocumentAssociation... excluding);
	
	/** Finds court document association count by offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findCountByOffender(Offender offender);
}
