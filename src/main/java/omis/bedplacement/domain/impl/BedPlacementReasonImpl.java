/**
 * 
 */
package omis.bedplacement.domain.impl;

import omis.bedplacement.domain.BedPlacementReason;

/**
 * Movement reason.
 * 
 * @author Joel Norris (Feb, 06 2013)
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class BedPlacementReasonImpl implements BedPlacementReason {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	private Integer sortOrder;
	
	/** 
	 * Instantiates a default instance of bed placement reason.
	 */
	public BedPlacementReasonImpl() {
		//empty constructor
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof BedPlacementReason)) {
			return false;
		}
		
		BedPlacementReason that = (BedPlacementReason) o;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}