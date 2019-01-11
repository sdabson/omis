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
package omis.media.io;

import omis.media.domain.Photo;

/**
 * Given a photo, retrieves the photo data.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public interface PhotoRetriever {
	
	/**
	 * Retrieve the photo data for the photo.
	 * 
	 * @param photo photo the data of which to retrieve; retrieve the photo
	 * at the file named {@code notFoundFilename} if not found; if
	 * {@code notFoundFilename} is not set or cannot be found, return an empty
	 * byte array
	 * @return photo from filename or empty byte array if no suitable
	 * file can be found
	 */
	byte[] retrieve(Photo photo);
	
	/**
	 * Retrieve the photo data for the photo.
	 * 
	 * @param filename filename of the photo the data of which to retrieve;
	 * retrieve the photo at the file named {@code notFoundFilename} if not
	 * found; if {@code notFoundFilename} is not set or cannot be found, return
	 * an empty byte array
	 * @return photo from filename or empty byte array if no suitable
	 * file can be found
	 */
	byte[] retrieve(String filename);
}