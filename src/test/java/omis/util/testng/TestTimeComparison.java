package omis.util.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import omis.util.TimeComparison;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Test time comparison.
 * @author Ryan Johns.
 * @version 0.1.0 (Jan 15, 2015)
 * @since OMIS 3.0 */
@Test
public class TestTimeComparison {
	private static final String ASSERTIONFAILED = 
			"%1$tm/%1$td/%1$tY -> %2$tm/%2$td/%2$tY";
	private static final long YEARSATOB = (long) 27;
	private static final long YEARSCTOB = (long) 34;
	private static final long YEARSDTOA = (long) 46;
	private static final long YEARSBTOA = (long) -27;
	private static final long YEARSCTOA = (long) 7;
	private static final long YEARSBTOE = (long) 0;
	
	private static final long MONTHSATOB = (long) 324;
	private static final long MONTHSCTOB = (long) 410;
	private static final long MONTHSCTOA = (long) 86;
	private static final long MONTHSBTOE = (long) 0;
	private static final long MONTHSDTOE = (long) 882;
	
	private static final long DAYSATOB = (long) 9875;
	private static final long DAYSCTOB = (long) 12501;
	private static final long DAYSDTOC = (long) 14363;
	private static final long DAYSBTOE = (long) 1;
	
	 
	private final SimpleDateFormat formatter = 
			new SimpleDateFormat("MM/dd/yyyy");
	
	private final Date a;
	private final Date b;
	private final Date c;
	private final Date d;
	private final Date e;
	
	/** Constructor.
	 * @throws ParseException - When date string is invalid. */
	public TestTimeComparison() throws ParseException {
		this.a = this.formatter.parse("1/1/1989");
		this.b = this.formatter.parse("1/15/2016");
		this.c = this.formatter.parse("10/24/1981");
		this.d = this.formatter.parse("6/28/1942");
		this.e = this.formatter.parse("1/16/2016");
	}
	
	/** Tests year comparison. */
	@Test
	public void testElapsedYears() {
		Assert.assertEquals(TimeComparison.elapsedYears(this.a, this.b),
				YEARSATOB, String.format(ASSERTIONFAILED, this.a, this.b));
		Assert.assertEquals(TimeComparison.elapsedYears(this.c, this.b),
				YEARSCTOB, String.format(ASSERTIONFAILED, this.c, this.b));
		Assert.assertEquals(TimeComparison.elapsedYears(this.d, this.a),
				YEARSDTOA, String.format(ASSERTIONFAILED, this.d, this.a));
		Assert.assertEquals(TimeComparison.elapsedYears(this.b, this.a),
				YEARSBTOA, String.format(ASSERTIONFAILED, this.b, this.a));
		Assert.assertEquals(TimeComparison.elapsedYears(this.c, this.a),
				YEARSCTOA, String.format(ASSERTIONFAILED, this.c, this.a));
		Assert.assertEquals(TimeComparison.elapsedYears(this.b, this.e),
				YEARSBTOE, String.format(ASSERTIONFAILED, this.b, this.e));
	}
	
	/** Test months elapsed. */
	@Test
	public void testElapsedMonths() {
		Assert.assertEquals(TimeComparison.elapsedMonths(this.a, this.b), 
				MONTHSATOB, String.format(ASSERTIONFAILED, this.a, this.b));
		Assert.assertEquals(TimeComparison.elapsedMonths(this.c, this.b), 
				MONTHSCTOB, String.format(ASSERTIONFAILED, this.c, this.b));
		Assert.assertEquals(TimeComparison.elapsedMonths(this.c, this.a), 
				MONTHSCTOA, String.format(ASSERTIONFAILED, this.c, this.a));
		Assert.assertEquals(TimeComparison.elapsedMonths(this.b, this.e), 
				MONTHSBTOE, String.format(ASSERTIONFAILED, this.b, this.e));
		Assert.assertEquals(TimeComparison.elapsedMonths(this.d, this.e), 
				MONTHSDTOE, String.format(ASSERTIONFAILED, this.d, this.e));
	}
	
	/** Test days elapsed. */
	@Test
	public void testElapsedDays() {
		Assert.assertEquals(TimeComparison.elapsedDays(this.a, this.b), 
				DAYSATOB, String.format(ASSERTIONFAILED, this.a, this.b));
		Assert.assertEquals(TimeComparison.elapsedDays(this.c, this.b),
				DAYSCTOB, String.format(ASSERTIONFAILED, this.c, this.b));
		Assert.assertEquals(TimeComparison.elapsedDays(this.d, this.c),
				DAYSDTOC, String.format(ASSERTIONFAILED, this.d, this.c));
		Assert.assertEquals(TimeComparison.elapsedDays(this.b, this.e),
				DAYSBTOE, String.format(ASSERTIONFAILED, this.b, 
						this.e));
	}
}
