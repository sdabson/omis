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
package omis.courtdocument.dao.impl.hibernate;

import omis.courtdocument.dao.CourtDocumentCategoryDao;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

/** 
 * Hibernate implementation of court document category data access object.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.2 (Sep 25, 2018)
 * @since OMIS 3.0
 */
public class CourtDocumentCategoryDaoHibernateImpl 
		extends GenericHibernateDaoImpl<CourtDocumentCategory>
		implements CourtDocumentCategoryDao {
	
	/* Queries */
	
	private static final String FIND_QUERY_NAME =
			"findCourtDocumentCategory";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findCourtDocumentCategoryExcluding";
	
	private static final String FIND_VALID_QUERY_NAME =
			"findValidCourtDocumentCategories";
	
	/* Parameters */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_PARAM_NAME = "excluded";
	
	/** 
	 * Constructor.
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CourtDocumentCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public CourtDocumentCategory find(final String name) {
		CourtDocumentCategory courtDocumentCategory = 
				(CourtDocumentCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return courtDocumentCategory;
	}

	/** {@inheritDoc} */
	@Override
	public CourtDocumentCategory findExcluding(String name,
			CourtDocumentCategory excludedCourtDocumentCategory) {
		CourtDocumentCategory courtDocumentCategory = 
				(CourtDocumentCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_PARAM_NAME, 
						excludedCourtDocumentCategory)
				.uniqueResult();
		return courtDocumentCategory;
	}

	/** {@inheritDoc} */
	@Override
	public List<CourtDocumentCategory> findValid() {
		@SuppressWarnings("unchecked")
		List<CourtDocumentCategory> courtDocumentCategories = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_VALID_QUERY_NAME)
				.list();
		return courtDocumentCategories;
	}
}