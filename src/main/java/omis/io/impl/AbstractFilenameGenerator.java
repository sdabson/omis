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

import omis.io.FilenameGenerator;

/**
 * Abstract implementation of filename generator.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
public abstract class AbstractFilenameGenerator
		implements FilenameGenerator {

	private String prefix;
	
	private String suffix;

	private String extension;

	/** {@inheritDoc} */
	@Override
	public void setPrefix(final String prefix) {
		this.prefix = prefix;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setExtension(final String extension) {
		this.extension = extension;
	}
	
	/** {@inheritDoc} */
	@Override
	public String generate() {
		return (this.prefix != null ? this.prefix : "")
				+ this.getDistinguisher()
				+ (this.suffix != null ? this.suffix : "")
				+ (this.extension != null ? "." + this.extension : "");
	}
	
	/**
	 * Returns a value used to distinguish files.
	 * @return value used to distinguish files
	 */
	protected abstract String getDistinguisher();
}