package omis.schedule.builder;

import java.util.Set;

import omis.instance.factory.InstanceFactory;
import omis.schedule.domain.SchedulableEvent;


/**
 * Event schedule builder.
 * 
 * <p>Each implementation of this class should build a specific type of schedule
 * (such as one time only, weekly, yearly). A configuration can be generated
 * which contains properties used to determine factors used in scheduling the
 * event such as the start and end date and frequency. These properties are
 * specific to the type of schedule been built. 
 * 
 * <p>Note: It is not the purpose of implementations of this class to check for
 * conflicts in schedules. The purpose is simply to build a set of events as
 * they would be scheduled were the scheduling successful. Clients of
 * implementations of this class are responsible for ensuring that there are
 * no scheduling conflicts between existing scheduled events and the proposed
 * set of events assembled by the builder.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (July 9, 2013)
 * @since OMIS 3.0
 */
public interface ScheduleBuilder {
	
	/**
	 * Returns a set of scheduled events instantiated by the factory. The
	 * way in which the event is scheduled is determined by the type of event
	 * schedule builder and additional properties supplied to the builder.
	 * 
	 * <p>This method does not check for scheduling conflicts but rather
	 * provides a view of how the events would be scheduled were there no
	 * conflicts. The client is responsible for detecting and resolving possible
	 * conflicts from the view provided.
	 * 
	 * @param <T> type of event
	 * @param instanceFactory instance factory
	 * @return set of produced events scheduled according to date range, builder
	 * implementation type and optional builder properties
	 */
	<T extends SchedulableEvent> Set<T> build(
			InstanceFactory<T> instanceFactory);
}