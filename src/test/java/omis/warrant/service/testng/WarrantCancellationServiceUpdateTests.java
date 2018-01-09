package omis.warrant.service.testng;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCancellation;
import omis.warrant.domain.WarrantReasonCategory;
import omis.warrant.service.WarrantCancellationService;
import omis.warrant.service.WarrantService;

/**
 * WarrantCancellationServiceUpdateTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 15, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCancellationServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	private WarrantService warrantService;
	
	@Autowired
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("warrantCancellationService")
	private WarrantCancellationService warrantCancellationService;
	
	@Test
	public void testWarrantCancellationUpdate() throws DuplicateEntityFoundException {
		final Warrant warrant = this.createWarrant();
		final Date date = this.parseDateText("05/15/2017");
		final String comment = "Cancellation Comment";
		final Person clearedBy = this.personDelegate.create("Dent", "Harvey",
				null, null);
		final Person clearedBy0 = this.personDelegate.create("Grayson", "Richard",
				null, null);
		final Date clearedByDate = this.parseDateText("01/01/2017");
		
		WarrantCancellation warrantCancellation = this.warrantCancellationService
				.create(warrant, this.parseDateText("01/11/2011"),
						"Old Comment", clearedBy0, this.parseDateText("02/22/2012"));
		
		warrantCancellation =
				this.warrantCancellationService.update(warrantCancellation,
						date, comment, clearedBy, clearedByDate);
		
		assert warrant.equals(warrantCancellation.getWarrant())
		: String.format("Wrong warrant for warrantCancellation: %d found; "
				+ "%d expected",
				warrantCancellation.getWarrant().getId(), warrant.getId());
		assert date.equals(warrantCancellation.getDate())
		: String.format("Wrong date for warrantCancellation: %s found; %s expected",
				warrantCancellation.getDate(), date);
		assert comment.equals(warrantCancellation.getComment())
		: String.format("Wrong comment for warrantCancellation: %s found; "
				+ "%s expected",
				warrantCancellation.getComment(), comment);
		assert clearedBy.equals(warrantCancellation.getClearSignature().getPerson())
		: String.format("Wrong clearedBy for warrantCancellation: %s found; "
				+ "%s expected",
				warrantCancellation.getClearSignature().getPerson().getName()
				.getFirstName(), clearedBy.getName().getFirstName());
		assert clearedByDate.equals(warrantCancellation.getClearSignature().getDate())
		: String.format("Wrong clearedByDate for warrantCancellation: %s found; "
				+ "%s expected",
				warrantCancellation.getClearSignature().getDate(), clearedByDate);
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
	private Warrant createWarrant() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Date date = this.parseDateText("05/12/2017");
		final Person issuedBy = this.personDelegate.create(
				"Grayson", "Richard", "J", null);
		final String addressee = "Addressed To Someone, Somewhere";
		final WarrantReasonCategory warrantReason = WarrantReasonCategory
				.AUTHORIZATION_TO_PICKUP_AND_HOLD;
		final Boolean bondable = true;
		final BigDecimal bondRecommendation = new BigDecimal("500");
		
		return this.warrantService.create(offender, date, addressee,
				issuedBy, bondable, bondRecommendation, warrantReason);
	}
	
}
