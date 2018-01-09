package omis.disciplinaryCode.report;

import java.util.Date;
import java.util.List;

import omis.supervision.domain.SupervisoryOrganization;

/**
 * DisciplinaryCodeReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public interface DisciplinaryCodeReportService {
	
	/**
	 * Finds and returns a list of disciplinary codes summaries by specified
	 * properties
	 * @param supervisoryOrganization - supervisory organization
	 * @param effectiveDate - effective Date
	 * @return list of disciplinary codes summaries by specified properties
	 */
	List<SupervisoryOrganizationDisciplinaryCodeSummary> 
		findDisciplinaryCodeSummariesBySupervisoryOrganization(
				SupervisoryOrganization supervisoryOrganization, Date effectiveDate);
	/**
	 * Finds and returns a list of disciplinary codes summaries by specified
	 * supervisory organization
	 * @param supervisoryOrganization - supervisory
	 * @param effectiveDate - effective Date
	 * @param startDate - start date
	 * @param endDate - end date
	 * @return list of disciplinary codes summaries by specified properties
	 */
	List<SupervisoryOrganizationDisciplinaryCodeSummary> 
		findDisciplinaryCodeSummariesBySupervisoryOrganization(
			SupervisoryOrganization supervisoryOrganization, Date effectiveDate, 
			Date startDate, Date endDate);
	
	/**
	 * Finds and returns a list of summaries of all supervisory organizations
	 * @return list of summaries of all supervisory organizations
	 */
	List<SupervisoryOrganizationDisciplinarySummary> 
		findAllSupervisoryOrganizationSummaries();
	
	
	
	
	
	
}
