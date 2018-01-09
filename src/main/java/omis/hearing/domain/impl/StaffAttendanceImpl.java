package omis.hearing.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.StaffAttendance;
import omis.staff.domain.StaffAssignment;

/**
 * StaffAttendanceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class StaffAttendanceImpl implements StaffAttendance {

	private static final long serialVersionUID = 1L;
	
	private Hearing hearing;
	
	private StaffAssignment staff;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	/**{@inheritDoc} */
	@Override
	public Hearing getHearing() {
		return this.hearing;
	}

	/**{@inheritDoc} */
	@Override
	public void setHearing(final Hearing hearing) {
		this.hearing = hearing;
	}

	/**{@inheritDoc} */
	@Override
	public StaffAssignment getStaff() {
		return this.staff;
	}

	/**{@inheritDoc} */
	@Override
	public void setStaff(final StaffAssignment staff) {
		this.staff = staff;
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
		if(!(obj instanceof StaffAttendance)){
			return false;
		}
		
		StaffAttendance that = (StaffAttendance) obj;
		
		if(this.getHearing() == null){
			throw new IllegalStateException("Hearing required.");
		}
		if(this.getStaff() == null){
			throw new IllegalStateException("Staff required.");
		}
		
		if(!this.getHearing().equals(that.getHearing())){
			return false;
		}
		if(!this.getStaff().equals(that.getStaff())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getHearing() == null){
			throw new IllegalStateException("Hearing required.");
		}
		if(this.getStaff() == null){
			throw new IllegalStateException("Staff required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getHearing().hashCode();
		hashCode = 29 * hashCode + this.getStaff().hashCode();
		
		return hashCode;
	}
	
	
}
