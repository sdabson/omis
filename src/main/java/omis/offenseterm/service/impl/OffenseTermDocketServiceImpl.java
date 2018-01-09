package omis.offenseterm.service.impl;

import java.util.List;

import omis.court.domain.Court;
import omis.court.service.delegate.CourtDelegate;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.offenseterm.service.OffenseTermDocketService;

/**
 * Implementation of service to manage offense term dockets.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenseTermDocketServiceImpl 
		implements OffenseTermDocketService {

	private final CourtDelegate courtDelegate;
	
	private final DocketDelegate docketDelegate;
	
	/**
	 * Instantiates implementation of service to manage offense term dockets.
	 * 
	 * @param courtDelegate delegate for courts
	 * @param docketDelegate delegate for dockets
	 */
	public OffenseTermDocketServiceImpl(final CourtDelegate courtDelegate,
			final DocketDelegate docketDelegate) {
		this.courtDelegate = courtDelegate;
		this.docketDelegate = docketDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Docket update(final Docket docket, final Court court, 
			final String docketValue) throws DocketExistsException {
		return this.docketDelegate.update(docket, court, docketValue);
	}

	/** {@inheritDoc} */
	@Override
	public List<Court> findCourts() {
		return this.courtDelegate.findAllCourts();
	}

}
