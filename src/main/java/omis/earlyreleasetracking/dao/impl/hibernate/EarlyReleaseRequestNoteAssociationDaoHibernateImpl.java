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

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.earlyreleasetracking.dao.EarlyReleaseRequestNoteAssociationDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestNoteAssociation;

/**
 * Early Release Request Note Association Dao Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestNoteAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<EarlyReleaseRequestNoteAssociation>
		implements EarlyReleaseRequestNoteAssociationDao {
	
	private static final String FIND_QUERY_NAME =
			"findEarlyReleaseRequestNoteAssociation";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findEarlyReleaseRequestNoteAssociationExcluding";
	
	private static final String FIND_BY_EARLY_RELEASE_REQUEST =
			"findEarlyReleaseRequestNoteAssociationsByEarlyReleaseRequest";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String EARLY_RELEASE_REQUEST_PARAM_NAME =
			"earlyReleaseRequest";
	
	private static final String EARLY_RELEASE_REQUEST_NOTE_ASSOC_PARAM_NAME =
			"earlyReleaseRequestNoteAssociation";

	/**
	 * @param sessionFactory Session Factory
	 * @param entityName Entity Name
	 */
	public EarlyReleaseRequestNoteAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestNoteAssociation find(
			final EarlyReleaseRequest earlyReleaseRequest,
			final String description, final Date date) {
		EarlyReleaseRequestNoteAssociation noteAssociation =
				(EarlyReleaseRequestNoteAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.uniqueResult();
		
		return noteAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestNoteAssociation findExcluding(
			final EarlyReleaseRequest earlyReleaseRequest,
			final String description, final Date date,
			final EarlyReleaseRequestNoteAssociation
				earlyReleaseRequestNoteAssociationExcluding) {
		EarlyReleaseRequestNoteAssociation association =
				(EarlyReleaseRequestNoteAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.setParameter(EARLY_RELEASE_REQUEST_NOTE_ASSOC_PARAM_NAME,
						earlyReleaseRequestNoteAssociationExcluding)
				.uniqueResult();
		
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestNoteAssociation> findByEarlyReleaseRequest(
			final EarlyReleaseRequest earlyReleaseRequest) {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseRequestNoteAssociation> associations =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_EARLY_RELEASE_REQUEST)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.list();
		
		return associations;
	}
}
