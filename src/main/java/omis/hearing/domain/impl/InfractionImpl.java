package omis.hearing.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.Infraction;
import omis.hearing.domain.component.Resolution;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * InfractionImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class InfractionImpl implements Infraction {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private ConditionViolation conditionViolation;
	
	private DisciplinaryCodeViolation disciplinaryCodeViolation;
	
	private Hearing hearing;
	
	private Resolution resolution;
	
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
	public ConditionViolation getConditionViolation() {
		return this.conditionViolation;
	}

	/**{@inheritDoc} */
	@Override
	public void setConditionViolation(
			final ConditionViolation conditionViolation) {
		this.conditionViolation = conditionViolation;
	}

	/**{@inheritDoc} */
	@Override
	public DisciplinaryCodeViolation getDisciplinaryCodeViolation() {
		return this.disciplinaryCodeViolation;
	}

	/**{@inheritDoc} */
	@Override
	public void setDisciplinaryCodeViolation(
			final DisciplinaryCodeViolation disciplinaryCodeViolation) {
		this.disciplinaryCodeViolation = disciplinaryCodeViolation;
	}

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
	public Resolution getResolution() {
		return this.resolution;
	}

	/**{@inheritDoc} */
	@Override
	public void setResolution(final Resolution resolution) {
		this.resolution = resolution;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof Infraction)){
			return false;
		}
		
		Infraction that = (Infraction) obj;
		
		if(!((this.getConditionViolation() != null
				&& this.getDisciplinaryCodeViolation() == null)
			|| (this.getConditionViolation() == null
				&& this.getDisciplinaryCodeViolation() != null))
			||  (this.getConditionViolation() == null
				&& this.getDisciplinaryCodeViolation() == null)){
			throw new IllegalStateException(
					"Either ConditionViolation or DisciplinaryCodeViolation "
					+ "required.");
		}
		
		if(this.getHearing() != null && that.getHearing() == null){
			return false;
		}
		else if(this.getHearing() == null && that.getHearing() != null){
			return false;
		}
		else if(this.getHearing() != null && that.getHearing() != null){
			if(!this.getHearing().equals(that.getHearing())){
				return false;
			}
		}
		if(this.getConditionViolation() != null &&
				that.getConditionViolation() == null){
			return false;
		}
		else if(this.getConditionViolation() == null &&
				that.getConditionViolation() != null){
			return false;
		}
		else if(this.getConditionViolation() != null &&
				that.getConditionViolation() != null){
			if(!this.getConditionViolation().equals(
					that.getConditionViolation())){
				return false;
			}
		}
		if(this.getDisciplinaryCodeViolation() != null &&
				that.getDisciplinaryCodeViolation() == null){
			return false;
		}
		else if(this.getDisciplinaryCodeViolation() == null &&
				that.getDisciplinaryCodeViolation() != null){
			return false;
		}
		else if(this.getDisciplinaryCodeViolation() != null &&
				that.getDisciplinaryCodeViolation() != null){
			if(!this.getDisciplinaryCodeViolation().equals(
					that.getDisciplinaryCodeViolation())){
				return false;
			}
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(!((this.getConditionViolation() != null
				&& this.getDisciplinaryCodeViolation() == null)
			|| (this.getConditionViolation() == null
				&& this.getDisciplinaryCodeViolation() != null))
			||  (this.getConditionViolation() == null
				&& this.getDisciplinaryCodeViolation() == null)){
			throw new IllegalStateException(
					"Either ConditionViolation or DisciplinaryCodeViolation "
					+ "required.");
		}
		
		
		int hashCode = 14;
		if(this.getHearing() != null)
			hashCode = 29 * hashCode + this.getHearing().hashCode();
		if(this.getConditionViolation() != null)
			hashCode = 29 * hashCode + this.getConditionViolation().hashCode();
		if(this.getDisciplinaryCodeViolation() != null)
			hashCode = 29 * hashCode +
					this.getDisciplinaryCodeViolation().hashCode();
		
		
		return hashCode;
	}
	
}
