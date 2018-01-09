package omis.schedule.builder.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.schedule.conflict.ConflictDetails;
import omis.schedule.domain.Event;
import omis.schedule.domain.SchedulableEvent;
import omis.schedule.domain.Schedule;


/**
 * Common functionality for recurring schedule builder tests.
 * @author Stephen Abson
 * @version 0.1.1 (Dec 18, 2012)
 * @since OMIS 3.0
 */
public abstract class AbstractRecurringScheduleBuilderTests {
	
	// Event instance ID
	private static Long currentId = 0L;
	
	/** Logger for test class. */
	protected Logger logger = Logger.getLogger(getClassName());
	
	/**
	 * Returns the class name.
	 * @return class name
	 */
	protected abstract String getClassName();
	
	/** An event producer for simple events that store a date range. */
	protected static InstanceFactory<SchedulableEvent> simpleInstanceFactory = 
		new InstanceFactory<SchedulableEvent>() {
			@Override
			public SchedulableEvent createInstance() {
				return new SchedulableEvent() {
					private static final long serialVersionUID = 1L;
					private Long id = ++currentId;
					private DateRange dateRange;
					@Override public void setId(Long id) { this.id = id; }
					@Override public Long getId() { return id; }
					@Override public void setDateRange(DateRange dateRange) {
						this.dateRange = dateRange;
					}
					@Override public DateRange getDateRange() {
						return dateRange;
					}
					@Override public boolean conflictsWith(Event event) {
						throw new UnsupportedOperationException(
								"Event type not persistent");
					}
					@Override
					public List<ConflictDetails> getConflictDetailsList(
							Event event) {
						throw new UnsupportedOperationException(
								"Event type not persistent");
					}
					@Override
					public void setSchedule(Schedule schedule) {
						throw new UnsupportedOperationException(
								"ScheduleImpl not supported");
					}
					@Override
					public Schedule getSchedule() {
						throw new UnsupportedOperationException(
								"ScheduleImpl not supported");
					}
					@Override public String toString() {
						return String.format("%d: %s", id,
								dateRange.toString());
					}
					@Override public int hashCode() {
						return dateRange.getStartDate().hashCode();
					}
					@Override
					public void setUpdateSignature(
							UpdateSignature updateSignature) {
						throw new UnsupportedOperationException(
								"Update signature not supported");
					}
					@Override
					public UpdateSignature getUpdateSignature() {
						throw new UnsupportedOperationException(
								"Update signature not supported");
					}
					@Override
					public void setCreationSignature(
							CreationSignature creationSignature) {
						throw new UnsupportedOperationException(
								"Creation signature not supported");
					}
					@Override
					public CreationSignature getCreationSignature() {
						throw new UnsupportedOperationException(
								"Creation signature not supported");
					}
				};
			}
	};
	
	/**
	 * Prints the set of schedulable events to {@code logger}.
	 * @param events schedulable event to print
	 */
	protected void print(Set<SchedulableEvent> events) {
		for (SchedulableEvent e : events) {
			logger.info(e.toString());
		}
	}
	
	/**
	 * Returns a date range representing the two time string parsed as:
	 * <pre>HH:mm:ss</pre>
	 * @param startTime start time
	 * @param endTime end time
	 * @return date range from parsed strings
	 * @throws ParseException if the string cannot be parsed
	 */
	protected static DateRange parseTimes(String startTime, String endTime)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return new DateRange(sdf.parse(startTime), sdf.parse(endTime));
	}
	
	/**
	 * Returns a date range for the two date string parsed as:
	 * <pre>MM/dd/yyyy HH:mm:ss</pre>
	 * @param startDate start date
	 * @param endDate end date
	 * @return date range from parsed strings
	 * @throws ParseException if the string cannot be parsed
	 */
	protected static DateRange parseDates(String startDate, String endDate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return new DateRange(sdf.parse(startDate), sdf.parse(endDate));
	}
}