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
package omis.response.report;

import java.io.Serializable;

import omis.response.domain.ResponseCategory;

/**
 * Response summary.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Mar 5, 2019)
 * @since OMIS 3.0
 */
public class ResponseSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long responseId;
	
	private final String responseDescription;
	
	private final ResponseCategory category;
	
	private final String gridName;
	
	private final Boolean valid;
	
	private final String levelName;

	/**
	 * Instantiates a response summary.
	 * 
	 * @param responseId response id
	 * @param responseDescription response description
	 * @param category response category
	 * @param gridName grid name
	 * @param valid valid
	 * @param levelName level name
	 */
	public ResponseSummary(final Long responseId, 
			final String responseDescription, final ResponseCategory category,
			final String gridName, final Boolean valid,
			final String levelName) {
		this.responseId = responseId;
		this.responseDescription = responseDescription;
		this.category = category;
		this.gridName = gridName;
		this.valid = valid;
		this.levelName = levelName;
	}
	
	/**
	 * Returns the response id.
	 *
	 * @return response id
	 */
	public Long getResponseId() {
		return responseId;
	}

	/**
	 * Returns the response description.
	 *
	 * @return response description
	 */
	public String getResponseDescription() {
		return responseDescription;
	}

	/**
	 * Returns the category.
	 *
	 * @return category
	 */
	public ResponseCategory getCategory() {
		return category;
	}

	/**
	 * Returns the grid name.
	 *
	 * @return grid name
	 */
	public String getGridName() {
		return gridName;
	}

	/**
	 * Returns the valid.
	 *
	 * @return valid
	 */
	public Boolean getValid() {
		return valid;
	}

	/**
	 * Returns the level name.
	 *
	 * @return level name
	 */
	public String getLevelName() {
		return levelName;
	}
}