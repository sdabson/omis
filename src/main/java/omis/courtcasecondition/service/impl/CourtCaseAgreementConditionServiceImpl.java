package omis.courtcasecondition.service.impl;

import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.Condition;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionGroup;
import omis.condition.domain.ConditionTitle;
import omis.condition.service.delegate.ConditionClauseDelegate;
import omis.condition.service.delegate.ConditionDelegate;
import omis.condition.service.delegate.ConditionGroupDelegate;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.courtcasecondition.service.CourtCaseAgreementConditionService;
import omis.exception.DuplicateEntityFoundException;
/*
 *  OMIS - Offender Management Information System
 *  Copyright (C) 2011 - 2017 State of Montana
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Court case agreement condition service implementation.
 * 
 * @author Joel Norris
 * @author Annie Jacques
 * @version 0.1.0 (Oct 6, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseAgreementConditionServiceImpl
	implements CourtCaseAgreementConditionService {
	
	/* Delegates. */
	
	private final ConditionDelegate conditionDelegate;
	private final ConditionClauseDelegate conditionClauseDelegate;
	private final ConditionGroupDelegate conditionGroupDelegate;
	
	/* Constructor. */
	
	public CourtCaseAgreementConditionServiceImpl(final ConditionDelegate conditionDelegate,
			final ConditionClauseDelegate conditionClauseDelegate,
			final ConditionGroupDelegate conditionGroupDelegate) {
		this.conditionDelegate = conditionDelegate;
		this.conditionClauseDelegate = conditionClauseDelegate;
		this.conditionGroupDelegate = conditionGroupDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Condition createCondition(final Agreement agreement, final String clause,
			final ConditionClause conditionClause, final ConditionCategory category,
			final Boolean waived) throws DuplicateEntityFoundException {
		return this.conditionDelegate.create(agreement, clause, conditionClause,
				category, waived);
	}

	/** {@inheritDoc} */
	@Override
	public Condition updateCondition(final Condition condition,
			final ConditionClause conditionClause, final String clause,
			final ConditionCategory category, final Boolean waived)
					throws DuplicateEntityFoundException {
		return this.conditionDelegate.update(condition, conditionClause,
				clause, category, waived);
	}

	/** {@inheritDoc} */
	@Override
	public void removeCondition(final Condition condition) {
		this.conditionDelegate.remove(condition);
		
	}

	/** {@inheritDoc} */
	@Override
	public List<ConditionClause> findConditionClausesByConditionGroup(
			final ConditionGroup conditionGroup) {
		return this.conditionClauseDelegate.findConditionClausesByConditionGroup(
				conditionGroup);
	}

	/** {@inheritDoc} */
	@Override
	public List<ConditionClause> findConditionClausesByCourtCaseAgreementCategory(
			final CourtCaseAgreementCategory courtCaseAgreementCategory) {
		return this.conditionClauseDelegate.findByCourtCaseAgreementCategory(
				courtCaseAgreementCategory);
	}

	/** {@inheritDoc} */
	@Override
	public List<ConditionClause>
		findConditionClausesByCourtCaseAgreementCategoryExcludingAgreement(
			final CourtCaseAgreementCategory courtCaseAgreementCategory,
			final CourtCaseAgreement courtCaseAgreement) {
		return this.conditionClauseDelegate
				.findByCourtCaseAgreementCategoryExcludingAgreement(
						courtCaseAgreementCategory, courtCaseAgreement);
	}

	/** {@inheritDoc} */
	@Override
	public List<ConditionGroup> findAllConditionGroups() {
		return this.conditionGroupDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Condition> findWaivedConditionsByAgreement(final Agreement agreement) {
		return this.conditionDelegate.findWaivedByAgreement(agreement);
	}

	/** {@inheritDoc} */
	@Override
	public List<Condition> findConditionsByAgreement(final Agreement agreement) {
		return this.conditionDelegate.findByAgreement(agreement);
	}

	/** {@inheritDoc} */
	@Override
	public ConditionClause findConditionClauseByConditionTitle(
			final ConditionTitle conditionTitle) {
		return this.conditionClauseDelegate
				.findByConditionTitle(conditionTitle);
	}

	/**{@inheritDoc} */
	@Override
	public List<ConditionGroup> findUnusedConditionGroupsByAgreement(
			final Agreement agreement) {
		return this.conditionGroupDelegate.findUnusedByAgreement(agreement);
	}

	/**{@inheritDoc} */
	@Override
	public List<ConditionGroup> findUsedConditionGroupsByAgreement(
			final Agreement agreement) {
		return this.conditionGroupDelegate.findUsedByAgreement(agreement);
	}
}
