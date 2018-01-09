package omis.citation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.Updatable;
import omis.audit.domain.UpdateSignature;
import omis.datatype.Month;
import omis.offender.domain.Offender;
import omis.offender.domain.OffenderAssociable;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Misdemeanor citation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 5, 2016)
 * @since OMIS 3.0
 */

public interface MisdemeanorCitation 
	extends OffenderAssociable, Creatable, Updatable {

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
	 * Returns the offense.
	 * 
	 * @return offense
	 */
	MisdemeanorOffense getOffense();
	
	/**
	 * Sets the offense.
	 * 
	 * @param Offense offense
	 */
	void setOffense(MisdemeanorOffense offense);
	
	/**
	 * Returns the state.
	 * 
	 * @return state
	 */
	State getState();
	
	/**
	 * Sets the state.
	 * 
	 * @param State state
	 */
	void setState(State state);
	
	/**
	 * Returns the city.
	 * 
	 * @return city
	 */
	City getCity();
	
	/**
	 * Sets the city.
	 * 
	 * @param City city
	 */
	void setCity(City city);
	
	/**
	 * Returns the day.
	 * 
	 * @return day
	 */
	Integer getDay();
	
	/**
	 * Sets the day.
	 * 
	 * @param Day day
	 */
	void setDay(Integer day);
	
	/**
	 * Returns the month.
	 * 
	 * @return month
	 */
	Month getMonth();
	
	/**
	 * Sets the month.
	 * 
	 * @param Month month
	 */
	void setMonth(Month month);
	
	/**
	 * Returns the year.
	 * 
	 * @return year
	 */
	Integer getYear();
	
	/**
	 * Sets the year.
	 * 
	 * @param Year year
	 */
	void setYear(Integer year);
	
	/**
	 * Sets disposition.
	 * 
	 * @return disposition disposition
	 */
	void setDisposition(MisdemeanorDisposition disposition);
	
	/**
	 * Gets misdemeanor counts
	 * 
	 * @param Counts counts
	 */
	Integer getCounts();
	
	/**
	 * Sets misdemeanor counts
	 * 
	 * @return Counts counts
	 */
	void setCounts(Integer count);
	
	/**
	 * Returns disposition.
	 * 
	 * @return disposition
	 */
	MisdemeanorDisposition getDisposition();
	
	/**
	 * Returns creationSignature.
	 * 
	 * @return creationSignature
	 */
	CreationSignature getCreationSignature();
	
	/**
	 * Sets creationSignature.
	 * 
	 * @return creationSignature
	 */
	void setCreationSignature(CreationSignature creationSignature);
	
	/**
	 * Returns updateSignature.
	 * 
	 * @return updateSignature
	 */
	UpdateSignature getUpdateSignature();
	
	/**
	 * Sets updateSignature.
	 * 
	 * @return updateSignature
	 */
	void setUpdateSignature(UpdateSignature updateSignature);
		
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
