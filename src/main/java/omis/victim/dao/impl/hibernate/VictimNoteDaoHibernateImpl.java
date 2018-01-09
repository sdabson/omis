package omis.victim.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;
import omis.victim.dao.VictimNoteDao;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimNote;
import omis.victim.domain.VictimNoteCategory;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 23, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<VictimNote>
		implements VictimNoteDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findVictimNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findVictimNoteExcluding";
	
	private static final String FIND_BY_VICTIM_QUERY_NAME
		= "findVictimNotesByVictim";
	
	private static final String FIND_BY_ASSOCIATION_QUERY_NAME
		= "findVictimNotesByAssociation";
	
	private static final String COUNT_BY_ASSOCIATION_QUERY_NAME
		= "countVictimNotesByAssociation";

	/* Parameter names. */
	
	private static final String VICTIM_PARAM_NAME = "victim";

	private static final String CATEGORY_PARAM_NAME = "category";

	private static final String DATE_PARAM_NAME = "date";

	private static final String EXCLUDED_NOTES_PARAM_NAME = "excludedNotes";

	private static final String ASSOCIATION_PARAM_NAME = "association";

	/* Property names. */
	
	private static final String ASSOCIATION_PROPERTY_NAME = "association";
	
	/* Constructors. */

	/**
	 * Instantiates Hibernate implementation of data access object for victim
	 * notes.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VictimNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public VictimNote find(
			final Person victim, final VictimNoteCategory category,
			final Date date) {
		VictimNote note = (VictimNote) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public VictimNote findExcluding(
			final Person victim, final VictimNoteCategory category,
			final Date date, final VictimNote... excludedNotes) {
		VictimNote note = (VictimNote) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameterList(EXCLUDED_NOTES_PARAM_NAME, excludedNotes)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimNote> findByVictim(final Person victim) {
		@SuppressWarnings("unchecked")
		List<VictimNote> notes = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_VICTIM_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim).list();
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimNote> findByAssociation(
			final VictimAssociation association) {
		@SuppressWarnings("unchecked")
		List<VictimNote> notes = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_ASSOCIATION_QUERY_NAME)
				.setParameter(ASSOCIATION_PARAM_NAME, association,
						this.getEntityPropertyType(ASSOCIATION_PROPERTY_NAME))
				.list();
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public long countNotes(final VictimAssociation association) {
		long count = (Long) this.getSessionFactory().getCurrentSession()
			.getNamedQuery(COUNT_BY_ASSOCIATION_QUERY_NAME)
				.setParameter(ASSOCIATION_PARAM_NAME, association)
				.uniqueResult();
		return count;
	}
}