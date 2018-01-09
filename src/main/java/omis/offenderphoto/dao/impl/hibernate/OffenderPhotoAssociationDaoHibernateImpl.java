package omis.offenderphoto.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.offenderphoto.dao.OffenderPhotoAssociationDao;
import omis.offenderphoto.domain.OffenderPhotoAssociation;

/**
 * Hibernate implementation of data access object for offender photo
 * associations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 23, 2014)
 * @since OMIS 3.0
 */
public class OffenderPhotoAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<OffenderPhotoAssociation>
		implements OffenderPhotoAssociationDao {

	private static final String FIND_PROFILE_PHOTO_ASSOCIATION_QUERY_NAME
		= "findOffenderProfilePhotoAssociation";
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findOffenderPhotoAssociationsByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * offender photo associations.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenderPhotoAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociation findProfilePhotoAssociation(
			final Offender offender) {
		OffenderPhotoAssociation association
			= (OffenderPhotoAssociation) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_PROFILE_PHOTO_ASSOCIATION_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderPhotoAssociation> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<OffenderPhotoAssociation> associations
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		return associations;
	}
}