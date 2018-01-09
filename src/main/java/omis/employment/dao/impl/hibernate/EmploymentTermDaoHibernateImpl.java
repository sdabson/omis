package omis.employment.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.employment.dao.EmploymentTermDao;
import omis.employment.domain.Employer;
import omis.employment.domain.EmploymentChangeReason;
import omis.employment.domain.EmploymentTerm;
import omis.employment.domain.component.Job;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Employment term data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Dec 14, 2017)
 * @since OMIS 3.0
 */
public class EmploymentTermDaoHibernateImpl 
	extends GenericHibernateDaoImpl<EmploymentTerm>	
	implements EmploymentTermDao {

	/* Query names. */
	private static final String FIND_EMPLOYMENT_TERM_QUERY_NAME
 		= "findEmploymentTerm";
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findEmploymentTermExcluding";
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findEmploymentTermsByOffender";
	private static final String FIND_EMPLOYMENT_TERMS_BY_EMPLOYER_QUERY_NAME
		= "findEmploymentTermsByEmployer";

	/* Parameter names. */
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	private static final String START_DATE_PARAMETER_NAME = "startDate";
	private static final String END_DATE_PARAMETER_NAME = "endDate";
	private static final String EXCLUDED_EMPLOYMENT_TERM_PARAMETER_NAME 
		= "excludedEmploymentTerm";
	private static final String TITLE_PARAMETER_NAME = "title";
	private static final String CHANGE_REASON_PARAMETER_NAME = "endReason";
	private static final String END_REASON_PARAMETER_NAME = "endReason";
	private static final String JOB_TITLE_PARAMETER_NAME = "jobTitle";
	private static final String EMPLOYER_LOCATION_PARAMETER_NAME 
		= "employerLocation";
	private static final String EMPLOYER_PARAMETER_NAME 
		= "employer";
	
	/**
	 * Instantiates an instance of employment term data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public EmploymentTermDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public EmploymentTerm find(final Offender offender, 
		final Employer employer, final DateRange dateRange, final Job job, 
		final EmploymentChangeReason endReason){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EMPLOYMENT_TERM_QUERY_NAME)
			.setParameter(OFFENDER_PARAMETER_NAME, offender)
			.setParameter(START_DATE_PARAMETER_NAME, dateRange.getStartDate())
			.setParameter(END_DATE_PARAMETER_NAME, dateRange.getEndDate())
			.setParameter(TITLE_PARAMETER_NAME, job.getJobTitle())
			.setParameter(EMPLOYER_PARAMETER_NAME, employer)
			.setParameter(CHANGE_REASON_PARAMETER_NAME, endReason);
		return (EmploymentTerm) q.uniqueResult() ; 
	}

	/** {@inheritDoc} */
	@Override
	public EmploymentTerm findExcluding(
		final EmploymentTerm excludedEmploymentTerm, 
		final DateRange dateRange, final Job job, 
		final EmploymentChangeReason endReason){
		EmploymentTerm employmentTermDuplicate;
		employmentTermDuplicate = (EmploymentTerm)getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
			.setParameter(EXCLUDED_EMPLOYMENT_TERM_PARAMETER_NAME, 
				excludedEmploymentTerm)
			.setParameter(OFFENDER_PARAMETER_NAME, 
					excludedEmploymentTerm.getOffender())
			.setParameter(START_DATE_PARAMETER_NAME, dateRange.getStartDate())
			.setParameter(END_DATE_PARAMETER_NAME, dateRange.getEndDate())
			.setParameter(JOB_TITLE_PARAMETER_NAME, job.getJobTitle())
			.setParameter(END_REASON_PARAMETER_NAME, endReason)
			.setParameter(EMPLOYER_LOCATION_PARAMETER_NAME, 
				job.getEmployer().getLocation())
			.uniqueResult();
		return employmentTermDuplicate;
	}
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<EmploymentTerm> findByOffender(final Offender offender) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_OFFENDER_QUERY_NAME);
			
			q.setParameter(OFFENDER_PARAMETER_NAME, offender);
			
			List<EmploymentTerm> result = (List<EmploymentTerm>) q.list();
			
			return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public EmploymentTerm findEmploymentTermByEmployer(
		final EmploymentTerm employmentTerm, 
		final Employer employer) {
		EmploymentTerm employmentTermDuplicate;
		Date startDate;
		Date endDate;
		if(employmentTerm.getDateRange()!=null){
			startDate = employmentTerm.getDateRange().getStartDate();
			endDate = employmentTerm.getDateRange().getEndDate();
		}
		else {
			startDate = null;
			endDate = null;
		}
		employmentTermDuplicate = (EmploymentTerm)getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EMPLOYMENT_TERMS_BY_EMPLOYER_QUERY_NAME)
			.setParameter(OFFENDER_PARAMETER_NAME, 
				employmentTerm.getOffender())
			.setParameter(START_DATE_PARAMETER_NAME, startDate)
			.setParameter(END_DATE_PARAMETER_NAME, endDate)
			.setParameter(JOB_TITLE_PARAMETER_NAME, 
				employmentTerm.getJob().getJobTitle())
			.setParameter(EMPLOYER_PARAMETER_NAME, employer)
			.setParameter(EXCLUDED_EMPLOYMENT_TERM_PARAMETER_NAME, employmentTerm)
			.uniqueResult();
		return employmentTermDuplicate;
	}
}

