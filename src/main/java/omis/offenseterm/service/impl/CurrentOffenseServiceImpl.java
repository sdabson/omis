package omis.offenseterm.service.impl;

import omis.conviction.domain.Conviction;
import omis.conviction.service.delegate.ConvictionDelegate;
import omis.offenseterm.service.CurrentOffenseService;
import omis.sentence.domain.Sentence;
import omis.sentence.exception.ConnectedSentenceExistsException;
import omis.sentence.service.delegate.SentenceDelegate;

/**
 * Implementation of service for current offense.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CurrentOffenseServiceImpl
		implements CurrentOffenseService {

	private final ConvictionDelegate convictionDelegate;
	
	private final SentenceDelegate sentenceDelegate;
	
	/**
	 * Implementation of service for current offense.
	 * 
	 * @param sentenceDelegate delegate for sentences
	 * @param convictionDelegate delegate for convictions
	 */
	public CurrentOffenseServiceImpl(
			final ConvictionDelegate convictionDelegate,
			final SentenceDelegate sentenceDelegate) {
		this.convictionDelegate = convictionDelegate;
		this.sentenceDelegate = sentenceDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Conviction conviction)
			throws ConnectedSentenceExistsException {
		for (Sentence sentence
				: this.sentenceDelegate.findByConviction(conviction)) {
			if (sentence.getActive()
					&& this.sentenceDelegate.countConnected(sentence) > 0) {
				throw new ConnectedSentenceExistsException(
						"Connected sentences exist");
			}
			this.sentenceDelegate.remove(sentence);
		}
		this.convictionDelegate.remove(conviction);
	}
}