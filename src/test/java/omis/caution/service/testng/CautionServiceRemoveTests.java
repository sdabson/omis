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
package omis.caution.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import omis.caution.domain.OffenderCaution;
import omis.caution.service.OffenderCautionService;
import omis.caution.service.delegate.CautionDescriptionDelegate;
import omis.caution.service.delegate.CautionSourceDelegate;
import omis.caution.service.delegate.OffenderCautionDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

/**
 * Tests for removing cautions using caution service.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 17, 2016)
 * @since OMIS 3.0
 */
@Test(groups = {"caution"})
public class CautionServiceRemoveTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;

	@Autowired
	@Qualifier("offenderCautionDelegate")
	private OffenderCautionDelegate offenderCautionDelegate;
	
	@Autowired
	@Qualifier("cautionSourceDelegate")
	private CautionSourceDelegate cautionSourceDelegate;
	
	@Autowired
	@Qualifier("cautionDescriptionDelegate")
	private CautionDescriptionDelegate cautionDescriptionDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("offenderCautionService")
	private OffenderCautionService offenderCautionService;
	
	/* Tests. */

	/** Tests removal of cautions. */
	@Test
	public void testRemove() {
		
		// Arrangements
		final Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", "Stavro", null);
		OffenderCaution caution;
		try {
			caution = this.offenderCautionDelegate
					.create(offender,
							new DateRange(parseDateText("12/15/1996"), null),
							this.cautionSourceDelegate
								.create("Previous Facility", false, true),
							this.cautionDescriptionDelegate
								.create("Separation", true),
							"Cell restriction with Blofeld",
							null);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Caution exists", e);
		}
		
		// Action
		this.offenderCautionService.remove(caution);
		
		// Assertion
		assert !this.offenderCautionDelegate.findByOffender(offender)
				.contains(caution)
			: "Caution was not removed";
	}
	
	// Parses date text - returns result
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}