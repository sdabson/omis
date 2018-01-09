package omis.military.service.note.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
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
 * Tests for updating military service notes.
 *
 * @author Trevor Isles
 * @version 0.0.1 (Jul 19, 2016)
 * @since OMIS 3.0
 */

@Test(groups = {"military"})
public class MilitaryServiceTermNoteUpdateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests  {

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
	 * Tests updates of military service term notes.
	 *  
	 * @throws DuplicateEntityFoundException if military service term note 
	 * exists.
	 * */
	@Test
	public void testUpdate() throws DuplicateEntityFoundException, 
		DateConflictException {
		
		MilitaryServiceTermNote militaryServiceTermNote;
		MilitaryServiceTerm militaryServiceTerm;
		
		final Offender offender = this.offenderDelegate.createWithoutIdentity
				("Blofeld", "Ernst", "Stavro", null);

		try {
			
		militaryServiceTerm = this.militaryServiceTermDelegate
			.create(offender, 
					this.militaryBranchDelegate.create("Marines", "USMC", true),
					this.militaryDischargeStatusDelegate.create("Dishonorable", 
							true), 
					this.parseDateText("07/15/2015"), 
					this.parseDateText("07/25/2016"));
		
		militaryServiceTermNote = this.
				militaryServiceTermNoteDelegate.create(militaryServiceTerm, 
						this.parseDateText("07/15/2015"), "Tropic Thunder!");
		
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Military term exists.", e);
		}
		
		final Date startDate = this.parseDateText("07/15/2015");
		final String note = "Tropic Thunder!";

		militaryServiceTermNote = this.militaryServiceTermService
				.updateNote(militaryServiceTermNote, startDate, note);
		
		assertValues(militaryServiceTermNote, note, startDate);
	}

		// Parses date text - returns result
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date.", e);
		}
	}
	
	// Asserts military service term note values
	private void assertValues(
			final MilitaryServiceTermNote serviceTermNote,
			final String note,
			final Date date) {
		assert date.equals(serviceTermNote.getDate())
		: String.format("Wrong date: %s found; %s expected.", 
				serviceTermNote.getDate(), date);
		assert note.equals(serviceTermNote.getNote())
		: String.format("Wrong note: %s found; %s expected.", 
				serviceTermNote.getNote(), note);
	}
}
