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
package omis.unitmanagerreview.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.unitmanagerreview.dao.UnitManagerReviewDocumentAssociationDao;
import omis.unitmanagerreview.domain.UnitManagerReview;
import omis.unitmanagerreview.domain.UnitManagerReviewDocumentAssociation;

/**
 * Hibernate implementation of the unit manager review document association data 
 * access object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewDocumentAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<UnitManagerReviewDocumentAssociation>
		implements UnitManagerReviewDocumentAssociationDao {


	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findUnitManagerReviewDocumentAssociation";
	
	private static final String FIND_BY_UNIT_MANAGER_REVIEW_QUERY_NAME = 
			"findUnitManagerReviewDocumentAssociationsByUnitManagerReview";
	
	/* Parameters. */
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String UNIT_MANAGER_REVIEW_PARAM_NAME = 
			"unitManagerReview";
	
	
	/** Instantiates a hibernate implementation of the data access object for 
	 *  unit manager review document association.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UnitManagerReviewDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
		// TODO Auto-generated constructor stub
	}

	/** {@inheritDoc} */
	@Override
	public UnitManagerReviewDocumentAssociation find(final Document document, 
			final UnitManagerReview unitManagerReview) {
		UnitManagerReviewDocumentAssociation documentAssociation = 
				(UnitManagerReviewDocumentAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(UNIT_MANAGER_REVIEW_PARAM_NAME, unitManagerReview)
				.uniqueResult();
		return documentAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public List<UnitManagerReviewDocumentAssociation> findByUnitManagerReview(
			final UnitManagerReview unitManagerReview) {
		@SuppressWarnings("unchecked")
		List<UnitManagerReviewDocumentAssociation> documentAssociations = this
		.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_UNIT_MANAGER_REVIEW_QUERY_NAME)
				.setParameter(UNIT_MANAGER_REVIEW_PARAM_NAME, unitManagerReview)
				.list();
		return documentAssociations;
	}
}