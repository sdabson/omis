package omis.mailroom.domain.impl;

import omis.mailroom.domain.DenialCategory;

/** Implementation of denial category.
 * @author Ryan Johns
 * @version 0.1.0 (May 27, 2016)
 * @since OMIS 3.0 */
public class DenialCategoryImpl 
	implements DenialCategory {
	public static final long serialVersionUID = 1l;
	private static final String DESCRIPTION_REQUIRED_MSG 
		= "Description required";
	private static final int[] HASHS = {3};
	
	private Long id;
	private String description;
	private Boolean incoming;
	private Boolean outgoing;
	private Boolean valid;
	
	/** Constructor. */
	public DenialCategoryImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getIncoming() { 
		return this.incoming;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getOutgoing() {
		return this.outgoing;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setIncoming(final Boolean incoming) {
		this.incoming = incoming;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOutgoing(final Boolean outgoing) {
		this.outgoing = outgoing;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	
	 /** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	public boolean equals(final Object obj) {
		Boolean result = false;
		
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof DenialCategory) {
				this.chackState();
				DenialCategory that = (DenialCategory) obj;
				if (this.getDescription().equals(that.getDescription())) {
					result = true;
				}
			}
		}
		return result;
	}
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	public int hashCode() {
		this.chackState();
		return this.getDescription().hashCode() * HASHS[0];
	}
	
	/* Private check state. */
	private void chackState() {
		if (this.getDescription() == null) {
			throw new IllegalStateException(DESCRIPTION_REQUIRED_MSG);
		}
	}
}
