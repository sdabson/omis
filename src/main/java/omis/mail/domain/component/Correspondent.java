package omis.mail.domain.component;

import java.io.Serializable;

import omis.address.domain.Address;
import omis.person.domain.Person;

/** Mail correspondent.
 * @author Ryan Johns
 * @version 0.1.0 (May 2, 2016)
 * @since OMIS 3.0 */
public class Correspondent implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int[] HASHS = {3};
	private static final String NO_DESCRIPTION 
		= "Description cannot be null";
	private String description;
	private Address address;
	private Person person;
	
	/** Constructor. */
	public Correspondent() {
	}
	
	/** Constructor. 
	 * @param description - description.
	 * @param address - address. */
	public Correspondent(final String description, final Address address) {
		this.description = description;
		this.address = address;
	}
	
	/** Constructor.
	 * @param decription - description.
	 * @param person - person. */
	public Correspondent(final String description, final Person person) {
		this.person = person;
		this.description = description;
	}
	
	/** Gets description.
	 * @return description. */
	public final String getDescription() {
		return this.description; 
	}
	
	/** Gets address.
	 * @return address. */
	public final Address getAddress() {
		return this.address;
	}
	
	/** Gets person.
	 * @return person. */
	public final Person getPerson() {
		return this.person; 
	}
	
	/** Sets description. 
	 * @param description - description. */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	
	/** Sets address.
	 * @param address - address. */
	public void setAddress(final Address address) {
		this.address = address;
	}
	
	/** Sets person.
	 * @param person - person. */
	public void setPerson(final Person person) {
		this.person = person;
	}
	
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
	public boolean equals(final Object obj) {
		boolean result = false;
		this.checkState();
		
		if (this == obj) {
			result = true;
		} else if (obj instanceof Correspondent) {
			Correspondent that = (Correspondent) obj;
			if (this.getAddress().equals(that.getAddress())
					&& this.getDescription().equals(that.getDescription())) {
				result = true;
			}
		}
		return result;
	}
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	public int hashCode() {
		this.checkState();
		return this.getDescription().hashCode() * HASHS[0];
	}
	
	private void checkState() {
		if (this.getDescription() == null) {
			throw new IllegalStateException(NO_DESCRIPTION);
		}
	}
}
