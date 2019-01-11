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
package omis.offenderflag.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.AuditComponentRetriever;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.offenderflag.domain.FlagUsage;
import omis.offenderflag.domain.OffenderFlagCategory;
import omis.offenderflag.service.OffenderFlagService;
import omis.offenderflag.service.delegate.OffenderFlagCategoryDelegate;
import omis.offenderflag.service.delegate.OffenderFlagDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests for service method to unset offender flags.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Dec 7, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"offenderFlag", "service"})
public class OffenderFlagServiceUnsetTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("offenderFlagCategoryDelegate")
	private OffenderFlagCategoryDelegate offenderFlagCategoryDelegate;
	
	@Autowired
	@Qualifier("offenderFlagDelegate")
	private OffenderFlagDelegate offenderFlagDelegate;
	
	// This should be removed along with userAccount and date parameters of set
	// method - services should lookup audit components themselves
	@Autowired
	@Qualifier("auditComponentRetriever")
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Service. */
	
	@Autowired
	@Qualifier("offenderFlagService")
	private OffenderFlagService offenderFlagService;
	

	/**
	 * Tests unsetting of offender flags. 
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 */
	public void test() throws DuplicateEntityFoundException {
		
		// Arranges offender with "sexual offender" flag set
		Offender offender = this.createOffender();
		OffenderFlagCategory sexualFlagCategory
			= this.createSexualFlagCategory();
		this.offenderFlagDelegate.create(offender, sexualFlagCategory, true);
		
		// Action - unsets flag
		this.offenderFlagService.unset(offender, sexualFlagCategory);
		
		// Asserts that no flags exist for offender
		assert this.offenderFlagDelegate.findByOffender(offender).size() == 0
				: "Flags exists for offender after only one was unset";
	}
	
	/* Helper methods. */
	
	// Creates offender
	private Offender createOffender() {
		return this.offenderDelegate
				.createWithoutIdentity("Scaramanga", "Julius", null, "XX");
	}
	
	// Creates sexual offender flag category
	private OffenderFlagCategory createSexualFlagCategory()
			throws DuplicateEntityFoundException {
		return this.offenderFlagCategoryDelegate
				.createOffenderFlagCategory(
						"Sexual Offender",
						"Offender is convicted of a sex crime", true,
						(short) 1, FlagUsage.REQUIREMENT);
	}
}