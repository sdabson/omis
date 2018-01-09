package omis.region.service.impl;

import java.util.List;

import omis.country.domain.Country;
import omis.region.dao.StateDao;
import omis.region.domain.State;
import omis.region.service.StateService;

/** Implementation of state service.
 * @author Ryan Johns
 * @author Joel Norris
 * @version 0.1.0 (Feb 09, 2013)
 * @since OMIS 3.0 */
public class StateServiceImpl implements StateService {

    private StateDao stateDao;
    
    /** {@inheritDoc} */
    @Override
    public void setStateDao(final StateDao stateDao) { 
    	this.stateDao = stateDao; 
    }
    
    /** {@inheritDoc} */
    @Override
    public State findById(final Long id) { 
    	return this.stateDao.findById(id, false); 
    }
    
    /** {@inheritDoc} */
    @Override
    public List<State> findByCountry(final Country country) { 
    	return this.stateDao.findByCountry(country); 
    }

    /** {@inheritDoc} */
	@Override
	public State findHomeState() {
		return this.stateDao.findHomeState();
	}
}