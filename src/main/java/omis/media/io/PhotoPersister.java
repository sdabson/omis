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

import java.io.File;

import omis.media.domain.Photo;

/**
 * Persists photo data.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
public interface PhotoPersister {

	/**
	 * Persists specified photo data for specified photo.
	 * 
	 * @param photo photo
	 * @param data photo data
	 * @return file
	 * @throws IllegalArgumentException if the filename of the photo is not
	 * valid (for instance, if it is a directory)
	 */
	File persist(Photo photo, byte[] data);
}