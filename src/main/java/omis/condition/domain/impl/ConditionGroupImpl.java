package omis.condition.domain.impl;

import omis.condition.domain.ConditionGroup;

/** Condition Group Implementation
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.1 (Sep 26, 2017)
 * @since OMIS 3.0 
 * */
public class ConditionGroupImpl implements ConditionGroup {


	private static final long serialVersionUID = 1L;
	
	private static final String EMPTY_NAME_MSG = "Name required";
	
	private Long id;
	
	private String name;
	
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
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		}else if (obj instanceof ConditionGroup) {
			this.checkState();
			ConditionGroup that 
				= (ConditionGroup) obj;
			if (this.getName().equals(that.getName()))
			{
				result = true;
			}
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
		
	/* Checks state. */
	private void checkState() {

		if (this.getName() == null) {
			throw new IllegalStateException(EMPTY_NAME_MSG);
		}
	}
}
