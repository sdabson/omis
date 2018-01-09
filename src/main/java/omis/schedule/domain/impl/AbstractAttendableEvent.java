package omis.schedule.domain.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import omis.person.domain.Person;
import omis.schedule.conflict.ConflictDetails;
import omis.schedule.domain.AttendableEvent;
import omis.schedule.domain.Event;
import omis.schedule.domain.impl.delegate.EventAttendeesPropertyDelegate;

/**
 * Abstract implementation of attendable event.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 17, 2012)
 * @since OMIS 3.0
 */
public abstract class AbstractAttendableEvent
		extends AbstractSchedulableEvent implements AttendableEvent {

	private static final long serialVersionUID = 1L;
	
	private Set<Person> attendees = new HashSet<Person>();
	
	private final EventAttendeesPropertyDelegate eventAttendeesPropertyDelegate;
	
	/** Instantiates a default attendable event. */
	protected AbstractAttendableEvent() {
		this.eventAttendeesPropertyDelegate =
									new EventAttendeesPropertyDelegate(this);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setAttendees(final Set<Person> attendees) {
		this.attendees = attendees;
	}

	/** {@inheritDoc} */
	@Override
	public Set<Person> getAttendees() {
		return this.attendees;
	}

	/** {@inheritDoc} */
	@Override
	public void addAttendee(final Person attendee) {
		this.eventAttendeesPropertyDelegate.add(attendee);
	}

	/** {@inheritDoc} */
	@Override
	public void addAllAttendees(final Collection<Person> attendees) {
		this.eventAttendeesPropertyDelegate.addAll(attendees);
	}

	/** {@inheritDoc} */
	@Override
	public void replaceAllAttendees(final Collection<Person> attendees) {
		this.eventAttendeesPropertyDelegate.replaceAll(attendees);
	}

	/** {@inheritDoc} */
	@Override
	public void removeAttendee(final Person attendee) {
		this.eventAttendeesPropertyDelegate.remove(attendee);
	}

	/** {@inheritDoc} */
	@Override
	public void removeAllAttendees(final Collection<Person> attendees) {
		this.eventAttendeesPropertyDelegate.removeAll(attendees);
	}

	/** {@inheritDoc} */
	@Override
	public void clearAttendees() {
		this.eventAttendeesPropertyDelegate.clear();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isAttendee(final Person person) {
		return this.eventAttendeesPropertyDelegate.contains(person);
	}
	
	/** {@inheritDoc} */
	@Override
	protected boolean hasConflicts(final Event event) {
		if (this.eventAttendeesPropertyDelegate.hasConflicts(event)) {
			return true;
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	protected void populateConflictDetailList(
			final List<ConflictDetails> details,
			final Event event) {
		this.eventAttendeesPropertyDelegate.populateConflictDetailList(
				details, event);
	}
}