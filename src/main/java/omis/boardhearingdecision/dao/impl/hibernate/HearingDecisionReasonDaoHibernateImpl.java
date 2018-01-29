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
package omis.boardhearingdecision.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.boardhearingdecision.dao.HearingDecisionReasonDao;
import omis.boardhearingdecision.domain.DecisionCategory;
import omis.boardhearingdecision.domain.HearingDecisionReason;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the hearing decision reason data access object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class HearingDecisionReasonDaoHibernateImpl 
		extends GenericHibernateDaoImpl<HearingDecisionReason>
		implements HearingDecisionReasonDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findHearingDecisionReason";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findHearingDecisionReasonExcluding";
	
	private static final String FIND_BY_DECISION_CATEGORY_QUERY_NAME = 
			"findHearingDecisionReasonByDecisionCategory";
	
	/* Parameters. */
	
	private static final String REASON_NAME_PARAM_NAME = "reasonName";
	
	private static final String DECISION_CATEGORY_PARAM_NAME = 
			"decisionCategory";
	
	private static final String EXCLUDED_HEARING_DECISION_NOTE_PARAM_NAME = 
			"excludedHearingDecisionReason";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  board hearing decision.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HearingDecisionReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public HearingDecisionReason find(final String reasonName) {
		HearingDecisionReason hearingDecisionReason = 
				(HearingDecisionReason) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(REASON_NAME_PARAM_NAME, reasonName)
				.uniqueResult();
		return hearingDecisionReason;
	}

	/**{@inheritDoc} */
	@Override
	public HearingDecisionReason findExcluding(final String reasonName, 
			final HearingDecisionReason excludedHearingDecisionReason) {
		HearingDecisionReason hearingDecisionReason = 
				(HearingDecisionReason) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(REASON_NAME_PARAM_NAME, reasonName)
				.setParameter(EXCLUDED_HEARING_DECISION_NOTE_PARAM_NAME, 
						excludedHearingDecisionReason)
				.uniqueResult();
		return hearingDecisionReason;
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingDecisionReason> findByDecisionCategory(
			final DecisionCategory decisionCategory) {
		@SuppressWarnings("unchecked")
		List<HearingDecisionReason> hearingDecisionReasons = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_DECISION_CATEGORY_QUERY_NAME)
				.setParameter(DECISION_CATEGORY_PARAM_NAME, decisionCategory)
				.list();
		return hearingDecisionReasons;
	}
}