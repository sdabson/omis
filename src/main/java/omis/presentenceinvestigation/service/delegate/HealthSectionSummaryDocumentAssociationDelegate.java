package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.HealthSectionSummaryDocumentAssociationDao;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryDocumentAssociation;

/**
 * Health section summary document association delegate.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 9, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryDocumentAssociationDelegate {
	
	private final HealthSectionSummaryDocumentAssociationDao 
		healthSectionSummaryDocumentAssociationDao;
	
	private final InstanceFactory<HealthSectionSummaryDocumentAssociation> 
		healthSectionSummaryDocumentAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/** Instantiates an implementation of HealthSectionSummaryDocumentAssociationDelegate */
	public HealthSectionSummaryDocumentAssociationDelegate(
			final HealthSectionSummaryDocumentAssociationDao 
				healthSectionSummaryDocumentAssociationDao,
			final InstanceFactory<HealthSectionSummaryDocumentAssociation> 
				healthSectionSummaryDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.healthSectionSummaryDocumentAssociationDao 
			= healthSectionSummaryDocumentAssociationDao;
		this.healthSectionSummaryDocumentAssociationInstanceFactory 
			= healthSectionSummaryDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Create a health section summary document association.
	 *
	 * @param healthSectionSummary health section summary
	 * @param document document
	 * @return new health section summary document association
	 */
	public HealthSectionSummaryDocumentAssociation 
		createHealthSectionSummaryDocumentAssociation(
			final HealthSectionSummary healthSectionSummary, 
			final Document document) 
					throws DuplicateEntityFoundException {
		if (this.healthSectionSummaryDocumentAssociationDao
				.find(healthSectionSummary, document) != null) {
			throw new DuplicateEntityFoundException("Health section summary "
					+ "document already associated.");
		}
		
		HealthSectionSummaryDocumentAssociation documentAssociation 
			= this.healthSectionSummaryDocumentAssociationInstanceFactory
			.createInstance();
		documentAssociation.setHealthSectionSummary(healthSectionSummary);
		documentAssociation.setDocument(document);
		documentAssociation.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		documentAssociation.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.healthSectionSummaryDocumentAssociationDao.makePersistent(
				documentAssociation);
	}

	/**
	 * Updates an existing health section summary document association.
	 *
	 * @param healthSectionSummaryDocumentAssociation health section summary 
	 * document association
	 * @param healthSectionSummary health section summary
	 * @param document document
	 * @return updated health section summary document association
	 */
	public HealthSectionSummaryDocumentAssociation 
		updateHealthSectionSummaryDocumentAssociation(
			final HealthSectionSummaryDocumentAssociation 
				healthSectionSummaryDocumentAssociation,
			final HealthSectionSummary healthSectionSummary, 
			final Document document) throws DuplicateEntityFoundException {
		if (this.healthSectionSummaryDocumentAssociationDao
				.findExcluding(healthSectionSummaryDocumentAssociation, 
						healthSectionSummary, document) != null) {
			throw new DuplicateEntityFoundException("Health section summary "
					+ "document already associated.");
		}
		
		healthSectionSummaryDocumentAssociation.setDocument(document);
		healthSectionSummaryDocumentAssociation.setHealthSectionSummary(
				healthSectionSummary);
		healthSectionSummaryDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.healthSectionSummaryDocumentAssociationDao.makePersistent(
				healthSectionSummaryDocumentAssociation);
	}

	/**
	 * Removes a health section summary document association.
	 *
	 * @param healthSectionSummaryDocumentAssociation health section summary 
	 * document association
	 */
	public void removeHealthSectionSummaryDocumentAssociation(
			HealthSectionSummaryDocumentAssociation healthSectionSummaryDocumentAssociation) {
		this.healthSectionSummaryDocumentAssociationDao.makeTransient(
				healthSectionSummaryDocumentAssociation);
	}

	/**
	 * Find a list of health section summary document associations by health 
	 * section summary.
	 *
	 * @param healthSectionSummary health section summary
	 * @return list of health section summary document association
	 */
	public List<HealthSectionSummaryDocumentAssociation> findHealthSectionSummaryDocumentAssociation(
			HealthSectionSummary healthSectionSummary) {
		return this.healthSectionSummaryDocumentAssociationDao
				.findHealthSectionSummaryDocumentAssociation(
						healthSectionSummary);
	}
}