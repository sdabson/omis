package omis.substance.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.offender.domain.Offender;
import omis.substance.domain.Substance;
import omis.substance.domain.SubstanceUseAdmission;

/**
 * Substance use admission impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 20, 2013)
 * @since OMIS 3.0
 */
public class SubstanceUseAdmissionImpl implements SubstanceUseAdmission {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Substance substance;
	
	private Offender offender;
	
	private Date admissionDate;
	
	private CreationSignature creationSignature;
	
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
	public Substance getSubstance() {
		return this.substance;
	}

	/** {@inheritDoc} */
	@Override
	public void setSubstance(final Substance substance) {
		this.substance = substance;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getAdmissionDate() {
		return this.admissionDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setAdmissionDate(final Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof SubstanceUseAdmission)) {
			return false;
		}
		
		SubstanceUseAdmission that = (SubstanceUseAdmission) obj;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getSubstance() == null) {
			throw new IllegalStateException("Substance required.");
		}
		if (!this.getSubstance().equals(that.getSubstance())) {
			return false;
		}
		if (this.getAdmissionDate() == null) {
			throw new IllegalStateException("Admission date required.");
		}
		if (!this.getAdmissionDate().equals(that.getAdmissionDate())) {
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
		if (this.getSubstance() == null) {
			throw new IllegalStateException("Substance required.");
		}
		if (this.getAdmissionDate() == null) {
			throw new IllegalStateException("Admission date required.");
		}
		
		int hashCode = 14;
		hashCode = hashCode * 29 + this.getOffender().hashCode();
		hashCode = hashCode * 29 + this.getSubstance().hashCode();
		hashCode = hashCode * 29 + this.getAdmissionDate().hashCode();
		return hashCode;
	}
}