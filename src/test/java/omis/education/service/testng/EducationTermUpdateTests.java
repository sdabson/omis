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
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievement;
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
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * EducationTermUpdateTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 8, 2016)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"education"})
public class EducationTermUpdateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate accountDelegate;
	
	@Autowired
	@Qualifier("educationalAchievementCategoryDelegate")
	private EducationalAchievementCategoryDelegate 
		educationalAchievementCategoryDelegate;
	
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
	public void testUpdate()
			throws DuplicateEntityFoundException, DateConflictException {
		
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"lastName", "firstName", "middleName", null);	
		final Institute instituteFirst = new Institute();
		instituteFirst.setCategory(this.instituteCategoryDelegate
				.create("firstInsteCat", (short) 2, true));
		instituteFirst.setName("First Institute");
		final Person person = this.personDelegate.create("Buttehead", "Joel", "Trevor", null);
		final UserAccount userAccount = this.accountDelegate.create(person, "YeaMe", null, null, null, null);
		
		EducationTerm educationTerm
			= this.educationService.createEducationTermWithAchievement(
					offender, "firstDesc", true, new DateRange(
							this.parseDateText("08/08/2008"), 
							this.parseDateText("09/09/2009")), 
					instituteFirst, this.parseDateText("03/03/2003"), 
					"firstAcheDesc", this.educationalAchievementCategoryDelegate.
						create("firstAche", (short)3, true), 
					new VerificationSignature(
							userAccount, 
							this.parseDateText("01/01/2011"), true, 
							this.verificationMethodDelegate
								.create("firstVeriMethod", (short) 2, true)));
			
	
		final VerificationMethod veriMethod 
		= this.verificationMethodDelegate.create(
				"VeriMethod", (short) 1, true);
	
		final EducationalAchievementCategory acheCat 
			= this.educationalAchievementCategoryDelegate.create(
					"AcheCat", (short) 1, true);
		
		final InstituteCategory insteCat 
		= this.instituteCategoryDelegate.create(
				"InsteCat", (short) 1, true);	
		final Person person2 = this.personDelegate.create("Buttehead2", "Joel2", "Trevor2", null);
		final UserAccount userAccount2 = this.accountDelegate.create(person2, "YeaMe2", null, null, null, null);
					
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
					userAccount2, 
					this.parseDateText("08/04/2016"), true, veriMethod);
		
		final EducationalAchievement achievement 
		= this.educationService.createEducationalAchievement(educationTerm, 
				achievementDate, achievementDescription, acheCat);	
			
		educationTerm = this.educationService.updateEducationTerm(
				educationTerm, description, specialEducation, attendedDateRange, 
				institute, achievement, verificationSignature);	
			
			
			
		//educationTerm.getAttendedDateRange() to attendedDateRange
		assert DateRange.areEqual(educationTerm.getAttendedDateRange(), 
				attendedDateRange) 
		: String.format("Wrong attended date range: %s found; %s expected",
				educationTerm.getAttendedDateRange(), attendedDateRange);
		//educationTerm.getSpecialEducation() to specialEducation
		assert specialEducation.equals(educationTerm.getSpecialEducation())
		: String.format("Wrong special education: %s found; %s expected",
				educationTerm.getSpecialEducation(), 
				specialEducation);
		//educationTerm.getInstitute().getCategory() to insteCat
		assert insteCat.equals(educationTerm.getInstitute().getCategory())
		: String.format("Wrong institute category: %s found; %s expected",
				educationTerm.getInstitute().getCategory().getName(), 
				insteCat.getName());
		//educationTerm.getInstitute().getName() to insteName
		assert insteName.equals(educationTerm.getInstitute().getName())
		: String.format("Wrong institute name: %s found; %s expected",
				educationTerm.getInstitute().getName(), 
				insteName);
		//educationTerm.getAchievement().getCategory() to achievementCategory
		assert acheCat.equals(educationTerm.getAchievement().getCategory())
		: String.format("Wrong achievement category: %s found; %s expected",
				educationTerm.getAchievement().getCategory().getName(), 
				acheCat.getName());
		//educationTerm.getAchievement().getDate() to achievementDate
		assert achievementDate.equals(educationTerm.getAchievement().getDate())
		: String.format("Wrong achievement date: %s found; %s expected",
				educationTerm.getAchievement().getDate(), 
				achievementDate);
		//educationTerm.getAchievement().getDescription() to achievementDescription
		assert achievementDescription.equals(educationTerm.getAchievement()
				.getDescription())
		: String.format("Wrong achievement description: %s found; %s expected",
				educationTerm.getAchievement().getDescription(), 
				achievementDescription);
		//educationTerm.getVerificationSignature() to verificationSignature
		assert verificationSignature.getDate().equals(educationTerm
				.getVerificationSignature().getDate())
		: String.format("Wrong verification signature date: "
				+ "%s found; %s expected",
				educationTerm.getVerificationSignature().getDate(), 
				verificationSignature.getDate());
		assert verificationSignature.getMethod().equals(educationTerm
				.getVerificationSignature().getMethod())
		: String.format("Wrong verification signature method: "
				+ "%s found; %s expected",
				educationTerm.getVerificationSignature().getMethod().getName(), 
				verificationSignature.getMethod().getName());
		assert verificationSignature.getUserAccount().equals(educationTerm
				.getVerificationSignature().getUserAccount())
		: String.format("Wrong verification signature user account: "
				+ "%s found; %s expected",
				educationTerm.getVerificationSignature().getUserAccount()
				.getUsername(), 
				verificationSignature.getUserAccount().getUsername());
		//educationTerm.getOffender() to offender
		assert offender.equals(educationTerm.getOffender())
		: String.format("Wrong offender: #%d found; #%d expected",
				educationTerm.getOffender().getOffenderNumber(),
				offender.getOffenderNumber());
	}
	
	// Parses date text - returns result
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date.", e);
		}
	}
}
