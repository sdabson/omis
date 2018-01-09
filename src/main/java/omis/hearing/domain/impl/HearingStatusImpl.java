package omis.hearing.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingStatus;
import omis.hearing.domain.HearingStatusCategory;

/**
 * HearingStatusImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public class HearingStatusImpl implements HearingStatus {

	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Hearing hearing;
	
	private Date date;
	
	private String description;
	
	private HearingStatusCategory category;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
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
	public Hearing getHearing() {
		return this.hearing;
	}

	/**{@inheritDoc} */
	@Override
	public void setHearing(final Hearing hearing) {
		this.hearing = hearing;
	}

	/**{@inheritDoc} */
	@Override
	public HearingStatusCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(final HearingStatusCategory category) {
		this.category = category;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof HearingStatus)){
			return false;
		}
		
		HearingStatus that = (HearingStatus) obj;
		
		if(this.getHearing() == null){
			throw new IllegalStateException("Hearing required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		
		if(!this.getHearing().equals(that.getHearing())){
			return false;
		}
		if(!this.getDate().equals(that.getDate())){
			return false;
		}
		if(!this.getCategory().equals(that.getCategory())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getHearing() == null){
			throw new IllegalStateException("Hearing required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getHearing().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		
		return hashCode;
	}
	
}
