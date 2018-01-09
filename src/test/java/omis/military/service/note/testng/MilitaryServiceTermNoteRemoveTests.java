package omis.military.service.note.testng;

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
import omis.military.domain.MilitaryServiceTermNote;
import omis.military.service.MilitaryServiceTermService;
import omis.military.service.delegate.MilitaryBranchDelegate;
import omis.military.service.delegate.MilitaryDischargeStatusDelegate;
import omis.military.service.delegate.MilitaryServiceTermDelegate;
import omis.military.service.delegate.MilitaryServiceTermNoteDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests for removing military service notes.
 *
 * @author Trevor Isles
 * @version 0.0.1 (Jul 19, 2016)
 * @since OMIS 3.0
 */

@Test(groups = {"military"})
public class MilitaryServiceTermNoteRemoveTests
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
	
	/* Service to test. */
	@Autowired
	@Qualifier("militaryServiceTermService")
	private MilitaryServiceTermService militaryServiceTermService; 

	/* Tests. */
	
	/**
	 * Tests removal of military service term notes.
	 *  
	 * @throws DuplicateEntityFoundException if military service term note 
	 * exists.
	 * */

	@Test
	public void testRemove() 
			throws DuplicateEntityFoundException, DateConflictException {
		
		MilitaryServiceTermNote militaryServiceTermNote;
		
		final Offender offender
			= this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Ernst", "Stavro", null);
		final MilitaryServiceTerm serviceTerm;
		try {
	
			final MilitaryBranch branch = this.militaryBranchDelegate
			.create("U.S. Marines", "USMC", true);
			final MilitaryDischargeStatus dischargeStatus 
				= this.militaryDischargeStatusDelegate.create("Honorable", true);
			final Date startDate = this.parseDateText("07/07/2015");
			final Date endDate = this.parseDateText("07/11/2016");
			
			serviceTerm = this.militaryServiceTermDelegate
					.create(offender, branch, dischargeStatus, 
							startDate, endDate);
			final String note = "Tropic Thunder!";
	
			militaryServiceTermNote = this.militaryServiceTermNoteDelegate
					.create(serviceTerm, endDate, note);
			
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("MilitaryServiceTerm exists", e);
		} catch (DateConflictException ee) {
			throw new RuntimeException("DateConflict Exception", ee);
		}
		
		this.militaryServiceTermNoteDelegate.remove(militaryServiceTermNote);

		assert !this.militaryServiceTermService
			.findServiceTermNotesByServiceTerm(serviceTerm)
				.contains(militaryServiceTermNote)
			: "MilitaryServiceTermNote was not removed.";
		
	}

	// Parses date text - returns result.

	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
}
