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

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.response.dao.ResponseDao;
import omis.response.domain.Response;

/**
 * Hibernate implementation of the response data access object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public class ResponseDaoHibernateImpl 
		extends GenericHibernateDaoImpl<Response> 
		implements ResponseDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findResponse";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findResponseExcluding";
	
	/* Parameters. */
	
	private static final String DESCRIPTION_PARAMETER_NAME = "description";
	
	private static final String EXCLUDED_RESPONSE_PARAMETER_NAME = 
			"excludedResponse";
	
	/**
	* Instantiates an hibernate implementation of the data access object for 
	* response level.
	* 
	* @param sessionFactory session factory
	* @param entityName entity name
	*/
	protected ResponseDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public Response find(final String name) {
		Response response = (Response) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAMETER_NAME, name)
				.uniqueResult();
		return response;
	}

	/** {@inheritDoc} */
	@Override
	public Response findExcluding(final String name, 
			final Response excludedResponse) {
		Response response = (Response) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAMETER_NAME, name)
				.setParameter(EXCLUDED_RESPONSE_PARAMETER_NAME, 
						excludedResponse)
				.uniqueResult();
		return response;
	}
}