package address.util.testing;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.domain.impl.AddressImpl;
import omis.address.domain.impl.ZipCodeImpl;
import omis.address.util.AddressFormat;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.domain.impl.CityImpl;
import omis.region.domain.impl.StateImpl;

/** Tests address format.
 * @author Ryan Johns
 * @version 0.1.0 (Aug 20, 2015)
 * @since OMIS 3.0 */
@Test(groups = {"address"})
public class AddressFormatTests {
	private Address a1;
	private Address a2;
	private AddressFormat addressFormat; 
	
	/** Set up addresses for testing. */
	@BeforeTest
	public void setUp() {
		this.a1 = new AddressImpl();
		this.a1.setValue("400 Conely Lake Rd");
		this.a1.setDesignator("North");
		ZipCode zc = new ZipCodeImpl();
		zc.setValue("59001");
		City city = new CityImpl();
		city.setName("ABSAROKEE");
		State state = new StateImpl();
		state.setName("Montana");
		state.setAbbreviation("MT");
		city.setState(state);
		zc.setCity(city);
		this.a1.setZipCode(zc);
		this.a2 = new AddressImpl();
		this.a2.setValue("121 1/2 Washington St");
		ZipCode zc2 = new ZipCodeImpl();
		zc2.setValue("59635");
		City city2 = new CityImpl();
		city2.setName("East Helena");
		city2.setState(state);
		zc2.setCity(city2);
		this.a2.setZipCode(zc2);
		
		this.addressFormat = new AddressFormat();
	}
	
	/** Tests full address. */
	@Test
	public void testFullAddress() {
		assert "400 Conely Lake Rd.\r\nABSAROKEE MT 59001".equals(
				this.addressFormat.format(this.a1, 
						AddressFormat.FULL_ADDRESS));
		assert "121 1/2 Washington St.\r\nEast Helena MT 59635".equals(
				this.addressFormat.format(this.a2, 
						AddressFormat.FULL_ADDRESS)); 
		
	}
	
	/** Test full address one line. */
	@Test
	public void testFullAddressOneLine() {
		assert "400 Conely Lake Rd, ABSAROKEE MT 59001".equals(
				this.addressFormat.format(this.a1, 
						AddressFormat.FULL_ADDRESS_ONE_LINE));
		assert "121 1/2 Washington St, East Helena MT 59635".equals(
				this.addressFormat.format(this.a2, 
						AddressFormat.FULL_ADDRESS_ONE_LINE));
		
	}
	
	/** Test address line one. */
	@Test
	public void testLineOne() {
		assert "400 Conely Lake Rd".equals(
				this.addressFormat.format(this.a1, AddressFormat.LINE_ONE));
		assert "121 1/2 Washington St".equals(
				this.addressFormat.format(this.a2, AddressFormat.LINE_ONE));
		
	}
	
	/** Test address line two. */
	@Test
	public void testLineTwo() {
		assert "ABSAROKEE MT 59001".equals(
				this.addressFormat.format(this.a1, AddressFormat.LINE_TWO));
		assert "East Helena MT 59635".equals(
				this.addressFormat.format(this.a2, AddressFormat.LINE_TWO));
		
	}
	
	/** Test invalid format exception. */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testException() {
		this.addressFormat.format(this.a1, "noper");
	}
	
	
}
