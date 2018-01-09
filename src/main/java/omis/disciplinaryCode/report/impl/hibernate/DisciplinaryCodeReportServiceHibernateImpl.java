package omis.disciplinaryCode.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.disciplinaryCode.report.DisciplinaryCodeReportService;
import omis.disciplinaryCode.report.SupervisoryOrganizationDisciplinaryCodeSummary;
import omis.disciplinaryCode.report.SupervisoryOrganizationDisciplinarySummary;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * DisciplinaryCodeReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeReportServiceHibernateImpl 
	implements DisciplinaryCodeReportService {
	
	/* Query names */
	
	private static final String 
		FIND_DISCIPLINARY_CODE_SUMMARIES_BY_SUPERVISORY_ORGANIZATION 
			= "findDisciplinaryCodeSummariesBySupervisoryOrganization";
	
	private static final String FIND_ALL_SUPERVISORY_ORGANIZATION_SUMMARIES
			= "findAllSupervisoryOrganizationSummaries";
	
	private static final String 
	FIND_DISCIPLINARY_CODE_SUMMARIES_WITH_DATE_RANGE_BY_SUPERVISORY_ORGANIZATION 
	= "findDisciplinaryCodeSummariesWithDateRangeBySupervisoryOrganization";
	
	/* Parameter names */
	
	private static final String SUPERVISORY_ORGANIZATION_PARAMETER_NAME 
		= "supervisoryOrganization";

	private static final String EFFECTIVE_DATE_PARAMETER_NAME = "effectiveDate";

	private static final String START_DATE_PARAMETER_NAME = "startDate";

	private static final String END_DATE_PARAMETER_NAME = "endDate";

	
	
	/* Members */
	
	private final SessionFactory sessionFactory;

	/**
	 * Constructor
	 * @param sessionFactory - session factory
	 */
	public DisciplinaryCodeReportServiceHibernateImpl(
			SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public List<SupervisoryOrganizationDisciplinaryCodeSummary> 
		findDisciplinaryCodeSummariesBySupervisoryOrganization(
			SupervisoryOrganization supervisoryOrganization, Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganizationDisciplinaryCodeSummary> 
			supervisoryOrganizationDisciplinaryCodeSummaries 
				= this.sessionFactory.getCurrentSession()
					.getNamedQuery(
					FIND_DISCIPLINARY_CODE_SUMMARIES_BY_SUPERVISORY_ORGANIZATION)
					.setParameter(SUPERVISORY_ORGANIZATION_PARAMETER_NAME, 
							supervisoryOrganization)
					.setParameter(EFFECTIVE_DATE_PARAMETER_NAME, 
							effectiveDate)
					.list();
		return supervisoryOrganizationDisciplinaryCodeSummaries;
	}

	/**{@inheritDoc} */
	@Override
	public List<SupervisoryOrganizationDisciplinarySummary> 
		findAllSupervisoryOrganizationSummaries() {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganizationDisciplinarySummary> 
			supervisoryOrganizationDisciplinarySummaries 
				= this.sessionFactory.getCurrentSession()
					.getNamedQuery(FIND_ALL_SUPERVISORY_ORGANIZATION_SUMMARIES)
					.list();
		return supervisoryOrganizationDisciplinarySummaries;
	}

	/**{@inheritDoc} */
	@Override
	public List<SupervisoryOrganizationDisciplinaryCodeSummary> 
	findDisciplinaryCodeSummariesBySupervisoryOrganization(
			SupervisoryOrganization supervisoryOrganization, Date effectiveDate,
				Date startDate, Date endDate) {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganizationDisciplinaryCodeSummary> 
			supervisoryOrganizationDisciplinaryCodeSummaries 
				= this.sessionFactory.getCurrentSession()
					.getNamedQuery(
FIND_DISCIPLINARY_CODE_SUMMARIES_WITH_DATE_RANGE_BY_SUPERVISORY_ORGANIZATION)
					.setParameter(SUPERVISORY_ORGANIZATION_PARAMETER_NAME, 
							supervisoryOrganization)
					.setParameter(EFFECTIVE_DATE_PARAMETER_NAME, 
							effectiveDate)
					.setTimestamp(START_DATE_PARAMETER_NAME, startDate)
					
					.setTimestamp(END_DATE_PARAMETER_NAME, endDate)
					.list();
		return supervisoryOrganizationDisciplinaryCodeSummaries;
	}

	
	
	
}
