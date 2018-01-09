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

import omis.caution.dao.CautionSourceDao;
import omis.caution.domain.CautionSource;
import omis.caution.service.CautionSourceService;

/**
 * Implementation of service for caution sources.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (June 19, 2013)
 * @since OMIS 3.0
 */
public class CautionSourceServiceImpl
		implements CautionSourceService {

	private final CautionSourceDao cautionSourceDao;
	
	/**
	 * Instantiates a implementation of service for caution sources with the
	 * specified data access object.
	 * 
	 * @param cautionSourceDao data access object for caution sources
	 */
	public CautionSourceServiceImpl(final CautionSourceDao cautionSourceDao) {
		this.cautionSourceDao = cautionSourceDao;
	}

	/** {@inheritDoc} */
	@Override
	public List<CautionSource> findAll() {
		return this.cautionSourceDao.findAll();
	}
}