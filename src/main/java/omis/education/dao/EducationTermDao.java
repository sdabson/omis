package omis.education.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.education.domain.EducationTerm;
import omis.education.domain.InstituteCategory;
import omis.offender.domain.Offender;

/**
 * EducationTermDao.java
 * 
 *@author Annie Jacques 
 *@author Ryan Johns
 *@version 0.1.1 (Nov 21, 2016)
 *@since OMIS 3.0
 *
 */
public interface EducationTermDao extends GenericDao<EducationTerm> {
	
	
	/**
	 * Returns an education term with specified properties
	 * @param offender - offender
	 * @param description - description
	 * @return education term with specified propertied
	 */
	EducationTerm find(Offender offender, String description);
	
	/**
	 * Returns an education term with specified properties excluding 
	 * specified education term
	 * @param offender - offender
	 * @param description - description
	 * @param instituteCategory - institute category.
	 * @param instituteName - institute name.
	 * @param educationTerm - education term
	 * @return education term with specified properties excluding 
	 * specified education term
	 */
	EducationTerm findExcluding(Offender offender, String description, 
			InstituteCategory instituteCategory, String instituteName,
			EducationTerm educationTerm);
	
	/**
	 * Finds and returns a list of all education terms by specified offender
	 * @param offender - offender
	 * @return list of all education terms by specified offender
	 */
	List<EducationTerm> findByOffender(Offender offender);
	
}
