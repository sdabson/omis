package omis.education.service;

import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.education.domain.EducationNote;
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievement;
import omis.education.domain.EducationalAchievementCategory;
import omis.education.domain.InstituteCategory;
import omis.education.domain.component.Institute;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * EducationService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public interface EducationService {
	
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
	public EducationTerm createEducationTermWithoutAchievement(Offender offender, 
			String description, Boolean specialEducation, 
			DateRange attendedDateRange, Institute institute, 
			VerificationSignature verificationSignature)
					throws DuplicateEntityFoundException;
	
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
	public EducationTerm createEducationTermWithAchievement(Offender offender, 
			String description, Boolean specialEducation, 
			DateRange attendedDateRange, Institute institute,
			Date achievementDate, String achievementDescription,
			EducationalAchievementCategory achievementCategory,
			VerificationSignature verificationSignature)
					throws DuplicateEntityFoundException;
	
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
	public EducationTerm updateEducationTerm(EducationTerm educationTerm, 
			String description, Boolean specialEducation, 
			DateRange attendedDateRange, Institute institute, 
			EducationalAchievement achievement, 
			VerificationSignature verificationSignature)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an education term
	 * @param educationTerm - education term to remove
	 */
	public void removeEducationTerm(EducationTerm educationTerm);
	
	/**
	 * Creates a new educational achievement
	 * @param educationTerm - education achievement
	 * @param date - date
	 * @param description - description
	 * @param category - category
	 * @return newly created educational achievement
	 * @throws DuplicateEntityFoundException - when education achievement
	 * exists with given date and description for the given education term
	 */
	public EducationalAchievement createEducationalAchievement(
			EducationTerm educationTerm, Date date, String description, 
			EducationalAchievementCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing educational achievement
	 * @param educationalAchievement - educational achievement to update
	 * @param date - date
	 * @param description - description
	 * @param category - category
	 * @return newly created educational achievement
	 * @throws DuplicateEntityFoundException - when education achievement
	 * exists with given date and description for the its education term
	 */
	public EducationalAchievement updateEducationalAchievement(
			EducationalAchievement educationalAchievement, Date date, 
			String description, EducationalAchievementCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an educational achievement
	 * @param educationalAchievement - educational achievement to remove
	 */
	public void removeEducationalAchievement(
			EducationalAchievement educationalAchievement);
	
	/**
	 * Creates a new education note
	 * @param educationTerm - education note
	 * @param date - date
	 * @param description - description
	 * @return newly created education note
	 * @throws DuplicateEntityFoundException - when education note already
	 * exists with given date and description for specified education term
	 */
	public EducationNote createNote(EducationTerm educationTerm, Date date, 
			String description)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing education note
	 * @param educationNote - education note to update
	 * @param date - date
	 * @param description - description
	 * @return newly created education note
	 * @throws DuplicateEntityFoundException - when education note already
	 * exists with given date and description for its education term
	 */
	public EducationNote updateNote(EducationNote educationNote, Date date, 
			String description)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an education note
	 * @param educationNote - education note to remove
	 */
	public void removeNote(EducationNote educationNote);
	
	/**
	 * Finds and returns a list of all education achievement categories
	 * @return list of education achievement categories
	 */
	public List<EducationalAchievementCategory> 
		findAllEducationalAchievementCategories();
	
	/**
	 * Finds and returns a list of all institute categories
	 * @return list of institute categories
	 */
	public List<InstituteCategory> findAllInstituteCategories();
	
	/**
	 * Finds and returns a list of all educational achievements by specified
	 * education term
	 * @param educationTerm - education term
	 * @return list of educational achievements
	 */
	public List<EducationalAchievement> 
		findAllEducationalAchievementsByEducationTerm(EducationTerm educationTerm);
	
	/**
	 * Finds and returns a list of all educational achievements excluding
	 * the education term's "degree" achievement by specified education term
	 * @param educationTerm - education term
	 * @return list of all educational achievements excluding
	 * the education term's "degree" achievement by specified education term
	 */
	public List<EducationalAchievement> 
		findAllEducationalAchievementsExcludingDegreeByEducationTerm
			(EducationTerm educationTerm);
	
	/**
	 * Finds and returns a list of all notes by specified education term
	 * @param educationTerm - education term
	 * @return list of Education Notes
	 */
	public List<EducationNote> findAllNotesByEducationTerm(
			EducationTerm educationTerm);
	
}
 