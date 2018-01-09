package omis.schedule.domain.impl.delegate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import omis.person.domain.Person;
import omis.schedule.conflict.ConflictDetails;
import omis.schedule.domain.AttendableEvent;
import omis.schedule.domain.Event;

/**
 * Delegate to handle the {@code attendees} property of {@code AttendableEvent}.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 16, 2012)
 * @since OMIS 3.0
 */
public class EventAttendeesPropertyDelegate implements Serializable {

	private static final long serialVersionUID = 1L;

	private final AttendableEvent event;
	
	/**
	 * Instantiates a delegate for the specified event.
	 * 
	 * @param event event the attendee functionality of which to delegate
	 */
	public EventAttendeesPropertyDelegate(final AttendableEvent event) {
		this.event = event;
	}
	
	/**
	 * Adds an attendee.
	 * 
	 * @see AttendableEvent#addAttendee(Person)
	 * @param attendee attendee to add
	 */
	public void add(final Person attendee) {
		this.event.getAttendees().add(attendee);
	}
	
	/**
	 * Adds all the attendees.
	 * 
	 * @see AttendableEvent#addAllAttendees(Collection)
	 * @param attendees attendees to add
	 */
	public void addAll(final Collection<Person> attendees) {
		this.event.getAttendees().addAll(attendees);
	}
	
	/**
	 * Replaces all the attendees.
	 * 
	 * @see AttendableEvent#replaceAllAttendees(Collection)
	 * @param attendees attendees with which to replace
	 */
	public void replaceAll(final Collection<Person> attendees) {
		this.event.getAttendees().clear();
		this.event.getAttendees().addAll(attendees);
	}
	
	/**
	 * Removes an attendee.
	 * 
	 * @see AttendableEvent#removeAttendee(Person)
	 * @param attendee attendee to remove
	 */
	public void remove(final Person attendee) {
		this.event.getAttendees().remove(attendee);
	}
	
	/**
	 * Removes all the attendees.
	 * 
	 * @see AttendableEvent#removeAllAttendees(Collection)
	 * @param attendees attendees to remove
	 */
	public void removeAll(final Collection<Person> attendees) {
		this.event.getAttendees().removeAll(attendees);
	}
	
	/**
	 * Clears all the attendees.
	 * 
	 * @see AttendableEvent#clearAttendees()
	 */
	public void clear() {
		this.event.getAttendees().clear();
	}
	
	/**
	 * Returns whether the specified person is an attendee of the event.
	 *  
	 * @param person person whose attendeeship to determine
	 * @see AttendableEvent#isAttendee(Person)
	 * @return whether the person is an attendee of the event
	 */
	public boolean contains(final Person person) {
		return this.event.getAttendees().contains(person);
	}
	
	/**
	 * Returns whether the event the functionality of which {@code this}
	 * is delegated has conflicts with {@code event}.
	 * 
	 * @param event event with which to check for conflicts with event the
	 * functionality of which {@code this} is delegated 
	 * @return whether event the functionality of which {@code this} is
	 * delegated and {@code event} have conflicts 
	 */
	public boolean hasConflicts(final Event event) {
		if (event instanceof AttendableEvent) {
			for (Person person : ((AttendableEvent) event).getAttendees()) {
				if (this.event.isAttendee(person)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Populates a list of conflict details with conflicts between the event
	 * the functionality of which {@code this} is delegated and {@code event}.
	 * 
	 * @param details details of conflicts between the event the functionality
	 * of which {@code this} is delegated and {@code this}
	 * @param event event with which to check for conflicts with event the
	 * functionality of which {@code this} is delegated 
	 */
	public void populateConflictDetailList(
			final List<ConflictDetails> details, final Event event) {
		if (event instanceof AttendableEvent) {
			for (Person person : ((AttendableEvent) event).getAttendees()) {
				if (this.event.isAttendee(person)) {
					details.add(new ConflictDetails(
						"Person conflict: " + person, Person.class, "person"));
				}
			}
		}
	}
}