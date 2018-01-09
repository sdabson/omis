package omis.region.dao;

import omis.dao.GenericDao;
import omis.region.domain.City;
import omis.region.domain.CityCountyAssociation;
import omis.region.domain.County;

/**
 * Data access object for association of city to county.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 9, 2015)
 * @since OMIS 3.0
 */
public interface CityCountyAssociationDao
		extends GenericDao<CityCountyAssociation> {

	/**
	 * Returns association of city to county.
	 * 
	 * <p>Returns {@code null} if the city and county are not associated.
	 * 
	 * @param city city
	 * @param county county
	 * @return association of city to county; {@code null} if city and county
	 * are not associated
	 */
	CityCountyAssociation find(City city, County county);
	
	/**
	 * Returns association of city to county with associations excluded.
	 * 
	 * <p>Returns {@code null} if the city and county are not associated
	 * other than by excluded associations.
	 * 
	 * @param city city
	 * @param county county
	 * @param excludedAssociations associations to exclude
	 * @return association of city to county; {@code null} if city and county
	 * are not associated other than by excluded associations
	 */
	CityCountyAssociation findExcluding(City city, County county,
			CityCountyAssociation... excludedAssociations);
}