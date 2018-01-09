package omis.substancetest.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.person.domain.Person;
import omis.substance.domain.SubstanceLab;

/**
 * Substance Test.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 4, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceTest 
	extends Creatable, Updatable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();

	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the test comment.
	 * 
	 * @return test comment
	 */
	String getTestComment();

	/**
	 * Sets the test comment.
	 * 
	 * @param testComment test comment
	 */
	void setTestComment(String testComment);
	
	/**
	 * Returns the test sample.
	 * 
	 * @return substance test sample
	 */
	SubstanceTestSample getSubstanceTestSample();

	/**
	 * Set the test sample of the substance test.
	 * 
	 * @param substanceTestSample the testSample to set
	 */
	void setSubstanceTestSample(SubstanceTestSample substanceTestSample);
	
	/**
	 * Returns whether a substance lab is involved.
	 * 
	 * @return lab involved
	 */
	Boolean getLabInvolved();

	/**
	 * Sets whether a substance lab is involved.
	 * 
	 * @param labInvolved crime lab involved
	 */
	void setLabInvolved(Boolean labInvolved);
	
	/**
	 * Return the result date.
	 * 
	 * @return result date
	 */
	Date getResultDate();

	/**
	 * Set the result date.
	 * 
	 * @param resultDate result date
	 */
	void setResultDate(Date resultDate);
	
	/**
	 * Returns the lab result date.
	 * 
	 * @return lab result date
	 */
	Date getLabResultDate();

	/**
	 * Sets the lab result date.
	 * 
	 * @param labResultDate lab result date
	 */
	void setLabResultDate(Date labResultDate);
	
	/**
	 * Returns the lab request date.
	 * 
	 * @return lab request date
	 */
	Date getLabRequestDate();
	
	/**
	 * Sets the lab request date.
	 * 
	 * @param labRequestDate crime lab request date
	 */
	void setLabRequestDate(Date labRequestDate);
	
	/**
	 * Returns the substance lab.
	 * 
	 * @return substance lab
	 */
	SubstanceLab getLab();
	
	/**
	 * Sets the substance lab.
	 * 
	 * @param substanceLab substance lab
	 */
	void setLab(SubstanceLab lab);
	
	/**
	 * Returns private lab justification.
	 * 
	 * @return private lab justification
	 */
	String getPrivateLabJustification();
	
	/**
	 * Set private lab justification.
	 * 
	 * @param privateLabJustification private lab justification
	 */
	void setPrivateLabJustification(String privateLabJustification);
	
	/**
	 * Returns the authorizing staff.
	 * 
	 * @return authorizing staff
	 */
	Person getAuthorizingStaff();
	
	/**
	 * Sets the authorizing staff.
	 * 
	 * @param authorizingStaff authorizing staff
	 */
	void setAuthorizingStaff(Person authorizingStaff);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
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
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}