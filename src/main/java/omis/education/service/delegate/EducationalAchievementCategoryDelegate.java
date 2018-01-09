package omis.education.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.education.dao.EducationalAchievementCategoryDao;
import omis.education.domain.EducationalAchievementCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * EducationalAchievementCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 8, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationalAchievementCategoryDelegate {
	
	
	private final InstanceFactory<EducationalAchievementCategory> 
		educationalAchievementCategoryInstanceFactory;
	
	private final EducationalAchievementCategoryDao 
		educationalAchievementCategoryDao;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor
	 * @param educationalAchievementCategoryInstanceFactory
	 * @param achievementCategoryDao
	 */
	public EducationalAchievementCategoryDelegate(
			InstanceFactory<EducationalAchievementCategory> 
				educationalAchievementCategoryInstanceFactory,
			EducationalAchievementCategoryDao 
				educationalAchievementCategoryDao, 
			AuditComponentRetriever auditComponentRetriever) {
		this.educationalAchievementCategoryInstanceFactory = 
				educationalAchievementCategoryInstanceFactory;
		this.educationalAchievementCategoryDao 
			= educationalAchievementCategoryDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates an educational achievement category
	 * @param name - name
	 * @param sortOrder - sort order
	 * @param valid - valid
	 * @return EducationalAchievementCategory
	 * @throws DuplicateEntityFoundException - when the category already exists
	 * 	with the given name
	 */
	public EducationalAchievementCategory create(final String name, 
			final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException {
						if(this.educationalAchievementCategoryDao
								.find(name) != null){
							throw new DuplicateEntityFoundException(
									"Duplicate achievement category found.");
						}
		EducationalAchievementCategory achievementCategory 
			= this.educationalAchievementCategoryInstanceFactory
				.createInstance(); 
		achievementCategory.setName(name);
		achievementCategory.setSortOrder(sortOrder);
		achievementCategory.setValid(valid);
		achievementCategory.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationalAchievementCategoryDao
				.makePersistent(achievementCategory);
		
	}
}
