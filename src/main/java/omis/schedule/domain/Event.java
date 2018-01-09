package omis.schedule.domain;

import java.util.List;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.schedule.conflict.ConflictDetails;


/**
 * Event.
 * <p>
 * Each event has a date range during which the event occurs.
 * @author Stephen Abson
 * @version 0.1.2 (Dec 16, 2012)
 * @since OMIS 3.0
 */
public interface Event extends Updatable, Creatable {

	/**
	 * Sets the ID of the event.
	 * @param id event ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the event.
	 * @return event ID
	 */
	Long getId();
	
	/**
	 * Sets the date range of the event.
	 * @param dateRange event date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range of the event.
	 * @return event date range 
	 */
	DateRange getDateRange();
	
	/**
	 * Returns whether {@code this} conflicts with {@code event}.
	 * <p>
	 * This method should test each property of the event for conflicts.
	 * <p>
	 * This method should also be symmetric with
	 * {@code getConflictDetailsList(Event)}. If this method returns
	 * {@code true}, {@code getConflictDetailsList(Event)} should return at
	 * least one conflict if the same object is passed and no modifications are
	 * made to either {@code this} and the event passed between invocations.
	 * @param event event with which to compare {@code this} for conflicts
	 * @return {@code true} if {@code this} and {@code event} conflict;
	 * {@code false} otherwise
	 */
	boolean conflictsWith(Event event);
	
	/**
	 * Returns a list of details of the conflicts between {@code this} and
	 * {@code event}. The conflicts returned are the ones detectable by
	 * {@code this}. Conflicts detectable by {@code event} will not be included
	 * in the results. Clients should call and compare the results of this
	 * method on both {@code this} and {@code event}.
	 * <p>
	 * If {@code this} is the same object as {@code event}, an empty list
	 * will be returned.
	 * <p>
	 * This method should be symmetric with {@code conflictsWith(Event)}. If
	 * {@code conflictsWith(Event)} returns {@code true}, this method should
	 * return at least one conflict if the same object is passed and no
	 * modifications are made to either {@code this} or the object passed
	 * between invocations.
	 * @param event event with which to compare {@code this} for conflicts
	 * @return list of conflict details between {@code this} and {@code event};
	 * if no conflicts are found - or if {@code this} is equal to
	 * {@code event}, an empty list will be returned 
	 */
	List<ConflictDetails> getConflictDetailsList(Event event);
	
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
}