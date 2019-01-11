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
package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.domain.Docket;
import omis.presentenceinvestigation.dao.PresentenceInvestigationDocketAssociationDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDocketAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Presentence investigation docket association data access object hibernate 
 * implementation.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Oct 25, 2018)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationDocketAssociationDaoHibernateImpl extends
		GenericHibernateDaoImpl<PresentenceInvestigationDocketAssociation>
		implements PresentenceInvestigationDocketAssociationDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = 
			"findPresentenceInvestigationDocketAssociation";
	
	private static final String 
			FIND_BY_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME = 
			"findPresentenceInvestigationDocketAssociationsByPresentenceInvestigationRequest";
	
	/* Parameter names. */
	
	private static final String DOCKET_PARAM_NAME = "docket";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME = 
			"presentenceInvestigationRequest";
	
	/**
	 * Constructor.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PresentenceInvestigationDocketAssociationDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public PresentenceInvestigationDocketAssociation find(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			Docket docket) {
		PresentenceInvestigationDocketAssociation association = 
				(PresentenceInvestigationDocketAssociation) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, 
						presentenceInvestigationRequest)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.uniqueResult();
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public List<PresentenceInvestigationDocketAssociation> findByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest) {
		@SuppressWarnings("unchecked")
		List<PresentenceInvestigationDocketAssociation> associations = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, 
						presentenceInvestigationRequest)
				.list();
		return associations;
	}
}