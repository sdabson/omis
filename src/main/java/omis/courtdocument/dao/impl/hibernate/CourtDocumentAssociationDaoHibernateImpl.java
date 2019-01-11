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

import java.util.List;

import org.hibernate.SessionFactory;

import omis.courtdocument.dao.CourtDocumentAssociationDao;
import omis.courtdocument.domain.CourtDocumentAssociation;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.offender.domain.Offender;

/** 
 * Hibernate implementation of court document association data access object.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 6, 2018)
 * @since OMIS 3.0
 */
public class CourtDocumentAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<CourtDocumentAssociation>
		implements CourtDocumentAssociationDao {
	
	/* Queries */
	
	private static final String FIND_QUERY_NAME =
			"findCourtDocumentAssociations";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findCourtDocumentAssociationsExcluding";
	
	private static final String
		FIND_COUNT_BY_OFFENDER 
			= "findCourtDocumentAssociationByOffender";
	
	/* Parameters */
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String EXCLUDING_PARAM_NAME = "excluding";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	/** Constructor. 
	 * @param sessionFactory - session factory.
	 * @param entityName - entity name. */
	public CourtDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtDocumentAssociation> findByDocument(
			final Document document) {
		@SuppressWarnings("unchecked")
		List<CourtDocumentAssociation> associations = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.list();
		return associations;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtDocumentAssociation> findByDocumentExcluding(
			final Document document,  
			final CourtDocumentAssociation...excluding) {
		@SuppressWarnings("unchecked")
		List<CourtDocumentAssociation> associations = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameterList(EXCLUDING_PARAM_NAME, excluding)
				.list();
		return associations;
	}
	
	/** {@inhertiDoc} */
	@Override
	public Integer findCountByOffender(final Offender offender) {
		Integer count = ((Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_COUNT_BY_OFFENDER)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult()).intValue();
		return count;
	}
}