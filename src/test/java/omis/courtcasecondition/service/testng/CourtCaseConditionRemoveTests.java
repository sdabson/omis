
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
 * Court Case Condition Remove Tests.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 9, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseConditionRemoveTests
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
	private AgreementDelegate agreementDelegate;
	
	@Autowired
	private OffenderDelegate offenderDelegate;
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is found
	 */
	@Test
	public void testConditionRemove() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.agreementDelegate
				.create(offender, this.parseDateText("05/12/2017"), 
						this.parseDateText("05/15/2019"), null, null);
		final ConditionTitle conditionTitle = this.conditionTitleDelegate
				.create("Condition Title");
		final ConditionClause conditionClause = this.conditionClauseDelegate
				.create("Condition Clause Description", conditionTitle, true);
		final String clause = "Santa Clause";
		final ConditionCategory category = ConditionCategory.STANDARD;
		final Boolean waived = false;
		
		final Condition condition = this.courtCaseAgreementConditionService
				.createCondition(agreement, clause, conditionClause, category,
						waived);
		
		this.courtCaseAgreementConditionService.removeCondition(condition);
		
		assert !this.courtCaseAgreementConditionService
			.findConditionsByAgreement(agreement).contains(condition)
			: "Condition Was Not Removed!";
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}
