package omis.health.domain.impl;

import omis.datatype.DateRange;
import omis.facility.domain.Facility;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderTitle;
import omis.person.domain.Person;

/**
 * Provider assignment implementation.
 *
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class ProviderAssignmentImpl implements ProviderAssignment {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Person provider;

	private Facility facility;

	private DateRange dateRange;

	private MedicalFacility medicalFacility;
	
	private ProviderTitle title;

	private Boolean contracted;

	/**
	 * Instantiates a default instance of provider assignment implementation.
	 */
	public ProviderAssignmentImpl() {
		//default constructor
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
	public Person getProvider() {
		return this.provider;
	}

	/** {@inheritDoc} */
	@Override
	public void setProvider(final Person provider) {
		this.provider = provider;
	}

	/** {@inheritDoc} */
	@Override
	public Facility getFacility() {
		return this.facility;
	}

	/** {@inheritDoc} */
	@Override
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public MedicalFacility getMedicalFacility() {
		return this.medicalFacility;
	}

	/** {@inheritDoc} */
	@Override
	public void setMedicalFacility(final MedicalFacility medicalFacility) {
		this.medicalFacility = medicalFacility;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderTitle getTitle() {
		return this.title;
	}

	/** {@inheritDoc} */
	@Override
	public void setTitle(final ProviderTitle title) {
		this.title = title;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getContracted() {
		return this.contracted;
	}

	/** {@inheritDoc} */
	@Override
	public void setContracted(final Boolean contracted) {
		this.contracted = contracted;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProviderAssignment)) {
			return false;
		}

		final ProviderAssignment that = (ProviderAssignment) o;

		if (this.getProvider() == null) {
			throw new IllegalStateException("Provider required.");
		}
		if (!this.getProvider().equals(that.getProvider())) {
			return false;
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required.");
		}
		if (!this.getFacility().equals(that.getFacility())) {
			return false;
		}
		/*if (this.getDateRange().getStartDate() == null) {
			throw new IllegalStateException("Start date required.");
		}
		if (!this.getDateRange().getStartDate().equals(
				that.getDateRange().getStartDate())) {
			return false;
		}
		if (this.getDateRange().getEndDate() == null) {
			throw new IllegalStateException("End date required.");
		}
		if (!this.getDateRange().getEndDate().equals(
				that.getDateRange().getEndDate())) {
			return false;
		}*/
		if (this.getTitle() == null) {
			throw new IllegalStateException("Title required.");
		}
		if (!this.getTitle().equals(that.getTitle())) {
			return false;
		}

		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getProvider() == null) {
			throw new IllegalStateException("Provider required.");
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required.");
		}
		/*if (this.getDateRange().getStartDate() == null) {
			throw new IllegalStateException("Start date required.");
		}
		*/
		int hashCode = 14;

		hashCode = 29 * hashCode + this.getProvider().hashCode();
		hashCode = 29 * hashCode + this.getFacility().hashCode();
		//hashCode = 29 * hashCode + this.getDateRange()
				//.getStartDate().hashCode();

		return hashCode;
	}
}