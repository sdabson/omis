package omis.warrant.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCancellation;
import omis.warrant.domain.component.ClearSignature;

/**
 * WarrantCancellationImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCancellationImpl implements WarrantCancellation {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Warrant warrant;
	
	private Date date;
	
	private String comment;
	
	private ClearSignature clearSignature;
	
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
	public Warrant getWarrant() {
		return this.warrant;
	}

	/**{@inheritDoc} */
	@Override
	public void setWarrant(final Warrant warrant) {
		this.warrant = warrant;
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
	public String getComment() {
		return this.comment;
	}

	/**{@inheritDoc} */
	@Override
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/**{@inheritDoc} */
	@Override
	public ClearSignature getClearSignature() {
		return this.clearSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setClearSignature(final ClearSignature clearSignature) {
		this.clearSignature = clearSignature;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof WarrantCancellation)){
			return false;
		}
		
		WarrantCancellation that = (WarrantCancellation) obj;
		
		if(this.getWarrant() == null){
			throw new IllegalStateException("Warrant required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getComment() == null){
			throw new IllegalStateException("Comment required.");
		}
		if(this.getClearSignature().getPerson() == null){
			throw new IllegalStateException("ClearSignature Person required.");
		}
		if(this.getClearSignature().getDate() == null){
			throw new IllegalStateException("ClearSignature Date required.");
		}
		
		if(!this.getWarrant().equals(that.getWarrant())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getWarrant() == null){
			throw new IllegalStateException("Warrant required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getComment() == null){
			throw new IllegalStateException("Comment required.");
		}
		if(this.getClearSignature().getPerson() == null){
			throw new IllegalStateException("ClearSignature Person required.");
		}
		if(this.getClearSignature().getDate() == null){
			throw new IllegalStateException("ClearSignature Date required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getWarrant().hashCode();
		hashCode = 29 * hashCode + this.getClearSignature().getPerson().hashCode();
		hashCode = 29 * hashCode + this.getClearSignature().getDate().hashCode();
		
		
		return hashCode;
	}
	
}
