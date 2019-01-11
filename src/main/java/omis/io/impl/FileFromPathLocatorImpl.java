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

/**
 * Implementation of file locator that locates a file from a path.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public class FileFromPathLocatorImpl
		implements FileLocator {

	private final String path;
	
	/**
	 * Instantiates an implementation of default file from path locator.
	 * 
	 * @param path path
	 */
	public FileFromPathLocatorImpl(final String path) {
		this.path = path;
	}
	
	/** {@inheritDoc} */
	@Override
	public File locate(final String filename, final boolean mustExist) {
		String actualFilename;
		if (this.path == null) {
			actualFilename = "";
		} else {
			if (!this.path.endsWith(File.separator)) {
				actualFilename = this.path + File.separator + filename;
			} else {
				actualFilename = this.path + filename;
			}
		}
		File file = new File(actualFilename);
		if ((!file.exists() && mustExist) || file.isDirectory()) {
			return null;
		} else {
			return file;
		}
	}
}