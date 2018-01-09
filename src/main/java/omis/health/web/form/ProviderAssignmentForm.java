package omis.health.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderSpecialty;
import omis.health.domain.ProviderTitle;
import omis.person.domain.Person;

/**
 * Provider form.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class ProviderAssignmentForm {

	private Person provider;
	
	private Date startDate;
	
	private Date endDate;
	
	private MedicalFacility medicalFacility;
	
	private ProviderType providerType;
	
	private ProviderTitle title;
	
	private List<ProviderSpecialty> providerSpecialties = 
			new ArrayList<ProviderSpecialty>();

	/**
	 * Instantiates a default instance of provider form.
	 */
	public ProviderAssignmentForm() {
		//Default constructor.
	}
	
	/**
	 * Returns the provider.
	 * 
	 * @return provider
	 */
	public Person getProvider() {
		return this.provider;
	}

	/**
	 * Sets the provider.
	 * 
	 * @param provider person
	 */
	public void setProvider(final Person provider) {
		this.provider = provider;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Returns medical facility.
	 * 
	 * @return medical facility
	 */
	public MedicalFacility getMedicalFacility() {
		return this.medicalFacility;
	}

	/**
	 * Sets medical facility.
	 * 
	 * @param medicalFacility medical facility
	 */
	public void setMedicalFacility(final MedicalFacility medicalFacility) {
		this.medicalFacility = medicalFacility;
	}

	/**
	 * Returns the provider type.
	 * 
	 * @return provider type
	 */
	public ProviderType getProviderType() {
		return this.providerType;
	}

	/**
	 * Sets the provider type.
	 * 
	 * @param providerType provider type
	 */
	public void setProviderType(final ProviderType providerType) {
		this.providerType = providerType;
	}

	/**
	 * Returns the provider title.
	 * 
	 * @return title
	 */
	public ProviderTitle getTitle() {
		return this.title;
	}

	/**
	 * Sets the provider title.
	 * 
	 * @param title provider title
	 */
	public void setTitle(final ProviderTitle title) {
		this.title = title;
	}

	/**
	 * Returns a list of provider specialties.
	 * 
	 * @return provider specialties
	 */
	public List<ProviderSpecialty> getProviderSpecialties() {
		return this.providerSpecialties;
	}

	/**
	 * Sets a list of provider specialties.
	 * 
	 * @param providerSpecialties provider specialties
	 */
	public void setProviderSpecialties(
			final List<ProviderSpecialty> providerSpecialties) {
		this.providerSpecialties = providerSpecialties;
	}
}