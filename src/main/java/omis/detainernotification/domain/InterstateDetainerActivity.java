package omis.detainernotification.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.document.domain.Document;

/**
 * InterstateDetainerActivity.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public interface InterstateDetainerActivity extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the InterstateDetainerActivity
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the InterstateDetainerActivity
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the InterstateAgreementDetainer for the InterstateDetainerActivity
	 * @return interstateAgreementDetainer - InterstateAgreementDetainer
	 */
	public InterstateAgreementDetainer getInterstateAgreementDetainer();
	
	/**
	 * Sets the InterstateAgreementDetainer for the InterstateDetainerActivity
	 * @param interstateAgreementDetainer - InterstateAgreementDetainer
	 */
	public void setInterstateAgreementDetainer(InterstateAgreementDetainer
			interstateAgreementDetainer);
	
	/**
	 * Returns the ActivityDate for the InterstateDetainerActivity
	 * @return activityDate - Date
	 */
	public Date getActivityDate();
	
	/**
	 * Sets the ActivityDate for the InterstateDetainerActivity
	 * @param activityDate - Date
	 */
	public void setActivityDate(Date activityDate);
	
	/**
	 * Returns the Direction for the InterstateDetainerActivity
	 * @return direction - Direction
	 */
	public Direction getDirection();
	
	/**
	 * Sets the Direction for the InterstateDetainerActivity
	 * @param direction - Direction
	 */
	public void setDirection(Direction direction);
	
	/**
	 * Returns the Document for the InterstateDetainerActivity
	 * @return document - Document
	 */
	public Document getDocument();
	
	/**
	 * Sets the Document for the InterstateDetainerActivity
	 * @param document - Document
	 */
	public void setDocument(Document document);
	
	/**
	 * Returns the Category for the InterstateDetainerActivity
	 * @return category - DetainerActivityCategory
	 */
	public DetainerActivityCategory getCategory();
	
	/**
	 * Sets the Category for the InterstateDetainerActivity
	 * @param category - DetainerActivityCategory
	 */
	public void setCategory(DetainerActivityCategory category);
	
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
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
