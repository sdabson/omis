package omis.address.util;

import omis.address.domain.Address;

/** Formats address text from address. 
 * <h2>Address Formats</h2>
 *  <table><tbody>
 * 		<tr><td>
 * 			FULL_ADDRESS</td><td>Displays {@link #FULL_ADDRESS full} address.
 * 		</td></tr><tr><td>
 * 			FULL_ADDRESS_ONE_LINE</td><td>Displays 
 * 			{@link #FULL_ADDRESS_ONE_LINE full on one line} address.
 * 		</td></tr><tr><td>
 * 			LINE_ONE</td><td>Displays {@link #LINE_ONE line one} of address.
 * 		</td></tr><tr><td>
 * 			LINE_TWO</td><td>Displays {@link #LINE_TWO line two} of address.
 * 		</td></tr>
 * 	</tbody></table> 
 * @author Ryan Johns
 * @version 0.1.0 (Jul 15, 2015)
 * @since OMIS 3.0 */
public class AddressFormat {
	/** Full address format. 
	 * <div>{@code ####<street name> <directional> <suffix>}</div>
	 * <div>{@code <City> <State> <zip>}</div> */
	public static final String FULL_ADDRESS = "fullAddress";
	
	/** Full address on one line format.
	 * {@code ##### <street name> <directional> <suffix>, <City> <State> <zip>}.
	 */
	public static final String FULL_ADDRESS_ONE_LINE = "fullAddressOneLine";
	
	/** Line one of address format.
	 * {@code ##### <street name> <directional> <suffix>}.*/
	public static final String LINE_ONE = "lineOne";
	
	/** Line two of address format.
	 * {@code <city> <State> <zip>}. */
	public static final String LINE_TWO = "lineTwo";
	
	private static final String ILLEGAL_ARGUMENT_EXCEPTION_MSG = 
			"Supplied argument must be a valid PATTERN";
	
	/** Formats address into string.
	 * @param address - address.
	 * @param format - format type including {@link #FULL_ADDRESS FULL_ADDRESS},
	 * {@link FULL_ADDRESS_ONE_LINE FULL_ADDRESS_ONE_LINE}, 
	 * {@link LINE_ONE LINE_ONE}, {@link LINE_TWO LINE_TWO}.
	 * @return address string. */
	public String format(final Address address, 
			final String format) {
		final StringBuilder resultBuilder = new StringBuilder();
		
		if (format.equals(FULL_ADDRESS)) {
			this.buildLineOne(address.getValue(), resultBuilder);
			
			resultBuilder.append(".");
			resultBuilder.append("\r\n");
			
			this.buildLineTwo(address.getZipCode().getCity().getName(),
					address.getZipCode().getCity().getState().getAbbreviation(),
					address.getZipCode().getValue(), resultBuilder);
		} else if (format.equals(FULL_ADDRESS_ONE_LINE)) {
			this.buildLineOne(address.getValue(), resultBuilder);
			resultBuilder.append(", ");
			this.buildLineTwo(address.getZipCode().getCity().getName(),
					address.getZipCode().getCity().getState().getAbbreviation(),
					address.getZipCode().getValue(), resultBuilder);
		} else if (format.equals(LINE_ONE)) {
			this.buildLineOne(address.getValue(), resultBuilder);
		} else if (format.equals(LINE_TWO)) {
			this.buildLineTwo(address.getZipCode().getCity().getName(),
					address.getZipCode().getCity().getState().getAbbreviation(),
					address.getZipCode().getValue(), resultBuilder);
		} else {
			throw new IllegalArgumentException(
					ILLEGAL_ARGUMENT_EXCEPTION_MSG);
		}
		return resultBuilder.toString();
	}
	
	/* appends first line.
	 * @param streetNumber - street number.
	 * @param addressDesignator - address designator.
	 * @param streetName - street name.
	 * @param streetSuffix - street suffix.
	 * @param stringBuilder - string builder. */
	private void buildLineOne(final String value,
			final StringBuilder stringBuilder) {
		stringBuilder.append(value);
	}
	
	/* appends line two.
	 * @param cityName - city name.
	 * @param stateName - state name.
	 * @param zipCode - zip code.
	 * @param stringBuilder - string builder. */
	private void buildLineTwo(final String cityName, final String stateName,
			final String zipCode,
			final StringBuilder stringBuilder) {
		stringBuilder.append(cityName);
		stringBuilder.append(" ");
		stringBuilder.append(stateName);
		stringBuilder.append(" ");
		stringBuilder.append(zipCode);
	}
	
}
