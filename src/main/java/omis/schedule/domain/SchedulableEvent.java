package omis.schedule.domain;

/**
 * Schedulable event entity object.
 * <p>
 * Schedulable events can be built into an event schedule.
 * @author Stephen Abson
 * @version 0.1.0 (Jan 3, 2012)
 * @since OMIS 3.0
 */
public interface SchedulableEvent extends Event {

	/**
	 * Sets the event schedule into which the schedulable event is built.
	 * @param schedule event schedule
	 */
	void setSchedule(Schedule schedule);
	
	/**
	 * Returns the event schedule into which the schedulable event is built.
	 * @return event schedule
	 */
	Schedule getSchedule();
}