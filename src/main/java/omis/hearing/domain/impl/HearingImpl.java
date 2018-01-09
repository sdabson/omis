package omis.hearing.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingCategory;
import omis.hearing.domain.component.Subject;
import omis.location.domain.Location;
import omis.staff.domain.StaffAssignment;

/**
 * HearingImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class HearingImpl implements Hearing {

	private static final long serialVersionUID = 1L;
	
	private Location location;
	
	private Date date;
	
	private StaffAssignment officer;
	
	private HearingCategory category;
	
	private Subject subject;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**{@inheritDoc} */
	@Override
	public Location getLocation() {
		return this.location;
	}

	/**{@inheritDoc} */
	@Override
	public void setLocation(final Location location) {
		this.location = location;
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
	public StaffAssignment getOfficer() {
		return this.officer;
	}

	/**{@inheritDoc} */
	@Override
	public void setOfficer(final StaffAssignment officer) {
		this.officer = officer;
	}

	/**{@inheritDoc} */
	@Override
	public HearingCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(final HearingCategory category) {
		this.category = category;
	}

	/**{@inheritDoc} */
	@Override
	public Subject getSubject() {
		return this.subject;
	}

	/**{@inheritDoc} */
	@Override
	public void setSubject(final Subject subject) {
		this.subject = subject;
	}
	
	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

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
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof Hearing)){
			return false;
		}
		
		Hearing that = (Hearing) obj;
		
		if(this.getLocation() == null){
			throw new IllegalStateException("Location required.");
		}
		if(this.getSubject().getOffender() == null){
			throw new IllegalStateException("Offender required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getOfficer() == null){
			throw new IllegalStateException("Officer required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		
		if(!this.getLocation().equals(that.getLocation())){
			return false;
		}
		if(!this.getSubject().getOffender()
				.equals(that.getSubject().getOffender())){
			return false;
		}
		if(!this.getDate().equals(that.getDate())){
			return false;
		}
		if(!this.getOfficer().equals(that.getOfficer())){
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
		if(this.getLocation() == null){
			throw new IllegalStateException("Location required.");
		}
		if(this.getSubject().getOffender() == null){
			throw new IllegalStateException("Offender required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getOfficer() == null){
			throw new IllegalStateException("Officer required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getLocation().hashCode();
		hashCode = 29 * hashCode + this.getSubject().getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getOfficer().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		
		return hashCode;
		
	}

	
	
}
