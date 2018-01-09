package omis.military.dao;

import omis.dao.GenericDao;
import omis.military.domain.MilitaryDischargeStatus;

/**
 * Military discharge status data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public interface MilitaryDischargeStatusDao 
extends GenericDao<MilitaryDischargeStatus>{

	MilitaryDischargeStatus find(String name, Boolean valid);
}