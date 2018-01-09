package omis.courtdocument.dao.impl.hibernate;

import omis.courtdocument.dao.CourtDocumentCategoryDao;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/** Hibernate implementation of court document category dao.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 18, 2015)
 * @since OMIS 3.0 */
public class CourtDocumentCategoryDaoHibernateImpl 
		extends GenericHibernateDaoImpl<CourtDocumentCategory>
		implements CourtDocumentCategoryDao {
	/** constructor.
	 * @param sessionFactory - session factory.
	 * @param entityName - entity name. */
	public CourtDocumentCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

}
