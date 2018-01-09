package omis.employment.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.employment.domain.Employer;
import omis.employment.domain.EmploymentChangeReason;
import omis.employment.domain.EmploymentTerm;
import omis.employment.domain.component.Job;
import omis.offender.domain.Offender;

/**
 * Data access object for employment Term.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Dec 14, 2017)
 * @since OMIS 3.0
 */
public interface EmploymentTermDao
	extends GenericDao<EmploymentTerm> {

	/**
	 * Find corresponding employment term.
	 * @param offender offender
	 * @param employer employer
	 * @param dateRange date range
	 * @param job job
	 * @param endReason employment end reason
	 * @return same employment term in DB
	 */
	EmploymentTerm find(Offender offender, Employer employer,
		DateRange dateRange, Job job, EmploymentChangeReason endReason);
	
	/**
	 * Find corresponding employment term.
	 * @param employmentTerm employment term to be excluded
	 * @param dateRange date range
	 * @param job job
	 * @param endReason employment end reason
	 * @return same employment term in DB except itself
	 */
	EmploymentTerm findExcluding(EmploymentTerm employmentTerm, 
		DateRange dateRange, Job job, EmploymentChangeReason endReason);

	/**
	 * Find employment term by offender.
	 * @param offender offender
	 * @return list of existing matched employment term
	 */
	List<EmploymentTerm> findByOffender(Offender offender);
	
	/**
	 * Check if an employment term with the specified employer already existed.
	 * @param employmentTerm employment term to be changed
	 * @param employer
	 * @return employment term
	 */
	EmploymentTerm findEmploymentTermByEmployer(EmploymentTerm employmentTerm, 
		Employer employer);
}