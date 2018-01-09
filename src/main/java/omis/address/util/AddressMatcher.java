package omis.address.util;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Parses address text.
 * @author Ryan Johns
 * @version 0.1.0 (Aug 17, 2015)
 * @since OMIS 3.0 
 * <h2>Address Fields</h2>
 * 	<table><tbody>
 * 		<tr><td>STREET_NUMBER</td><td>Displays {@link #STREET_NUMBER number of 
 * street.
 * 		<tr><td>
 * 			LINE_ONE</td><td>Displays {@link #LINE_ONE line one} of address.
 * 		</td></tr><tr><td>
 * 			LINE_TWO</td><td>Displays {@link #LINE_TWO line two} of address.
 * 		</td></tr><tr><td>
 * 			PRE_DIRECTION</td><td>Displays 
 * 			{@link #PRE_DIRECTION pre-direction} of street.
 *		</td></tr><tr><td>
 *			POST_DIRECTION</td><td>Displays 
 *			{@link #POST_DIRECTION post-direction} of street.
 *		</td></tr><tr><td>
 *			STREET_NAME</td><td>Displays {@link #STREET_NAME name} of street.
 *		</td></tr><tr><td>
 *			STREET_SUFFIX</td><td>Displays {@link #STREET_SUFFIX} of street.
 *		</td></tr><tr><td>
 *			CITY</td><td>Displays {@link #CITY name} of city.
 *		</td></tr><tr><td>
 *			STATE</td><td>Displays {@link #STATE name} of state.
 *		</td></tr><tr><td>
 *			ZIP</td><td>Displays {@link #ZIP_CODE zip code} of address.
 *		</td></tr>
 * </tbody></table > */
public class AddressMatcher {
	/** Line one of address format.
	 * {@code ##### <street name> <directional> <suffix>}.*/
	public static final String LINE_ONE = "lineOne";
	/** Line two of address format.
	 * {@code <city> <State> <zip>}. */
	public static final String LINE_TWO = "lineTwo";
	/** Street number of address.
	 * {@code #####}. */
	public static final String STREET_NUMBER = "streetNumber";
	/** Pre direction of street.
	 * {@code <preDirectional>}. */
	public static final String PRE_DIRECTION = "preDirection";
	/** Post direction of street.
	 * {@code <postDirectional>}. */
	public static final String POST_DIRECTION = "postDirection";
	/** Street name of address.
	 * {@code <street name>}. */
	public static final String STREET_NAME = "streetName";
	/** Street suffix of address.
	 * {@code <street suffix>}. */
	public static final String STREET_SUFFIX = "streetSuffix";
	/** City of address.
	 * {@code <city>}. */
	public static final String CITY = "city";
	/** State of address.
	 * {@code <state>}. */
	public static final String STATE = "state";
	/** Zip code of address.
	 * {@code <zip>}. */
	public static final String ZIP_CODE = "zipCode";
	
	private static final String ILLEGAL_ARGUMENT_EXCEPTION_MSG = 
			"Argument must be in one of the following: LINE_ONE, LINE_TWO, "
			+ "STREET_NUMBER PRE_DIRECTION, POST_DIRECTION, STREET_NAME, "
			+ "STREET_SUFFIX, CITY STATE, ZIP_CODE";
	private static final String ILLEGAL_STATE_EXCEPTION_MSG = 
			"No Matcher; Make sure that AddressMather.find() was called.";  
	
	

	private static final String STREET_NUMBER_PATTERN = 
			"(?<streetNumber>\\d+\\s??\\d*?/?\\d*?)";
	private static final String DIRECTIONS_PATTERN = 
			"(?:(?:[n](?:orth)?)|" 
			+ "(?:[e](?:ast)?)|"
			+ "(?:[s](?:outh)?)|"
			+ "(?:[w](?:est)?))";
	private static final String DIRECTIONAL_PATTERN = 
				DIRECTIONS_PATTERN + "?+(?:\\s)*" 
						+ DIRECTIONS_PATTERN  + "?+";
	private static final String PRE_DIRECTIONAL_PATTERN = 
			"(?<" + PRE_DIRECTION + ">" + DIRECTIONAL_PATTERN + ")";
	private static final String STREET_NAME_PATTERN = 
			"(?<" + STREET_NAME + ">\\w[\\w\\s]+?)";
	private static final String POST_DIRECTIONAL_PATTERN = 
			"(?<" + POST_DIRECTION + ">" + DIRECTIONAL_PATTERN + ")";
	private static final String CITY_PATTERN = "(?<" + CITY 
			+ ">[[a-z]\\s]+)";
	private static final String STATE_PATTERN = "(?<" + STATE 
			+ ">[a-z]{2,})";
	private static final String ZIPCODE_PATTERN = 
			"(?<" + ZIP_CODE + ">([0-9]{0,5}(?:-?[0-9]{0,4})?)?)";
	
	
	
	private final String streetSuffixPattern;
	private final String addressPattern;
	private final Pattern pattern;
	private Matcher matcher;
	
	/** Constructor. 
	 * @param suffixes - suffixes to check for. */
	public AddressMatcher(final List<String> suffixes) { 
	
		this.streetSuffixPattern = "(?<" + STREET_SUFFIX + ">\\b("
				+ this.captureGroupsFromList(suffixes) + ")?\\b)";
		
		
	 	this.addressPattern = "^"
			+ "(?<" + LINE_ONE + ">"
			+ STREET_NUMBER_PATTERN  + "\\s+"
			+ "(?:" + PRE_DIRECTIONAL_PATTERN  + "\\.?\\s)?"
			+ STREET_NAME_PATTERN + "\\s+"
			+ this.streetSuffixPattern + "\\.?\\s?(?:#?[0-9]*)??\\.?\\s?"
			+ "(?:" + POST_DIRECTIONAL_PATTERN  + "\\.?)??" 
			+ ")(?:" 
			+ "\\s(?<" + LINE_TWO + ">"
			+ CITY_PATTERN + "[\\,\\.]?\\s"
			+ STATE_PATTERN + "[\\,\\.]?\\s"
			+ ZIPCODE_PATTERN
			+ ".*))?"
			+ "$";
	
	 	this.pattern = Pattern.compile(this.addressPattern, 
	 			Pattern.CASE_INSENSITIVE);
		
	} 
	
	/** Runs address string through pattern matcher.
	 * @param addressString - address string. 
	 * @return true if pattern matched address pattern. */
	public Boolean find(final String addressString) {
		this.matcher = this.pattern.matcher(addressString);
		return this.matcher.find();
	}
		
		
	/** Parses field value.
	 * @param addressField - address field to parse can be one of 
	 * {@link #LINE_ONE}, {@link #LINE_TWO},{@link #STREET_NUMBER}, 
	 * {@link #PRE_DIRECTION}, {@link #STREET_NAME}, {@link #STREET_SUFFIX}, 
	 * {@link #POST_DIRECTION}, {@link #CITY}, {@link #STATE}, {@link ZIP_CODE}.
	 * @return field value, if address string */
	public String parse(final String addressField) {
		final String result;
		if (this.matcher != null) {
			if (this.checkFieldParameter(addressField)) {
				String temp = this.matcher.group(addressField);
				if (temp != null) {
					result = temp;
				} else {
					result = "";
				}
			} else {
				throw new IllegalArgumentException(
						ILLEGAL_ARGUMENT_EXCEPTION_MSG);
			}
		} else {
			throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION_MSG);
		}
		return result;
	}
	
	/* Checks that parameter is in expected fields. */
	private boolean checkFieldParameter(final String fieldParameter) {
		return (AddressMatcher.LINE_ONE.equals(fieldParameter)
				|| AddressMatcher.LINE_TWO.equals(fieldParameter)
				|| AddressMatcher.STREET_NUMBER.equals(fieldParameter)
				|| AddressMatcher.PRE_DIRECTION.equals(fieldParameter)
				|| AddressMatcher.STREET_NAME.equals(fieldParameter)
				|| AddressMatcher.STREET_SUFFIX.equals(fieldParameter)
				|| AddressMatcher.POST_DIRECTION.equals(fieldParameter)
				|| AddressMatcher.CITY.equals(fieldParameter)
				|| AddressMatcher.STATE.equals(fieldParameter)
				|| AddressMatcher.ZIP_CODE.equals(fieldParameter));
	}
	
	/* Retrieves capture groups from list of strings. */
	private String captureGroupsFromList(final List<String> strings) {
		StringBuffer sb = new StringBuffer();
		Iterator<String> i = strings.iterator();
		while (i.hasNext()) {
			sb.append("(?:").append(i.next()).append(")");
			if (i.hasNext()) {
				sb.append("|");
			}
		}
		return sb.toString();
	}
	
}
