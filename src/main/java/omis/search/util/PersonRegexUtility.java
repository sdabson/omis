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
package omis.search.util;

import java.util.regex.Pattern;

/** 
 * Regular expression utility for person related strings.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.3 (Mar 19, 2019)
 * @since OMIS 3.0
 */
public final class PersonRegexUtility {
	
	private static final String FIRSTLAST = "^[a-zA-Z\\-]+[\\s,]+[a-zA-Z\\-]+$";
	
	private static final String FIRSTMIDDLELAST =
			"^[a-zA-Z\\-]+[\\s,]+[a-zA-Z\\-]+\\s[a-zA-Z\\-]+$";

	private static final String USER = "^[cC][a-zA-Z]*[0-9]+$";
	
	private static final String OFFENDERNUMBER = "^[0-9]+$";
	
	private static final String NAME = "^[a-zA-Z\\-]+(?:,*\\s*)$";
	
	private static final String SSN = "^\\d{3}-?\\d{2}-?\\d{4}$";

	private PersonRegexUtility() { }

	/** Determines if string is an offender number.
	 * @param compare comparison criteria.
	 * @return is an offender number string. */
	public static boolean isOffenderNumber(final String compare) {
		final Pattern offenderNumberPattern = Pattern.compile(OFFENDERNUMBER);

		return offenderNumberPattern.matcher(compare).matches();
	} //isOffendernUMBER


	/** Determines if string is a username.
	 * @param compare comparison criteria.
	 * @return is username string */
	public static boolean isUserName(final String compare) {
		final Pattern userNamePattern = Pattern.compile(USER);

		return userNamePattern.matcher(compare).matches();
	}

	/** Determine if string is a first and last name.
	 * @param compare comparison criteria.
	 * @return is first and last string */
	public static boolean isFirstLast(final String compare) {
		final Pattern firstLastPattern = Pattern.compile(FIRSTLAST);

		return firstLastPattern.matcher(compare).matches();
	}

	/** Determine if  string ins a first middle last.
	 * @param compare comparison criteria.
	 * @return  is first middle last string */
	public static boolean isFirstMiddleLast(final String compare) {
		final Pattern firstMiddleLast = Pattern.compile(FIRSTMIDDLELAST);

		return firstMiddleLast.matcher(compare).matches();
	}

	/** Determine if string is a name.
	 * @param compare comparison criteria.
	 * @return is name string */
	public static boolean isName(final String compare) {
		final Pattern name = Pattern.compile(NAME);

		return name.matcher(compare).matches();
	}
	
	/**
	 * Determine if a string is a SSN.
	 * 
	 * @param compare comparison criteria.
	 * @return is SSN string
	 */
	public static boolean isSsn(final String compare) {
		final Pattern ssnPattern = Pattern.compile(SSN);
		
		return ssnPattern.matcher(compare).matches();
	}
}