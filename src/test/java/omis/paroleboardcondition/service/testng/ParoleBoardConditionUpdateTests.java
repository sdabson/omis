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
import omis.condition.service.delegate.AgreementAssociableDocumentDelegate;
import omis.condition.service.delegate.AgreementDelegate;
import omis.condition.service.delegate.AgreementNoteDelegate;
import omis.condition.service.delegate.ConditionClauseDelegate;
import omis.condition.service.delegate.ConditionDelegate;
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
import omis.paroleboardcondition.service.delegate.ParoleBoardAgreementDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Parole Board Condition Update Tests.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 26, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardConditionUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("paroleBoardConditionService")
	private ParoleBoardConditionService paroleBoardConditionService;
	
	@Autowired
	private ParoleBoardAgreementDelegate paroleBoardAgreementDelegate;
	
	@Autowired
	private ParoleBoardAgreementCategoryDelegate
		paroleBoardAgreementCategoryDelegate;
	
	@Autowired
	private ConditionDelegate conditionDelegate;
	
	@Autowired
	private AgreementNoteDelegate agreementNoteDelegate;
	
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
	
	@Autowired
	private AgreementAssociableDocumentDelegate
		agreementAssociableDocumentDelegate;
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity exists.
	 */
	@Test
	public void testParoleBoardAgreementUpdate()
			throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.agreementDelegate.create(offender,
				this.parseDateText("07/16/2017"),
				this.parseDateText("09/20/2019"), null, null);
		final ParoleBoardAgreementCategory category =
				this.paroleBoardAgreementCategoryDelegate
				.create("Old Category");
		ParoleBoardAgreement paroleBoardAgreement =
				this.paroleBoardAgreementDelegate.create(
						agreement, category);
		final Agreement newAgreement = this.agreementDelegate.create(offender,
				this.parseDateText("01/11/2011"),
				this.parseDateText("02/22/2020"), "description", null);
		final ParoleBoardAgreementCategory newCategory =
				this.paroleBoardAgreementCategoryDelegate
				.create("Different Category");
		paroleBoardAgreement = this.paroleBoardConditionService
				.updateParoleBoardAgreement(paroleBoardAgreement,
						newAgreement, newCategory);
		
		PropertyValueAsserter.create()
					.addExpectedValue("agreement", newAgreement)
					.addExpectedValue("category", newCategory)
					.performAssertions(paroleBoardAgreement);
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity exists.
	 */
	@Test
	public void testAgreementUpdate() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		Agreement agreement = this.agreementDelegate.create(
				offender, this.parseDateText("06/16/2017"),
				this.parseDateText("09/20/2019"), "Say what, now.", null);
		final Date startDate = this.parseDateText("05/12/2017");
		final Date endDate = this.parseDateText("05/15/2019");
		final String description = "Agreement Description";
		final AgreementCategory agreementCategory =
				AgreementCategory.BOARD_PARDONS_PAROLE;
		
		agreement = this.paroleBoardConditionService
				.updateAgreement(agreement, startDate, endDate, description,
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
	public void testAgreementNoteUpdate()
			throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.agreementDelegate.create(offender,
						this.parseDateText("05/12/2017"),
						this.parseDateText("05/15/2019"), null, null);
		AgreementNote agreementNote = this.agreementNoteDelegate.create(
				this.parseDateText("01/01/2001"), agreement,
				"old descriptiopn");
		final String description = "Agreement Note Description";
		final Date date = this.parseDateText("06/10/2017");
		
		agreementNote = this.paroleBoardConditionService
				.updateAgreementNote(agreementNote, description, date);
		
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
	public void testAgreementAssociableDocumentUpdate()
			throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.agreementDelegate.create(offender,
						this.parseDateText("05/12/2017"),
						this.parseDateText("05/15/2019"), null, null);
		final Document document = this.documentDelegate.create(
				this.parseDateText("05/12/2017"), "documentFileName",
				".doc", "Document Title");
		final Agreement newAgreement = this.agreementDelegate.create(offender,
				this.parseDateText("06/12/2019"),
				this.parseDateText("00/19/2021"), null, null);
		final Document newDocument = this.documentDelegate.create(
				this.parseDateText("01/01/2019"), "newDocumentFileName",
				".jpg", "New Document Title");
		AgreementAssociableDocument agreementAssociableDocument =
				this.agreementAssociableDocumentDelegate
				.create(agreement, document);
		
		
		agreementAssociableDocument =
				this.paroleBoardConditionService
				.updateAgreementAssociableDocument(agreementAssociableDocument,
						newAgreement, newDocument);
		
		PropertyValueAsserter.create()
				.addExpectedValue("agreement", newAgreement)
				.addExpectedValue("document", newDocument)
				.performAssertions(agreementAssociableDocument);
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity exists.
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
		Condition condition = this.conditionDelegate
				.create(agreement, "Old Clause", conditionClauseOld,
						ConditionCategory.SPECIAL,
						true);
		condition = this.paroleBoardConditionService.updateCondition(
				condition, conditionClause, clause, category, waived);
		
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
