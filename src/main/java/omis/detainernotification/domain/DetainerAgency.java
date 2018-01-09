package omis.detainernotification.domain;

import omis.address.domain.Address;
import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Detainer Agency
 * 
 * @author Annie Jacques
 * @version 0.1.0 (Jul 7, 2016)
 * @since OMIS 3.0
 * 
 */
public interface DetainerAgency extends Creatable, Updatable {
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/**
	 * Gets agency name
	 * @return agency name
	 */
	public String getAgencyName();
	
	/**
	 * Gets if valid
	 * @return valid
	 */
	public Boolean isValid();
	
	/**
	 * Gets telephone number
	 * @return telephone number
	 */
	public String getTelephoneNumber();
	
	/**
	 * Gets address
	 * @return address
	 */
	public Address getAddress();
	
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
	/**
	 * Sets agency name
	 * @param agencyName - agency name
	 */
	public void setAgencyName(String agencyName);
	
	/**
	 * Sets valid
	 * @param valid - valid
	 */
	public void setValid(boolean valid);
	
	/**
	 * Sets telephone number
	 * @param telephoneNumber - telephone number
	 */
	public void setTelephoneNumber(String telephoneNumber);
	
	/**
	 * Sets address
	 * @param address - address
	 */
	public void setAddress(Address address);
	
	
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
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
	
}