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
package omis.conviction.web.form.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.web.form.ConvictionFields;
import omis.instance.factory.InstanceFactory;
import omis.offense.domain.Offense;
import omis.offense.domain.OffenseClassification;
import omis.testng.AbstractNonTransactionalTestNGSpringContextTests;
import omis.util.DateUtility;
import omis.util.PropertyValueAsserter;

/**
 * Tests for conviction fields.
 * 
 * @author Stephen Abson
 * @since OMIS 3.0
 * @version 0.0.1 (Feb 8, 2019)
 */
@Test(groups = {"form", "conviction"})
public class ConvictionFieldsTests
		extends AbstractNonTransactionalTestNGSpringContextTests {
	
	/* Instance factories. */
	
	@Autowired
	@Qualifier("offenseInstanceFactory")
	private InstanceFactory<Offense> offenseInstanceFactory;
	
	@Autowired
	@Qualifier("convictionInstanceFactory")
	private InstanceFactory<Conviction> convictionInstanceFactory;

	/* Helpers. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Test cases. */
	
	/**
	 * Tests instantiation from conviction.
	 */
	public void testInstantiationFromConviction() {
		
		// Arranges conviction
		Offense offense = this.offenseInstanceFactory.createInstance();
		offense.setClassification(OffenseClassification.VIOLENT);
		offense.setName("Assault");
		offense.setShortName("Assault");
		offense.setUrl("https://annotations.org/assault.html");
		offense.setViolationCode("3.14.12c");
		offense.setValid(true);
		Conviction conviction = this.convictionInstanceFactory
				.createInstance();
		conviction.setOffense(offense);
		conviction.setSeverity(OffenseSeverity.FELONY);
		conviction.setCounts(2);
		conviction.setDate(this.dateUtility.parseDateText("12/12/2012"));
		conviction.setFlags(new ConvictionFlags(true, false, true, false));
		
		// Action - instantiates fields from conviction
		ConvictionFields convictionFields = new ConvictionFields(conviction);
		
		// Asserts property values
		PropertyValueAsserter.create()
			.addExpectedValue("offense", conviction.getOffense())
			.addExpectedValue("severity", conviction.getSeverity())
			.addExpectedValue("counts", conviction.getCounts())
			.addExpectedValue("date", conviction.getDate())
			.addExpectedValue("violentOffense",
					conviction.getFlags().getViolentOffense())
			.addExpectedValue("sexualOffense",
					conviction.getFlags().getSexualOffense())
			.addExpectedValue("paroleIneligible",
					conviction.getFlags().getParoleIneligible())
			.addExpectedValue("supervisedReleaseIneligible",
					conviction.getFlags().getSupervisedReleaseIneligible())
			.performAssertions(convictionFields);
	}
}