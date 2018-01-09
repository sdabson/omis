package omis.detainernotification.domain.impl;

import omis.detainernotification.domain.DetainerActivityCategory;

/**
 * DetainerActivityCategoryImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class DetainerActivityCategoryImpl implements DetainerActivityCategory {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Boolean receivable;
	
	private Boolean sendable;

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
	public String getName() {
		return this.name;
	}

	/**{@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
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
	public Boolean getReceivable() {
		return this.receivable;
	}

	/**{@inheritDoc} */
	@Override
	public void setReceivable(final Boolean receivable) {
		this.receivable = receivable;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getSendable() {
		return this.sendable;
	}

	/**{@inheritDoc} */
	@Override
	public void setSendable(final Boolean sendable) {
		this.sendable = sendable;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof DetainerActivityCategory)){
			return false;
		}
		
		DetainerActivityCategory that = (DetainerActivityCategory) obj;
		
		if(this.getName() == null){
			throw new IllegalStateException("Name required.");
		}
		if(this.getReceivable() == null){
			throw new IllegalStateException("Receivable required.");
		}
		if(this.getSendable() == null){
			throw new IllegalStateException("Sendable required.");
		}
		
		if(!this.getName().equals(that.getName())){
			return false;
		}
		if(!this.getDescription().equals(that.getDescription())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getName() == null){
			throw new IllegalStateException("Name required.");
		}
		if(this.getReceivable() == null){
			throw new IllegalStateException("Receivable required.");
		}
		if(this.getSendable() == null){
			throw new IllegalStateException("Sendable required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		
		return hashCode;
	}
	
	
}
