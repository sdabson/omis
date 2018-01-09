package omis.employment.domain.impl;

import omis.employment.domain.Employer;
import omis.location.domain.Location;

/** Implementation of employer.
 * @author Yidong Li
 * @version 0.1.0 (Feb 11, 2015)
 * @since OMIS 3.0 */
public class EmployerImpl implements Employer {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Location location;
	private Long telephoneNumber;

	/** Constructor. */
	public EmployerImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Location getLocation() {
		return this.location;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(final Location location) {
		this.location = location;
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getTelephoneNumber() {
		return this.telephoneNumber;
	}

	/** {@inheritDoc} */
	@Override
	public void setTelephoneNumber(final Long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Employer)) {
			return false;
		}
		Employer that = (Employer) obj;
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		if(this.getLocation().equals(that.getLocation())) {
			return true;
		} else {
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		hashCode += 29 * this.getLocation().hashCode();
		return hashCode;
	}
}
