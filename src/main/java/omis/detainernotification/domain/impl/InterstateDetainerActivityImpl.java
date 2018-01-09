package omis.detainernotification.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.domain.DetainerActivityCategory;
import omis.detainernotification.domain.Direction;
import omis.detainernotification.domain.InterstateAgreementDetainer;
import omis.detainernotification.domain.InterstateDetainerActivity;
import omis.document.domain.Document;

/**
 * InterstateDetainerActivityImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class InterstateDetainerActivityImpl implements InterstateDetainerActivity {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private InterstateAgreementDetainer interstateAgreementDetainer;
	
	private Date activityDate;
	
	private Direction direction;
	
	private Document document;
	
	private DetainerActivityCategory category;
	
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
	public InterstateAgreementDetainer getInterstateAgreementDetainer() {
		return this.interstateAgreementDetainer;
	}

	/**{@inheritDoc} */
	@Override
	public void setInterstateAgreementDetainer(
			final InterstateAgreementDetainer interstateAgreementDetainer) {
		this.interstateAgreementDetainer = interstateAgreementDetainer;
	}

	/**{@inheritDoc} */
	@Override
	public Date getActivityDate() {
		return this.activityDate;
	}

	/**{@inheritDoc} */
	@Override
	public void setActivityDate(final Date activityDate) {
		this.activityDate = activityDate;
	}

	/**{@inheritDoc} */
	@Override
	public Direction getDirection() {
		return this.direction;
	}

	/**{@inheritDoc} */
	@Override
	public void setDirection(final Direction direction) {
		this.direction = direction;
	}

	/**{@inheritDoc} */
	@Override
	public Document getDocument() {
		return this.document;
	}

	/**{@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**{@inheritDoc} */
	@Override
	public DetainerActivityCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(final DetainerActivityCategory category) {
		this.category = category;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof InterstateDetainerActivity)){
			return false;
		}
		
		InterstateDetainerActivity that = (InterstateDetainerActivity) obj;
		
		if(this.getInterstateAgreementDetainer() == null){
			throw new IllegalStateException("InterstateAgreementDetainer required.");
		}
		if(this.getDirection() == null){
			throw new IllegalStateException("Direction required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		
		if(!this.getInterstateAgreementDetainer().equals(
				that.getInterstateAgreementDetainer())){
			return false;
		}
		if(!this.getActivityDate().equals(that.getActivityDate())){
			return false;
		}
		if(!this.getDocument().equals(that.getDocument())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getInterstateAgreementDetainer() == null){
			throw new IllegalStateException("InterstateAgreementDetainer required.");
		}
		if(this.getDirection() == null){
			throw new IllegalStateException("Direction required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getInterstateAgreementDetainer().hashCode();
		hashCode = 29 * hashCode + this.getActivityDate().hashCode();
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		
		return hashCode;
	}
	
	
}
