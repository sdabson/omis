package omis.visitation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.visitation.dao.VisitationAssociationNoteDao;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationNote;

/**
 * Visitation association note data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 10, 2017)
 * @since OMIS 3.0
 */
public class VisitationAssociationNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<VisitationAssociationNote>
	implements VisitationAssociationNoteDao {
	
	/* Query names. */
	
	private static final String FIND_BY_ASSOCIATION_QUERY_NAME
		= "findVisitaitonAssociationNoteByAssociation";
	
	private static final String FIND_QUERY_NAME
		= "findVisitationAssociationNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findVisitationAssociationNoteExcluding";

	/* Parameter names. */
	
	private static final String ASSOCIATION_PARAM_NAME = "association";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String VALUE_PARAM_NAME = "value";
	
	private static final String NOTE_PARAM_NAME  = "note";
	
	/**
	 * Instantiates an instance of visitation association note data access
	 * object with the specified session factory and entity name.
	 *  
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VisitationAssociationNoteDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<VisitationAssociationNote> findByAssociation(
			final VisitationAssociation association) {
		@SuppressWarnings("unchecked")
		List<VisitationAssociationNote> notes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_ASSOCIATION_QUERY_NAME)
				.setParameter(ASSOCIATION_PARAM_NAME, association)
				.list();
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public VisitationAssociationNote find(
			final VisitationAssociation association, final Date date,
			final String value) {
		VisitationAssociationNote note = (VisitationAssociationNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(ASSOCIATION_PARAM_NAME, association)
				.setParameter(VALUE_PARAM_NAME, value)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public VisitationAssociationNote findExcluding(
			final VisitationAssociation association, final Date date, 
			final String value, final VisitationAssociationNote note) {
		VisitationAssociationNote assocNote = (VisitationAssociationNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(ASSOCIATION_PARAM_NAME, association)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameter(NOTE_PARAM_NAME, note)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return assocNote;
	}
}