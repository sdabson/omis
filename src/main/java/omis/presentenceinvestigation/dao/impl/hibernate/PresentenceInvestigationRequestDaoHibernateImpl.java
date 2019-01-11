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

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;
import omis.presentenceinvestigation.dao.PresentenceInvestigationRequestDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Presentence investigation request data access object hibernate 
 * implementation.
 *  
 * @author Joel Norris
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.2 (Oct 25, 2017)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationRequestDaoHibernateImpl 
extends GenericHibernateDaoImpl<PresentenceInvestigationRequest>
implements PresentenceInvestigationRequestDao {

	/* Query names. */
	
	private static final String
			FIND_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME = 
			"findPresentenceInvestigationRequest";
	
	private static final String
			FIND_PRESENTENCE_INVESTIGATION_REQUEST_EXCLUDING_QUERY_NAME = 
			"findPresentenceInvestigationRequestExcluding";
	
	/* Parameter names. */
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String REQUEST_DATE_PARAM_NAME = "requestDate";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME = 
			"presentenceInvestigationRequest";
	
	/* Constructor. */
	
	public PresentenceInvestigationRequestDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequest find(final Person person,
			final Date requestDate) {
		PresentenceInvestigationRequest presentenceInvestigationRequest =
				(PresentenceInvestigationRequest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(REQUEST_DATE_PARAM_NAME, requestDate)
				.uniqueResult();
		return presentenceInvestigationRequest;
	}

	/** {@inheritDoc} */
	@Override
	public PresentenceInvestigationRequest findExcluding(
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final Person person, final Date requestDate) {
		PresentenceInvestigationRequest request =
				(PresentenceInvestigationRequest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_PRESENTENCE_INVESTIGATION_REQUEST_EXCLUDING_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(REQUEST_DATE_PARAM_NAME, requestDate)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		return request;
	}
}