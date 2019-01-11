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

import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.probationparole.dao.ProbationParoleDocumentAssociationDao;
import omis.probationparole.domain.ProbationParoleDocumentAssociation;

/**
 * Probation Parole Document Association DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 6, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<ProbationParoleDocumentAssociation>
		implements ProbationParoleDocumentAssociationDao {
	
	private static final String
		FIND_PROBATION_PAROLE_DOCUMENT_ASSOCIATION_QUERY_NAME =
			"findProbationParoleDocumentAssociation";
	
	private static final String
		FIND_EXCLUDING_PROBATION_PAROLE_DOCUMENT_ASSOCIATION_QUERY_NAME =
			"findProbationParoleDocumentAssociationExcluding";
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String
		PROBATION_PAROLE_DOCUMENT_ASSOCIATION_PARAM_NAME =
			"probationParoleDocumentAssociation";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - Entity Name
	 */
	public ProbationParoleDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public ProbationParoleDocumentAssociation find(final Document document) {
		ProbationParoleDocumentAssociation association =
				(ProbationParoleDocumentAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_PROBATION_PAROLE_DOCUMENT_ASSOCIATION_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
		
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public ProbationParoleDocumentAssociation findExcluding(
			final Document document,
			final ProbationParoleDocumentAssociation
				probationParoleDocumentAssociationExcluding) {
		ProbationParoleDocumentAssociation association =
				(ProbationParoleDocumentAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_EXCLUDING_PROBATION_PAROLE_DOCUMENT_ASSOCIATION_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(PROBATION_PAROLE_DOCUMENT_ASSOCIATION_PARAM_NAME,
						probationParoleDocumentAssociationExcluding)
				.uniqueResult();
		
		return association;
	}

}
