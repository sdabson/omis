package omis.paroleboarditinerary.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.paroleboarditinerary.dao.ParoleBoardItineraryNoteDao;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;
import omis.paroleboarditinerary.domain.ParoleBoardItineraryNote;

/**
 * Parole board itinerary note delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 20, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardItineraryNoteDelegate {

	/* Data access objects. */
	
	private final ParoleBoardItineraryNoteDao paroleBoardItineraryNoteDao;

	/* Instance factories. */
	
	private final InstanceFactory<ParoleBoardItineraryNote> 
			paroleBoardItineraryNoteInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Instantiates an implementation of parole board itinerary note delegate 
	 * with the specified date access object and instance factory.
	 * 
	 * @param paroleBoardItineraryNoteDao parole board itinerary note data 
	 * access object
	 * @param paroleBoardItineraryNoteInstanceFactory parole board itinerary 
	 * note instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public ParoleBoardItineraryNoteDelegate(
			final ParoleBoardItineraryNoteDao paroleBoardItineraryNoteDao,
			final InstanceFactory<ParoleBoardItineraryNote> 
				paroleBoardItineraryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.paroleBoardItineraryNoteDao = paroleBoardItineraryNoteDao;
		this.paroleBoardItineraryNoteInstanceFactory = 
				paroleBoardItineraryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new parole board itinerary note.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @param description description
	 * @param date date
	 * @return parole board itinerary note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ParoleBoardItineraryNote create(
			final ParoleBoardItinerary boardItinerary, final String description, 
			final Date date) throws DuplicateEntityFoundException {
		if (this.paroleBoardItineraryNoteDao.find(boardItinerary, description, 
				date) != null) {
			throw new DuplicateEntityFoundException(
					"Parole board itinerary note already exists.");
		}
		ParoleBoardItineraryNote note = this
				.paroleBoardItineraryNoteInstanceFactory.createInstance();
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		populateBoardItineraryNote(note, boardItinerary, description, date);
		return this.paroleBoardItineraryNoteDao.makePersistent(note);
	}

	/**
	 * Updates an existing parole board itinerary.
	 * 
	 * @param note parole board itinerary note
	 * @param boardItinerary parole board itinerary
	 * @param description description
	 * @param date date
	 * @return parole board itinerary note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ParoleBoardItineraryNote update(final ParoleBoardItineraryNote note, 
			final ParoleBoardItinerary boardItinerary, final String description, 
			final Date date) throws DuplicateEntityFoundException {
		if (this.paroleBoardItineraryNoteDao.findExcluding(boardItinerary,
				description, date, note) != null) {
			throw new DuplicateEntityFoundException(
					"Parole board itinerary note already exists.");
		}
		populateBoardItineraryNote(note, boardItinerary, description, date);
		return this.paroleBoardItineraryNoteDao.makePersistent(note);
	}
	
	/**
	 * Removes the specified parole board itinerary note.
	 * 
	 * @param note parole board itinerary note
	 */
	public void remove(final ParoleBoardItineraryNote note) {
		this.paroleBoardItineraryNoteDao.makeTransient(note);
	}
	
	/**
	 * Returns list of notes for the specified parole board itinerary.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @return list of parole board itinerary notes
	 */
	public List<ParoleBoardItineraryNote> findItineraryNotesByBoardItinerary(
			final ParoleBoardItinerary boardItinerary) {
		return this.paroleBoardItineraryNoteDao
				.findItineraryNotesByBoardItinerary(boardItinerary);
	}
	
	// Populates a parole board itinerary note
	private void populateBoardItineraryNote(
			final ParoleBoardItineraryNote note, 
			final ParoleBoardItinerary boardItinerary, final String description, 
			final Date date) {
		note.setBoardItinerary(boardItinerary);
		note.setDescription(description);
		note.setDate(date);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}
