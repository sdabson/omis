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
package omis.prisonterm.dao.impl.hibernate;

import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.prisonterm.dao.PrisonTermDocumentAssociationDao;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermDocumentAssociation;

/**
 * Prison Term Document Association DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2018)
 *@since OMIS 3.0
 *
 */
public class PrisonTermDocumentAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<PrisonTermDocumentAssociation>
		implements PrisonTermDocumentAssociationDao {
	
	private static final String FIND_QUERY_NAME =
			"findPrisonTermDocumentAssociation";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findExcludingPrisonTermDocumentAssociation";
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String PRISON_TERM_PARAM_NAME = "prisonTerm";
	
	private static final String PRISON_TERM_DOCUMENT_ASSOCIATION_PARAM_NAME =
			"prisonTermDocumentAssociation";
	
	/**
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PrisonTermDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public PrisonTermDocumentAssociation find(final Document document,
			final PrisonTerm prisonTerm) {
		PrisonTermDocumentAssociation documentAssociation =
				(PrisonTermDocumentAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(PRISON_TERM_PARAM_NAME, prisonTerm)
				.uniqueResult();
		
		return documentAssociation;
	}
	
	/** {@inheritDoc} */
	@Override
	public PrisonTermDocumentAssociation findExcluding(final Document document,
			final PrisonTerm prisonTerm,
			final PrisonTermDocumentAssociation
				prisonTermDocumentAssociationExcluding) {
		PrisonTermDocumentAssociation documentAssociation =
				(PrisonTermDocumentAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(PRISON_TERM_PARAM_NAME, prisonTerm)
				.setParameter(PRISON_TERM_DOCUMENT_ASSOCIATION_PARAM_NAME,
						prisonTermDocumentAssociationExcluding)
				.uniqueResult();
		
		return documentAssociation;
	}
}
