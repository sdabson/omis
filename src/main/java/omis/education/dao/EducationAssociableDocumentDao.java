package omis.education.dao;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.education.domain.EducationAssociableDocument;
import omis.offender.domain.Offender;

/**
 * EducationAssociableDocumentDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface EducationAssociableDocumentDao
	extends GenericDao<EducationAssociableDocument> {
	
	/**
	 * Finds an EducationAssociableDocument with the specified parameters
	 * @param offender - Offender
	 * @param document - Document
	 * @return EducationAssociableDocument
	 */
	EducationAssociableDocument find(Offender offender, Document document);
	
	/**
	 * Finds an EducationAssociableDocument with the specified parameters
	 * excluding specified EducationAssociableDocument
	 * @param offender - Offender 
	 * @param document - Document
	 * @param EducationAssociableDocumentExcluded - EducationAssociableDocument
	 * to exclude
	 * @return EducationAssociableDocument
	 */
	EducationAssociableDocument findExcluding(Offender offender, Document document,
			EducationAssociableDocument educationAssociableDocumentExcluded);
}
