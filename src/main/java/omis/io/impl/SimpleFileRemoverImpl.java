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
package omis.io.impl;

import java.io.File;

import omis.io.FileLocator;
import omis.io.FileRemover;

/**
 * Simple implementation of file remover.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (Dec 8, 2016)
 * @since OMIS 3.0
 */
public class SimpleFileRemoverImpl
		implements FileRemover {

	private final FileLocator fileLocator;

	/**
	 * Instantiates a simple implementation of file remover.
	 * 
	 * @param fileLocator file locator
	 */
	public SimpleFileRemoverImpl(final FileLocator fileLocator) {
		this.fileLocator = fileLocator;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean remove(final String filename) {
		File file = this.fileLocator.locate(filename, true);
		if (file != null) {
			return file.delete();
		} else {
			return false;
		}
	}
}