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
package omis.sentence.web.form.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.conviction.domain.component.ConvictionCredit;
import omis.instance.factory.InstanceFactory;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;
import omis.sentence.domain.TermRequirement;
import omis.sentence.web.form.SentenceFields;
import omis.term.domain.component.Term;
import omis.term.service.delegate.TermCalculatorDelegate;
import omis.testng.AbstractNonTransactionalTestNGSpringContextTests;
import omis.util.DateUtility;
import omis.util.PropertyValueAsserter;

/**
 * Tests for sentence fields.
 * 
 * @author Stephen Abson
 * @since OMIS 3.0
 * @version 0.0.1 (Feb 8, 2019)
 */
@Test(groups = {"web", "sentence"})
public class SentenceFieldsTests
		extends AbstractNonTransactionalTestNGSpringContextTests {
	
	/* Instance factories. */
	
	@Autowired
	@Qualifier("sentenceInstanceFactory")
	private InstanceFactory<Sentence> sentenceInstanceFactory;
	
	@Autowired
	@Qualifier("sentenceCategoryInstanceFactory")
	private InstanceFactory<SentenceCategory> sentenceCategoryInstanceFactory;
	
	@Autowired
	@Qualifier("legalDispositionCategoryInstanceFactory")
	private InstanceFactory<LegalDispositionCategory>
	legalDispositionCategoryInstanceFactory;
	
	/* Delegate to calculate terms. */
	
	@Autowired
	@Qualifier("termCalculatorDelegate")
	private TermCalculatorDelegate termCalculatorDelegate;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Test cases. */
	
	/**
	 * Tests instantiation of sentence fields from sentence. 
	 */
	public void testInstantiationFromSentence() {
		
		// Arranges sentence with category
		SentenceCategory category = this.sentenceCategoryInstanceFactory
				.createInstance();
		category.setName("Deferred Term");
		category.setDeferredRequirement(TermRequirement.REQUIRED);
		category.setPrisonRequirement(TermRequirement.REQUIRED);
		category.setPrisonRequirement(TermRequirement.REQUIRED);
		LegalDispositionCategory legalDispositionCategory
			= this.legalDispositionCategoryInstanceFactory
				.createInstance();
		legalDispositionCategory.setName("Amendment");
		legalDispositionCategory.setValid(true);
		Sentence sentence = this.sentenceInstanceFactory.createInstance();
		sentence.setCategory(category);
		sentence.setLegalDispositionCategory(legalDispositionCategory);
		sentence.setLengthClassification(SentenceLengthClassification.NOT_LIFE);
		sentence.setPrisonTerm(new Term(1, 2, 3));
		sentence.setProbationTerm(new Term(4, 5, 6));
		sentence.setDeferredTerm(new Term(7, 8, 9));
		sentence.setEffectiveDate(
				this.dateUtility.parseDateText("12/02/2015"));
		sentence.setPronouncementDate(
				this.dateUtility.parseDateText("12/03/2012"));
		sentence.setCredit(new ConvictionCredit(1, 2));
		sentence.setTurnSelfInDate(
				this.dateUtility.parseDateText("12/01/2015"));
		
		// Action - instantiates sentence fields from sentence
		SentenceFields sentenceFields = new SentenceFields(
				sentence, this.termCalculatorDelegate::calculateTotalDays);
		
		// Asserts sentence fields properties
		PropertyValueAsserter.create()
			.addExpectedValue("category",
					sentence.getCategory())
			.addExpectedValue("legalDispositionCategory",
					sentence.getLegalDispositionCategory())
			.addExpectedValue("lengthClassification",
					sentence.getLengthClassification())
			.addExpectedValue("prisonYears",
					sentence.getPrisonTerm().getYears())
			.addExpectedValue("prisonMonths",
					sentence.getPrisonTerm().getMonths())
			.addExpectedValue("prisonDays",
					sentence.getPrisonTerm().getDays())
			.addExpectedValue("prisonTotalDays",
					this.termCalculatorDelegate.calculateTotalDays(
							sentence.getPrisonTerm()))
			.addExpectedValue("probationYears",
					sentence.getProbationTerm().getYears())
			.addExpectedValue("probationMonths",
					sentence.getProbationTerm().getMonths())
			.addExpectedValue("probationDays",
					sentence.getProbationTerm().getDays())
			.addExpectedValue("probationTotalDays",
					this.termCalculatorDelegate.calculateTotalDays(
							sentence.getProbationTerm()))
			.addExpectedValue("deferredYears",
					sentence.getDeferredTerm().getYears())
			.addExpectedValue("deferredMonths",
					sentence.getDeferredTerm().getMonths())
			.addExpectedValue("deferredDays",
					sentence.getDeferredTerm().getDays())
			.addExpectedValue("deferredTotalDays",
					this.termCalculatorDelegate.calculateTotalDays(
							sentence.getDeferredTerm()))
			.addExpectedValue("effectiveDate",
					sentence.getEffectiveDate())
			.addExpectedValue("pronouncementDate",
					sentence.getPronouncementDate())
			.addExpectedValue("jailTimeCredit",
					sentence.getCredit().getJailTime())
			.addExpectedValue("streetTimeCredit",
					sentence.getCredit().getStreetTime())
			.addExpectedValue("turnSelfInDate",
					sentence.getTurnSelfInDate())
			.performAssertions(sentenceFields);
	}
}