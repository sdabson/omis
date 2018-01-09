package omis.schedule.builder;

import java.util.HashSet;
import java.util.Set;

import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.schedule.domain.SchedulableEvent;


/**
 * Builds the schedule of a one time only event.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (July 9, 2013)
 * @since OMIS 3.0
 * @see ScheduleBuilder
 */
public class OneTimeOnlyScheduleBuilder
		implements ScheduleBuilder {

	private final DateRange dateRange;
	
	/**
	 * Instantiates a one time only builder for a single event with the
	 * specified date range.
	 * 
	 * @param dateRange date range of the single event
	 */
	public OneTimeOnlyScheduleBuilder(final DateRange dateRange) {
		this.dateRange = new DateRange(dateRange.getStartDate(),
				dateRange.getEndDate());
	}
	
	/**
	 * Returns a set containing a single instance of the one time only event.
	 * 
	 * @param <T> event type
	 * @see ScheduleBuilder#build(InstanceFactory<T>)
	 * @param instanceFactory instance factory
	 * @return single instance of one time only event
	 */
	@Override
	public <T extends SchedulableEvent>
	Set<T> build(final InstanceFactory<T> instanceFactory) {
		Set<T> results = new HashSet<T>();
		T t = instanceFactory.createInstance();
		t.setDateRange(this.dateRange);
		results.add(t);
		return results;
	}
}