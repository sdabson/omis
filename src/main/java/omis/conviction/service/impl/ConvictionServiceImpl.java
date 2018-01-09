package omis.conviction.service.impl;

import java.util.Date;
import java.util.List;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.exception.ConvictionExistsException;
import omis.conviction.service.ConvictionService;
import omis.conviction.service.delegate.ConvictionDelegate;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.service.delegate.ChargeDelegate;
import omis.offense.domain.Offense;
import omis.offense.service.delegate.OffenseDelegate;
import omis.sentence.domain.Sentence;
import omis.sentence.service.delegate.SentenceDelegate;

/**
 * Implementation of service for convictions.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.3 (Jan 30, 2017)
 * @since OMIS 3.0
 */
public class ConvictionServiceImpl implements ConvictionService {
	
	private final ConvictionDelegate convictionDelegate;
	
	private final ChargeDelegate chargeDelegate;
	
	private final OffenseDelegate offenseDelegate;
	
	private final SentenceDelegate sentenceDelegate;
	
	/**
	 * Instantiates implementation of service for convictions.
	 * 
	 * @param convictionDelegate delegate to manage convictions
	 * @param chargeDelegate delegate to manage charges
	 * @param offenseDelegate delegate to manage offenses
	 * @param termCalculatorDelegate delegate to manage term calculations
	 * @param sentenceDelegate delegate to manage sentences
	 */
	public ConvictionServiceImpl(
			final ConvictionDelegate convictionDelegate,
			final ChargeDelegate chargeDelegate,
			final OffenseDelegate offenseDelegate,
			final SentenceDelegate sentenceDelegate) {
		this.convictionDelegate = convictionDelegate;
		this.chargeDelegate = chargeDelegate;
		this.offenseDelegate = offenseDelegate;
		this.sentenceDelegate = sentenceDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Conviction convict(final CourtCase courtCase, final Offense offense, 
			final OffenseSeverity severity, 
			final Date date, final Integer counts, final ConvictionFlags flags) 
					throws ConvictionExistsException {
		return this.convictionDelegate.convict(courtCase, offense, severity, 
				date, counts, flags);
	}

	/** {@inheritDoc} */
	@Override
	public Conviction update(final Conviction conviction, final Offense offense, 
			final OffenseSeverity severity, 
			final Date date, final Integer counts, final ConvictionFlags flags) 
					throws ConvictionExistsException {
		return this.convictionDelegate.update(conviction, severity, offense, 
				date, counts, flags);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(Conviction conviction) {
		this.convictionDelegate.remove(conviction);
	}

	/** {@inheritDoc} */
	@Override
	public List<Charge> findChargesByCourtCase(
			final CourtCase courtCase) {
		return this.chargeDelegate.findByCourtCase(courtCase);
	}

	/** {@inheritDoc} */
	@Override
	public List<Offense> findOffenses() {
		return this.offenseDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasConvictions(final CourtCase courtCase) {
		return this.convictionDelegate.hasConvictions(courtCase);
	}

	/** {@inheritDoc} */
	@Override
	public List<Sentence> findByConviction(final Conviction conviction) {
		return this.sentenceDelegate.findByConviction(conviction);
	}

	/** {@inheritDoc} */
	@Override
	public Sentence findActiveByConviction(final Conviction conviction) {
		return this.sentenceDelegate.findActiveByConviction(conviction);
	}

	/** {@inheritDoc} */
	@Override
	public List<Conviction> findConvictionsByCourtCase(CourtCase courtCase) {
		return this.convictionDelegate.findByCourtCase(courtCase);
	}
	
}