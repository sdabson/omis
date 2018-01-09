package omis.region.service.impl;

import java.util.List;

import omis.region.dao.CountyDao;
import omis.region.domain.County;
import omis.region.domain.State;
import omis.region.service.CountyService;

/** Implementation of service for county.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 09, 2013)
 * @since OMIS 3.0 */
public class CountyServiceImpl implements CountyService {

    private CountyDao countyDao;
    
    /** {@inheritDoc} */
    @Override
    public void setCountyDao(CountyDao countyDao) { this.countyDao = countyDao; }
    
    /** {@inheritDoc} */
    @Override
    public County findbyId(Long id) { return countyDao.findById(id, false); }

    @Override
    public List<County> findByState(State state) { return countyDao.findByState(state); }
}//CountyServiceImpl
