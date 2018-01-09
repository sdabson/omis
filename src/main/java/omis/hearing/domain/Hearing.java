package omis.hearing.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.hearing.domain.component.Subject;
import omis.location.domain.Location;
import omis.staff.domain.StaffAssignment;

/**
 * Hearing.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface Hearing extends Creatable, Updatable{
	
	/**
	 * Returns the location
	 * @return location
	 */
	public Location getLocation();
	
	/**
	 * Sets the location
	 * @param location
	 */
	public void setLocation(Location location);
	
	/**
	 * Returns the hearing date
	 * @return date
	 */
	public Date getDate();
	
	/**
	 * Sets the hearing date
	 * @param date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the hearing Officer
	 * @return officer - StaffAssignment
	 */
	public StaffAssignment getOfficer();
	
	
	/**
	 * Sets the hearing Officer
	 * @param officer - StaffAssignment
	 */
	public void setOfficer(StaffAssignment officer);
	
	/**
	 * Returns the hearing Category
	 * @return category - HearingCategory
	 */
	public HearingCategory getCategory();
	
	/**
	 * Sets the hearing Category
	 * @param category - HearingCategory
	 */
	public void setCategory(HearingCategory category);
	
	/**
	 * Returns the hearing Subject
	 * @return Subject
	 */
	public Subject getSubject();
	
	/**
	 * Sets the hearing Subject
	 * @param subject
	 */
	public void setSubject(Subject subject);
	
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
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
