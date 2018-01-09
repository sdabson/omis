package omis.education.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievement;
import omis.education.domain.component.Institute;
import omis.offender.domain.Offender;

/**
 * EducationTermImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationTermImpl implements EducationTerm {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private Institute institute;
	
	private String description;
	
	private EducationalAchievement achievement;
	
	private Boolean specialEducation;
	
	private DateRange attendedDateRange;
	
	private VerificationSignature verificationSignature;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	

	public EducationTermImpl() {
		//Default Constructor
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
	public Offender getOffender() {
		return this.offender;
	}

	/**{@inheritDoc} */
	@Override
	public Institute getInstitute() {
		return this.institute;
	}

	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public EducationalAchievement getAchievement() {
		return this.achievement;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getSpecialEducation() {
		return this.specialEducation;
	}

	/**{@inheritDoc} */
	@Override
	public DateRange getAttendedDateRange() {
		return this.attendedDateRange;
	}

	/**{@inheritDoc} */
	@Override
	public VerificationSignature getVerificationSignature() {
		return this.verificationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	/**{@inheritDoc} */
	@Override
	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public void setAchievement(EducationalAchievement achievement) {
		this.achievement = achievement;
	}

	/**{@inheritDoc} */
	@Override
	public void setSpecialEducation(Boolean specialEducation) {
		this.specialEducation = specialEducation;
	}

	/**{@inheritDoc} */
	@Override
	public void setAttendedDateRange(DateRange attendedDateRange) {
		this.attendedDateRange = attendedDateRange;
	}

	/**{@inheritDoc} */
	@Override
	public void setVerificationSignature(
			VerificationSignature verificationSignature) {
		this.verificationSignature = verificationSignature;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof EducationTerm)){
			return false;
		}
		
		EducationTerm that = (EducationTerm) obj;
		
		if(this.getOffender() == null){
			throw new IllegalStateException("Offender required.");
		}
		if(this.getSpecialEducation() == null){
			throw new IllegalStateException("Special Education required.");
		}
		if(this.getVerificationSignature().getResult() == null){
			throw new IllegalStateException("Verification signature result"
					+ " required."); 
		}
		if(this.getVerificationSignature().getMethod() == null){
			throw new IllegalStateException("Verification signature method "
					+ "required.");
		}
		
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
	
		return hashCode;
	}
}
