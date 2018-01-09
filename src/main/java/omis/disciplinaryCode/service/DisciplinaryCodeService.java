package omis.disciplinaryCode.service;

import java.util.List;

import omis.datatype.DateRange;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.domain.SupervisoryOrganizationCode;
import omis.exception.DuplicateEntityFoundException;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * DisciplinaryCodeService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Sep 1, 2017)
 *@since OMIS 3.0
 *
 */
public interface DisciplinaryCodeService {
	
	
	/**
	 * Creates a disciplinary code
	 * @param value - value
	 * @param description - description
	 * @param extendedDescription - extendedDescription
	 * @return newly created disciplinary code
	 * @throws DuplicateEntityFoundException - when disciplinary code already
	 * exists with given value
	 */
	public DisciplinaryCode createDisciplinaryCode(
			String value, String description, String extendedDescription)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a disciplinary code
	 * @param disciplinaryCode - disciplinary code to update
	 * @param value - value
	 * @param description - description
	 * @param extendedDescription - extended description
	 * @return Updated disciplinary code
	 * @throws DuplicateEntityFoundException - when disciplinary code already
	 * exists with given value
	 */
	public DisciplinaryCode updateDisciplinaryCode(
			DisciplinaryCode disciplinaryCode, String value, String description,
			String extendedDescription)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a disciplinary code
	 * @param disciplinaryCode - disciplinary code to remove
	 */
	public void removeDisciplinaryCode(DisciplinaryCode disciplinaryCode);
	
	/**
	 * Creates a supervisory organization code
	 * @param supervisoryOrganization - supervisory organization
	 * @param dateRange - date range
	 * @param code - disciplinary code
	 * @return newly created supervisory organization code
	 * @throws DuplicateEntityFoundException - when supervisory organization
	 * code already exists for given supervisory organization
	 */
	public SupervisoryOrganizationCode createSupervisoryOrganizationCode(
			SupervisoryOrganization supervisoryOrganization, 
			DateRange dateRange, DisciplinaryCode code) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Update a specified supervisory organization code
	 * @param supervisoryOrganizationCode - supervisory organization code to update
	 * @param supervisoryOrganization - supervisory organization
	 * @param dateRange - date range
	 * @param code - disciplinary code
	 * @return updated supervisory organization code
	 * @throws DuplicateEntityFoundException - when supervisory organization
	 * code already exists for given supervisory organization
	 */
	public SupervisoryOrganizationCode updateSupervisoryOrganizationCode(
			SupervisoryOrganizationCode supervisoryOrganizationCode, 
			DateRange dateRange, DisciplinaryCode code)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a supervisory organization code
	 * @param supervisoryOrganizationCode - supervisory organization code to remove
	 */
	public void removeSupervisoryOrganizationCode(
			SupervisoryOrganizationCode supervisoryOrganizationCode);
	
	/**
	 * Returns a list of disciplinary codes with the specified value
	 * @param value - value
	 * @return list disciplinary codes with the specified value
	 */
	public List<DisciplinaryCode> findDisciplinaryCodeByValue(String value);
	
	
	
}
