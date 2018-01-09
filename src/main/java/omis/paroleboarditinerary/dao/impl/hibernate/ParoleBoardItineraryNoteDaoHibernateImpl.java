package omis.paroleboarditinerary.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleboarditinerary.dao.ParoleBoardItineraryNoteDao;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;
import omis.paroleboarditinerary.domain.ParoleBoardItineraryNote;

/**
 * Hibernate implementation of the parole board itinerary note data access 
 * object.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 20, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardItineraryNoteDaoHibernateImpl 
		extends GenericHibernateDaoImpl<ParoleBoardItineraryNote>
		implements ParoleBoardItineraryNoteDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findParoleBoardItineraryNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findParoleBoardItineraryNoteExcluding";
	
	private static final String FIND_BY_BOARD_ITINERARY_QUERY_NAME = 
			"findParoleBoardItineraryNoteByBoardItinerary";
	
	/* Parameters. */
	
	private static final String BOARD_ITINERARY_PARAM_NAME = "boardItinerary";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String EXCLUDED_NOTE_PARAM_NAME = "excludedNote";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  parole board itinerary note.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ParoleBoardItineraryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardItineraryNote find(
			final ParoleBoardItinerary boardItinerary, final String description, 
			final Date date) {
		ParoleBoardItineraryNote note = (ParoleBoardItineraryNote) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(BOARD_ITINERARY_PARAM_NAME, boardItinerary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardItineraryNote findExcluding(
			final ParoleBoardItinerary boardItinerary, final String description, 
			final Date date, final ParoleBoardItineraryNote excludedNote) {
		ParoleBoardItineraryNote note = (ParoleBoardItineraryNote) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(BOARD_ITINERARY_PARAM_NAME, boardItinerary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(EXCLUDED_NOTE_PARAM_NAME, excludedNote)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public List<ParoleBoardItineraryNote> findItineraryNotesByBoardItinerary(
			final ParoleBoardItinerary boardItinerary) {
		@SuppressWarnings("unchecked")
		List<ParoleBoardItineraryNote> notes = (List<ParoleBoardItineraryNote>)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_BOARD_ITINERARY_QUERY_NAME)
				.setParameter(BOARD_ITINERARY_PARAM_NAME, boardItinerary)
				.list();
		return notes;
	}

	
}
