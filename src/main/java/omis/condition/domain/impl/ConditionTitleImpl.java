package omis.condition.domain.impl;

import omis.condition.domain.ConditionTitle;

/** Agreement
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @version 0.1.0 (Jul 7, 2016)
 * @since OMIS 3.0 
 * */
public class ConditionTitleImpl implements ConditionTitle{

	private static final long serialVersionUID = 1L;

	private static final String EMPTY_TITLE_MSG = "Title Required";
	
	
	private Long id;
	private String title;

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
	public String getTitle() {
		return this.title;
	}

	/** {@inheritDoc} */
	@Override
	public void setTitle(final String title) {
		this.title = title;
		
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		}else if (obj instanceof ConditionTitle) {
			this.checkState();
			ConditionTitle that 
				= (ConditionTitle) obj;
			if (this.getTitle().equals(that.getTitle()))
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

		hashCode = 29 * hashCode + this.getTitle().hashCode();
		return hashCode;
	}

	/* Checks state. */
	private void checkState() {

		if (this.getTitle() == null) {
			throw new IllegalStateException(EMPTY_TITLE_MSG);
			
		}
	}

}
