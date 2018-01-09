package omis.search.util;

import java.util.regex.Pattern;

/** Regular expression utility for person related strings.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 26, 2013)
 * @since OMIS 3.0 */
public final class PersonRegexUtility {
	private static final String FIRSTLAST = "^[a-zA-Z]+[\\s,]+[a-zA-Z]+$";
	private static final String FIRSTMIDDLELAST =
			"^[a-zA-Z]+[\\s,]+[a-zA-Z]+\\s[a-zA-Z]+$";

	private static final String USER = "^[cC][a-zA-Z]*[0-9]+$";
	private static final String OFFENDERNUMBER = "^[0-9]+$";
	private static final String NAME = "^[a-zA-Z]+$";

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
}
