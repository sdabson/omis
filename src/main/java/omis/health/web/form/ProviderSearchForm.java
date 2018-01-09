package omis.health.web.form;

import java.io.Serializable;

import omis.person.domain.Person;
import omis.region.domain.City;
import omis.region.domain.County;

/**
 * @author cib168
 *
 */
public class ProviderSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Person provider;
	
	//should be enum ServiceCategory
	private String serviceCategory;
	
	private City city;
	
	private County county;
	
	//should be enum TargetPopulation
	private String population;	
	

	/** Instantiates a default offender form. */
	public ProviderSearchForm() {
		//Default instantiation
	}


	/**
	 *
	 * @return the provider
	 */
	public Person getProvider() {
		return provider;
	}


	/**
	 * @param provider the provider to set
	 */
	public void setProvider(Person provider) {
		this.provider = provider;
	}


	/**
	 *
	 * @return the serviceCategory
	 */
	public String getServiceCategory() {
		return serviceCategory;
	}


	/**
	 * @param serviceCategory the serviceCategory to set
	 */
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}


	/**
	 *
	 * @return the city
	 */
	public City getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}


	/**
	 *
	 * @return the county
	 */
	public County getCounty() {
		return county;
	}


	/**
	 * @param county the county to set
	 */
	public void setCounty(County county) {
		this.county = county;
	}


	/**
	 *
	 * @return the population
	 */
	public String getPopulation() {
		return population;
	}


	/**
	 * @param population the population to set
	 */
	public void setPopulation(String population) {
		this.population = population;
	}	
}