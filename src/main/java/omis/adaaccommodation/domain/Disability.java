package omis.adaaccommodation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.Offender;

/**
 * ADA disability.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 14, 2015)
 * @since OMIS 3.0
 */
public interface Disability 
	extends Creatable, Updatable {

	/**
	 * Sets the ID of the ADA disability.
	 * 
	 * @param id ID
	 */
	 void setId(Long id);
	 
	 /**
	  * Return the ID of the ADA disability.
	  * 
	  * @return ID
	  */
	 Long getId();
	 
	 /**
	  * Set the offender with the ADA disability.
	  * 
	  * @param offender offender
	  */
	 void setOffender(Offender offender);
	 
	 /**
	  * Return the offender with the ADA disability.
	  * 
	  * @return offender
	  */
	 Offender getOffender();
	 
	 /**
	  * Set the description of the ADA disability.
	  * 
	  * @param description description
	  */
	 void setDescription(String description);
	 
	 /**
	  * Return the description of the ADA disability.
	  * 
	  * @return description
	  */
	 String getDescription();
	 
	 /**
	  * Sets the disability classification category for the ADA disability.
	  * 
	  * @param disabilityClassification disability classification
	  */
	 void setDisabilityClassification(
			 DisabilityClassificationCategory disabilityClassification);
	 
	 /**
	  * Returns the disability classification category for the ADA disability.
	  * 
	  * @return disability classification
	  */
	 DisabilityClassificationCategory getDisabilityClassification();
	 
	 /**
		 * Compares {@code this} and {@code obj} for equality.
		 * <p>
		 * Any mandatory property may be used in the comparison. If a  mandatory
		 * property of {@code this} that is used in the comparison is 
		 * {@code null} an {@code IllegalStateException} will be thrown.
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