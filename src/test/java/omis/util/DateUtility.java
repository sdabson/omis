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

/**
 * Date utility.
 * 
 * <p>Used to handle dates in application configured format. Format for
 * application is configured in property editor for dates.
 * 
 * @see DateRangeUtility
 * @author Stephen Abson
 * @version 0.0.1 (Aug 17, 2018)
 * @since OMIS 3.0
 */
public class DateUtility {

	private final CustomDateEditorFactory customDateEditorFactory;
	
	/**
	 * Instantiates date utility.
	 * 
	 * @param customDateEditorFactory factory for editors used to convert dates
	 */
	public DateUtility(
			final CustomDateEditorFactory customDateEditorFactory) {
		this.customDateEditorFactory = customDateEditorFactory;
	}
	
	/**
	 * Parses date text.
	 * 
	 * @param dateText date text to parse
	 * @return parsed date
	 */
	public Date parseDateText(final String dateText) {
		CustomDateEditor dateEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		dateEditor.setAsText(dateText);
		return (Date) dateEditor.getValue();
	}
	
	/**
	 * Parses time text.
	 * 
	 * @param timeText time text to parse
	 * @return parsed time
	 */
	public Date parseTimeText(final String timeText) {
		CustomDateEditor dateEditor = this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true);
		dateEditor.setAsText(timeText);
		return (Date) dateEditor.getValue();
	}
	
	/**
	 * Parses date time text.
	 * 
	 * @param dateTimeText date time text to parse
	 * @return parsed date time
	 */
	public Date parseDateTimeText(final String dateTimeText) {
		CustomDateEditor dateEditor = this.customDateEditorFactory
				.createCustomDateTimeEditor(true);
		dateEditor.setAsText(dateTimeText);
		return (Date) dateEditor.getValue();
	}
}