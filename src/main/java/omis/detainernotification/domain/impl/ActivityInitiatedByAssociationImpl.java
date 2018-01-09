package omis.detainernotification.domain.impl;

import omis.detainernotification.domain.ActivityInitiatedByAssociation;
import omis.detainernotification.domain.DetainerActivityCategory;
import omis.detainernotification.domain.InterstateAgreementInitiatedByCategory;

/**
 * ActivityInitiatedByAssociationImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class ActivityInitiatedByAssociationImpl implements ActivityInitiatedByAssociation {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private InterstateAgreementInitiatedByCategory initiatedBy;
	
	private DetainerActivityCategory activity;
	
	
	
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
	public InterstateAgreementInitiatedByCategory getInitiatedBy() {
		return this.initiatedBy;
	}

	/**{@inheritDoc} */
	@Override
	public void setInitiatedBy(final InterstateAgreementInitiatedByCategory
			initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	/**{@inheritDoc} */
	@Override
	public DetainerActivityCategory getActivity() {
		return this.activity;
	}

	/**{@inheritDoc} */
	@Override
	public void setActivity(final DetainerActivityCategory activity) {
		this.activity = activity;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof ActivityInitiatedByAssociationImpl)){
			return false;
		}
		
		ActivityInitiatedByAssociationImpl that = (ActivityInitiatedByAssociationImpl) obj;
		
		if(this.getInitiatedBy() == null){
			throw new IllegalStateException("InitiatedBy required.");
		}
		if(this.getActivity() == null){
			throw new IllegalStateException("Activity required.");
		}
		
		if(!this.getInitiatedBy().equals(that.getInitiatedBy())){
			return false;
		}
		if(!this.getActivity().equals(that.getActivity())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getInitiatedBy() == null){
			throw new IllegalStateException("InitiatedBy required.");
		}
		if(this.getActivity() == null){
			throw new IllegalStateException("Activity required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getInitiatedBy().hashCode();
		hashCode = 29 * hashCode + this.getActivity().hashCode();
		
		return hashCode;
	}
	
	
	
}
