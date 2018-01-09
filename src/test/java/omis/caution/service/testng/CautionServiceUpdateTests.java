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

import omis.caution.domain.CautionDescription;
import omis.caution.domain.CautionSource;
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
 * Tests for updating cautions using caution service.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 17, 2016)
 * @since OMIS 3.0
 */
@Test(groups = {"caution"})
public class CautionServiceUpdateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderCautionDelegate")
	private OffenderCautionDelegate offenderCautionDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
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

	/**
	 * Tests update of cautions.
	 * 
	 * @throws DuplicateEntityFoundException if caution exists
	 */
	@Test
	public void testUpdate() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", "Stavro", null);
		OffenderCaution caution;
		try {
			caution = this.offenderCautionDelegate
					.create(offender,
							new DateRange(
									parseDateText("10/15/2007"),
									parseDateText("07/15/2010")),
							this.cautionSourceDelegate
								.create("Physician", false, true),
							this.cautionDescriptionDelegate
								.create("Single Cell Restriction", true),
							null, null);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Caution exists", e);
		}
		final CautionDescription stgDescription
			= this.cautionDescriptionDelegate.create("STG", true);
		final CautionSource lawEnforcementSource = this.cautionSourceDelegate
				.create("Law Enforcement", false, true);
		final DateRange dateRange = new DateRange(
				this.parseDateText("11/20/2009"), null);
		final String comment = "WHITE SUPREMACIST ASSOCIATE";
		final String sourceComment = "Reported on 09/12/2008";
		caution = this.offenderCautionService.update(caution,
				stgDescription, lawEnforcementSource, dateRange, comment,
				sourceComment);
		assertValues(caution, offender, lawEnforcementSource, stgDescription,
				dateRange, comment, sourceComment);
	}

	// Parses date text - returns result
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
	// Asserts caution values
	private void assertValues(
			final OffenderCaution caution,
			final Offender offender,
			final CautionSource source,
			final CautionDescription description,
			final DateRange dateRange,
			final String comment,
			final String sourceComment) {
		assert offender.equals(caution.getOffender())
		: String.format("Wrong offender: #%d found; #%d expected",
				caution.getOffender().getOffenderNumber(),
				offender.getOffenderNumber());
		assert source.equals(caution.getSource())
			: String.format("Wrong source: %s found; %s expected",
					caution.getSource().getName(), source.getName());
		assert description.equals(caution.getDescription())
			: String.format("Wrong description: %s found; %s expected",
					caution.getDescription().getName(),
					description.getName());
		assert dateRange.equals(caution.getDateRange())
			: String.format("Wrong date range: %s found; %s expected",
					caution.getDateRange(), dateRange);
		assert comment.equals(caution.getComment())
			: String.format("Wrong comment: %s found; %s expected",
					caution.getComment(), comment);
		assert sourceComment.equals(caution.getSourceComment())
			: String.format("Wrong source comment: %s found; %s expected",
					caution.getSourceComment(), sourceComment);
	}
}