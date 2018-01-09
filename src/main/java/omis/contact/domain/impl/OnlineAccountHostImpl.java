package omis.contact.domain.impl;

import omis.contact.domain.OnlineAccountCategory;
import omis.contact.domain.OnlineAccountHost;

/** Implementation of online account host.
 * @author Yidong Li
 * @version 0.1.0 (April 1, 2015)
 * @since OMIS 3.0 */
public class OnlineAccountHostImpl implements OnlineAccountHost {
	private static final long serialVersionUID = 1L;
	private Long id;
	private OnlineAccountCategory category;
	private String name;
	private Boolean valid;

	/** Constructor. */
	public OnlineAccountHostImpl() {
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
	public OnlineAccountCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(OnlineAccountCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OnlineAccountHost)) {
			return false;
		}
		
		OnlineAccountHost that = (OnlineAccountHost) obj;
		
		if(this.getName() == null){
			throw new IllegalStateException("Name required");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid required");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Online account category required");
		}
		if(this.getName().equals(that.getName())&&
			this.getCategory().equals(that.getCategory())){
			return true;
		} else {
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		
		if(this.getName() == null){
			throw new IllegalStateException("Name required");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid required");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Online account category required");
		}
		hashCode += 29 * this.getName().hashCode();
		hashCode += 29 * this.getValid().hashCode();
		hashCode += 29 * this.getCategory().hashCode();
		return hashCode;
	}
}
