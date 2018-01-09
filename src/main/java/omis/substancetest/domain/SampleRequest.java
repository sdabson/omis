package omis.substancetest.domain;

import java.io.Serializable;
import java.util.Date;

import omis.offender.domain.Offender;
import omis.substancetest.domain.component.SampleRequestResolution;

/**
 * Substance test sample request.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 28, 2015)
 * @since OMIS 3.0
 */
public interface SampleRequest extends Serializable {

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
	 * Returns the date.
	 * 
	 * @return date
	 */
	Date getDate();
	
	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	void setDate(Date date);

	/**
	 * Returns the sample request resolution.
	 * 
	 * @return resolution
	 */
	SampleRequestResolution getResolution();
	
	/**
	 * Sets the sample request resolution.
	 * 
	 * @param resolution sample request resolution
	 */
	void setResolution(SampleRequestResolution resolution);
	
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