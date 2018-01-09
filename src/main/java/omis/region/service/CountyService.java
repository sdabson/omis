package omis.region.service;

import java.util.List;

import omis.region.dao.CountyDao;
import omis.region.domain.County;
import omis.region.domain.State;

/** Service object for County related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 09, 2013)
 * @since OMIS 3.0 */
public interface CountyService {
    
    /** Set the county data access object. 
     * @param county dao. */
    public void setCountyDao(CountyDao countyDao);

    /** Returns county with specified id.
     * @param id.
     * @return County. */
    public County findbyId(Long id);
    
    /** List county by state.
     * @param State.
     * @return list of counties. */
    public List<County> findByState(State state);    
}//CountyService
