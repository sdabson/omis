package omis.schedule.web.taglib;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import omis.datatype.DateRange;
import omis.schedule.domain.Event;
import omis.util.DateManipulator;

/**
 * Displays a schedule for a given set of events.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (April 25, 2013)
 * @since OMIS 3.0
 */
public class CalendarTag
		extends SimpleTagSupport {

	private static final String DEFAULT_TIME_PATTERN = "h:mm a";
	
	private static final String DEFAULT_DATE_PATTERN = "MM/dd/yyyy";
	
	private static final int MS_IN_SECOND = 1000;
	
	private static final int SECONDS_IN_MINUTE = 60;
	
	private static final int MS_IN_MINUTE = MS_IN_SECOND * SECONDS_IN_MINUTE;
	
	private static final int MINUTES_IN_HOUR = 60;
	
	private static final int MS_IN_HOUR = MS_IN_MINUTE * MINUTES_IN_HOUR;
	
	private String id;
	
	private String className;
	
	private String var;
	
	private List<? extends Event> items;
	
	private Date date;
	
	private DateRange dateRange;
	
	private DateRange timeRange;
	
	private String viewType;
	
	private String datePattern;
	
	private String timePattern;
	
	private String timeClass;
	
	private String dateClass;
	
	private String dataClass;
	
	private Integer interval;
	
	/** Instantiates a default calendar tag. */
	public CalendarTag() {
		// Default instantiation
	}
	
	/* Set attributes */
	
	/**
	 * Sets the ID of the table.
	 * 
	 * @param id table ID
	 */
	public void setId(final String id) {
		this.id = id;
	}
	
	/**
	 * Sets the class of the calendar.
	 * 
	 * @param className calendar class name
	 */
	public void setClassName(final String className) {
		this.className = className;
	}
	
	/**
	 * Sets the variable name.
	 * 
	 * @param var variable name
	 */
	public void setVar(final String var) {
		this.var = var;
	}
	
	/**
	 * Sets the items.
	 * 
	 * @param items items
	 */
	public void setItems(final List<? extends Event> items) {
		this.items = items;
	}
	
	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/**
	 * Sets the date range.
	 * 
	 * <p>Only supported for daily view types.
	 * 
	 * @param dateRange date range
	 */
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = DateRange.deepCopy(dateRange);
	}
	
	/**
	 * Sets the time range.
	 * 
	 * <p>Only supported for daily view types.
	 * 
	 * @param timeRange time range
	 */
	public void setTimeRange(final DateRange timeRange) {
		this.timeRange = DateRange.deepCopy(timeRange);
	}
	
	/**
	 * Sets the view type.
	 * 
	 * @param viewType view type
	 */
	public void setViewType(final String viewType) {
		this.viewType = viewType;
	}
	
	/**
	 * Sets the date pattern.
	 * 
	 * @param datePattern date pattern
	 */
	public void setDatePattern(final String datePattern) {
		this.datePattern = datePattern;
	}
	
	/**
	 * Sets the time pattern.
	 * 
	 * @param timePattern time pattern
	 */
	public void setTimePattern(final String timePattern) {
		this.timePattern = timePattern;
	}
	
	/**
	 * Sets the class for cells containing times.
	 * 
	 * @param timeClass time class
	 */
	public void setTimeClass(final String timeClass) {
		this.timeClass = timeClass;
	}
	
	/**
	 * Sets the class for cells containing dates.
	 * 
	 * @param dateClass date class
	 */
	public void setDateClass(final String dateClass) {
		this.dateClass = dateClass;
	}
	
	/**
	 * Sets the class for cells containing data.
	 * 
	 * @param dataClass data class
	 */
	public void setDataClass(final String dataClass) {
		this.dataClass = dataClass;
	}
	
	/**
	 * Parses the specified string into an interval.
	 * 
	 * <p>The specified string must contain an integer optionally followed by
	 * time unit specified. If no time unit is specified, milliseconds is
	 * assumed. Acceptable time unit specifiers are:
	 * 
	 * <dl>
	 * <dt>h<dd>hours
	 * <dt>m<dd>minutes
	 * <dt>s<dd>seconds
	 * <dt>ms<dd>milliseconds (default)
	 * </dl>
	 * 
	 * @param interval interval
	 * @throws IllegalArgumentException if the string format is invalid
	 */
	public void setInterval(final String interval) {
		String[] items = interval.split("\\d+hms", 1);
		if (items.length < 1) {
			throw new IllegalArgumentException(
					"Unparsable interval: " + interval);
		}
		int units = Integer.valueOf(items[0]);
		if (items.length == 1 || (items.length == 2 && "ms".equals(items[1]))) {
			this.interval = units;
		} else if (items.length == 2 && "s".equals(items[1])) {
			this.interval = units * MS_IN_SECOND;
		} else if (items.length == 2 && "m".equals(items[1])) {
			this.interval = units * MS_IN_MINUTE;
		} else if (items.length == 2 && "h".equals(items[1])) {
			this.interval = units * MS_IN_HOUR;
		} else {
			throw new IllegalArgumentException(
					"Unparsable interval: " + interval);
		}
	}
	
	/* Handle tag */
	
	/** {@inheritDoc} */
	@Override
	public void doTag() throws JspException, IOException {
		if ("daily".equalsIgnoreCase(this.viewType)) {
			DateRange dateRangeToUse;
			if (this.dateRange != null) {
				dateRangeToUse = this.dateRange;
			} else {
				dateRangeToUse = DateRange.findDailyRange(this.date); 
			}
			if (this.timeRange != null) {
				doHourlyTagAtTimeRange(dateRangeToUse, this.timeRange);
			} else {
				doHourlyTag(dateRangeToUse);
			}
		} else if ("weekly".equalsIgnoreCase(this.viewType)) {
			if (this.timeRange != null) {
				doHourlyTagAtTimeRange(DateRange.findWeeklyRange(this.date),
						this.timeRange);
			} else {
				doHourlyTag(DateRange.findWeeklyRange(this.date));
			}
		} else if ("monthly".equalsIgnoreCase(this.viewType)) {
			doMonthlyTag(DateRange.findWeekOfMonthRange(this.date));
		} else {
			throw new IllegalArgumentException(
					"Unknown view type: " + this.viewType);
		}
	}
	
	// Outputs an hourly schedule for the specified date range
	private void doHourlyTag(final DateRange dateRange)
			throws JspException, IOException {
		doHourlyTagAtTimeRange(dateRange,
				DateRange.findTimeRangeOfDate(new Date(0L)));
	}


	// Outputs an hourly schedule for the specified date range at only
	// the specified time range per day
	private void doHourlyTagAtTimeRange(final DateRange dateRange,
		final DateRange timeRange) throws JspException, IOException {
		
		// Begin table and body
		beginTable();
		beginTableBody();
		
		// Output a row of date labels and populate a list of each day in the
		// range and a map of events to each of these days
		List<Date> days = dateRange.findEarliestTimesInEachDay();
		Map<Date, List<Event>> eventsInDay = new HashMap<Date, List<Event>>();
		beginRow();
		beginCol();
		endCol();
		for (Date day : days) {
			beginCol(day.getTime(), this.dateClass);
			getJspWriter().println(formatDate(day));
			endCol();
			eventsInDay.put(day, getOverlappingDateRange(this.items,
					DateRange.findTimeRangeOnDay(day, timeRange)));
		}
		endRow();
		
		// Determine which interval to use
		int intervalToUse;
		if (this.interval != null) {
			intervalToUse = this.interval;
		} else {
			
			// Use hours by default
			intervalToUse = CalendarTag.MS_IN_HOUR;
		}
		
		// Iterate over time range by interval
		for (long time = timeRange.getStartDate().getTime();
				time <= timeRange.getEndDate().getTime();
				time += intervalToUse) {
			
			// Row per time "block"
			beginRow(time);
			
			// First column is time information - times are for 01/01/1970 so
			// as to not include a DST alteration
			beginCol(time, this.timeClass);
			final Date timeDate = new Date(time);
			getJspWriter().println(formatTime(timeDate));
			endCol();
			
			// Display event information for each date during current time block
			for (Date date : days) {
				
				// Determine whether hour actually exists using the literal
				// hour
				DateManipulator manipulator = new DateManipulator(date);
				manipulator.setToTimeOfDate(timeDate);
				int hour = manipulator.getHour();
				if (isDaylightSavingsHour(date, hour)) {
					beginCol();
					endCol();
				} else {
					
					// Determine whether there is a DST offset between the
					// current and next hour
					Date timeInDate = manipulator.getDate();
					long dstOffset = getDstOffsetNextInterval(timeInDate,
							CalendarTag.MS_IN_HOUR);
					
					// Calculate end date
					Date endDate = new Date(
							timeInDate.getTime() + intervalToUse);
					
					// If this condition evaluates to false, it is a Spring
					// skipped DST hour
					if (dstOffset <= 0) {
					
						// A negative DST offset incurs the possibility of a
						// unit of time been skipped. If a unit of time is
						// skipped, events will not be displayed. Subtract this
						// negative from the end date to include events in
						// overlapping this unit of time making the 
						endDate = new Date(endDate.getTime() - dstOffset);
					}
					
					// Display events
					List<Event> eventsInHour = getOverlappingDateRange(
							eventsInDay.get(date),
							new DateRange(timeInDate, endDate));
					beginCol(timeInDate.getTime(), this.dataClass);
					for (Event event : eventsInHour) {
						getJspContext().setAttribute(this.var, event);
						getJspBody().invoke(null);
					}
					endCol();
				}
			}
			endRow();
		}

		// End body and table
		endTableBody();
		endTable();
	}
	
	// Outputs a monthly schedule
	private void doMonthlyTag(final DateRange dateRange)
			throws JspException, IOException {
		beginTable();
		beginTableBody();
		DateManipulator current = new DateManipulator(dateRange.getStartDate());
		final int daysInWeek = current.getDaysInWeek();
		while (current.isLessThanOrEqualTo(dateRange.getEndDate())) {
			beginRow(current.getDate().getTime());
			for (int i = 0; i < daysInWeek; i++) {
				beginCol(current.getDate().getTime());
				writeSpan(formatDate(current.getDate()), this.dateClass);
				List<Event> events = getOverlappingDateRange(this.items,
						DateRange.findDailyRange(current.getDate()));
				for (Event event : events) {
					getJspContext().setAttribute(this.var, event);
					getJspBody().invoke(null);
				}
				endCol();
				current.changeDate(1);
			}
			endRow();
		}
		endTableBody();
		endTable();
	}
	
	// Given a date, set the time of the date to the hour specified
	private static Date getDateAtHour(final Date date, final int hour) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(GregorianCalendar.HOUR_OF_DAY, hour);
		cal.add(GregorianCalendar.MINUTE, 0
				- cal.get(GregorianCalendar.MINUTE));
		cal.add(GregorianCalendar.SECOND, 0
				- cal.get(GregorianCalendar.SECOND));
		cal.add(GregorianCalendar.MILLISECOND, 0
				- cal.get(GregorianCalendar.MILLISECOND));
		return cal.getTime();
	}
	
	// Determines whether the literal hour represented by {@code hour} in the
	// day of {@code date} is skipped due to daylight savings
	private boolean isDaylightSavingsHour(final Date date, final int hour) {
		Date hourInDate = getDateAtHour(date, hour);
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(hourInDate);
		int newHour = cal.get(GregorianCalendar.HOUR_OF_DAY);
		if (hour == newHour) {
			return false;
		} else {
			long dstOffset = cal.get(GregorianCalendar.DST_OFFSET);
			long msInHour = MS_IN_HOUR;
			if (dstOffset / msInHour == newHour - hour) {
				return true;
			} else {
				
				// This mysteriously occurs on "Sun Mar 08 00:00:00 MST 2674"
				// Timestamp: 22222395199999
				throw new AssertionError("Error calculating daylight savings: "
						+ " dstOffset / msInHour != newHour - hour ("
						+ (dstOffset / msInHour) + " != " + (newHour - hour)
						+ ")");
			}
		}
	}
	
	// Returns the difference in daylight savings offset, in milliseconds,
	// between the current hour of {@code date} and the next hour
	@SuppressWarnings("unused")
	private long getDstOffsetNextHour(final Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		long origDstOffset = cal.get(GregorianCalendar.DST_OFFSET);
		cal.set(GregorianCalendar.HOUR_OF_DAY,
					cal.get(GregorianCalendar.HOUR_OF_DAY) + 1);
		long newDstOffset = cal.get(GregorianCalendar.DST_OFFSET);
		long diff = newDstOffset - origDstOffset;
		return diff;
	}
	
	// Returns the difference in daylight savings offset, in milliseconds,
	// between the current time of {@code date} and the time after the
	// specified interval
	private long getDstOffsetNextInterval(final Date date, final int interval) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		long origDstOffset = cal.get(GregorianCalendar.DST_OFFSET);
		cal.set(GregorianCalendar.MILLISECOND,
					cal.get(GregorianCalendar.MILLISECOND) + interval);
		long newDstOffset = cal.get(GregorianCalendar.DST_OFFSET);
		long diff = newDstOffset - origDstOffset;
		return diff;
	}
	
	// Returns the date at the latest point in the same hour of the given
	// date. This is accurate to the millisecond.
	@SuppressWarnings("unused")
	private Date getLatestTimeInHour(final Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.MINUTE,
				cal.getActualMaximum(GregorianCalendar.MINUTE)
					- cal.get(GregorianCalendar.MINUTE));
		cal.add(GregorianCalendar.SECOND,
				cal.getActualMaximum(GregorianCalendar.SECOND)
					- cal.get(GregorianCalendar.SECOND));
		cal.add(GregorianCalendar.MILLISECOND,
				cal.getActualMaximum(GregorianCalendar.MILLISECOND)
					- cal.get(GregorianCalendar.MILLISECOND));
		return cal.getTime();
	}
	
	/* HTML output */
	
	// Returns JSP writer
	private JspWriter getJspWriter() {
		return getJspContext().getOut();
	}
	
	// Begins a table
	private void beginTable() throws IOException {
		String classAttr = formatAttribute(" ", "class", this.className);
		if (this.id != null && this.id.length() > 0) {
			this.getJspWriter().println(
					"<table id=\"" + this.id + "\"" + classAttr + ">");
		} else {
			this.getJspWriter().println("<table" + classAttr + ">");
		}
	}
	
	// Ends a table
	private void endTable() throws IOException {
		this.getJspWriter().println("</table>");
	}
	
	// Writes a caption
	@SuppressWarnings("unused")
	private void writeCaption(final String caption) throws IOException {
		this.getJspWriter().println("<caption>" + caption + "</caption>");
	}
	
	// Begins a table body
	private void beginTableBody() throws IOException {
		this.getJspWriter().println("<tbody>");
	}
	
	// Ends a table body
	private void endTableBody() throws IOException {
		this.getJspWriter().println("</tbody>");
	}
	
	// Begins a table row
	private void beginRow(final long timeStamp) throws IOException {
		if (this.id != null && this.id.length() > 0) {
			this.getJspWriter().println("<tr id=\"" + this.id + "-row-"
					+ timeStamp + "\">");
		} else {
			this.getJspWriter().println("<tr id=\"row-" + timeStamp + "\">");
		}
	}
	
	// Begins a table row
	private void beginRow() throws IOException {
		this.getJspWriter().println("<tr>");
	}
	
	// Ends a table row
	private void endRow() throws IOException {
		this.getJspWriter().println("</tr>");
	}
	
	// Begins a table column with a specified timestamp based ID
	private void beginCol(final long timeStamp, final String... classNames)
			throws IOException {
		String classString = formatAttribute(" ", "class", classNames);
		if (this.id != null && this.id.length() > 0) {
			this.getJspWriter().println("<td id=\"" + this.id + "-col-"
					+ timeStamp + "\"" + classString + ">");
		} else {
			this.getJspWriter().println("<td id=\"col-" + timeStamp
					+ "\"" + classString + ">");
		}
	}
	
	// Begins a table column
	private void beginCol(final String... classNames) throws IOException {
		this.getJspWriter().println("<td"
				+ formatAttribute(" ", "class", classNames) + ">");
	}
	
	// Ends a table column
	private void endCol() throws IOException {
		this.getJspWriter().println("</td>");
	}
	
	// Begins a span
	private void writeSpan(final String text, final String... classNames)
			throws IOException {
		this.getJspWriter().println(
				"<span" + formatAttribute(" ", "class", classNames) + ">");
		this.getJspWriter().println(text);
		this.getJspWriter().println("</span>");
	}
	
	// Formats a mark up attribute
	// If the value is empty, return an empty string; otherwise return the
	// prefix, attribute name, an equals ("=") sign and the attribute values
	// space separated and in double quotes.
	private String formatAttribute(final String preWhitespace,
			final String attributeName, final String... attributeValues) {
		if (attributeValues.length > 0) {
			String attributeValue = null;
			for (String val : attributeValues) {
				if (val != null && val.length() > 0) {
					if (attributeValue == null) {
						attributeValue = val;
					} else {
						attributeValue = attributeValue + " " + val;
					}
				}
			}
			if (attributeValue != null) {
				return String.format("%s%s=\"%s\"",
						preWhitespace, attributeName, attributeValue);
			}
		}
		return "";
	}
	
	/* Format dates */
	
	// Formats the specified time using the time pattern
	private String formatTime(final Date date) {
		return format(date, getTimePattern());
	}
	
	// Formats the specified date using the date pattern
	private String formatDate(final Date date) {
		return format(date, getDatePattern());
	}
	
	// Formats the specified date range
	@SuppressWarnings("unused")
	private String formatDateRange(final DateRange dateRange) {
		return String.format("%s - %s",
				format(dateRange.getStartDate(), getDateTimePattern()),
				format(dateRange.getEndDate(), getDateTimePattern()));
	}
	
	// Returns the date pattern if set, otherwise returns default date pattern
	private String getDatePattern() {
		if (this.datePattern != null && this.datePattern.length() > 0) {
			return this.datePattern;
		} else {
			return DEFAULT_DATE_PATTERN;
		}
	}
	
	// Returns the time pattern if set, otherwise returns default time pattern
	private String getTimePattern() {
		if (this.timePattern != null && this.timePattern.length() > 0) {
			return this.timePattern;
		} else {
			return DEFAULT_TIME_PATTERN;
		}
	}
	
	// Returns the date time pattern
	private String getDateTimePattern() {
		return this.getDatePattern() + " " + this.getTimePattern();
	}
	
	// Formats the specified date using the specified pattern
	private String format(final Date date, final String pattern) {
		Locale locale = getJspContext().getELContext().getLocale();
		SimpleDateFormat sdf;
		if (locale != null) {
			sdf = new SimpleDateFormat(pattern, locale);
		} else {
			sdf = new SimpleDateFormat(pattern);
		}
		return sdf.format(date);
	}
	
	/* Manipulate events */
	
	// Returns all the specified events overlapping the specified date range
	private List<Event> getOverlappingDateRange(
			final List<? extends Event> events, final DateRange dateRange) {
		List<Event> results = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getDateRange().overlaps(dateRange)) {
				results.add(event);
			}
		}
		return results;
	}
}