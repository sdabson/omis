package omis.schedule.domain;

import java.util.Collection;
import java.util.Set;

import omis.person.domain.Person;

/**
 * An event that can be attended by attendee people.
 * <p>
 * A conflict will occur if two attendable events have overlapping date
 * ranges and are attended by one or more of the same person.
 * @author Stephen Abson
 * @version 0.1.0 (Dec 16, 2012)
 * @since OMIS 3.0
 */
public interface AttendableEvent extends SchedulableEvent {

	/**
	 * Sets the attendees.
	 * @param attendees attendees
	 */
	void setAttendees(Set<Person> attendees);
	
	/**
	 * Returns the attendees.
	 * @return attendees
	 */
	Set<Person> getAttendees();
	
	/**
	 * Adds an attendee.
	 * @param attendee attendee to add
	 */
	void addAttendee(Person attendee);
	
	/**
	 * Adds all the attendees.
	 * @param attendees attendees to all
	 */
	void addAllAttendees(Collection<Person> attendees);
	
	/**
	 * Replaces the attendees.
	 * @param attendees attendees with which to replace
	 */
	void replaceAllAttendees(Collection<Person> attendees);
	
	/**
	 * Removes an attendee.
	 * @param attendee attendee to remove
	 */
	void removeAttendee(Person attendee);
	
	/**
	 * Removes all the attendees.
	 * @param attendees attendees to remove
	 */
	void removeAllAttendees(Collection<Person> attendees);
	
	/**
	 * Clears the attendees.
	 */
	void clearAttendees();
	
	/**
	 * Returns whether the specified person is an attendee of the event.
	 * @param person person whose attendeeship to determine
	 * @return whether person is an attendee
	 */
	boolean isAttendee(Person person);
}