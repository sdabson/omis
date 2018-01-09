package omis.education.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.audit.service.VerificationMethodService;
import omis.audit.service.delegate.VerificationMethodDelegate;
import omis.datatype.DateRange;
import omis.education.domain.EducationNote;
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievementCategory;
import omis.education.domain.InstituteCategory;
import omis.education.domain.component.Institute;
import omis.education.service.EducationService;
import omis.education.service.delegate.EducationalAchievementCategoryDelegate;
import omis.education.service.delegate.InstituteCategoryDelegate;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * EducationNoteRemoveTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 8, 2016)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"education"})
public class EducationNoteRemoveTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate accountDelegate;
	
	@Autowired
	@Qualifier("educationalAchievementCategoryDelegate")
	private EducationalAchievementCategoryDelegate achievementCategoryDelegate;
	
	@Autowired
	@Qualifier("instituteCategoryDelegate")
	private InstituteCategoryDelegate instituteCategoryDelegate;
	
	@Autowired
	@Qualifier("verificationMethodDelegate")
	private VerificationMethodDelegate verificationMethodDelegate;
	
	/* Services */
	
	@Autowired
	@Qualifier("educationService")
	private EducationService educationService;
	
	@Autowired
	@Qualifier("verificationMethodService")
	private VerificationMethodService verificationMethodService;
	
	
	@Test
	public void testRemove()
			throws DuplicateEntityFoundException, DateConflictException {
		
		final VerificationMethod veriMethod 
		= this.verificationMethodDelegate.create(
				"VeriMethod", (short) 1, true);
	
		final EducationalAchievementCategory acheCat 
			= this.achievementCategoryDelegate.create(
					"AcheCat", (short) 1, true);
		
		final InstituteCategory insteCat 
		= this.instituteCategoryDelegate.create(
				"InsteCat", (short) 1, true);
		
		final String description = "Term Description";
		final Date startDate = this.parseDateText("07/07/2016");
		final Date endDate = this.parseDateText("07/11/2016");
		final DateRange attendedDateRange = new DateRange(startDate, endDate);
		final Boolean specialEducation = false;
		final Institute institute = new Institute();
		institute.setCategory(insteCat);
		final String insteName = "Test Institute";
		institute.setName(insteName);
		final Date achievementDate = this.parseDateText("01/27/2016");
		final String achievementDescription = "Achievement Description";
		final VerificationSignature verificationSignature 
			= new VerificationSignature(
					this.accountDelegate.findByUsername("CIB587"), 
					this.parseDateText("08/04/2016"), true, veriMethod);
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"lastName", "firstName", "middleName", null);
		
		final EducationTerm educationTerm
			= this.educationService.createEducationTermWithAchievement(
					offender, description, specialEducation, attendedDateRange, 
					institute, achievementDate, achievementDescription, 
					acheCat, verificationSignature);
		
		final Date noteDate = this.parseDateText("07/07/2016");
		final String noteDescription = "Note Description";
		final EducationNote note 
			= this.educationService.createNote(educationTerm, noteDate, 
					noteDescription);
		
		this.educationService.removeNote(note);
		
		assert !this.educationService.findAllNotesByEducationTerm(educationTerm)
			.contains(note) 
			: "Education Note was not removed!";
			
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
