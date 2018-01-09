package omis.paroleboarditinerary.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.paroleboardmember.domain.ParoleBoardMember;

/**
 * Board attendee.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 16, 2017)
 * @since OMIS 3.0
 */
public interface BoardAttendee extends Creatable, Updatable {

	/**
	 * Sets the ID of the parole board itinerary.
	 *
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the ID of the parole board itinerary.
	 *
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the parole board itinerary.
	 * 
	 * @param boardItinerary parole board itinerary
	 */
	void setBoardItinerary(ParoleBoardItinerary boardItinerary);
	
	/**
	 * Returns the parole board itinerary.
	 * 
	 * @return parole board itinerary
	 */
	ParoleBoardItinerary getBoardItinerary();
	
	/**
	 * Sets the parole board member.
	 * 
	 * @param boardMember parole board member
	 */
	void setBoardMember(ParoleBoardMember boardMember);
	
	/**
	 * Returns the parole board member.
	 * 
	 * @return parole board member
	 */
	ParoleBoardMember getBoardMember();
	
	/**
	 * Sets the number.
	 * 
	 * @param number number
	 */
	void setNumber(Long number);
	
	/**
	 * Returns the number.
	 * 
	 * @return number
	 */
	Long getNumber();
	
	/**
	 * Sets the attendee role category.
	 * 
	 * @param attendeeRoleCategory attendee role category
	 */
	void setRole(AttendeeRoleCategory attendeeRoleCategory);
	
	/**
	 * Returns the attendee role category.
	 * 
	 * @return attendee role category
	 */
	AttendeeRoleCategory getRole();
	
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
