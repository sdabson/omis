package omis.region.service;

import java.util.List;

import omis.country.domain.Country;
import omis.region.dao.StateDao;
import omis.region.domain.State;

/** Service object for state related operations.
 * 
 * @author Ryan Johns
 * @author Joel Norris
 * @version 0.1.0 (Feb 09, 2013)
 * @since OMIS 3.0 */
public interface StateService {
    
    /** Sets state data access object.
     * 
     * @param stateDao state dao. 
     */
    void setStateDao(StateDao stateDao);
    
    /** Returns State with the corresponding id.
     * 
     * @param id id.
     * @return State. */
    State findById(Long id);
    
    /** Lists the states by country.
     * 
     * @param country country
     * @return list of States. */
    List<State> findByCountry(Country country);
    
    /**
     * Returns the home state.
     * 
     * @return state
     */
    State findHomeState();
}