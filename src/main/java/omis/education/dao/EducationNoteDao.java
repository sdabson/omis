package omis.education.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.education.domain.EducationNote;
import omis.education.domain.EducationTerm;

/**
 * EducationNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public interface EducationNoteDao extends GenericDao<EducationNote> {
	
	/**
	 * Returns an education note with the specified properties
	 * @param description - description
	 * @param educationTerm - education term
	 * @param date - date
	 * @return education note with the specified properties
	 */
	EducationNote find(String description, EducationTerm educationTerm,
			Date date);
	
	/**
	 * Returns an education note with the specified properties excluding
	 * specified education note
	 * @param description - description
	 * @param educationTerm - education term
	 * @param date - date
	 * @param educationNote - education note to exclude
	 * @return education note with the specified properties excluding
	 * specified education note
	 */
	EducationNote findExcluding(String description, EducationTerm educationTerm,
			Date date, EducationNote educationNote);
	
	/**
	 * Finds and returns a list of all education notes by specified education term
	 * @param educationTerm - education term
	 * @return list of all education notes by specified education term
	 */
	List<EducationNote> findByEducationTerm(EducationTerm educationTerm);
	
}
