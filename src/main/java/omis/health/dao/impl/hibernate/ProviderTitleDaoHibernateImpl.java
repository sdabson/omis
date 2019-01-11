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
package omis.health.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ProviderTitleDao;
import omis.health.domain.ProviderTitle;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for provider titles.
 *  
 * @author Sheronda Vaughn
 * @author Yidong Li
 * @version 0.1.0 (Oct 26, 2018)
 * @since OMIS 3.0
 */
public class ProviderTitleDaoHibernateImpl 
		extends GenericHibernateDaoImpl<ProviderTitle>
		implements ProviderTitleDao {
	/* Query names. */

	private static final String FIND_EXISTING_PROVIDER_TITLE_QUERY_NAME =
			"findExistingProviderTitle";
	
	/* Parameter names. */

	private static final String NAME_PARAMETER_NAME = "name";

	/**
	 * Instantiates a hibernate implementation of data access object for
	 * provider titles.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProviderTitleDaoHibernateImpl(final SessionFactory sessionFactory,
					final String entityName) { super(sessionFactory, 
							entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderTitle findExisting(final String name) {
		final ProviderTitle title
			= (ProviderTitle) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXISTING_PROVIDER_TITLE_QUERY_NAME)
			.setParameter(NAME_PARAMETER_NAME, name)
			.setReadOnly(true)
			.uniqueResult();
		return title;
	}
}
