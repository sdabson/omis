package address.util.testing;

import java.util.Arrays;

import omis.address.util.AddressMatcher;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/** Tests address fields parser.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 16, 2015)
 * @since OMIS 3.0 */
@Test(groups = {"address"})
public class AddressMatcherTests {
	private static final String[] SUFFIXES = {"DR", "ST", "RD", "DRIVE", 
		"LOOP", "ROAD", "STREET", "BOULEVARD", "AVE"}; 
	private AddressMatcher af1;
	private AddressMatcher af2;
	private AddressMatcher af3;
	private AddressMatcher af4;
	private AddressMatcher af5;
	private AddressMatcher af6;
	private AddressMatcher af7;
	private AddressMatcher af8;
	private AddressMatcher af9;
	private AddressMatcher af10;
	private AddressMatcher af11;
	private AddressMatcher af12;
	private AddressMatcher af13;
	
	/** Sets up test matchers. */
	@BeforeClass
	public void setup() {
		
		this.af1 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af2 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af3 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af4 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af5 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af6 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af7 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af8 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af9 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af10 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af11 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af12 = new AddressMatcher(Arrays.asList(SUFFIXES));
		this.af13 = new AddressMatcher(Arrays.asList(SUFFIXES));
		
	}
	
	/** Tests illegal state exception when no address was provided. */
	@Test(expectedExceptions = IllegalStateException.class)
	public void testAddressMatcherParseIllegalStateException() {
		this.af13.parse(AddressMatcher.CITY);
	}
	
	/** Tests pattern matchers ability to find addresses. */
	@Test(dependsOnMethods = "testAddressMatcherParseIllegalStateException")
	public void testAddressMatcherFind() {
		assert this.af1.find("222 N. Fairview Ave.");
		assert this.af2.find("2401 Buckboard Dr.");
		assert this.af3.find("101 E. Arbuckle St. Helena Mt, 59783");
		assert !this.af4.find("bcn S East Walker Rd.");
		assert this.af5.find("45 W. timothy Drive East.");
		assert this.af6.find("243 East South Glacier Point Loop");
		assert this.af7.find("880 Kempsville Road #2200 Norfolk, VA 23502");
		assert this.af8.find("150 Reynoir Street Biloxi, MS 39530");
		assert this.af9.find("4401 Harrison Boulevard Ogden, UT 84403-5360");
		assert this.af10.find("400 Conely Lake ROAD ABSAROKEE, MT 59001");
		assert this.af11.find("400 Conely Lake ROAD");
		assert this.af12.find("40 cnley lk RD absrkee montana 591");
		assert this.af13.find("121 1/2 Washington ST. Helena MT 59635");
	}
	
	/** Test illegal arguments for parse. */
	@Test(dependsOnMethods = "testAddressMatcherFind",
		  expectedExceptions = IllegalArgumentException.class) 
	public void testAddressMatcherParseIllegalArgumentException() {
		this.af9.parse("polkaj;dkl;");
	}
	
	/** Tests street number. */
	@Test(dependsOnMethods = "testAddressMatcherFind")
	public void testAddressMatcherStreetNumber() {
		assert "222".equals(this.af1.parse(AddressMatcher.STREET_NUMBER));
		assert "2401".equals(this.af2.parse(AddressMatcher.STREET_NUMBER));
		assert "101".equals(this.af3.parse(AddressMatcher.STREET_NUMBER));
		assert "243".equals(this.af6.parse(AddressMatcher.STREET_NUMBER));
		assert "45".equals(this.af5.parse(AddressMatcher.STREET_NUMBER));
		assert "880".equals(this.af7.parse(AddressMatcher.STREET_NUMBER));
		assert "150".equals(this.af8.parse(AddressMatcher.STREET_NUMBER));
		assert "4401".equals(this.af9.parse(AddressMatcher.STREET_NUMBER));
		assert "400".equals(this.af10.parse(AddressMatcher.STREET_NUMBER));
		assert "400".equals(this.af11.parse(AddressMatcher.STREET_NUMBER));
		assert "40".equals(this.af12.parse(AddressMatcher.STREET_NUMBER));
		assert "121 1/2".equals(this.af13.parse(
				AddressMatcher.STREET_NUMBER));
	}
	
	/** Tests line one. */
	@Test(dependsOnMethods = "testAddressMatcherFind")
	public void testAddressMatcherLineOne() {
		assert "222 N. Fairview Ave.".equals(this.af1.parse(
				AddressMatcher.LINE_ONE));
		assert "2401 Buckboard Dr.".equals(this.af2.parse(
				AddressMatcher.LINE_ONE));
		assert "101 E. Arbuckle St.".equals(this.af3.parse(
				AddressMatcher.LINE_ONE));
		assert "45 W. timothy Drive East.".equals(
				this.af5.parse(AddressMatcher.LINE_ONE));
		assert "243 East South Glacier Point Loop".equals(
				this.af6.parse(AddressMatcher.LINE_ONE));
		assert "880 Kempsville Road #2200".equals(
				this.af7.parse(AddressMatcher.LINE_ONE));
		assert "150 Reynoir Street".equals(this.af8.parse(
				AddressMatcher.LINE_ONE));
		assert "4401 Harrison Boulevard".equals(
				this.af9.parse(AddressMatcher.LINE_ONE));
		assert "400 Conely Lake ROAD".equals(this.af10.parse(
				AddressMatcher.LINE_ONE));
		assert "400 Conely Lake ROAD".equals(this.af11.parse(
				AddressMatcher.LINE_ONE));
		assert "40 cnley lk RD".equals(this.af12.parse(
				AddressMatcher.LINE_ONE));
		assert "121 1/2 Washington ST.".equals(this.af13.parse(
				AddressMatcher.LINE_ONE));
		
	}
	
	/** Tests line two. */
	@Test(dependsOnMethods = "testAddressMatcherFind")
	public void testAddressMatcherLineTwo() {
		assert "".equals(this.af1.parse(AddressMatcher.LINE_TWO));
		assert "".equals(this.af2.parse(AddressMatcher.LINE_TWO));
		assert "Helena Mt, 59783".equals(this.af3.parse(
				AddressMatcher.LINE_TWO));
		assert "".equals(this.af5.parse(AddressMatcher.LINE_TWO));
		assert "".equals(this.af6.parse(AddressMatcher.LINE_TWO));
		assert "Norfolk, VA 23502".equals(this.af7.parse(
				AddressMatcher.LINE_TWO));
		assert "Biloxi, MS 39530".equals(this.af8.parse(
				AddressMatcher.LINE_TWO));	
		assert "Ogden, UT 84403-5360".equals(
				this.af9.parse(AddressMatcher.LINE_TWO));
		assert "ABSAROKEE, MT 59001".equals(this.af10.parse(
				AddressMatcher.LINE_TWO));
		assert "".equals(this.af11.parse(AddressMatcher.LINE_TWO));
		assert "absrkee montana 591".equals(this.af12.parse(
				AddressMatcher.LINE_TWO));
	}
	
	/** tests directional. */
	@Test(dependsOnMethods = "testAddressMatcherFind")
	public void testAddressMatcherDirectional() {
		assert "N".equals(this.af1.parse(AddressMatcher.PRE_DIRECTION));
		assert "".equals(this.af2.parse(AddressMatcher.PRE_DIRECTION));
		assert "E".equals(this.af3.parse(AddressMatcher.PRE_DIRECTION));
		assert "East South".equals(this.af6.parse(
				AddressMatcher.PRE_DIRECTION));
		assert "W".equals(this.af5.parse(AddressMatcher.PRE_DIRECTION));
		assert "".equals(this.af1.parse(AddressMatcher.POST_DIRECTION));
		assert "".equals(this.af2.parse(AddressMatcher.POST_DIRECTION));
		assert "".equals(this.af3.parse(AddressMatcher.POST_DIRECTION));
		assert "".equals(this.af6.parse(AddressMatcher.POST_DIRECTION));
		assert "East".equals(this.af5.parse(
				AddressMatcher.POST_DIRECTION));
		assert "".equals(this.af7.parse(AddressMatcher.PRE_DIRECTION));
		assert "".equals(this.af7.parse(AddressMatcher.POST_DIRECTION));
		assert "".equals(this.af8.parse(AddressMatcher.PRE_DIRECTION));
		assert "".equals(this.af8.parse(AddressMatcher.POST_DIRECTION));
		assert "".equals(this.af9.parse(AddressMatcher.PRE_DIRECTION));
		assert "".equals(this.af9.parse(AddressMatcher.POST_DIRECTION));
		assert "".equals(this.af10.parse(AddressMatcher.PRE_DIRECTION));
		assert "".equals(this.af10.parse(AddressMatcher.POST_DIRECTION));
		assert "".equals(this.af11.parse(AddressMatcher.PRE_DIRECTION));
		assert "".equals(this.af11.parse(AddressMatcher.POST_DIRECTION));
		assert "".equals(this.af12.parse(AddressMatcher.PRE_DIRECTION));
		assert "".equals(this.af12.parse(AddressMatcher.POST_DIRECTION));
	}
	
	/** tests string name. */
	@Test(dependsOnMethods = "testAddressMatcherFind")
	public void testStreetName() {
		assert "Fairview".equals(this.af1.parse(
				AddressMatcher.STREET_NAME));
		assert "Buckboard".equals(this.af2.parse(
				AddressMatcher.STREET_NAME));
		assert "Arbuckle".equals(this.af3.parse(
				AddressMatcher.STREET_NAME));
		assert "Glacier Point".equals(this.af6.parse(
				AddressMatcher.STREET_NAME));
		assert "timothy".equals(this.af5.parse(
				AddressMatcher.STREET_NAME));
		assert "Kempsville".equals(this.af7.parse(
				AddressMatcher.STREET_NAME));
		assert "Reynoir".equals(this.af8.parse(
				AddressMatcher.STREET_NAME));
		assert "Harrison".equals(this.af9.parse(
				AddressMatcher.STREET_NAME));
		assert "Conely Lake".equals(this.af10.parse(
				AddressMatcher.STREET_NAME));
		assert "Conely Lake".equals(this.af11.parse(
				AddressMatcher.STREET_NAME));
		assert "cnley lk".equals(this.af12.parse(
				AddressMatcher.STREET_NAME));
	}
		
	/** Tests street suffix. */
	@Test(dependsOnMethods = "testAddressMatcherFind")
	public void testAddressMatcherStreetSuffix() {
		assert "Ave".equals(this.af1.parse(AddressMatcher.STREET_SUFFIX));
		assert "Dr".equals(this.af2.parse(AddressMatcher.STREET_SUFFIX));
		assert "St".equals(this.af3.parse(AddressMatcher.STREET_SUFFIX));
		assert "Loop".equals(this.af6.parse(AddressMatcher.STREET_SUFFIX));
		assert "Drive".equals(this.af5.parse(AddressMatcher.STREET_SUFFIX));
		assert "Road".equals(this.af7.parse(AddressMatcher.STREET_SUFFIX));
		assert "Street".equals(this.af8.parse(
				AddressMatcher.STREET_SUFFIX));
		assert "Boulevard".equals(this.af9.parse(
				AddressMatcher.STREET_SUFFIX));
		assert "ROAD".equals(this.af10.parse(AddressMatcher.STREET_SUFFIX));
		assert "ROAD".equals(this.af11.parse(AddressMatcher.STREET_SUFFIX));
		assert "RD".equals(this.af12.parse(AddressMatcher.STREET_SUFFIX));
	}
	
	/** Test city. */
	@Test(dependsOnMethods = "testAddressMatcherFind")
	public void testAddressMatcherCity() {
		assert "".equals(this.af1.parse(AddressMatcher.CITY));
		assert "".equals(this.af2.parse(AddressMatcher.CITY));
		assert "Helena".equals(this.af3.parse(AddressMatcher.CITY));
		assert "".equals(this.af5.parse(AddressMatcher.CITY));
		assert "".equals(this.af6.parse(AddressMatcher.CITY));
		assert "Norfolk".equals(this.af7.parse(AddressMatcher.CITY));
		assert "Biloxi".equals(this.af8.parse(AddressMatcher.CITY));
		assert "Ogden".equals(this.af9.parse(AddressMatcher.CITY));
		assert "ABSAROKEE".equals(this.af10.parse(AddressMatcher.CITY));
		assert "".equals(this.af11.parse(AddressMatcher.CITY));
		assert "absrkee".equals(this.af12.parse(AddressMatcher.CITY));
	}
	
	/** Tests state. */
	@Test(dependsOnMethods = "testAddressMatcherFind")
	public void testAddressMatcherState() {
		assert "".equals(this.af1.parse(AddressMatcher.STATE));
		assert "".equals(this.af2.parse(AddressMatcher.STATE));
		assert "Mt".equals(this.af3.parse(AddressMatcher.STATE));
		assert "".equals(this.af5.parse(AddressMatcher.STATE));
		assert "".equals(this.af6.parse(AddressMatcher.STATE));
		assert "VA".equals(this.af7.parse(AddressMatcher.STATE));
		assert "MS".equals(this.af8.parse(AddressMatcher.STATE));
		assert "UT".equals(this.af9.parse(AddressMatcher.STATE));
		assert "MT".equals(this.af10.parse(AddressMatcher.STATE));
		assert "".equals(this.af11.parse(AddressMatcher.STATE));
		assert "montana".equals(this.af12.parse(AddressMatcher.STATE));
	}

	/** Tests zip code. */
	@Test(dependsOnMethods = "testAddressMatcherFind")
	public void testAddressMatcherZipCode() {
		assert "".equals(this.af1.parse(AddressMatcher.ZIP_CODE));
		assert "".equals(this.af2.parse(AddressMatcher.ZIP_CODE));
		assert "59783".equals(this.af3.parse(AddressMatcher.ZIP_CODE));
		assert "".equals(this.af5.parse(AddressMatcher.ZIP_CODE));
		assert "".equals(this.af6.parse(AddressMatcher.ZIP_CODE));
		assert "23502".equals(this.af7.parse(AddressMatcher.ZIP_CODE));
		assert "39530".equals(this.af8.parse(AddressMatcher.ZIP_CODE));
		assert "84403-5360".equals(this.af9.parse(AddressMatcher.ZIP_CODE));
		assert "59001".equals(this.af10.parse(AddressMatcher.ZIP_CODE));
		assert "".equals(this.af11.parse(AddressMatcher.ZIP_CODE));
		assert "591".equals(this.af12.parse(AddressMatcher.ZIP_CODE));
	}
	
	/** Prints parser results. 
	 * @param parser - address matcher. */
	protected void printAddress(final AddressMatcher parser) {
		System.out.println("streetNumber=" + parser.parse(
				AddressMatcher.STREET_NUMBER));
		System.out.println("preDirection=" + parser.parse(
				AddressMatcher.PRE_DIRECTION));
		System.out.println("streetName=" + parser.parse(
				AddressMatcher.STREET_NAME));
		System.out.println("streetSuffix=" + parser.parse(
				AddressMatcher.STREET_SUFFIX));
		System.out.println("postDirection=" + parser.parse(
				AddressMatcher.POST_DIRECTION));
		System.out.println("city=" + parser.parse(AddressMatcher.CITY));
		System.out.println("state=" + parser.parse(AddressMatcher.STATE));
		System.out.println("zipCode=" + parser.parse(
				AddressMatcher.ZIP_CODE) + "\n");
	}
}
