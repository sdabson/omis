package omis.criminalassociation.dao.impl.hibernate;

import java.util.List;

import omis.criminalassociation.dao.CriminalAssociationDao;
import omis.criminalassociation.domain.CriminalAssociation;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.relationship.domain.Relationship;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of the data access object for
 * criminal association.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (Apr, 14 2015)
 * @since OMIS 3.0
 *
 */
public class CriminalAssociationDaoHibernateImpl  
	extends GenericHibernateDaoImpl<CriminalAssociation>
	implements CriminalAssociationDao {
	
	/**
	 * Instantiates a data access object for criminal association with the 
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CriminalAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CriminalAssociation> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<CriminalAssociation> associations = getSessionFactory()
			.getCurrentSession()
			.getNamedQuery("findAssociationsByOffender")
			.setParameter("offender", offender)
			.list();
		return associations;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CriminalAssociation> findByOtherOffender(
		final Offender offender) {
		@SuppressWarnings("unchecked")
		List<CriminalAssociation> associations = getSessionFactory()
			.getCurrentSession()
			.getNamedQuery("findAssociationsByOtherOffender")
			.setParameter("offender", offender)
			.list();
		
		return associations;
	}

	/** {@inheritDoc} */
	@Override
	public CriminalAssociation findCriminalAssociation(final Relationship 
		relationship, final DateRange dateRange) {
		if (dateRange == null) {
			throw new NullPointerException("Date range cannot be null");
		}
		
		CriminalAssociation association = (CriminalAssociation)
				getSessionFactory()
			.getCurrentSession()
			.getNamedQuery("findCriminalAssociation")
			.setParameter("relationship", relationship)
			.setParameter("startDate", dateRange.getStartDate())
			.setParameter("endDate", dateRange.getEndDate())
			.uniqueResult();
		return association;
	}
}