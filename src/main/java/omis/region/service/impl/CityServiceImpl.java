package omis.region.service.impl;

import java.util.List;

import omis.region.dao.CityDao;
import omis.region.domain.City;
import omis.region.domain.County;
import omis.region.domain.State;
import omis.region.service.CityService;

/** Implementation of service for City.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 09, 2013)
 * @since OMIS 3.0*/
public class CityServiceImpl 
       implements CityService {
    
    private CityDao cityDao;
    
    /** {@inheritDoc} */
    @Override
    public void setCityDao(CityDao cityDao) { this.cityDao = cityDao; }
    
    /** {@inheritDoc} */
    @Override
    public City findById(Long id) { return cityDao.findById(id, false); }
    
    /** {@inheritDoc} */
    @Override
    public List<City> findByState(State state) { return cityDao.findByState(state); }
    
    /** {@inheritDoc} */
    @Override
    public List<City> findByCounty(County county) { return cityDao.findByCounty(county); }
    
}//CityServiceImpl
