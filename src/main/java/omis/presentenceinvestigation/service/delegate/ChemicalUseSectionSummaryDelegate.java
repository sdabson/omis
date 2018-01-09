package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.ChemicalUseSectionSummaryDao;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * ChemicalUseSectionSummaryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ChemicalUseSectionSummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A Chemical Use Section Summary already exists with specified "
			+ "Presentence Investigation Request";
	
	private final ChemicalUseSectionSummaryDao chemicalUseSectionSummaryDao;
	
	private final InstanceFactory<ChemicalUseSectionSummary> 
		chemicalUseSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ChemicalUseSectionSummaryDelegate
	 * @param chemicalUseSectionSummaryDao
	 * @param chemicalUseSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public ChemicalUseSectionSummaryDelegate(
			final ChemicalUseSectionSummaryDao chemicalUseSectionSummaryDao,
			final InstanceFactory<ChemicalUseSectionSummary> 
				chemicalUseSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.chemicalUseSectionSummaryDao = chemicalUseSectionSummaryDao;
		this.chemicalUseSectionSummaryInstanceFactory =
				chemicalUseSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a ChemicalUseSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param text - String
	 * @return Newly created ChemicalUseSectionSummary
	 * @throws DuplicateEntityFoundException - when a ChemicalUseSectionSummary
	 * already exists with the specified PresentenceInvestigationRequest
	 */
	public ChemicalUseSectionSummary create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)
					throws DuplicateEntityFoundException{
		if(this.chemicalUseSectionSummaryDao.find(presentenceInvestigationRequest)
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ChemicalUseSectionSummary chemicalUseSectionSummary = 
				this.chemicalUseSectionSummaryInstanceFactory.createInstance();
		
		chemicalUseSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		chemicalUseSectionSummary.setText(text);
		chemicalUseSectionSummary.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		chemicalUseSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.chemicalUseSectionSummaryDao.makePersistent(
				chemicalUseSectionSummary);
	}
	
	/**
	 * Updates a ChemicalUseSectionSummary with the specified properties
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary to update
	 * @param text - String
	 * @return Updated ChemicalUseSectionSummary
	 * @throws DuplicateEntityFoundException - When a ChemicalUseSectionSummary
	 * already exists with its PresentenceInvestigationRequest (should never
	 * happen on update) 
	 */
	public ChemicalUseSectionSummary update(
			final ChemicalUseSectionSummary chemicalUseSectionSummary,
			final String text)
					throws DuplicateEntityFoundException{
		if(this.chemicalUseSectionSummaryDao.findExcluding(
				chemicalUseSectionSummary.getPresentenceInvestigationRequest(),
				chemicalUseSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		chemicalUseSectionSummary.setText(text);
		chemicalUseSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.chemicalUseSectionSummaryDao.makePersistent(
				chemicalUseSectionSummary);
	}
	
	/**
	 * Removes a ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary to remove
	 */
	public void remove(final ChemicalUseSectionSummary chemicalUseSectionSummary){
		this.chemicalUseSectionSummaryDao.makeTransient(chemicalUseSectionSummary);
	}
	
	/**
	 * Finds and returns a ChemicalUseSectionSummary by specified 
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ChemicalUseSectionSummary found by specified 
	 * PresentenceInvestigationRequest
	 */
	public ChemicalUseSectionSummary findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		return this.chemicalUseSectionSummaryDao.find(
				presentenceInvestigationRequest);
	}
	
}
