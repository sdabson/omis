package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.dao.BiographicAndContactSectionDao;
import omis.presentenceinvestigation.domain.BiographicAndContactSection;

/**
 * BiographicalAndContactSection data access object hibernate
 * implementation. 
 * @author Jonny Santy
 * @version 0.1.0 (Oct 27, 2016)
 * @since OMIS 3.0
 */
public class BiographicAndContactSectionDaoHibernateImpl 
extends GenericHibernateDaoImpl<BiographicAndContactSection>
implements BiographicAndContactSectionDao {

	/* Query names. */
	
	private static final String
	FIND_BIOGRAPHIC_AND_CONTACT_SECTION_QUERY_NAME
	 = "findBiographicAndContactSection";
	
	private static final String
	FIND_BIOGRAPHIC_AND_CONTACT_SECTION_EXCLUDING_QUERY_NAME
	= "findBiographicAndContactSectionExcluding";
	
	/* Parameter names. */
	
	private static final String BIOGRAPHIC_AND_CONTACT_SECTION_PARAM_NAME
		= "biographicAndContactSection";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME
		= "presentenceInvestigationRequest";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String DATE_OF_REPORT_PARAM_NAME = "dateOfReport";
	
	/* Constructor. */
	
	public BiographicAndContactSectionDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/**{@inheritDoc} */
	@Override
	public BiographicAndContactSection find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String name, 
			final Date dateOfReport) {
		BiographicAndContactSection biographicAndContactSection =
				(BiographicAndContactSection) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_BIOGRAPHIC_AND_CONTACT_SECTION_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, presentenceInvestigationRequest)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(DATE_OF_REPORT_PARAM_NAME, dateOfReport)
				.uniqueResult();
		return biographicAndContactSection;
	}

	/** {@inheritDoc} */
	@Override
	public BiographicAndContactSection findExcluding(
			BiographicAndContactSection biographicAndContactSection,
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String name,
			Date dateOfReport
			) {
		BiographicAndContactSection request =
				(BiographicAndContactSection) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_BIOGRAPHIC_AND_CONTACT_SECTION_EXCLUDING_QUERY_NAME)
				.setParameter(BIOGRAPHIC_AND_CONTACT_SECTION_PARAM_NAME, biographicAndContactSection)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, presentenceInvestigationRequest)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(DATE_OF_REPORT_PARAM_NAME, dateOfReport)
				.uniqueResult();
		return request;
	}
}