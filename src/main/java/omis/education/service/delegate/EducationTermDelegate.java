package omis.education.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.education.dao.EducationTermDao;
import omis.education.dao.EducationalAchievementDao;
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievement;
import omis.education.domain.EducationalAchievementCategory;
import omis.education.domain.component.Institute;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * EducationTermDelegate.java
 * 
 *@author Annie Jacques 
 *@author Ryan Johns
 *@version 0.1.1 (Nov 21, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationTermDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG = "Education Term "
			+ "already exists for this offender.";
	
	private final EducationTermDao educationTermDao;
	
	private final EducationalAchievementDao educationalAchievementDao;
	
	private final InstanceFactory<EducationTerm> educationTermInstanceFactory;
	
	private final InstanceFactory<EducationalAchievement> 
		achievementInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor
	 * @param educationTermDao - education term dao
	 * @param educationTermInstanceFactory - education term instance factory
	 * @param auditComponentRetriever - audit component retriever
	 */
	public EducationTermDelegate(EducationTermDao educationTermDao,
			EducationalAchievementDao educationalAchievementDao,
			InstanceFactory<EducationTerm> educationTermInstanceFactory,
			InstanceFactory<EducationalAchievement> achievementInstanceFactory,
			AuditComponentRetriever auditComponentRetriever) {
		this.educationTermDao = educationTermDao;
		this.educationalAchievementDao = educationalAchievementDao;
		this.educationTermInstanceFactory = educationTermInstanceFactory;
		this.achievementInstanceFactory = achievementInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an education Term without an achievement
	 * @param offender - offender
	 * @param description - description
	 * @param specialEducation - special education
	 * @param attendedDateRange - attended date range
	 * @param institute - institute
	 * @param verificationSignature - verification signature
	 * @return newly created education term
	 * @throws DuplicateEntityFoundException - when education term exists with
	 * given description for the given offender
	 */
	public EducationTerm createWithoutAchievement(final Offender offender, 
			final String description, final Boolean specialEducation, 
			final DateRange attendedDateRange, final Institute institute, 
			final VerificationSignature verificationSignature)
					throws DuplicateEntityFoundException{
		if(this.educationTermDao.find(offender, description) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EducationTerm educationTerm = 
				this.educationTermInstanceFactory.createInstance();
		
		educationTerm.setAchievement(null);
		educationTerm.setAttendedDateRange(attendedDateRange);
		educationTerm.setDescription(description);
		educationTerm.setInstitute(institute);
		educationTerm.setOffender(offender);
		educationTerm.setSpecialEducation(specialEducation);
		educationTerm.setVerificationSignature(verificationSignature);
		educationTerm.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		educationTerm.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationTermDao.makePersistent(educationTerm);
	}
	
	/**
	 * Creates an education Term with an achievement
	 * @param offender - offender
	 * @param description - description
	 * @param specialEducation - special education
	 * @param attendedDateRange - attended date range
	 * @param institute - institute
	 * @param achievementDate - achievement date
	 * @param achievementDescription - achievement description
	 * @param achievementCategory - achievement category
	 * @param verificationSignature - verification signature
	 * @return newly created education term
	 * @throws DuplicateEntityFoundException - when education term exists with
	 * given description for the given offender
	 */
	public EducationTerm createWithAchievement(final Offender offender, 
			final String description, final Boolean specialEducation, 
			final DateRange attendedDateRange, final Institute institute, 
			final Date achievementDate, final String achievementDescription,
			final EducationalAchievementCategory achievementCategory,
			final VerificationSignature verificationSignature)
					throws DuplicateEntityFoundException{
		if(this.educationTermDao.find(offender, description) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EducationTerm educationTerm = 
				this.educationTermInstanceFactory.createInstance();
		
		educationTerm.setAttendedDateRange(attendedDateRange);
		educationTerm.setDescription(description);
		educationTerm.setInstitute(institute);
		educationTerm.setOffender(offender);
		educationTerm.setSpecialEducation(specialEducation);
		educationTerm.setVerificationSignature(verificationSignature);
		educationTerm.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		educationTerm.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		this.educationTermDao.makePersistent(educationTerm);
		
		EducationalAchievement achievement 
			= this.achievementInstanceFactory.createInstance();
		achievement.setCategory(achievementCategory);
		achievement.setDate(achievementDate);
		achievement.setDescription(achievementDescription);
		achievement.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		achievement.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		achievement.setEducationTerm(educationTerm);
		educationTerm.setAchievement(achievement);
		this.educationalAchievementDao.makePersistent(achievement);
		this.educationTermDao.makePersistent(educationTerm);
		
		return educationTerm;
	}
	
	
	/**
	 * Updates an education term
	 * @param educationTerm - education term
	 * @param description - description
	 * @param specialEducation - special education
	 * @param attendedDateRange - attended date range
	 * @param institute - institute
	 * @param achievement - achievement
	 * @param verificationSignature - verification signature
	 * @return newly updated education term
	 * @throws DuplicateEntityFoundException - when education term exists with
	 * given description for the its offender
	 */
	public EducationTerm update(final EducationTerm educationTerm, 
			final String description, final Boolean specialEducation, 
			final DateRange attendedDateRange, final Institute institute, 
			final EducationalAchievement achievement, 
			final VerificationSignature verificationSignature)
					throws DuplicateEntityFoundException{
		if(this.educationTermDao.findExcluding(educationTerm.getOffender(), 
				description, educationTerm.getInstitute().getCategory(), 
				educationTerm.getInstitute().getName(), educationTerm)
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		educationTerm.setAchievement(achievement);
		educationTerm.setAttendedDateRange(attendedDateRange);
		educationTerm.setDescription(description);
		educationTerm.setInstitute(institute);
		educationTerm.setSpecialEducation(specialEducation);
		educationTerm.setVerificationSignature(verificationSignature);
		educationTerm.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationTermDao.makePersistent(educationTerm);
	}
	
	/**
	 * Removes an education term
	 * @param educationTerm - education term to remove
	 */
	public void remove(final EducationTerm educationTerm){
		if(educationTerm.getAchievement() != null){
			this.educationalAchievementDao
				.makeTransient(educationTerm.getAchievement());
		}
		this.educationTermDao.makeTransient(educationTerm);
	}
	
	/**
	 * Finds and returns a list of all education terms by specified offender
	 * @param offender - offender
	 * @return list of all education terms by specified offender
	 */
	public List<EducationTerm> findByOffender(final Offender offender){
		return this.educationTermDao.findByOffender(offender);
	}
}
