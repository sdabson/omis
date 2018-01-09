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
 * Tests for creating military service note using military service note.
 *
 * @author Trevor Isles
 * @version 0.0.1 (Jul 19, 2016)
 * @since OMIS 3.0
 */

@Test(groups = {"military"})
public class MilitaryServiceTermNoteCreateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests{

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
	 * Tests creation of military service term notes.
	 *  
	 * @throws DuplicateEntityFoundException if military service term note 
	 * exists.
	 * */

	@Test
	public void testCreate()
			throws DuplicateEntityFoundException, DateConflictException {
		final Offender offender
			= this.offenderDelegate.createWithoutIdentity(
					"Blofeld", "Ernst", "Stavro", null);
		
		final MilitaryBranch branch = this.militaryBranchDelegate
				.create("U.S. Marines", "USMC", true);
		final MilitaryDischargeStatus dischargeStatus 
			= this.militaryDischargeStatusDelegate.create("Honorable", true);
		final Date startDate = this.parseDateText("07/07/2015");
		final Date endDate = this.parseDateText("07/11/2016");
		final MilitaryServiceTerm serviceTerm = this.militaryServiceTermDelegate
				.create(offender, branch, dischargeStatus, startDate, endDate);
		final String note = "Tropic Thunder!";
		
		//Create service term note.
		final MilitaryServiceTermNote termNote = this.
				militaryServiceTermNoteDelegate.create(serviceTerm, startDate, 
						note);
		
		assertValues(offender, termNote, serviceTerm, note, startDate);
	}

	// Parses date text - returns result
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
	// Asserts military service term values

	private void assertValues(
			final Offender offender,
			final MilitaryServiceTermNote serviceTermNote,
			final MilitaryServiceTerm serviceTerm,
			final String note,
			final Date date) {
		assert date.equals(serviceTermNote.getDate())
		: String.format("Wrong date: %s found; %s expected.", 
				serviceTermNote.getDate(), date);
		assert note.equals(serviceTermNote.getNote())
		: String.format("Wrong note: %s found; %s expected.", 
				serviceTermNote.getNote(), note);
		assert serviceTerm.equals(serviceTermNote.getServiceTerm())
		: String.format("Wrong service term: %s found; %s expected.", 
				serviceTermNote.getServiceTerm(), serviceTerm);		
		
	}
}
