/**
 * DetainerWarrantProcessStatusDelegate
 * 
 *@author Annie Jacques 
 *@author Joel Norris
 *@version 0.1.2 (May 30, 2017)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.dao.DetainerWarrantProcessingStatusDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerWarrantCancellationReason;
import omis.detainernotification.domain.DetainerWarrantProcessingStatus;
import omis.detainernotification.domain.component.DetainerWarrantCancellation;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Unit;
import omis.instance.factory.InstanceFactory;

public class DetainerWarrantProcessStatusDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
	= "Detainer Warrant Process Status exists for given detainer.";
	
	private final DetainerWarrantProcessingStatusDao 
		detainerWarrantProcessingStatusDao;
	
	private final InstanceFactory<DetainerWarrantProcessingStatus> 
		detainerWarrantProcessStatusInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor
	 * @param detainerWarrantProcessingStatusDao - detainer warrant 
	 * processing request dao
	 * @param detainerWarrantProcessingStatusInstanceFactory - detainer warrant 
	 * proccessing status instance factory
	 * @param auditComponentRetriever - audit component retriever
	 */
	public DetainerWarrantProcessStatusDelegate(
			final DetainerWarrantProcessingStatusDao 
				detainerWarrantProcessingStatusDao, 
				final InstanceFactory<DetainerWarrantProcessingStatus> 
					detainerWarrantProcessStatusInstanceFactory, 
				final AuditComponentRetriever auditComponentRetriever){
		
		this.detainerWarrantProcessingStatusDao = 
				detainerWarrantProcessingStatusDao;
		this.detainerWarrantProcessStatusInstanceFactory = 
				detainerWarrantProcessStatusInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a detainer warrant processing status
	 * @param detainer - detainer
	 * @param sentToFacilityDate - sent to facility date
	 * @param facility - facility
	 * @param inmateServedDate - inmate served date
	 * @param refusedToSign - refused to sign
	 * @param waiverRequired - waiver required
	 * @param unit - unit
	 * @param cancellationDate - cancellation date
	 * @param detainweWarrantCancellationReason - detainer warrant cancellation 
	 * reason
	 * @param comments - comments
	 * @return DetainerWarrantProcessingStatus - Detainer Warrant Processing 
	 * Status
	 * @throws DuplicateEntityFoundException - when given Detainer Warrant
	 *  Processing Status exists for given detainer
	 */
	public DetainerWarrantProcessingStatus create(final Detainer detainer, 
			final Date sentToFacilityDate, final Facility facility,
			final Unit unit, final Complex complex,
			final Date inmateServedDate, final boolean refusedToSign, 
			final boolean waiverRequired, 
			final Date cancellationDate, final DetainerWarrantCancellationReason 
				detainerWarrantCancellationReason,
			final String refusedToSignComment, 
			final String waiverRequiredComment,
			final String facilityName)
						throws DuplicateEntityFoundException{
		if(this.detainerWarrantProcessingStatusDao.find(detainer) != null){
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
			}
		
		DetainerWarrantProcessingStatus detainerWarrantProcessingStatus =
		this.detainerWarrantProcessStatusInstanceFactory.createInstance();
		
		detainerWarrantProcessingStatus.setCancellation(
				new DetainerWarrantCancellation(cancellationDate, 
						detainerWarrantCancellationReason));
		detainerWarrantProcessingStatus.setRefusedToSignComment(
				refusedToSignComment);
		detainerWarrantProcessingStatus.setWaiverRequiredComment(
				waiverRequiredComment);
		detainerWarrantProcessingStatus.setDetainer(detainer);
		detainerWarrantProcessingStatus.setFacility(facility);
		detainerWarrantProcessingStatus.setInmateServedDate(inmateServedDate);
		detainerWarrantProcessingStatus.setRefusedToSign(refusedToSign);
		detainerWarrantProcessingStatus.setSentToFacilityDate(sentToFacilityDate);
		detainerWarrantProcessingStatus.setUnit(unit);
		detainerWarrantProcessingStatus.setComplex(complex);
		detainerWarrantProcessingStatus.setWaiverRequired(waiverRequired);
		detainerWarrantProcessingStatus.setFacilityName(facilityName);
		detainerWarrantProcessingStatus.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		detainerWarrantProcessingStatus.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return this.detainerWarrantProcessingStatusDao.
				makePersistent(detainerWarrantProcessingStatus);
	}
	
	/**
	 * Updates existing detainer warrant processing status
	 * @param detainerWarrantProcessingStatus - detainer warrant processing status
	 * @param sentToFacilityDate - sent to facility date
	 * @param facility - facility
	 * @param inmateServedDate - inmate served date
	 * @param refusedToSign - refused to sign
	 * @param waiverRequired - waiver required
	 * @param unit - unit
	 * @param cancellationDate - cancellation date
	 * @param detainweWarrantCancellationReason - detainer warrant cancellation 
	 * reason
	 * @param refusedToSignComment refused to sign comment
	 * @param waiverRequiredComment waiver required comment
	 * @return DetainerWarrantProcessingStatus - Detainer Warrant Processing 
	 * Status
	 * @throws DuplicateEntityFoundException - when given Detainer Warrant
	 *  Processing Status exists for given detainer
	 */
	public DetainerWarrantProcessingStatus update(
			final DetainerWarrantProcessingStatus 
				detainerWarrantProcessingStatus,
			final Date sentToFacilityDate, final Facility facility,
			final Unit unit, final Complex complex,
			final Date inmateServedDate, final boolean refusedToSign, 
			final boolean waiverRequired,
			final Date cancellationDate, final DetainerWarrantCancellationReason 
				detainerWarrantCancellationReason,
			final String refusedToSignComment, 
			final String waiverRequiredComment,
			final String facilityName)
				throws DuplicateEntityFoundException{
		if(this.detainerWarrantProcessingStatusDao.findExcluding(
				detainerWarrantProcessingStatus.getDetainer(), 
				detainerWarrantProcessingStatus) != null){
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
			}
		
		detainerWarrantProcessingStatus.setCancellation(
				new DetainerWarrantCancellation(cancellationDate, 
						detainerWarrantCancellationReason));
		detainerWarrantProcessingStatus.setRefusedToSignComment(
				refusedToSignComment);
		detainerWarrantProcessingStatus.setWaiverRequiredComment(
				waiverRequiredComment);
		detainerWarrantProcessingStatus.setFacility(facility);
		detainerWarrantProcessingStatus.setInmateServedDate(inmateServedDate);
		detainerWarrantProcessingStatus.setRefusedToSign(refusedToSign);
		detainerWarrantProcessingStatus.setSentToFacilityDate(sentToFacilityDate);
		detainerWarrantProcessingStatus.setUnit(unit);
		detainerWarrantProcessingStatus.setComplex(complex);
		detainerWarrantProcessingStatus.setWaiverRequired(waiverRequired);
		detainerWarrantProcessingStatus.setFacilityName(facilityName);
		detainerWarrantProcessingStatus.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return this.detainerWarrantProcessingStatusDao.
				makePersistent(detainerWarrantProcessingStatus);
	}
	
	/**
	 * Removes given detainer warrant processing status
	 * @param detainerWarrantProcessingStatus - detainer warrant processing status
	 */
	public void remove(final DetainerWarrantProcessingStatus 
			detainerWarrantProcessingStatus){
		this.detainerWarrantProcessingStatusDao.
			makeTransient(detainerWarrantProcessingStatus);
	}
	
	/**
	 * Finds an detainer warrant processing status by specified detainer
	 * @param detainer - detainer
	 * @return detainer warrant processing status
	 */
	public DetainerWarrantProcessingStatus find(final Detainer detainer){
		return this.detainerWarrantProcessingStatusDao.find(detainer);
	}
}