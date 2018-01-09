package omis.courtcasecondition.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.condition.domain.Agreement;
import omis.condition.domain.Condition;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionTitle;
import omis.condition.service.delegate.AgreementDelegate;
import omis.condition.service.delegate.ConditionClauseDelegate;
import omis.condition.service.delegate.ConditionTitleDelegate;
import omis.courtcasecondition.service.CourtCaseAgreementConditionService;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Court Case Condition Update Tests.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 9, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseConditionUpdateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("courtCaseAgreementConditionService")
	private CourtCaseAgreementConditionService
		courtCaseAgreementConditionService;
	
	@Autowired
	private ConditionClauseDelegate conditionClauseDelegate;
	
	@Autowired
	private ConditionTitleDelegate conditionTitleDelegate;
	
	@Autowired
	private OffenderDelegate offenderDelegate;

	@Autowired
	private AgreementDelegate agreementDelegate;
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is found
	 */
	@Test
	public void testConditionUpdate() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.agreementDelegate
				.create(offender, this.parseDateText("05/12/2017"), 
						this.parseDateText("05/15/2019"), null, null);
		final ConditionTitle conditionTitle = this.conditionTitleDelegate
				.create("Condition Title");
		final ConditionClause conditionClauseOld = this.conditionClauseDelegate
				.create("Old Condition Clause Description",
						conditionTitle, true);
		final ConditionClause conditionClause = this.conditionClauseDelegate
				.create("Condition Clause Description", conditionTitle, true);
		final String clause = "Santa Clause";
		final ConditionCategory category = ConditionCategory.STANDARD;
		final Boolean waived = false;
		
		Condition condition = this.courtCaseAgreementConditionService
				.createCondition(agreement, "Old Clause", conditionClauseOld,
						ConditionCategory.SPECIAL,
						true);
		
		condition = this.courtCaseAgreementConditionService
				.updateCondition(condition, conditionClause, clause,
						category, waived);
		
		assert agreement.equals(condition.getAgreement())
		: String.format("Wrong agreement for condition: %d found; %d expected",
				condition.getAgreement().getId(), agreement.getId());
		assert clause.equals(condition.getClause())
		: String.format("Wrong clause for condition: %s found; %s expected",
				condition.getClause(), clause);
		assert conditionClause.equals(condition.getConditionClause())
		: String.format("Wrong conditionClause for condition: %s found;"
				+ " %s expected",
				condition.getConditionClause().getDescription(),
				conditionClause.getDescription());
		assert category.equals(condition.getCategory())
		: String.format("Wrong category for condition: %s found; %s expected",
				condition.getCategory(), category);
		assert waived.equals(condition.getWaived())
		: String.format("Wrong waived for condition: %s found; %s expected",
				condition.getWaived(), waived);
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
}
