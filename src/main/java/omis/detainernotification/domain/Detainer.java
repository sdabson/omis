

package omis.detainernotification.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.Offender;

/**
 * Detainer implementation
 * 
 *@author Annie Jacques 
 *@author Joel Norris
 *@version 0.1.1 (May 25, 2017)
 *@since OMIS 3.0
 *
 */
public interface Detainer extends Creatable, Updatable{
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/**
	 * Gets offender
	 * @return offender
	 */
	public Offender getOffender();
	
	/**
	 * Gets alternate offender id
	 * @return alternate offender id
	 */
	public String getAlternateOffenderId();
	
	/**
	 * Gets offense description
	 * @return offense description
	 */
	public String getOffenseDescription();
	
	/**
	 * Gets court case number
	 * @return court case number
	 */
	public String getCourtCaseNumber();
	
	/**
	 * Gets detainer type
	 * @return detainer type
	 */
	public DetainerType getDetainerType();
	
	/**
	 * Gets detainer agency
	 * @return detainer agency
	 */
	public DetainerAgency getDetainerAgency();

	/**
	 * Gets detainer jurisdiction category
	 * @return jurisdiction - detainer jurisdiction category
	 */
	public DetainerJurisdictionCategory getJurisdiction();
	
	/**
	 * Sets id
	 * @param id - id
	 */
	public void setId(Long id);
	
	/**
	 * Sets offender
	 * @param offender - offender
	 */
	public void setOffender(Offender offender);
	
	/**
	 * Sets alternate offender id
	 * @param alternateOffenderId - alternate offender id
	 */
	public void setAlternateOffenderId(String alternateOffenderId);
	
	/**
	 * Sets offense description
	 * @param offenseDescription - offense description
	 */
	public void setOffenseDescription(String offenseDescription);
	
	/**
	 * Sets court case number
	 * @param courtCaseNumber - court case number
	 */
	public void setCourtCaseNumber(String courtCaseNumber);
	
	/**
	 * Sets detainer type
	 * @param detainerType - detainer type
	 */
	public void setDetainerType(DetainerType detainerType);
	
	/**
	 * Sets detainer agency
	 * @param detainerAgency - detainer agency
	 */
	public void setDetainerAgency(DetainerAgency detainerAgency);
	
	/**
	 * Sets jurisdiction
	 * @param jurisdiction - Detainer Jurisdiction Category
	 */
	public void setJurisdiction
	(DetainerJurisdictionCategory jurisdiction);
	
	/**
	 * Sets the receive date.
	 * 
	 * @param receiveDate receive date
	 */
	void setReceiveDate(Date receiveDate);
	
	/**
	 * Returns the receive date.
	 * 
	 * @return receive date
	 */
	Date getReceiveDate();
	
	/**
	 * Sets the issue date.
	 * 
	 * @param issueDate issue date
	 */
	void setIssueDate(Date issueDate);
	
	/**
	 * Returns the issue date.
	 * 
	 * @return issue date
	 */
	Date getIssueDate();
	
	/**
	 * Sets the warrant number.
	 * 
	 * @param warrantNumber warrant number
	 */
	void setWarrantNumber(String warrantNumber);
	
	/**
	 * Returns the warrant number.
	 * 
	 * @return warrant number
	 */
	String getWarrantNumber();
	
	
	 /** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	public boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	public int hashCode();
	
}