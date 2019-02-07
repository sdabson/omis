package omis.paroleboarditinerary.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.paroleboarditinerary.dao.BoardAttendeeDao;
import omis.paroleboarditinerary.domain.BoardAttendee;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;
import omis.paroleboardmember.domain.ParoleBoardMember;

/**
 * Board attendee delegate.
 *
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Feb 5, 2019)
 * @since OMIS 3.0
 */
public class BoardAttendeeDelegate {

	/* Data access objects. */
	
	private final BoardAttendeeDao boardAttendeeDao;

	/* Instance factories. */
	
	private final InstanceFactory<BoardAttendee> boardAttendeeInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Instantiates an implementation of board attendee delegate with the 
	 * specified date access object and instance factory.
	 * 
	 * @param boardAttendeeDao board attendee data access object
	 * @param boardAttendeeInstanceFactory board attendee instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public BoardAttendeeDelegate(
			final BoardAttendeeDao boardAttendeeDao,
			final InstanceFactory<BoardAttendee> boardAttendeeInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.boardAttendeeDao = boardAttendeeDao;
		this.boardAttendeeInstanceFactory = boardAttendeeInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new board attendee.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @param boardMember parole board member
	 * @param number number
	 * @return board attendee
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public BoardAttendee create(final ParoleBoardItinerary boardItinerary,
			final ParoleBoardMember boardMember, final Long number)
					throws DuplicateEntityFoundException {
		if (this.boardAttendeeDao.find(boardItinerary, boardMember, number)
				!= null) {
			throw new DuplicateEntityFoundException(
					"Board attendee already exists.");
		}
		BoardAttendee attendee = this.boardAttendeeInstanceFactory
				.createInstance();
		attendee.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		populateBoardAttendee(attendee, boardItinerary, boardMember, number);
		return this.boardAttendeeDao.makePersistent(attendee);
	}

	/**
	 * Updates an existing board attendee.
	 * 
	 * @param attendee board attendee
	 * @param boardItinerary parole board itinerary
	 * @param boardMember parole board member
	 * @param number number
	 * @return board attendee
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public BoardAttendee update(final BoardAttendee attendee,
			final ParoleBoardItinerary boardItinerary,
			final ParoleBoardMember boardMember,
			final Long number)
					throws DuplicateEntityFoundException {
		if (this.boardAttendeeDao.findExcluding(boardItinerary, boardMember,
				number, attendee) != null) {
			throw new DuplicateEntityFoundException(
					"Board attendee already exists.");
		}
		populateBoardAttendee(attendee, boardItinerary, boardMember, number);
		return this.boardAttendeeDao.makePersistent(attendee);
	}
	
	/**
	 * Removes the specified board attendee.
	 * 
	 * @param attendee board attendee
	 */
	public void remove(final BoardAttendee attendee) {
		this.boardAttendeeDao.makeTransient(attendee);
	}
	
	/**
	 * Returns a list of board attendees that match the specified parole 
	 * board itinerary.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @return list of board attendees
	 */
	public List<BoardAttendee> findBoardAttendeesByBoardItinerary(
			final ParoleBoardItinerary boardItinerary) {
		return this.boardAttendeeDao
				.findBoardAttendeesByBoardItinerary(boardItinerary);
	}
	
	// Populates a board attendee
	private void populateBoardAttendee(
			final BoardAttendee attendee, 
			final ParoleBoardItinerary boardItinerary,
			final ParoleBoardMember boardMember, final Long number) {
		attendee.setBoardItinerary(boardItinerary);
		attendee.setBoardMember(boardMember);
		attendee.setNumber(number);
		attendee.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}
