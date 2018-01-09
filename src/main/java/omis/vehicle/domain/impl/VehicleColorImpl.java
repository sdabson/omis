package omis.vehicle.domain.impl;

import omis.vehicle.domain.VehicleColor;

/** Implementation of vehicle color.
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.0 (July 22, 2014)
 * @since OMIS 3.0 */
public class VehicleColorImpl implements VehicleColor {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean valid;
	
	/** Constructor. */
	public VehicleColorImpl() {
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
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VehicleColor)) {
			return false;
		}
		
		VehicleColor that = (VehicleColor) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getName().equals(that.getName())) {
			return true;
		}
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		
		int hashCode = 14;
		
		hashCode += 29 * this.getName().hashCode();
		
		return hashCode;
	}
}
