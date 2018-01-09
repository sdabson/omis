package omis.grievance.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * Grievance.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.1 (May 7, 2015)
 * @since OMIS 3.0
 */
public interface Grievance
		extends OffenderAssociable, Creatable, Updatable {

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
	 * Sets location.
	 * 
	 * @param location location
	 */
	void setLocation(GrievanceLocation location);
	
	/**
	 * Returns location.
	 * 
	 * @return location
	 */
	GrievanceLocation getLocation();
	
	/**
	 * Sets unit.
	 * 
	 * @param unit unit
	 */
	void setUnit(GrievanceUnit unit);
	
	/**
	 * Returns unit.
	 * 
	 * @return unit
	 */
	GrievanceUnit getUnit();
	
	/**
	 * Sets subject.
	 * 
	 * @param subject subject
	 */
	void setSubject(GrievanceSubject subject);
	
	/**
	 * Returns subject.
	 * 
	 * @return subject
	 */
	GrievanceSubject getSubject();
	
	/**
	 * Sets the complaint category.
	 * 
	 * @param complaintCategory complaint category
	 */
	void setComplaintCategory(GrievanceComplaintCategory complaintCategory);
	
	/**
	 * Returns complaint category.
	 * 
	 * @return complaint category
	 */
	GrievanceComplaintCategory getComplaintCategory();
	
	/**
	 * Sets grievance number.
	 * 
	 * @param grievanceNumber grievance number
	 */
	void setGrievanceNumber(Integer grievanceNumber);
	
	/**
	 * Returns grievance number.
	 * 
	 * @return grievance number
	 */
	Integer getGrievanceNumber();
	
	/**
	 * Sets opened date.
	 * 
	 * @param openedDate opened date
	 */
	void setOpenedDate(Date openedDate);
	
	/**
	 * Returns opened date.
	 * 
	 * @return opened date
	 */
	Date getOpenedDate();
	
	/**
	 * Sets informal file date.
	 * 
	 * @param informalFileDate informal file date
	 */
	void setInformalFileDate(Date informalFileDate);
	
	/**
	 * Returns informal file date.
	 * 
	 * @return informal file date
	 */
	Date getInformalFileDate();
	
	/**
	 * Sets description.
	 * 
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * Returns description.
	 * 
	 * @return description
	 */
	String getDescription();
	
	/**
	 * Sets initial comment.
	 * 
	 * @param initialComment initial comment
	 */
	void setInitialComment(String initialComment);
	
	/**
	 * Returns initial comment.
	 * 
	 * @return initial comment
	 */
	String getInitialComment();
	
	/**
	 * Sets closed date.
	 * 
	 * @param closedDate closed date
	 */
	void setClosedDate(Date closedDate);
	
	/**
	 * Returns closed date.
	 * 
	 * @return closed date
	 */
	Date getClosedDate();
	
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