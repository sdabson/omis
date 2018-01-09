package omis.detainernotification.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.detainernotification.domain.component.DetainerWarrantCancellation;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Unit;

/**
 * Detainer warrant processing status.
 *
 * @author Sheronda Vaughn
 * @author Annie Jacques
 * @author Joel Norris
 * @version 0.1.2 (May 30, 2017)
 * @since OMIS 3.0
 */
public interface DetainerWarrantProcessingStatus extends Creatable, Updatable {
	
	/**
	 * Sets the id of the detainer warrant processing status.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the id of the detainer warrant processing status.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the detainer of the detainer warrant processing status.
	 * 
	 * @param detainer detainer
	 */
	void setDetainer(Detainer detainer);
	
	/**
	 * Returns the detainer of the detainer warrant processing status.
	 * 
	 * @return detainer
	 */
	Detainer getDetainer();
	
	/**
	 * Sets the sent facility date of the detainer warrant processing status.
	 * 
	 * @param sentToFacilityDate sent to facility date
	 */
	void setSentToFacilityDate(Date sentToFacilityDate);
	
	/**
	 * Returns the sent to facility date of the detainer warrant 
	 * processing status.
	 * 
	 * @return sent to facilitydate
	 */
	Date getSentToFacilityDate();
	
	/**
	 * Sets the facility name.
	 * 
	 * @param facilityName facility name
	 */
	void setFacilityName(String facilityName);
	
	/**
	 * Returns the facility name.
	 * 
	 * @return facility name
	 */
	String getFacilityName();
	
	/**
	 * Sets the facility of the detainer warrant processing status.
	 * 
	 * @param facility facility
	 */
	void setFacility(Facility facility);
	
	/**
	 * Returns the facility of the detainer warrant processing status.
	 * 
	 * @return facility
	 */
	Facility getFacility();
	
	/**
	 * Sets the unit.
	 * 
	 * @param unit unit
	 */
	void setUnit(Unit unit);
	
	/**
	 * Returns the unit.
	 * 
	 * @return unit
	 */
	Unit getUnit();
	
	/**
	 * Sets the complex.
	 * 
	 * @param complex complex
	 */
	void setComplex(Complex complex);
	
	/**
	 * Returns the complex.
	 * 
	 * @return complex
	 */
	Complex getComplex();
	
	/**
	 * Sets the inmate served date of the detainer warrant processing status.
	 * 
	 * @param inmateServedDate inmate served date
	 */
	void setInmateServedDate(Date inmateServedDate);
	
	/**
	 * Returns the inmate served date of the detainer warrant processing status.
	 * 
	 * @return inmate served date
	 */
	Date getInmateServedDate();
	
	/**
	 * Sets the refused to sign boolean of the detainer warrant 
	 * processing status.
	 * 
	 * @param refusedToSign refused to sign
	 */
	void setRefusedToSign(Boolean refusedToSign);
	
	/**
	 * Returns the refused to sign boolean of the detainer warrant 
	 * processing status.
	 * 
	 * @return refused to sign
	 */
	Boolean getRefusedToSign();
	
	/**
	 * Sets the waiver required boolean of the detainer warrant 
	 * processing status.
	 * 
	 * @param waiverRequired waiver required
	 */
	void setWaiverRequired(Boolean waiverRequired);
	
	/**
	 * Returns the waiver required boolean of the detainer warrant 
	 * processing status.
	 * 
	 * @return waiver required
	 */
	Boolean getWaiverRequired();
	
	/**
	 * Returns the refused to sign comment.
	 * 
	 * @return refused to sign comment
	 */
	String getRefusedToSignComment();
	
	/**
	 * Sets the refused to sign comment.
	 * 
	 * @param refusedToSignComment refused to sign comment
	 */
	void setRefusedToSignComment(String refusedToSignComment);
	
	/**
	 * Returns the waiver required comment.
	 * 
	 * @return waiver required comment
	 */
	String getWaiverRequiredComment();
	
	/**
	 * Sets the waiver required comment.
	 * 
	 * @param waiverRequiredComment waiver required comment
	 */
	void setWaiverRequiredComment(String waiverRequiredComment);
	
	/**
	 * Sets the cancellation of the detainer warrant processing status.
	 * 
	 * @param cancellation cancellation
	 */
	void setCancellation(DetainerWarrantCancellation cancellation);
	
	/**
	 * Returns the cancellation of the detainer warrant processing status.
	 * 
	 * @return cancellation
	 */
	DetainerWarrantCancellation getCancellation();
	
		
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