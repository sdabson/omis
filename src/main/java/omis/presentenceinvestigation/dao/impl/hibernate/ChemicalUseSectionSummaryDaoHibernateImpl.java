package omis.presentenceinvestigation.dao.impl.hibernate;



import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.ChemicalUseSectionSummaryDao;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * ChemicalUseSectionSummaryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ChemicalUseSectionSummaryDaoHibernateImpl
	extends GenericHibernateDaoImpl<ChemicalUseSectionSummary>
	implements ChemicalUseSectionSummaryDao {
	
	private static final String FIND_CHEMICAL_USE_SECTION_SUMMARY_QUERY_NAME =
			"findChemicalUseSectionSummary";
	
	private static final String
		FIND_CHEMICAL_USE_SECTION_SUMMARY_EXCLUDING_QUERY_NAME =
			"findChemicalUseSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME =
			"presentenceInvestigationRequest";
	
	private static final String CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME =
			"chemicalUseSectionSummary";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected ChemicalUseSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummary find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		ChemicalUseSectionSummary chemicalUseSectionSummary = 
				(ChemicalUseSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CHEMICAL_USE_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return chemicalUseSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final ChemicalUseSectionSummary chemicalUseSectionSummaryExcluded) {
		ChemicalUseSectionSummary chemicalUseSectionSummary = 
				(ChemicalUseSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CHEMICAL_USE_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.setParameter(CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME,
						chemicalUseSectionSummaryExcluded)
				.uniqueResult();
		
		return chemicalUseSectionSummary;
	}

}
