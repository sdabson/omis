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
package omis.response.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.response.dao.ResponseLevelDao;
import omis.response.domain.ResponseLevel;

/**
 * Hibernate implementation of the response level data access object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public class ResponseLevelDaoHibernateImpl 
		extends GenericHibernateDaoImpl<ResponseLevel> 
		implements ResponseLevelDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findResponseLevel";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findResponseLevelExcluding";
	
	private static final String FIND_ACTIVE_QUERY_NAME = 
			"findActiveResponseLevels";
	
	/* Parameters. */
	
	private static final String NAME_PARAMETER_NAME = "name";
	
	private static final String EXCLUDED_RESPONSE_LEVEL_PARAMETER_NAME = 
			"excludedResponseLevel";
	
	/**
	* Instantiates an hibernate implementation of the data access object for 
	* response level.
	* 
	* @param sessionFactory session factory
	* @param entityName entity name
	*/
	protected ResponseLevelDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ResponseLevel find(final String name) {
		ResponseLevel responseLevel = (ResponseLevel) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAMETER_NAME, name)
				.uniqueResult();
		return responseLevel;
	}

	/** {@inheritDoc} */
	@Override
	public ResponseLevel findExcluding(final String name, 
			final ResponseLevel excludedResponseLevel) {
		ResponseLevel responseLevel = (ResponseLevel) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAMETER_NAME, name)
				.setParameter(EXCLUDED_RESPONSE_LEVEL_PARAMETER_NAME, 
						excludedResponseLevel)
				.uniqueResult();
		return responseLevel;
	}

	/** {@inheritDoc} */
	@Override
	public List<ResponseLevel> findActive() {
		@SuppressWarnings("unchecked")
		List<ResponseLevel> responseLevels = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ACTIVE_QUERY_NAME)
				.list();
		return responseLevels;
	}
}