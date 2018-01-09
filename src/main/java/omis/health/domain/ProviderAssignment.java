package omis.health.domain;

import java.io.Serializable;

import omis.datatype.DateRange;
import omis.facility.domain.Facility;
import omis.person.domain.Person;

/**
 * Provider assignment.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public interface ProviderAssignment extends Serializable {

	/**
	 * Returns the id of the provider assignment.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id of the provider assignment.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the provider of the provider assignment.
	 * 
	 * @return provider
	 */
	Person getProvider();
	
	/**
	 * Sets the provider of the provider assignment.
	 * 
	 * @param provider provider
	 */
	void setProvider(Person provider);
	
	/**
	 * Returns the facility of the provider assignment.
	 * 
	 * @return facility
	 */
	Facility getFacility();
	
	/**
	 * Sets the facility of the provider assignment.
	 * 
	 * @param facility facility
	 */
	void setFacility(Facility facility);
	
	/**
	 * Returns the date range of the provider assignment.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the date range of the provider assignment.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns medical facility.
	 * 
	 * @return medical facility
	 */
	MedicalFacility getMedicalFacility();
	
	/**
	 * Sets medical facility.
	 * 
	 * @param medicalFacility medical facility
	 */
	void setMedicalFacility(MedicalFacility medicalFacility);
	
	/**
	 * Returns the provider title of the provider assignment.
	 * 
	 * @return title
	 */
	ProviderTitle getTitle();
	
	/**
	 * Sets the provider title of the provider assignment.
	 * 
	 * @param title provider title
	 */
	void setTitle(ProviderTitle title);
	
	/**
	 * Returns the contracted value.
	 * 
	 * @return contracted
	 */
	Boolean getContracted();
	
	/**
	 * Sets the contracted value.
	 * 
	 * @param contracted contracted
	 */
	void setContracted(Boolean contracted);
	
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