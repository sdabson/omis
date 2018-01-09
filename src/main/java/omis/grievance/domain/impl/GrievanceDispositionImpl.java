package omis.grievance.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.grievance.domain.GrievanceLocation;
import omis.user.domain.UserAccount;

/**
 * Implementation of grievance disposition.
 *
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.0.1 (May 18, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionImpl 
	implements GrievanceDisposition {

	private static final long serialVersionUID = 1L;
	private Long id;
	private GrievanceDispositionReason reason;
	private Date receivedDate;
	private Date responseDueDate;
	private UserAccount responseBy;
	private Date responseToOffenderDate;
	private Date appealDate;
	private String notes;
	private Grievance grievance;
	private GrievanceDispositionStatus status;
	private GrievanceDispositionLevel level;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	

	/** Constructor. */
	public GrievanceDispositionImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setGrievance(final Grievance grievance){
		this.grievance = grievance;
	}
		
	/** {@inheritDoc} */
	@Override
	public Grievance getGrievance() {
		return this.grievance;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setLevel(final GrievanceDispositionLevel level) {
		this.level = level;
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionLevel getLevel(){
		return this.level;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setStatus(final GrievanceDispositionStatus status) {
		this.status = status;
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionStatus getStatus(){
		return this.status;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReason(final GrievanceDispositionReason reason) {
		this.reason = reason;
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionReason getReason() {
		return this.reason;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getReceivedDate() {
		return this.receivedDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setReceivedDate(final Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getResponseDueDate() {
		return this.responseDueDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setResponseDueDate(final Date responseDueDate) {
		this.responseDueDate = responseDueDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount getResponseBy() {
		return this.responseBy;
	}

	/** {@inheritDoc} */
	@Override
	public void setResponseBy(final UserAccount responseBy) {
		this.responseBy = responseBy;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setResponseToOffenderDate(final Date responseToOffenderDate){
		this.responseToOffenderDate = responseToOffenderDate;
	}
		
	/** {@inheritDoc} */
	@Override
	public Date getResponseToOffenderDate() {
		return this.responseToOffenderDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setAppealDate(final Date appealDate){
		this.appealDate = appealDate;
	}
		
	/** {@inheritDoc} */
	@Override
	public Date getAppealDate() {
		return this.appealDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setNotes(final String notes){
		this.notes = notes;
	}
		
	/** {@inheritDoc} */
	@Override
	public String getNotes() {
		return this.notes;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature){
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}
	
		/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GrievanceLocation)) {
			return false;
		}
		GrievanceDisposition that = (GrievanceDisposition) obj;
		if (this.getGrievance()==null) {
			throw new IllegalStateException("Grievance required");
		}
		if (!this.getGrievance().equals(that.getGrievance())){
			return false;
		}
		if (this.getLevel()==null) {
			throw new IllegalStateException("Level required");
		} 
		if (!this.getLevel().equals(that.getLevel())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		if (this.getGrievance()==null) {
			throw new IllegalStateException("Grievance required");
		}
		if (this.getLevel()==null) {
			throw new IllegalStateException("Level required");
		} 	
		hashCode += 29 * this.getGrievance().hashCode();
		hashCode += 29 * this.getLevel().hashCode();
		return hashCode;
	}
}