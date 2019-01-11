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

/**
 * Generates a file name.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
public interface FilenameGenerator {

	/**
	 * Sets the prefix.
	 * 
	 * @param prefix prefix
	 */
	void setPrefix(String prefix);
	
	/**
	 * Sets the suffix.
	 * 
	 * @param suffix suffix
	 */
	void setSuffix(String suffix);
	
	/**
	 * Sets the extension.
	 * 
	 * @param extension extension
	 */
	void setExtension(String extension);
	
	/**
	 * Generates filename.
	 * 
	 * @return filename
	 */
	String generate();
}