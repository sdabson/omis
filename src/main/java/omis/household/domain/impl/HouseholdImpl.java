package omis.household.domain.impl;

import java.util.Set;

import omis.address.domain.Address;
import omis.household.domain.Household;
import omis.person.domain.Person;

/** Implementation of Household.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 08, 2013)
 * @since OMIS 3.0 */
public class HouseholdImpl implements Household {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Address address;
	private Set<Person> residents;
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) { this.id = id; }
	
	/** {@inheritDoc} */
	@Override
	public void setAddress(final Address address) { this.address = address; }
	
	/** {@inheritDoc} */
	@Override
	public void setResidents(final Set<Person> residents) { 
		this.residents = residents; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() { return this.id; }
	
	/** {@inheritDoc} */
	@Override
	public Address getAddress() { return this.address; }
	
	/** {@inheritDoc} */
	@Override
	public Set<Person> getResidents() { return this.residents; }
	
	@Override
	public int hashCode() {
		int res = 1;
		final int hash = 17;
		
		if (this.getAddress() == null) {
			throw new IllegalStateException("Address required");
		}
		
		res += this.getAddress().hashCode() * hash;
		
		return res;
	} //hashCode
	
	@Override
	public boolean equals(final Object obj) {
		boolean res = false;
		
		if (obj instanceof Household) {
			Household that = (Household) obj;
			
			if (this.getAddress() == null) {
				throw new IllegalStateException("Address required");
			}
			
			if (this.getAddress().equals(that.getAddress())) {
				res = true;
			}
		}
		return res;
	} //equals
	
} //HouseholdImpl
