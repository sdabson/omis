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
package omis.earlyreleasetracking.dao.impl.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.earlyreleasetracking.dao.EarlyReleaseRequestDocumentAssociationDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestDocumentAssociation;

/**
 * Early Release Request Document Association Dao Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestDocumentAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<EarlyReleaseRequestDocumentAssociation>
		implements EarlyReleaseRequestDocumentAssociationDao {
	
	private static final String FIND_QUERY_NAME =
			"findEarlyReleaseRequestDocumentAssociation";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findEarlyReleaseRequestDocumentAssociationExcluding";
	
	private static final String FIND_BY_EARLY_RELEASE_REQUEST =
			"findEarlyReleaseRequestDocumentAssociationsByEarlyReleaseRequest";
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String EARLY_RELEASE_REQUEST_PARAM_NAME =
			"earlyReleaseRequest";
	
	private static final String EARLY_RELEASE_REQUEST_DOC_ASSOC_PARAM_NAME =
			"earlyReleaseRequestDocumentAssociation";

	/**
	 * @param sessionFactory Session Factory
	 * @param entityName Entity Name
	 */
	public EarlyReleaseRequestDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestDocumentAssociation find(
			final EarlyReleaseRequest earlyReleaseRequest,
			final Document document) {
		EarlyReleaseRequestDocumentAssociation noteAssociation =
				(EarlyReleaseRequestDocumentAssociation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.uniqueResult();
		
		return noteAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestDocumentAssociation findExcluding(
			final EarlyReleaseRequest earlyReleaseRequest,
			final Document document,
			final EarlyReleaseRequestDocumentAssociation
				earlyReleaseRequestDocumentAssociationExcluding) {
		EarlyReleaseRequestDocumentAssociation association =
				(EarlyReleaseRequestDocumentAssociation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.setParameter(EARLY_RELEASE_REQUEST_DOC_ASSOC_PARAM_NAME,
						earlyReleaseRequestDocumentAssociationExcluding)
				.uniqueResult();
		
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestDocumentAssociation>
			findByEarlyReleaseRequest(final EarlyReleaseRequest
					earlyReleaseRequest) {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseRequestDocumentAssociation> associations =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_EARLY_RELEASE_REQUEST)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.list();
		
		return associations;
	}
}
