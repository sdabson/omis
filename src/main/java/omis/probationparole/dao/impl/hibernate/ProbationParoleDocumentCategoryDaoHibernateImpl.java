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
package omis.probationparole.dao.impl.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.probationparole.dao.ProbationParoleDocumentCategoryDao;
import omis.probationparole.domain.ProbationParoleDocumentCategory;

/**
 * Probation Parole Document Category DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 6, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<ProbationParoleDocumentCategory>
		implements ProbationParoleDocumentCategoryDao {
	
	private static final String
		FIND_PROBATION_PAROLE_DOCUMENT_CATEGORIES_QUERY_NAME =
			"findProbationParoleDocumentCategories";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - Entity Name
	 */
	public ProbationParoleDocumentCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	/** {@inheritDoc} */
	@Override
	public List<ProbationParoleDocumentCategory> findCategories() {
		@SuppressWarnings("unchecked")
		List<ProbationParoleDocumentCategory> categories =
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_PROBATION_PAROLE_DOCUMENT_CATEGORIES_QUERY_NAME)
			.list();
		
		return categories;
	}

}
