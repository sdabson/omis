package omis.education.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.education.dao.EducationalAchievementDao;
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievement;
import omis.education.domain.EducationalAchievementCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * EducationalAchievementDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationalAchievementDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG = "Education achievement "
			+ "already exists for this education term.";
	
	private final EducationalAchievementDao educationalAchievementDao;
	
	private final InstanceFactory<EducationalAchievement> educationalAchievementInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor
	 * @param educationalAchievementDao - educational achievement dao
	 * @param educationalAchievementInstanceFactory - educational achievement
	 * instance factory
	 * @param auditComponentRetriever - audit component retriever
	 */
	public EducationalAchievementDelegate(EducationalAchievementDao educationalAchievementDao,
			InstanceFactory<EducationalAchievement> educationalAchievementInstanceFactory,
			AuditComponentRetriever auditComponentRetriever) {
		this.educationalAchievementDao = educationalAchievementDao;
		this.educationalAchievementInstanceFactory = educationalAchievementInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
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
	public EducationalAchievement create(
			final EducationTerm educationTerm, final Date date, final String description, 
			final EducationalAchievementCategory category)
					throws DuplicateEntityFoundException{
		if(this.educationalAchievementDao.find(date, description, educationTerm)
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EducationalAchievement educationalAchievement 
			= this.educationalAchievementInstanceFactory.createInstance();
		
		educationalAchievement.setCategory(category);
		educationalAchievement.setDate(date);
		educationalAchievement.setDescription(description);
		educationalAchievement.setEducationTerm(educationTerm);
		educationalAchievement.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		educationalAchievement.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationalAchievementDao.makePersistent(
				educationalAchievement);
	}
	
	
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
	public EducationalAchievement update(
			final EducationalAchievement educationalAchievement, final Date date, final String description, 
			final EducationalAchievementCategory category)
					throws DuplicateEntityFoundException{
		if(this.educationalAchievementDao.findExcluding(date, description, 
				educationalAchievement.getEducationTerm(), 
				educationalAchievement) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		educationalAchievement.setCategory(category);
		educationalAchievement.setDate(date);
		educationalAchievement.setDescription(description);
		educationalAchievement.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationalAchievementDao.makePersistent(
				educationalAchievement);
	}
	
	/**
	 * Removes an educational achievement
	 * @param educationalAchievement - educational achievement to remove
	 */
	public void remove(final EducationalAchievement educationalAchievement){
		this.educationalAchievementDao.makeTransient(educationalAchievement);
	}
	
	/**
	 * Finds and returns a list of all educational achievements by 
	 * specified education term
	 * @param educationTerm - education term
	 * @return list of all educational achievements by specified education term
	 */
	public List<EducationalAchievement> findByEducationTerm(
			final EducationTerm educationTerm){
		return this.educationalAchievementDao.findByEducationTerm(educationTerm);
	}
	
	/**
	 * Finds and returns a list of all educational achievements excluding
	 * the education term's "degree" achievement by specified education term
	 * @param educationTerm - education term
	 * @return list of all educational achievements excluding
	 * the education term's "degree" achievement by specified education term
	 */
	public List<EducationalAchievement> findAllExcludingDegreeByEducationTerm
		(EducationTerm educationTerm){
		return this.educationalAchievementDao.
				findAllExcludingDegreeByEducationTerm(educationTerm);
	}
	
	
}
