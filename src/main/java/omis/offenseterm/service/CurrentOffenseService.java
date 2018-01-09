package omis.offenseterm.service;

import omis.conviction.domain.Conviction;
import omis.sentence.exception.ConnectedSentenceExistsException;

/**
 * Service for current offenses.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface CurrentOffenseService {

	/**
	 * Removes conviction and its sentences.
	 * 
	 * @param conviction conviction
	 * @throws ConnectedSentenceExistsException if the conviction has an
	 * associated active sentence that is connected to by other sentences
	 */
	void remove(Conviction conviction) throws ConnectedSentenceExistsException;
}