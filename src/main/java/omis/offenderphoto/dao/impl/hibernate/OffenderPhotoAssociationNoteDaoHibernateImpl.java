package omis.offenderphoto.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offenderphoto.dao.OffenderPhotoAssociationNoteDao;
import omis.offenderphoto.domain.OffenderPhotoAssociation;
import omis.offenderphoto.domain.OffenderPhotoAssociationNote;

/**
 * Offender photo association note data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Dec 15, 2016)
 * @since OMIS 3.0
 */
public class OffenderPhotoAssociationNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<OffenderPhotoAssociationNote> 
	implements  OffenderPhotoAssociationNoteDao{

	/* Query names. */
	
	private static final String FIND_ASSOCIATION_NOTES_BY_ASSOCIATION_QUERY_NAME
		= "findOffenderPhotoAssociationNotesByAssociation";
	
	private static final String FIND_QUERY_NAME
		= "findOffenderPhotoAssociationNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findOffenderPhotoAssociaitonNoteExcluding";
	
	/* Parameter names. */
	
	private static final String ASSOCIATION_PARAM_NAME = "association";
	
	private static final String NOTE_PARAM_NAME = "note";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String VALUE_PARAM_NAME = "value";
	
	/**
	 * Instantiates an offender photo association note data access object
	 * with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenderPhotoAssociationNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderPhotoAssociationNote> findByAssociation(
			final OffenderPhotoAssociation association) {
		@SuppressWarnings("unchecked")
		List<OffenderPhotoAssociationNote> notes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ASSOCIATION_NOTES_BY_ASSOCIATION_QUERY_NAME)
				.setParameter(ASSOCIATION_PARAM_NAME, association)
				.list();
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociationNote find(final String value,
			final Date date, final OffenderPhotoAssociation association) {
		OffenderPhotoAssociationNote note 
				= (OffenderPhotoAssociationNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameter(ASSOCIATION_PARAM_NAME, association)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociationNote findExcluding(final String value, 
			final Date date, final OffenderPhotoAssociation association,
			final OffenderPhotoAssociationNote note) {
		OffenderPhotoAssociationNote associationNote 
				= (OffenderPhotoAssociationNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameter(ASSOCIATION_PARAM_NAME, association)
				.setParameter(NOTE_PARAM_NAME, note)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return associationNote;
	}
}