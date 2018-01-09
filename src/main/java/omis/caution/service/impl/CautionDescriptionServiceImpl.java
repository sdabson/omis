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
package omis.caution.service.impl;

import java.util.List;

import omis.caution.dao.CautionDescriptionDao;
import omis.caution.domain.CautionDescription;
import omis.caution.service.CautionDescriptionService;

/**
 * Implementation of service for caution descriptions.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (June 19, 2013)
 * @since OMIS 3.0
 */
public class CautionDescriptionServiceImpl
		implements CautionDescriptionService {

	private final CautionDescriptionDao cautionDescriptionDao;
	
	/**
	 * Instantiates a implementation of service for caution descriptions
	 * with the specified date access object.
	 * 
	 * @param cautionDescriptionDao data access object for caution descriptions
	 */
	public CautionDescriptionServiceImpl(
			final CautionDescriptionDao cautionDescriptionDao) {
		this.cautionDescriptionDao = cautionDescriptionDao;
	}

	/** {@inheritDoc} */
	@Override
	public List<CautionDescription> findAll() {
		return this.cautionDescriptionDao.findAll();
	}
}