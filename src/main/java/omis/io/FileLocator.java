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
package omis.io;

import java.io.File;

/**
 * Locates a file using a file name.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public interface FileLocator {

	/**
	 * Returns a file using the given filename.
	 * 
	 * @param filename filename of file to locate
	 * @param mustExist enforces that the file exists
	 * @return file located using filename; {@code null} if the file
	 * does not exist and {@code mustExist} is {@code true} or is a directory
	 */
	File locate(String filename, boolean mustExist);
}