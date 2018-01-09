package omis.adaaccommodation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.dao.AccommodationNoteDao;
import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationNote;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibenate implementation of the accommodation note data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class AccommodationNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<AccommodationNote> 
	implements AccommodationNoteDao {
	
	/* Queries. */
	private static final String FIND_ACCOMMODATION_NOTES_QUERY_NAME
		= "findAccommodationNotes";
	private static final String FIND_ACCOMMODATION_NOTE_QUERY_NAME
		= "findAccommodationNote";
	private static final String FIND_ACCOMMODATION_NOTE_EXCLUDING_QUERY_NAME
		= "findAccommodationNoteExcluding";
			
	private static final String 
	FIND_ACCOMMODATION_NOTES_BY_ACCOMMODATION_QUERY_NAME
		= "findAccommodationNotesByAccommodation";
	
	/* Parameters. */
	private static final String TEXT_PARAM_NAME = "text";
	private static final String DATE_PARAM_NAME = "date";
	private static final String ACCOMMODATION_PARAM_NAME = "accommodation";
	private static final String ACCOMMODATION_NOTE_PARAM_NAME 
		= "accommodationNote";
		
	/**
	 * Instantiates a hibernate implementation of the data access object for
	 * accommodation note.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AccommodationNoteDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<AccommodationNote> findAll() {
		@SuppressWarnings("unchecked")
		List<AccommodationNote> notes = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ACCOMMODATION_NOTES_QUERY_NAME).list();
		return notes;
	}
	
	/** {@inheritDoc} */
	@Override
	public AccommodationNote find(final String text, final Date date, 
			final Accommodation accommodation) {
		AccommodationNote note = (AccommodationNote) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ACCOMMODATION_NOTE_QUERY_NAME)
				.setParameter(TEXT_PARAM_NAME, text)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public AccommodationNote findExcluding(final String text, final Date date, 
			final Accommodation accommodation, final AccommodationNote note) {
		AccommodationNote accommodationNote = (AccommodationNote) 
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_ACCOMMODATION_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(TEXT_PARAM_NAME, text)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
				.setParameter(ACCOMMODATION_NOTE_PARAM_NAME , note)
				.uniqueResult();
		return accommodationNote;
	}

	@Override
	public List<AccommodationNote> findByAccommodation(
			Accommodation accommodation) {
		@SuppressWarnings("unchecked")
		List<AccommodationNote> notes = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_ACCOMMODATION_NOTES_BY_ACCOMMODATION_QUERY_NAME)
				.setParameter(ACCOMMODATION_PARAM_NAME, accommodation).list();
		return notes;
	}
}