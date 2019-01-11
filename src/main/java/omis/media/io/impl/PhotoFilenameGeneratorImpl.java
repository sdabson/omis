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
package omis.media.io.impl;

import omis.io.FilenameGenerator;
import omis.io.impl.AbstractFilenameGenerator;
import omis.media.dao.PhotoDao;

/**
 * Generator for photo filenames.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
public class PhotoFilenameGeneratorImpl
		extends AbstractFilenameGenerator
		implements FilenameGenerator {

	private final PhotoDao photoDao;

	/**
	 * Instantiates a simple implementation of photo filename generator.
	 * 
	 * @param photoDao data access object for photos
	 */
	public PhotoFilenameGeneratorImpl(final PhotoDao photoDao) {
		this.photoDao = photoDao;
	}
	
	/** {@inheritDoc} */
	@Override
	protected String getDistinguisher() {
		return this.photoDao.findNextFilenameDistinguisher();
	}
}