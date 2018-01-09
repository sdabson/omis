package omis.military.service.testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.military.domain.MilitaryBranch;
import omis.military.domain.MilitaryDischargeStatus;
import omis.military.domain.MilitaryServiceTerm;
import omis.military.service.MilitaryServiceTermService;
import omis.military.service.delegate.MilitaryBranchDelegate;
import omis.military.service.delegate.MilitaryDischargeStatusDelegate;
import omis.military.service.delegate.MilitaryServiceTermDelegate;
import omis.military.service.delegate.MilitaryServiceTermNoteDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

	/**
	 * Tests for removing military service terms using military service term 
	 * service.
	 *
	 * @author Trevor Isles
	 * @version 0.0.1 (Jul 13, 2016)
	 * @since OMIS 3.0
	 */
	@Test(groups = {"military"})
	public class MilitaryServiceTermRemoveTests
			extends AbstractHibernateTransactionalTestNGSpringContextTests {
		
		/* Service delegates. */
		@Autowired
		@Qualifier("offenderDelegate")
		private OffenderDelegate offenderDelegate;
		
		@Autowired
		@Qualifier("militaryBranchDelegate")
		private MilitaryBranchDelegate militaryBranchDelegate;
		
		@Autowired
		@Qualifier("militaryDischargeStatusDelegate")
		private MilitaryDischargeStatusDelegate militaryDischargeStatusDelegate;
		
		@Autowired
		@Qualifier("militaryServiceTermDelegate")
		private MilitaryServiceTermDelegate militaryServiceTermDelegate;
		
		@Autowired
		@Qualifier("militaryServiceTermNoteDelegate")
		private MilitaryServiceTermNoteDelegate militaryServiceTermNoteDelegate;
						
		/* Service to test.*/
		
		@Autowired
		@Qualifier("militaryServiceTermService")
		private MilitaryServiceTermService militaryServiceTermService; 
		
		/* Tests. */

		/** Tests removal of MilitaryServiceTerms. */
		@Test
		public void testRemove() {
			
			MilitaryServiceTerm militaryServiceTerm;
		
			final Offender offender = this.offenderDelegate
					.createWithoutIdentity("Blofeld", "Ernst", "Stavro", null);

			try {
			
			final MilitaryBranch branch = this.militaryBranchDelegate
					.create("Marines", "USMC", true);
			final MilitaryDischargeStatus dischargeStatus 
				= this.militaryDischargeStatusDelegate.create("dishonorable", 
						true);
			final Date startDate = this.parseDateText("07/15/2015");
			final Date endDate = this.parseDateText("07/25/2016");
	
			militaryServiceTerm = this.militaryServiceTermDelegate
					.create(offender, branch, dischargeStatus, startDate, 
							endDate);
			
			} catch (DuplicateEntityFoundException e) {
				throw new RuntimeException("MilitaryServiceTerm exists", e);
			} catch (DateConflictException ee) {
				throw new RuntimeException("DateConflict Exception", ee);
			}
			
			this.militaryServiceTermService.remove(militaryServiceTerm);

			assert !this.militaryServiceTermService.findByOffender(offender)
					.contains(militaryServiceTerm)
				: "MilitaryServiceTerm was not removed.";
		}
		
		// Parses date text - returns result
		private Date parseDateText(final String dateText) {
			try {
				return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
			} catch (ParseException e) {
				throw new RuntimeException("Error parsing date", e);
			}
		}
	}

