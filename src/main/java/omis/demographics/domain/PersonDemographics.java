package omis.demographics.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.demographics.domain.component.PersonAppearance;
import omis.demographics.domain.component.PersonPhysique;
import omis.person.domain.Person;

/**
 * Person demographics.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 20, 2014)
 * @since OMIS 3.0
 */
public interface PersonDemographics
		extends Creatable, Updatable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the person.
	 * 
	 * @param person person
	 */
	void setPerson(Person person);
	
	/**
	 * Returns the person.
	 * 
	 * @return perosn
	 */
	Person getPerson();
	
	/**
	 * Sets the appearance of the offender.
	 * 
	 * @param appearance appearance of offender
	 */
	void setAppearance(PersonAppearance appearance);
	
	/**
	 * Returns the appearance of the offender.
	 * 
	 * @return appearance of offender
	 */
	PersonAppearance getAppearance();

	/**
	 * Sets the race of the offender.
	 * 
	 * @param race race of offender
	 */
	void setRace(Race race);

	/**
	 * Returns the race of the offender.
	 * 
	 * @return race of offender
	 */
	Race getRace();

	/**
	 * Returns whether person is of Hispanic ethnicity.
	 * 
	 * @param hispanicEthnicity whether person is of Hispanic ethnicity
	 */
	void setHispanicEthnicity(Boolean hispanicEthnicity);
	
	/**
	 * Sets whether person is of Hispanic ethnicity.
	 * 
	 * @return whether person is of Hispanic ethnicity.
	 */
	Boolean getHispanicEthnicity();
	
	/**
	 * Sets the physique of the offender.
	 * 
	 * @param physique physique of offender
	 */
	void setPhysique(PersonPhysique physique);

	/**
	 * Returns the physique of the offender.
	 * 
	 * @return physique of offender
	 */
	PersonPhysique getPhysique();
	
	/**
	 * Sets the dominant side.
	 * 
	 * @param dominantSide dominant side
	 */
	void setDominantSide(DominantSide dominantSide);
	
	/**
	 * Returns the dominant side.
	 * 
	 * @return dominant side
	 */
	DominantSide getDominantSide();

	/**
	 * Sets the marital status.
	 * 
	 * @param maritalStatus marital status
	 */
	void setMaritalStatus(MaritalStatus maritalStatus);
	
	/**
	 * Returns the marital status.
	 * 
	 * @return marital status
	 */
	MaritalStatus getMaritalStatus();

	/**
	 * Sets the tribe to which the offender belongs.
	 * 
	 * @param tribe offender tribe
	 */
	void setTribe(Tribe tribe);

	/**
	 * Returns the tribe to which the offender belongs.
	 * 
	 * @return offender tribe
	 */
	Tribe getTribe();
	
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