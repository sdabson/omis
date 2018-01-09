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
package omis.paroleboardcondition.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementAssociableDocument;
import omis.condition.domain.AgreementCategory;
import omis.condition.domain.AgreementNote;
import omis.condition.domain.Condition;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionTitle;
import omis.condition.service.delegate.AgreementDelegate;
import omis.condition.service.delegate.ConditionClauseDelegate;
import omis.condition.service.delegate.ConditionTitleDelegate;
import omis.document.domain.Document;
import omis.document.service.delegate.DocumentDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.paroleboardcondition.domain.ParoleBoardAgreement;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;
import omis.paroleboardcondition.service.ParoleBoardConditionService;
import omis.paroleboardcondition.service.delegate.ParoleBoardAgreementCategoryDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Parole Board Condition Create Tests.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 26, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardConditionCreateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("paroleBoardConditionService")
	private ParoleBoardConditionService paroleBoardConditionService;
	
	@Autowired
	private ParoleBoardAgreementCategoryDelegate
		paroleBoardAgreementCategoryDelegate;
	
	@Autowired
	private ConditionClauseDelegate conditionClauseDelegate;
	
	@Autowired
	private ConditionTitleDelegate conditionTitleDelegate;
	
	@Autowired
	private OffenderDelegate offenderDelegate;

	@Autowired
	private AgreementDelegate agreementDelegate;
	
	@Autowired
	private DocumentDelegate documentDelegate;
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity exists.
	 */
	@Test
	public void testParoleBoardAgreementCreate()
		throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.agreementDelegate.create(offender,
						this.parseDateText("05/12/2017"),
						this.parseDateText("05/15/2019"), null, null);
		final ParoleBoardAgreementCategory category =
				this.paroleBoardAgreementCategoryDelegate
				.create("Parole Category");
		
		final ParoleBoardAgreement paroleBoardAgreement =
				this.paroleBoardConditionService.createParoleBoardAgreement(
						agreement, category);
		
		
		PropertyValueAsserter.create()
					.addExpectedValue("agreement", agreement)
					.addExpectedValue("category", category)
					.performAssertions(paroleBoardAgreement);
		
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity exists.
	 */
	@Test
	public void testAgreementCreate() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Date startDate = this.parseDateText("05/12/2017");
		final Date endDate = this.parseDateText("05/15/2019");
		final String description = "Agreement Description";
		final AgreementCategory agreementCategory =
				AgreementCategory.BOARD_PARDONS_PAROLE;
		
		final Agreement agreement = this.paroleBoardConditionService
				.createAgreement(offender, startDate, endDate, description,
						agreementCategory);
		
		PropertyValueAsserter.create()
				.addExpectedValue("offender", offender)
				.addExpectedValue("dateRange.startDate", startDate)
				.addExpectedValue("dateRange.endDate", endDate)
				.addExpectedValue("description", description)
				.addExpectedValue("category", agreementCategory)
				.performAssertions(agreement);
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity exists.
	 */
	@Test
	public void testAgreementNoteCreate()
			throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.agreementDelegate.create(offender,
						this.parseDateText("05/12/2017"),
						this.parseDateText("05/15/2019"), null, null);
		final String description = "Agreement Note Description";
		final Date date = this.parseDateText("06/10/2017");
		
		final AgreementNote agreementNote = this.paroleBoardConditionService
				.createAgreementNote(date, agreement, description);
		
		PropertyValueAsserter.create()
				.addExpectedValue("agreement", agreement)
				.addExpectedValue("description", description)
				.addExpectedValue("date", date)
				.performAssertions(agreementNote);
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity exists.
	 */
	@Test
	public void testAgreementAssociableDocumentCreate()
			throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.agreementDelegate.create(offender,
						this.parseDateText("05/12/2017"),
						this.parseDateText("05/15/2019"), null, null);
		final Document document = this.documentDelegate.create(
				this.parseDateText("05/12/2017"), "documentFileName",
				".doc", "Document Title");
		
		final AgreementAssociableDocument agreementAssociableDocument =
				this.paroleBoardConditionService
				.createAgreementAssociableDocument(agreement, document);
		
		PropertyValueAsserter.create()
				.addExpectedValue("agreement", agreement)
				.addExpectedValue("document", document)
				.performAssertions(agreementAssociableDocument);
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity exists.
	 */
	@Test
	public void testConditionCreate() throws DuplicateEntityFoundException {
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
		
		final Condition condition = this.paroleBoardConditionService
				.createCondition(agreement, clause, conditionClause,
						category, waived);
		
		PropertyValueAsserter.create()
				.addExpectedValue("agreement", agreement)
				.addExpectedValue("clause", clause)
				.addExpectedValue("conditionClause", conditionClause)
				.addExpectedValue("category", category)
				.addExpectedValue("waived", waived)
				.performAssertions(condition);
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}
