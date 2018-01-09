package omis.victim.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.relationship.domain.Relationship;
import omis.victim.domain.component.VictimAssociationFlags;

/**
 * Victim association.
 *
 * <p>The {@code firstPerson} of {@code relationship} is the offender; the
 * {@code secondPerson} is the victim.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 19, 2015)
 * @since OMIS 3.0
 */
public interface VictimAssociation extends Creatable, Updatable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets relationship.
	 * 
	 * @param relationship relationship
	 */
	void setRelationship(Relationship relationship);
	
	/**
	 * Returns relationship.
	 * 
	 * @return relationship
	 */
	Relationship getRelationship();
	
	/**
	 * Sets register date.
	 * 
	 * @param registerDate register date
	 */
	void setRegisterDate(Date registerDate);
	
	/**
	 * Returns register date.
	 * 
	 * @return register date
	 */
	Date getRegisterDate();
	
	/**
	 * Sets whether packet is sent.
	 * 
	 * @param packetSent whether packet is sent
	 */
	void setPacketSent(Boolean packetSent);
	
	/**
	 * Returns whether packet is sent.
	 * 
	 * @return whether packet is sent
	 */
	Boolean getPacketSent();
	
	/**
	 * Sets date that packet is sent.
	 * 
	 * @param packetSendDate date that packet is sent
	 */
	void setPacketSendDate(Date packetSendDate);
	
	/**
	 * Returns date that packet is sent.
	 * 
	 * @return date that packet is sent
	 */
	Date getPacketSendDate();
	
	/**
	 * Sets flags.
	 * 
	 * @param flags flags
	 */
	void setFlags(VictimAssociationFlags flags);
	
	/**
	 * Returns flags.
	 * 
	 * @return flags
	 */
	VictimAssociationFlags getFlags();
	
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