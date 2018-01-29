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
package omis.hearingparticipant.dao.impl.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearingparticipant.dao.HearingParticipantIntentCategoryDao;
import omis.hearingparticipant.domain.HearingParticipantIntentCategory;

/**
 * Hearing Participant Intent Category DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 16, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantIntentCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<HearingParticipantIntentCategory>
		implements HearingParticipantIntentCategoryDao {
	
	private static final String FIND_ALL_QUERY_NAME =
			"findAllHearingParticipantIntentCategories";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String entity name
	 */
	protected HearingParticipantIntentCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingParticipantIntentCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<HearingParticipantIntentCategory> categories =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		
		return categories;
	}
}
