package omis.region.service;

import java.util.List;

import omis.region.dao.CityDao;
import omis.region.domain.City;
import omis.region.domain.County;
import omis.region.domain.State;

/** Service object for City related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 09, 2013)
 * @since OMIS 3.0 */
public interface CityService {
    
    /** Sets the city data access object.
     * @param City dao.*/
    public void setCityDao(CityDao cityDao);
    
    /** Returns city with the specified id.
     * @param id.
     * @return City. */
    public City findById(Long id);
    
    /** List cities by state.
     * @param State.
     * @return list of cites. */
    public List<City> findByState(State state);
    
    /** List cities by county.
     * @param County.
     * @return list of cities. */
    public List<City> findByCounty(County county);
}//CityService
