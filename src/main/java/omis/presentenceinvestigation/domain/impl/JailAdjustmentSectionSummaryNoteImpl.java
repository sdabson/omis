package omis.presentenceinvestigation.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummaryNote;

/**
 * JailAdjustmentSectionSummaryNoteImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public class JailAdjustmentSectionSummaryNoteImpl
implements JailAdjustmentSectionSummaryNote {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private String description;
	
	private Date date;
	
	private JailAdjustmentSectionSummary jailAdjustmentSectionSummary;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/**{@inheritDoc} */
	@Override
	public JailAdjustmentSectionSummary getJailAdjustmentSectionSummary() {
		return this.jailAdjustmentSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public void setJailAdjustmentSectionSummary(
			final JailAdjustmentSectionSummary jailAdjustmentSectionSummary) {
		this.jailAdjustmentSectionSummary = jailAdjustmentSectionSummary;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof JailAdjustmentSectionSummaryNote)){
			return false;
		}
		
		JailAdjustmentSectionSummaryNote that = 
				(JailAdjustmentSectionSummaryNote) obj;
		
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getJailAdjustmentSectionSummary() == null){
			throw new IllegalStateException(
					"Jail Adjustment Section Summary required.");
		}
		
		if(!this.getDescription().equals(that.getDescription())){
			return false;
		}
		if(!this.getDate().equals(that.getDate())){
			return false;
		}
		if(!this.getJailAdjustmentSectionSummary().equals(
				that.getJailAdjustmentSectionSummary())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getJailAdjustmentSectionSummary() == null){
			throw new IllegalStateException(
					"Jail Adjustment Section Summary required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getJailAdjustmentSectionSummary()
				.hashCode();
		
		return hashCode;
	}
	
}
