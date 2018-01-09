/**
 * DetainerDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 8, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.dao.DetainerDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerAgency;
import omis.detainernotification.domain.DetainerJurisdictionCategory;
import omis.detainernotification.domain.DetainerType;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

public class DetainerDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
	= "Detainer exists for this offender.";
	
	private final DetainerDao detainerDao;
	
	private final InstanceFactory<Detainer> detainerInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor
	 * @param detainerDao  detainer dao
	 * @param detainerInstanceFactory - detainer instance factory
	 * @param auditComponenentRetriever - audit component retriever
	 */
	public DetainerDelegate(final DetainerDao detainerDao, 
			final InstanceFactory<Detainer> detainerInstanceFactory, 
			final AuditComponentRetriever auditComponentRetriever){
		this.detainerDao = detainerDao;
		this.detainerInstanceFactory = detainerInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/** 
	 * Creates a detainer.
	 * @param offender - offender
	 * @param alternateOffenderId - alternate offender ID
	 * @param offenseDescription - offense description
	 * @param courtCaseNumber - court case number
	 * @param detainerType - detainer type
	 * @param detainerAgency - detainer agency
	 * @param jurisdiction - detainer jurisdiction category
	 * @param warrantReceivedDate - warrant received date
	 * @param warrantIssuedDate - warrant issued date
	 * @param warrantNumber - warrant number
	 * @return detainer - detainer
	 * @throws DuplicateEntityFoundException - when Detainer exists for given 
	 * offender, detainer type, and received date.
	 */
	public Detainer create(final Offender offender, 
			final String alternateOffenderId, final String offenseDescription,
			final String courtCaseNumber, final DetainerType detainerType,
			final DetainerAgency detainerAgency, 
			final DetainerJurisdictionCategory jurisdiction, 
			final Date warrantReceivedDate, final Date warrantIssuedDate, 
			final String warrantNumber)throws DuplicateEntityFoundException{
		if(this.detainerDao.find(offender, warrantReceivedDate,
				courtCaseNumber, warrantNumber) != null){
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		Detainer detainer = this.detainerInstanceFactory.createInstance();
		
		detainer.setAlternateOffenderId(alternateOffenderId);
		detainer.setCourtCaseNumber(courtCaseNumber);
		detainer.setDetainerAgency(detainerAgency);
		detainer.setDetainerType(detainerType);
		detainer.setReceiveDate(warrantReceivedDate);
		detainer.setIssueDate(warrantIssuedDate);
		detainer.setWarrantNumber(warrantNumber);
		detainer.setJurisdiction(jurisdiction);
		detainer.setOffender(offender);
		detainer.setOffenseDescription(offenseDescription);
		detainer.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		detainer.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.detainerDao.makePersistent(detainer);
	}
		
		
	/**
	 * Updates an existing detainer.
	 * @param detainer - detainer
	 * @param alternateOffenderId - alternate offender ID
	 * @param offenseDescription - offense description
	 * @param courtCaseNumber - court case number
	 * @param detainerType - detainer type
	 * @param detainerAgency - detainer agency
	 * @param jurisdiction - detainer jurisdiction category
	 * @param warrantReceivedDate - warrant received date
	 * @param warrantIssuedDate - warrant issued date
	 * @param warrantNumber - warrant number
	 * @throws DuplicateEntityFoundException - when Detainer exists for given 
	 * offender, detainer type, and received date.
	 */
	public Detainer update(final Detainer detainer, 
			final String alternateOffenderId, final String offenseDescription,
			final String courtCaseNumber, final DetainerType detainerType,
			final DetainerAgency detainerAgency, 
			final DetainerJurisdictionCategory jurisdiction, 
			final Date warrantReceivedDate, final Date warrantIssuedDate, 
			final String warrantNumber)throws DuplicateEntityFoundException{
		
		if(this.detainerDao.findExcluding(detainer.getOffender(), 
				warrantReceivedDate, courtCaseNumber, warrantNumber,
				detainer) != null){
			throw new DuplicateEntityFoundException(
						DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		detainer.setAlternateOffenderId(alternateOffenderId);
		detainer.setCourtCaseNumber(courtCaseNumber);
		detainer.setDetainerAgency(detainerAgency);
		detainer.setDetainerType(detainerType);
		detainer.setReceiveDate(warrantReceivedDate);
		detainer.setIssueDate(warrantIssuedDate);
		detainer.setWarrantNumber(warrantNumber);
		detainer.setJurisdiction(jurisdiction);
		detainer.setOffenseDescription(offenseDescription);
		detainer.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return this.detainerDao.makePersistent(detainer);
	}
	
	/**
	 * Removes specified detainer
	 * @param detainer - detainer
	 */
	public void remove(final Detainer detainer){
		this.detainerDao.makeTransient(detainer);
	}
	
	/**
	 * Finds detainers with the specified offender
	 * @param offender - offender
	 * @return detainers with the specified offender
	 */
	public List<Detainer> findByOffender(final Offender offender){
		return this.detainerDao.findByOffender(offender);
	}
	
}
