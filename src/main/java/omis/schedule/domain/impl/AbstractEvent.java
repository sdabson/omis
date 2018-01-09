package omis.schedule.domain.impl;

import java.util.ArrayList;
import java.util.List;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.schedule.conflict.ConflictDetails;
import omis.schedule.domain.Event;

/**
 * Implementation of event.
 * 
 * @author Stephen Abson
 * @version 0.3.1 (Dec 17, 2012)
 * @since OMIS 3.0
 */
public abstract class AbstractEvent implements Event {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private DateRange dateRange;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;
	
	/** Instantiates a default event. */
	protected AbstractEvent() {
		// Default
	}
	
	/**
	 * Instantiates an event with the specified date range.
	 * 
	 * @param dateRange event date range
	 */
	protected AbstractEvent(final DateRange dateRange) {
		if (dateRange != null) {
			this.dateRange = new DateRange(dateRange.getStartDate(),
					dateRange.getEndDate());
		}
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
	public void setDateRange(final DateRange dateRange) {
		if (dateRange != null) {
			this.dateRange = new DateRange(dateRange.getStartDate(),
					dateRange.getEndDate());
		} else {
			this.dateRange = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		if (this.dateRange != null) {
			return new DateRange(this.dateRange.getStartDate(),
					this.dateRange.getEndDate());
		} else {
			return null;
		}
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
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}
	
	
	/** {@inheritDoc} */
	@Override
	public final boolean conflictsWith(final Event event) {
		if (this.getDateRange().overlaps(event.getDateRange())) {
			return hasConflicts(event);
		}
		return false;
	}
	
	/**
	 * Called by {@link conflictsWith(Event)} in order to determine whether
	 * {@code this} conflicts with {@code event}. Events further down
	 * inheritance hierarchies should override this method adding additional
	 * tests for their additional properties.
	 * 
	 * <p>Typically, the implementation of this method will call the super
	 * implementation (if one exists) and, if no conflicts are detected,
	 * then compare additional properties added at the implementation's level in
	 * the inheritance hierarchy. For an {@code OffenderEvent} with an
	 * {@code offender} property:
	 * 
	 * <pre>
	 * protected boolean hasConflict(Event event) {
	 *   if (super.hasConflict(event)) {
	 *     return true;
	 *   }
	 *   if (event instanceof OffenderEvent) {
	 *   	OffenderEvent that = (OffenderEvent)event;
	 *      if (getOffender().equals(that.getOffender()) {
	 *        return true;
	 *      }
	 *   }
	 *   return false;
	 * }
	 * </pre>
	 * 
	 * <p>Alternatively, in more complex inheritance hierarchies, the
	 * functionality of this method might be delegated to one or more composited
	 * internal implementation classes. For a class extending both
	 * {@code UserEvent} and {@code OffenderEvent}, using composition to share
	 * common functionality, the implementation of this method might be as
	 * follows:
	 * 
	 * <pre>
	 * protected boolean hasConflict(Event event) {
	 *   if (userEventDelegate.hasConflict(event)) {
	 *     return true;
	 *   }
	 *   if (offenderEventDelegate.hasConflict(event)) {
	 *     return true;
	 *   }
	 *   return false;
	 * }
	 * </pre>
	 * 
	 * <p>This method will only be called by {@code conflictsWith(Event)} if
	 * {@code this} and {@code event} overlap. There should be no need
	 * to test for date overlaps in this method.
	 * 
	 * <p>This method is intended to be overridden extensively.
	 * 
	 * <p>Properties tested for conflicts in
	 * {@code populateConflictDetailList(List<ConflictDetails>, Event)} should
	 * also be tested in this method.
	 * 
	 * @param event event with which to compare {@code this} for conflicts
	 * @return {@code true} if {@code this} and {@code event} conflict;
	 * {@code false} otherwise
	 */
	protected abstract boolean hasConflicts(Event event);
	
	/** {@inheritDoc} */
	@Override
	public final List<ConflictDetails> getConflictDetailsList(
			final Event event) {
		List<ConflictDetails> results = new ArrayList<ConflictDetails>();
		if (this.getDateRange().overlaps(event.getDateRange())) {
			populateConflictDetailList(results, event);
		}
		return results;
	}
	
	/**
	 * Called by {@link getConflictDetailsList(Event)} to populate a list
	 * of details of conflicts between {@code this} and {@code event}. Events
	 * further down inheritance hierarchies should override this method adding
	 * additional tests for their additional properties.
	 * 
	 * <p>Typically, the implementation of this method will call the super
	 * implementation (if one exists) to populate the details list with possible
	 * conflicts of properties in parent classes. The implementation should then
	 * attempt to detect possible conflicts of additional properties added at
	 * the implementation's level in the inheritance hierarchy. For an
	 * {@code OffenderEvent} with an {@code offender} property of type
	 * {@code Offender}:
	 * 
	 * <pre>
	 * protected void populateConflictDetailList(
	 *     List&lt;ConflictDetails&gt; details, Event event) {
	 *   super.populateConflictDetailList(details, event);
	 *   if (event instanceof OffenderEvent) {
	 *     OffenderEvent that = (OffenderEvent)event;
	 *     if (getOffender().equals(that.getOffender()) {
	 *       details.add(new ConflictDetails("Offender conflict",
	 *           Offender.class, "offender"));
	 *     }
	 *   }
	 * }
	 * </pre>
	 * 
	 * <p>Alternatively, in more complex inheritance hierarchies, the
	 * functionality of this method might be delegated to one or more composited
	 * classes. For a class extending both {@code UserEvent} and
	 * {@code OffenderEvent}, using composition to share common functionality,
	 * the implementation of this method might be as follows:
	 * 
	 * <pre>
	 * protected void populateConflictDetailList(
	 *     List&lt;ConflictDetails&gt; details, Event event) {
	 *   userEventDelegate.populateConflictDetails(details, event);
	 *   offenderEventDelegate.populateConflictDetails(details, event);
	 * } 
	 * </pre>
	 * 
	 * <p>This method will only be called by
	 * {@code getConflictDetailsList(Event)} if {@code this} and {@code event}
	 * overlap. There should be no need to test for date overlaps in this
	 * method.
	 * 
	 * <p>This method is intended to be overridden extensively.
	 * 
	 * <p>Properties tested for conflicts in {@code hasConflicts(Event)} should
	 * also be tested in this method.
	 * 
	 * @param details list of conflict details
	 * @param event event with which to compare {@code this} for conflicts
	 */
	protected abstract void populateConflictDetailList(
			final List<ConflictDetails> details, final Event event);
}