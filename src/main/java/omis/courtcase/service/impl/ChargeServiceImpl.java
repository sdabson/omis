package omis.courtcase.service.impl;

import java.util.Date;
import java.util.List;

import omis.conviction.service.delegate.ConvictionDelegate;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.exception.ChargeExistsException;
import omis.courtcase.service.ChargeService;
import omis.courtcase.service.delegate.ChargeDelegate;
import omis.offense.domain.Offense;
import omis.offense.service.delegate.OffenseDelegate;

/**
 * Implementation of service for charges.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.4 (Aug 15, 2017)
 * @since OMIS 3.0
 */
public class ChargeServiceImpl
		implements ChargeService {

	private final ChargeDelegate chargeDelegate;
	private final OffenseDelegate offenseDelegate;
	private final ConvictionDelegate convictionDelegate;
	
	/**
	 * Implementation of service for charges.
	 * 
	 * @param chargeDelegate delegate for managing charges
	 * @param offenseDelegate delegate for managing offenses
	 * @param convictionDelegate delegate for managing convictions
	 */
	public ChargeServiceImpl(
			final ChargeDelegate chargeDelegate,
			final OffenseDelegate offenseDelegate,
			final ConvictionDelegate convictionDelegate) {
		this.chargeDelegate = chargeDelegate;
		this.offenseDelegate = offenseDelegate;
		this.convictionDelegate = convictionDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Charge create(final CourtCase courtCase, final Offense offense,
			final Date date, final Date fileDate, final Integer counts)
					throws ChargeExistsException {
		return this.chargeDelegate.create(courtCase, offense, date, fileDate, 
				counts);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Charge charge) {
		this.chargeDelegate.remove(charge);
	}

	/** {@inheritDoc} */
	@Override
	public List<Offense> findOffenses() {
		return this.offenseDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public Charge update(final Charge charge, final Offense offense, 
			final Date date, final Date fileDate, final Integer counts)
			throws ChargeExistsException {
		if (this.convictionDelegate.hasConvictions(charge.getCourtCase())) {
			throw new UnsupportedOperationException(
					"Cannot edit charges when a conviction exists.");
		}
		return this.chargeDelegate.update(charge, offense, date, fileDate, 
				counts);
	}
}