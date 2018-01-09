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
package omis.caution.dao.impl.hibernate;

import java.util.List;

import omis.caution.dao.CautionSourceDao;
import omis.caution.domain.CautionSource;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for caution sources.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 22, 2013)
 * @since OMIS 3.0
 */
public class CautionSourceDaoHibernateImpl
		extends GenericHibernateDaoImpl<CautionSource>
		implements CautionSourceDao {

	/**
	 * Instantiates a data access object for caution sources with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CautionSourceDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CautionSource> findAll() {
		@SuppressWarnings("unchecked")
		List<CautionSource> sources = getSessionFactory()
			.getCurrentSession().getNamedQuery("findCautionSources").list();
		return sources;
	}
	
	/** {@inheritDoc} */
	@Override
	public CautionSource find(final String name) {
		CautionSource source = (CautionSource) getSessionFactory()
			.getCurrentSession().getNamedQuery("findCautionSource")
			.setParameter("name", name).uniqueResult();
		return source;
	}
}