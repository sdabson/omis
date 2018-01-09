package omis.education.service.impl;

import java.util.Date;
import java.util.List;


import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.education.dao.EducationalAchievementCategoryDao;
import omis.education.dao.InstituteCategoryDao;
import omis.education.domain.EducationNote;
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievement;
import omis.education.domain.EducationalAchievementCategory;
import omis.education.domain.InstituteCategory;
import omis.education.domain.component.Institute;
import omis.education.service.EducationService;
import omis.education.service.delegate.EducationNoteDelegate;
import omis.education.service.delegate.EducationTermDelegate;
import omis.education.service.delegate.EducationalAchievementDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * EducationServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationServiceImpl implements EducationService {
	
	private final EducationTermDelegate educationTermDelegate;
	
	private final EducationalAchievementDelegate educationalAchievementDelegate;
	
	private final EducationNoteDelegate educationNoteDelegate;
	
	private final EducationalAchievementCategoryDao 
		educationalAchievementCategoryDao;
	
	private final InstituteCategoryDao instituteCategoryDao;
	
	
	
	
	/**
	 * Constructor
	 * @param educationTermDelegate
	 * @param educationalAchievementDelegate
	 * @param educationNoteDelegate
	 * @param educationalAchievementDao
	 * @param institutionalCategoryDao
	 */
	public EducationServiceImpl(final EducationTermDelegate educationTermDelegate,
			final EducationalAchievementDelegate educationalAchievementDelegate,
			final EducationNoteDelegate educationNoteDelegate,
			final EducationalAchievementCategoryDao 
				educationalAchievementCategoryDao, 
			final InstituteCategoryDao instituteCategoryDao) {
		this.educationTermDelegate = educationTermDelegate;
		this.educationalAchievementDelegate = educationalAchievementDelegate;
		this.educationNoteDelegate = educationNoteDelegate;
		this.educationalAchievementCategoryDao 
			= educationalAchievementCategoryDao;
		this.instituteCategoryDao = instituteCategoryDao;
	}

	/**{@inheritDoc} */
	@Override
	public EducationTerm createEducationTermWithoutAchievement(final Offender offender, 
			final String description, final Boolean specialEducation,
			final DateRange attendedDateRange, final Institute institute, 
			final VerificationSignature verificationSignature) 
					throws DuplicateEntityFoundException {
		return this.educationTermDelegate.createWithoutAchievement(offender, 
				description, specialEducation, attendedDateRange, institute, 
				verificationSignature);
	}
		
	/**{@inheritDoc} */
	@Override
	public EducationTerm createEducationTermWithAchievement(final Offender offender, 
			final String description, final Boolean specialEducation,
			final DateRange attendedDateRange, final Institute institute, 
			final Date achievementDate, final String achievementDescription,
			final EducationalAchievementCategory achievementCategory,
			final VerificationSignature verificationSignature) 
					throws DuplicateEntityFoundException {
		return this.educationTermDelegate.createWithAchievement(offender, 
				description, specialEducation, attendedDateRange, institute, 
				achievementDate, achievementDescription, achievementCategory,
				verificationSignature);
	}

	/**{@inheritDoc} */
	@Override
	public EducationTerm updateEducationTerm(final EducationTerm educationTerm,
			final String description, final Boolean specialEducation,
			final DateRange attendedDateRange, final Institute institute, 
			final EducationalAchievement achievement,
			final VerificationSignature verificationSignature) 
					throws DuplicateEntityFoundException {
		return this.educationTermDelegate.update(educationTerm, description, 
				specialEducation, attendedDateRange, institute, achievement, 
				verificationSignature);
	}

	/**{@inheritDoc} */
	@Override
	public void removeEducationTerm(final EducationTerm educationTerm) {
		this.educationTermDelegate.remove(educationTerm);
	}

	/**{@inheritDoc} */
	@Override
	public EducationalAchievement createEducationalAchievement(
			final EducationTerm educationTerm, final Date date,
			final String description, final EducationalAchievementCategory category) 
					throws DuplicateEntityFoundException {
		return this.educationalAchievementDelegate.create(educationTerm, date,
				description, category);
	}

	/**{@inheritDoc} */
	@Override
	public EducationalAchievement updateEducationalAchievement(
			final EducationalAchievement educationalAchievement, final Date date,
			final String description, final EducationalAchievementCategory category) 
					throws DuplicateEntityFoundException {
		return this.educationalAchievementDelegate.update(educationalAchievement,
				date, description, category);
	}

	/**{@inheritDoc} */
	@Override
	public void removeEducationalAchievement(
			final EducationalAchievement educationalAchievement) {
		this.educationalAchievementDelegate.remove(educationalAchievement);
	}

	/**{@inheritDoc} */
	@Override
	public EducationNote createNote(final EducationTerm educationTerm, 
			final Date date, final String description)
			throws DuplicateEntityFoundException {
		return this.educationNoteDelegate.create(educationTerm, date, description);
	}

	/**{@inheritDoc} */
	@Override
	public EducationNote updateNote(final EducationNote educationNote, 
			final Date date, final String description)
			throws DuplicateEntityFoundException {
		return this.educationNoteDelegate.update(educationNote, date, description);
	}

	/**{@inheritDoc} */
	@Override
	public void removeNote(final EducationNote educationNote) {
		this.educationNoteDelegate.remove(educationNote);
	}

	/**{@inheritDoc} */
	@Override
	public List<EducationalAchievementCategory> 
		findAllEducationalAchievementCategories() {
		return this.educationalAchievementCategoryDao.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public List<InstituteCategory> findAllInstituteCategories() {
		return this.instituteCategoryDao.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public List<EducationalAchievement> 
		findAllEducationalAchievementsByEducationTerm(
				final EducationTerm educationTerm) {
		return this.educationalAchievementDelegate
				.findByEducationTerm(educationTerm);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<EducationalAchievement> 
		findAllEducationalAchievementsExcludingDegreeByEducationTerm(
				final EducationTerm educationTerm) {
		return this.educationalAchievementDelegate
				.findAllExcludingDegreeByEducationTerm(educationTerm);
	}

	/**{@inheritDoc} */
	@Override
	public List<EducationNote> findAllNotesByEducationTerm(
			final EducationTerm educationTerm) {
		return this.educationNoteDelegate.findByEducationTerm(educationTerm);
	}
	

}
