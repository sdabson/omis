package omis.schedule.conflict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import omis.schedule.domain.Event;

/**
 * Represents a conflict between a currently saved event and a proposed event
 * that is yet to be saved. The details of the conflict are also represented.
 * 
 * @author Stephen Abson
 * @version 0.1 (Jan 3, 2012)
 * @since OMIS 3.0
 */
public final class Conflict
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Event currentEvent;
	
	private final Event proposedEvent;
	
	private final ConflictDetails conflictDetails;
	
	// Instantiates a conflict with all required properties
	private Conflict(final Event currentEvent, final Event proposedEvent,
			final ConflictDetails conflictDetails) {
		this.currentEvent = currentEvent;
		this.proposedEvent = proposedEvent;
		this.conflictDetails = conflictDetails;
	}
	
	/**
	 * Returns a list of conflicts between a list of currently saved and
	 * proposed events.
	 * 
	 * @param currentEvents currently saved events
	 * @param proposedEvents proposed events
	 * @return list of conflicts between current and proposed; empty list
	 * if not conflicts exist
	 */
	public static List<Conflict> findConflicts(
			final Collection<? extends Event> currentEvents,
			final Collection<? extends Event> proposedEvents) {
		List<Conflict> results = new ArrayList<Conflict>();
		if (currentEvents.size() == 0 || proposedEvents.size() == 0) {
			return results;
		}
		for (Event proposedEvent : proposedEvents) {
			synchronized (proposedEvent) {
				for (Event currentEvent : currentEvents) {
					synchronized (currentEvent) {
						if (proposedEvent.conflictsWith(currentEvent)
								|| currentEvent.conflictsWith(proposedEvent)) {
							List<ConflictDetails> list1 = proposedEvent
								.getConflictDetailsList(currentEvent);
							List<ConflictDetails> list2 = currentEvent
								.getConflictDetailsList(proposedEvent);
							List<ConflictDetails> all;
							if (list1.size() > 0 && list2.size() > 0) {
								all = mergeConflictDetailsLists(list1, list2);
							} else if (list1.size() == 0) {
								all = list2;
							} else if (list2.size() == 0) {
								all = list1;
							} else {
								
								// The following indicates a logical error.
								// The OR on each event's conflictsWith()
								// method should ensure that at least one of
								// the conflict list's size is > 0
								// If the following code is reached, the
								// contract between conflictsWith() and
								// getConflictDetailsList() is broken
								throw new AssertionError("Asymmetry in conflict"
									+ " detection for classes "
									+ proposedEvent.getClass().getName()
									+ " and "
									+ currentEvent.getClass().getName());
							}
							for (ConflictDetails details : all) {
								results.add(new Conflict(currentEvent,
										proposedEvent, details));
							}
						}
					}
				}
			}
		}
		return results;
	}
	
	/**
	 * Returns the currently saved event with which the proposed event
	 * conflicts.
	 * 
	 * @return currently saved event
	 */
	public Event getCurrentEvent() {
		return this.currentEvent;
	}
	
	/**
	 * Returns the proposed event which conflicts with the currently saved
	 * event.
	 * 
	 * @return proposed event
	 */
	public Event getProposedEvent() {
		return this.proposedEvent;
	}
	
	/**
	 * Returns the details of the conflict.
	 * 
	 * @return details of conflict
	 */
	public ConflictDetails getConflictDetails() {
		return this.conflictDetails;
	}
	
	// Produces a list of all unique conflict details in list1 and list2
	// Unique defined in this context as been not equal by the equals method
	private static List<ConflictDetails> mergeConflictDetailsLists(
			final List<ConflictDetails> list1,
			final List<ConflictDetails> list2) {
		List<ConflictDetails> result = new ArrayList<ConflictDetails>();
		result.addAll(list1);
		for (ConflictDetails item2 : list2) {
			boolean found = false;
			for (ConflictDetails item1 : list1) {
				if (item2.equals(item1)) {
					found = true;
					break;
				}
			}
			if (!found) {
				result.add(item2);
			}
		}
		return result;
	}
}