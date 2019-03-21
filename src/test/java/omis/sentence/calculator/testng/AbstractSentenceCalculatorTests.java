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
package omis.sentence.calculator.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstract class for testing sentence calculator.
 *
 * <p>This class is package private to prevent use outside of this testing
 * module.
 *
 * @author Stephen Abson
 * @version 0.1.0 (March 14, 2019)
 */
abstract class AbstractSentenceCalculatorTests {

	// Note that this test is not meant to be configured - custom date parsing
	// and formatting is therefore necessary. Configured unit tests should use
	// a date parser that uses the application's custom date editor (at the
	// time of writing, this is DateUtility - switch to this if this test is
	// ever configured) - SA
	private static final String DATE_FORMAT = "MM/dd/yyyy";
	
	
	/**
	 * Parses date text in {@code MM/dd/yyyy} format.
	 * 
	 * <p>This is only intended for use outside of a managed context. In managed
	 * contexts, use DateUtility instead.
	 * 
	 * @param dateText date text
	 * @return parsed date
	 */
	protected Date parseDate(final String dateText) {
		try {
			
			// See comment for DATE_FORMAT constant above
			return new SimpleDateFormat(DATE_FORMAT).parse(dateText);
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					String.format("Date %s not in %s format",
							dateText, DATE_FORMAT));
		}
	}
	
	/**
	 * Formats date in {@code MM/dd/yyyy}.
	 * 
	 * <p>This is only intended for use outside of a managed context. In managed
	 * contexts, use DateUtility instead.
	 * 
	 * @param date date
	 * @return date as formatted text
	 */
	protected String formatDate(final Date date) {
		
		// See comment for DATE_FORMAT constant above
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}
}