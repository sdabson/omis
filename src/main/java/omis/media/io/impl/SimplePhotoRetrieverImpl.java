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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import omis.io.FileLocator;
import omis.media.domain.Photo;
import omis.media.io.PhotoRetriever;

/**
 * Simple implementation of photo retriever.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public class SimplePhotoRetrieverImpl
		implements PhotoRetriever {

	private final FileLocator fileLocator;
	
	private final String notFoundFilename;

	/**
	 * Instantiates a simple implementation of photo retriever.
	 * 
	 * @param fileLocator locator used to find file
	 */
	public SimplePhotoRetrieverImpl(final FileLocator fileLocator,
			final String defaultFilename) {
		this.fileLocator = fileLocator;
		this.notFoundFilename = defaultFilename;
	}
	
	/** {@inheritDoc} */
	@Override
	public byte[] retrieve(final Photo photo) {
		return this.retrieve(photo.getFilename());
	}

	/** {@inheritDoc} */
	@Override
	public byte[] retrieve(final String filename) {
		File file = this.fileLocator.locate(filename, true);
		if (file == null
				&& (this.notFoundFilename != null
					&& this.notFoundFilename.length() > 0)) {
			file = this.fileLocator.locate(this.notFoundFilename, true);
		}
		if (file != null) {
			BufferedInputStream buffer = null;
			try {
				buffer = new BufferedInputStream(new FileInputStream(file));
				byte[] bytes = new byte[buffer.available()];
				buffer.read(bytes);
				return bytes;
			} catch (IOException ioe) {
				throw new RuntimeException(
						"Error opening file: " + file.getName(), ioe);
			} finally {
				if (buffer != null) {
					try {
						buffer.close();
					} catch (IOException ioe) {
						throw new RuntimeException(
								"Error closing file: " + file.getName(), ioe);
					}
				}
			}
		} else {
			return new byte[] { };
		}
	}
}