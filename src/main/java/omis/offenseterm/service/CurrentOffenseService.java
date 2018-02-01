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