package omis.schedule.domain.impl;

import omis.schedule.domain.SchedulableEvent;
import omis.schedule.domain.Schedule;

/**
 * Implementation of a schedulable event entity object.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Dec 17, 2012)
 * @since OMIS 3.0
 */
public abstract class AbstractSchedulableEvent
		extends AbstractEvent implements SchedulableEvent {

	private static final long serialVersionUID = 1L;
	
	private Schedule schedule;
	
	/** Instantiates a default schedulable event. */
	protected AbstractSchedulableEvent() {
		// Default
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSchedule(final Schedule schedule) {
		this.schedule = schedule;
	}

	/** {@inheritDoc} */
	@Override
	public Schedule getSchedule() {
		return this.schedule;
	}
}