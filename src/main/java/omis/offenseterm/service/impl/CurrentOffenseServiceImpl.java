/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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