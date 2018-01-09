package omis.offenseterm.service;

import java.util.List;

import omis.court.domain.Court;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;

/**
* Service to manage offense term dockets.
*
*
* @author Josh Divine
* @version 0.0.1
* @since OMIS 3.0
*/
public interface OffenseTermDocketService {

	/**
	 * Updates a docket.
	 * 
	 * @param docket docket
	 * @param court court 
	 * @param docketValue docket value
	 * @return docket
	 * @throws DocketExistsException if docket already exists
	 */
	Docket update(Docket docket, Court court, String docketValue) 
			throws DocketExistsException;
	
	/**
	 * Returns a list of courts.
	 * 
	 * @return list of courts
	 */
	List<Court> findCourts();
}
