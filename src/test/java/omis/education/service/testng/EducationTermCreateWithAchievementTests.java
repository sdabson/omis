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
 * EducationTermCreateTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 4, 2016)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"education"})
public class EducationTermCreateWithAchievementTests 
extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate accountDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
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
	public void testCreate()
			throws DuplicateEntityFoundException, DateConflictException {
		
		final VerificationMethod veriMethod 
			= this.verificationMethodDelegate.create(
					"VeriMethod", (short) 1, true);
		
		final EducationalAchievementCategory acheCat 
			= this.educationalAchievementCategoryDelegate.create(
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
		final Person person = this.personDelegate.create("Buttehead", "Joel", "Trevor", null);
		final UserAccount userAccount = this.accountDelegate.create(person, "YeaMe", null, null, null, null);
		final VerificationSignature verificationSignature 
			= new VerificationSignature(
					userAccount, this.parseDateText("08/04/2016"), 
					true, veriMethod);
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"lastName", "firstName", "middleName", null);
		
		final EducationTerm educationTerm
			= this.educationService.createEducationTermWithAchievement(
					offender, description, specialEducation, attendedDateRange, 
					institute, achievementDate, achievementDescription, 
					acheCat, verificationSignature);
		
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
	
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
}
