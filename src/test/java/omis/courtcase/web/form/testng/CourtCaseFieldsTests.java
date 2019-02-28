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
package omis.courtcase.web.form.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.country.domain.Country;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.courtcase.web.form.CourtCaseFields;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.person.domain.PersonName;
import omis.region.domain.State;
import omis.testng.AbstractNonTransactionalTestNGSpringContextTests;
import omis.util.DateUtility;
import omis.util.PropertyValueAsserter;

/**
 * Tests court case fields.
 * 
 * @author Stephen Abson
 * @since OMIS 3.0
 * @version 0.1.0 (22 Feb 2019)
 */
@Test(groups = {"web", "courtCase"})
public class CourtCaseFieldsTests
		extends AbstractNonTransactionalTestNGSpringContextTests {
	
	/* Instance factories. */
	
	@Autowired
	@Qualifier("courtCaseInstanceFactory")
	private InstanceFactory<CourtCase> courtCaseInstanceFactory;
	
	@Autowired
	@Qualifier("stateInstanceFactory")
	private InstanceFactory<State> stateInstanceFactory;
	
	@Autowired
	@Qualifier("countryInstanceFactory")
	private InstanceFactory<Country> countryInstanceFactory;

	@Autowired
	@Qualifier("personInstanceFactory")
	private InstanceFactory<Person> personInstanceFactory;
	
	@Autowired
	@Qualifier("personNameInstanceFactory")
	private InstanceFactory<PersonName> personNameInstanceFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/** Tests instantiation of court case fields from court case. */
	public void testInstantiationFromCourtCase() {
		
		// Arranges court case
		CourtCase courtCase = this.courtCaseInstanceFactory.createInstance();
		Country country = this.countryInstanceFactory.createInstance();
		country.setAbbreviation("US");
		country.setName("United States");
		country.setValid(true);
		State state = this.stateInstanceFactory.createInstance();
		state.setAbbreviation("MT");
		state.setName("Montana");
		state.setCountry(country);
		state.setValid(true);
		state.setHome(true);
		courtCase.setInterState(state);
		courtCase.setInterStateNumber("123654");
		courtCase.setPronouncementDate(
				this.dateUtility.parseDateText("10/10/2010"));
		courtCase.setJurisdictionAuthority(JurisdictionAuthority.FEDERAL);
		courtCase.setComments("A court case...");
		PersonName judgeName = this.personNameInstanceFactory.createInstance();
		judgeName.setFirstName("Julius");
		judgeName.setLastName("Blofeld");
		Person judge = this.personInstanceFactory.createInstance();
		judgeName.setPerson(judge);
		judge.setName(judgeName);
		courtCase.setPersonnel(new CourtCasePersonnel(
				judge, "No, Auric", "Scaramanga, Felix"));
		courtCase.setSentenceReviewDate(
				this.dateUtility.parseDateText("11/11/2011"));
		courtCase.setDangerDesignator(OffenderDangerDesignator.DANGEROUS);
		courtCase.setFlags(new CourtCaseFlags(true, false, true, false));
		
		// Action - creates fields from court case
		CourtCaseFields fields = new CourtCaseFields(courtCase);
		
		// Asserts properties are correctly set
		PropertyValueAsserter.create()
			.addExpectedValue("interState", courtCase.getInterState())
			.addExpectedValue("interStateNumber",
					courtCase.getInterStateNumber())
			.addExpectedValue("pronouncementDate",
					courtCase.getPronouncementDate())
			.addExpectedValue("jurisdictionAuthority",
					courtCase.getJurisdictionAuthority())
			.addExpectedValue("comments", courtCase.getComments())
			.addExpectedValue("judge",
					courtCase.getPersonnel().getJudge())
			.addExpectedValue("defenseAttorneyName",
					courtCase.getPersonnel().getDefenseAttorneyName())
			.addExpectedValue("prosecutingAttorneyName",
					courtCase.getPersonnel().getProsecutingAttorneyName())
			.addExpectedValue("sentenceReviewDate",
					courtCase.getSentenceReviewDate())
			.addExpectedValue("dangerDesignator",
					courtCase.getDangerDesignator())
			.addExpectedValue("criminallyConvictedYouth",
					courtCase.getFlags().getCriminallyConvictedYouth())
			.addExpectedValue("youthTransfer",
					courtCase.getFlags().getYouthTransfer())
			.addExpectedValue("convictionOverturned",
					courtCase.getFlags().getConvictionOverturned())
			.performAssertions(fields);
	}
}
