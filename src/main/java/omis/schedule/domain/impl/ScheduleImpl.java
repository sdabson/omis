package omis.schedule.domain.impl;

import java.util.HashSet;
import java.util.Set;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.schedule.domain.SchedulableEvent;
import omis.schedule.domain.Schedule;

/**
 * Implementation of event schedule.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 18, 2012)
 * @since OMIS 3.0
 */
public class ScheduleImpl implements Schedule {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Set<SchedulableEvent> events = new HashSet<SchedulableEvent>();

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;
	
	/** Instantiates a default event schedule. */
	public ScheduleImpl() {
		// Do nothing
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setEvents(final Set<SchedulableEvent> events) {
		this.events = events;
	}
	
	/** {@inheritDoc} */
	@Override
	public Set<SchedulableEvent> getEvents() {
		return this.events;
	}
	
	/** {@inheritDoc} */
	@Override
	public void addEvent(final SchedulableEvent event) {
		event.setSchedule(this);
		this.getEvents().add(event);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeEvent(final SchedulableEvent event) {
		this.getEvents().remove(event);
		event.setSchedule(null);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		
		// TODO: Implement ScheduleImpl#equals(Object) - SA
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		
		// TODO: Implement ScheduleImpl#hashCode() - SA
		throw new UnsupportedOperationException("Not yet implemented");
	}
}