/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.util;

import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;

/**
 * Date range utility.
 * 
 * <p>Used to handle date ranges in application configured format. Format for
 * application is configured in property editor for dates.
 * 
 * @see DateUtility
 * @author Stephen Abson
 * @version 0.0.1 (Aug 17, 2018)
 * @since OMIS 3.0
 */
public class DateRangeUtility {

	private final CustomDateEditorFactory customDateEditorFactory;
	
	/**
	 * Instantiates date range utility.
	 * 
	 * @param customDateEditorFactory factory for editors used to convert
	 * dates in date ranges
	 */
	public DateRangeUtility(
			final CustomDateEditorFactory customDateEditorFactory) {
		this.customDateEditorFactory = customDateEditorFactory;
	}
	
	/**
	 * Returns parsed date texts as date range.
	 * 
	 * @param startDateText start date text
	 * @param endDateText end date text
	 * @return parsed date texts
	 */
	public DateRange parseDateTexts(
			final String startDateText, final String endDateText) {
		return new DateRange(
				this.parseDateText(startDateText),
				this.parseDateText(endDateText));
	}
	
	/**
	 * Returns parsed time texts as date range.
	 * 
	 * @param startTimeText start time text
	 * @param endTimeText end time text
	 * @return parsed time texts
	 */
	public DateRange parseTimeTexts(
			final String startTimeText, final String endTimeText) {
		return new DateRange(
				this.parseTimeText(startTimeText),
				this.parseDateTimeText(endTimeText));
	}
	
	/**
	 * Returns parsed date time texts as date range.
	 * 
	 * @param startDateTimeText start date time text
	 * @param endDateTimeText end date time text
	 * @return parsed date time texts
	 */
	public DateRange parseDateTimeTexts(
			final String startDateTimeText,
			final String endDateTimeText) {
		return new DateRange(
				this.parseDateText(startDateTimeText),
				this.parseDateText(endDateTimeText));
	}
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		CustomDateEditor dateEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		dateEditor.setAsText(dateText);
		return (Date) dateEditor.getValue();
	}
	
	// Parses time text
	private Date parseTimeText(final String timeText) {
		CustomDateEditor dateEditor = this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true);
		dateEditor.setAsText(timeText);
		return (Date) dateEditor.getValue();
	}
	
	// Parses date time text
	private Date parseDateTimeText(final String dateTimeText) {
		CustomDateEditor dateEditor = this.customDateEditorFactory
				.createCustomDateTimeEditor(true);
		dateEditor.setAsText(dateTimeText);
		return (Date) dateEditor.getValue();
	}
}