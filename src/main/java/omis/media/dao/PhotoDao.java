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
package omis.media.dao;

import omis.dao.GenericDao;
import omis.media.domain.Photo;

/**
 * Data access object for photos.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (July 9, 2018)
 * @since OMIS 3.0
 */
public interface PhotoDao
		extends GenericDao<Photo> {
	
	/**
	 * Returns photo with filename.
	 * 
	 * @param filename filename
	 * @return photo
	 */
	Photo find(String filename);

	/**
	 * Returns photo with filename if not excluded.
	 * 
	 * @param filename filename
	 * @param excludedPhotos photos to exclude
	 * @return photo with filename if not excluded
	 */
	Photo findExcluding(String filename, Photo... excludedPhotos);
	
	/**
	 * Returns the next database generated filename distinguisher for photos.
	 * 
	 * <p>An implementation may not use database generated distinguisher for
	 * photo filenames. In such an implementation, an
	 * {@code UnsupportedOperationException} will be thrown.
	 * 
	 * @return next database generated filename distinguisher
	 * @throws UnsupportedOperationException if the implementation does not
	 * support database generated filename distinguisher for photos
	 */
	String findNextFilenameDistinguisher();
}