package omis.citizenship.dao;

import java.util.List;

import omis.citizenship.domain.Citizenship;
import omis.country.domain.Country;
import omis.dao.GenericDao;
import omis.offender.domain.Offender;

/**
 * Data access object for citizenships.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 24, 2014)
 * @since OMIS 3.0
 */
public interface CitizenshipDao
		extends GenericDao<Citizenship> {

	/**
	 * Returns citizenship of offender.
	 * 
	 * @param offender offender
	 * @return citizenship of offender
	 */
	Citizenship findByOffender(Offender offender);
	
	/**
	 * Returns citizenships by country.
	 * 
	 * @param country country
	 * @return citizenships by country
	 */
	List<Citizenship> findByCountry(Country country);
}