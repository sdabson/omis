package omis.schedule.domain;

import java.util.Set;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Event schedule.
 * <p>
 * An event schedule is used to store a series of one or more related
 * schedulable events. Events are related if they are scheduled together as
 * part of a recurring event.
 * @author Stephen Abson
 * @version 0.1.2 (Dec 18, 2012)
 * @since OMIS 3.0
 */
public interface Schedule extends Creatable, Updatable {

	/**
	 * Sets the ID of the schedule.
	 * @param id schedule ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the event schedule.
	 * @return event schedule ID
	 */
	Long getId();
	
	/**
	 * Sets the set of related schedulable events.
	 * @param events related schedulable events
	 */
	void setEvents(Set<SchedulableEvent> events);
	
	/**
	 * Returns the set of related schedulable events.
	 * @return related schedulable events
	 */
	Set<SchedulableEvent> getEvents();
	
	/**
	 * Adds an event to the set of related schedulable events. This will set
	 * the schedule property of the schedulable event to {@code this}.
	 * <p>
	 * If the schedulable event is already contained in the list, this method
	 * does nothing.
	 * @param event event to add to related schedulable events 
	 */
	void addEvent(SchedulableEvent event);
	
	/**
	 * Removes an event from  the set of related schedulable events. This will
	 * set the schedule property of the schedulable event to {@code null}.
	 * <p>
	 * If the schedulable event is not contained in the list, this method
	 * does nothing.
	 * @param event event to remove from related schedulable events 
	 */
	void removeEvent(SchedulableEvent event);	

	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
	
	/**
	 * Returns a string representation of the schedule entity object containing
	 * the ID and information on the set of events.
	 * @return string containing ID and information on set of events
	 */
	@Override
	String toString();
}