package omis.courtcasecondition.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.courtcasecondition.dao.CourtCaseAgreementCategoryDao;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate Implementation for CourtCaseAgreementCategory DAO.
 *  
 * @author Jonny Santy
 * @version 0.1.0 (Jul 18, 2016)
 * @since OMIS 3.0
 */
public class CourtCaseAgreementCategoryDaoHibernateImpl
	extends GenericHibernateDaoImpl<CourtCaseAgreementCategory>
	implements CourtCaseAgreementCategoryDao{
	
	/**
	 * Instantiates a data access object for court case agreement 
	 * category with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	protected CourtCaseAgreementCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc}*/
	@Override
	public List<CourtCaseAgreementCategory> findAll() {
		return super.findAll();
	}
}
