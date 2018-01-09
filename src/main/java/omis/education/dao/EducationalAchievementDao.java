package omis.education.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievement;

/**
 * EducationalAchievementDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public interface EducationalAchievementDao 
	extends GenericDao<EducationalAchievement> {
	
	/**
	 * Returns an educational achievement with the specified properties
	 * @param date - date
	 * @param description - description
	 * @param educationTerm - education term
	 * @return educational achievement with the specified properties
	 */
	EducationalAchievement find(Date date, String description, 
			EducationTerm educationTerm);
	
	/**
	 * Returns an educational achievement with the specified properties
	 * excluding specified educational achievement
	 * @param date - date
	 * @param description - description
	 * @param educationTerm - education term
	 * @param educationalAchievement - educational achievement to exclude
	 * @return educational achievement with the specified properties
	 * excluding specified educational achievement
	 */
	EducationalAchievement findExcluding(Date date, String description, 
			EducationTerm educationTerm, 
			EducationalAchievement educationalAchievement);
	
	/**
	 * Finds and returns a list of all educational achievements by 
	 * specified education term
	 * @param educationTerm - education term
	 * @return list of all educational achievements by specified education term
	 */
	List<EducationalAchievement> findByEducationTerm(EducationTerm educationTerm);
	
	/**
	 * Finds and returns a list of all educational achievements excluding
	 * the education term's "degree" achievement by specified education term
	 * @param educationTerm - education term
	 * @return list of all educational achievements excluding
	 * the education term's "degree" achievement by specified education term
	 */
	List<EducationalAchievement> findAllExcludingDegreeByEducationTerm
		(EducationTerm educationTerm);
	
	
	
	
}
