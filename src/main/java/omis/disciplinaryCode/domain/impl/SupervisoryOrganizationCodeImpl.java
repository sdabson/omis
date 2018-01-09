package omis.disciplinaryCode.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.domain.SupervisoryOrganizationCode;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * SupervisoryOrganizationCodeImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public class SupervisoryOrganizationCodeImpl implements SupervisoryOrganizationCode {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private SupervisoryOrganization supervisoryOrganization;
	
	private DateRange dateRange;
	
	private DisciplinaryCode code;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	

	/**
	 * Default Constructor
	 */
	public SupervisoryOrganizationCodeImpl() {
	}

	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
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
	public SupervisoryOrganization getSupervisoryOrganization() {
		return this.supervisoryOrganization;
	}

	/**{@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public void setSupervisoryOrganization(SupervisoryOrganization supervisoryOrganization) {
		this.supervisoryOrganization = supervisoryOrganization;
	}

	/**{@inheritDoc} */
	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}
	
	/**{@inheritDoc} */
	@Override
	public DisciplinaryCode getCode() {
		return this.code;
	}

	/**{@inheritDoc} */
	@Override
	public void setCode(DisciplinaryCode code) {
		this.code = code;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof SupervisoryOrganizationCode)){
			return false;
		}
		
		SupervisoryOrganizationCode that = (SupervisoryOrganizationCode) obj;
		
		if(this.getSupervisoryOrganization() == null){
			throw new IllegalStateException("Supervisory Organization required.");
		}
		if(this.getDateRange().getStartDate() == null){
			throw new IllegalStateException("Start Date required.");
		}
		if(this.getCode() == null){
			throw new IllegalStateException("Disciplinary Code required.");
		}
		
		
		if(!this.getSupervisoryOrganization().equals(that.getSupervisoryOrganization())){
			return false;
		}
		if(!this.getCode().equals(that.getCode())){
			return false;
		}
		
		return true;
	}
		
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getSupervisoryOrganization() == null){
			throw new IllegalStateException("Supervisory Organization required.");
		}
		if(this.getDateRange().getStartDate() == null){
			throw new IllegalStateException("Start Date required.");
		}
		if(this.getCode() == null){
			throw new IllegalStateException("Disciplinary Code required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getSupervisoryOrganization().hashCode();
		hashCode = 29 * hashCode + this.getCode().hashCode();
		
		return hashCode;
		
	}

	

}
