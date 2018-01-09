package omis.family.report.impl.hibernate;

import java.util.List;
import java.util.ArrayList;

import omis.family.report.FamilyAssociationReportService;
import omis.family.report.FamilyAssociationSummary;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of offender family association report service.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.0 (June 22, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationReportServiceHibernateImpl
		implements FamilyAssociationReportService {

	/* Queries */
	private static final String 
		FIND_FAMILY_ASSOCIATION_SUMMARY_BY_OFFENDER_QUERY_NAME 
		= "findFamilyAssociationSummaryByOffender";
	
	/* Parameters */
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private SessionFactory sessionFactory;
		
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of offender family association 
	 * report service.
	 * @param sessionFactory session factory
	 */

	public FamilyAssociationReportServiceHibernateImpl(final SessionFactory
		sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	/** {@inheritDoc} */
	@Override 
	public List<FamilyAssociationSummary> findByOffender(
		final Offender offender) {
		List<FamilyAssociationSummary> summaries
			= new ArrayList<FamilyAssociationSummary>();
		@SuppressWarnings("unchecked")
		List<FamilyAssociationSummary> internalSummaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(
					FIND_FAMILY_ASSOCIATION_SUMMARY_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		summaries.addAll(internalSummaries); 
		return summaries;
	}

}