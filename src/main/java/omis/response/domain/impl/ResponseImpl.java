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
package omis.response.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.response.domain.Grid;
import omis.response.domain.Response;
import omis.response.domain.ResponseCategory;
import omis.response.domain.ResponseLevel;

/**
 * Implementation of response.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public class ResponseImpl implements Response {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String description;
	
	private Grid grid;
	
	private ResponseCategory category;
	
	private ResponseLevel level;
	
	private Boolean valid;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default implementation of response.
	 */
	public ResponseImpl() {
		// Default constructor
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return description;
	}

	/** {@inheritDoc} */
	@Override
	public void setGrid(final Grid grid) {
		this.grid = grid;
	}

	/** {@inheritDoc} */
	@Override
	public Grid getGrid() {
		return grid;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final ResponseCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public ResponseCategory getCategory() {
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public void setLevel(final ResponseLevel level) {
		this.level = level;
	}

	/** {@inheritDoc} */
	@Override
	public ResponseLevel getLevel() {
		return level;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}
}