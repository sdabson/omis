package omis.household.domain;

import java.io.Serializable;
import java.util.Set;

import omis.address.domain.Address;
import omis.person.domain.Person;


/** Residential unit housing relationships. 
 * @author Ryan Johns
 * @version 0.1.0 (Mar 07, 2013) 
 * @since OMIS 3.0 */
public interface Household extends Serializable {

	/** sets the ID.
	 * @param id */
	void setId(final Long id);
	
	/** sets address.
	 * @param address */
	void setAddress(final Address address);
	
	/** sets residents.
	 * @param residents */
	void setResidents(final Set<Person> residents);
	
	/** gets the ID.
	 * @return id */
	Long getId();
	
	/** gets address.
	 * @return address */
	Address getAddress();
	
	/** gets residents.
	 * @return residents */
	Set<Person> getResidents();
} //Household
