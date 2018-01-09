package omis.employment.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.employment.domain.component.Job;

/**
 * Employment term.
 * 
 * @author: Yidong Li
 * @author Josh Divine
 * @version: 0.1.1 (Dec 13, 2017)
 * @since: OMIS 3.0
 */
public interface EmploymentTerm extends Updatable, Creatable {

	/**
	 * Returns the employment term id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the employment term id
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the offender.
	 * 
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the offender.
	 * 
	 * @param offender offender
	 */
	void setOffender(Offender offender);

	/**
	 * Sets the date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Gets the date range.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the verification signature
	 * 
	 * @param verificationSignature verification signature
	 */
	void setVerificationSignature(VerificationSignature verificationSignature);
	
	/**
	 * Gets the verification signature.
	 * 
	 * @return verificationSignature verification signature
	 */
	VerificationSignature getVerificationSignature();

	/**
	 * Sets if this offender is convicted of eEmployer theft
	 * 
	 * @param convictedOfEmployerTheft convicted of employer theft
	 */
	void setConvictedOfEmployerTheft(Boolean convictedOfEmployerTheft);
	
	/**
	 * Gets the info on if this  offender is convicted of employer theft.
	 * 
	 * @return Yes or No
	 */
	Boolean getConvictedOfEmployerTheft();
	
	/**
	 * Sets the job
	 * 
	 * @param job job
	 */
	void setJob(Job job);
	
	/**
	 * Gets the job.
	 * 
	 * @return job
	 */
	Job getJob();
	
	/**
	 * Sets employment change reason
	 * 
	 * @param endReason employment change reason
	 */
	void setEndReason(EmploymentChangeReason endReason);
	
	/**
	 * Gets employment end reason.
	 * 
	 * @return employment end reason
	 */
	EmploymentChangeReason getEndReason();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}