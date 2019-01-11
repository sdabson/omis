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
package omis.probationparole.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.docket.exception.DocketExistsException;
import omis.document.domain.Document;
import omis.document.service.delegate.DocumentDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.probationparole.domain.ProbationParoleDocumentAssociation;
import omis.probationparole.domain.ProbationParoleDocumentCategory;
import omis.probationparole.service.ProbationParoleDocumentService;
import omis.probationparole.service.delegate.ProbationParoleDocumentAssociationDelegate;
import omis.probationparole.service.delegate.ProbationParoleDocumentCategoryDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Probation Parole Document Service Update Tests.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 12, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentServiceUpdateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates */
	
	@Autowired
	@Qualifier("probationParoleDocumentCategoryDelegate")
	private ProbationParoleDocumentCategoryDelegate
				probationParoleDocumentCategoryDelegate;
	
	@Autowired
	@Qualifier("documentDelegate")
	private DocumentDelegate documentDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("probationParoleDocumentAssociationDelegate")
	private ProbationParoleDocumentAssociationDelegate
			probationParoleDocumentAssociationDelegate;
	
	/* Service to test */
	
	@Autowired
	@Qualifier("probationParoleDocumentService")
	private ProbationParoleDocumentService probationParoleDocumentService;
	
	@Test
	public void testUpdate()
			throws DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		Document document0 = this.documentDelegate.create(
				this.parseDateText("10/20/2010"), "Ye Olde File", ".bmp",
				"Oldey Old old");
		Date date0 = this.parseDateText("12/21/2012");
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		ProbationParoleDocumentCategory category0 =
					this.probationParoleDocumentCategoryDelegate
					.create("Different Category", true);
		
		//create
		ProbationParoleDocumentAssociation documentAssociation =
			this.probationParoleDocumentAssociationDelegate.create(
				document0, offender, date0, category0);
		
		Document document = this.documentDelegate.create(
				this.parseDateText("12/21/2012"), "File", ".file",
				"Filey File-o");
		Date date = this.parseDateText("12/21/2012");
		ProbationParoleDocumentCategory category =
					this.probationParoleDocumentCategoryDelegate
					.create("Document Category", true);
		
		//update
		documentAssociation = this.probationParoleDocumentService
				.updateProbationParoleDocumentAssociation(documentAssociation,
						document,offender, date, category);
		
		//Assertion
		PropertyValueAsserter.create()
			.addExpectedValue("document", document)
			.addExpectedValue("date", date)
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.performAssertions(documentAssociation);
	}
	
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException()
			throws DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		Document document0 = this.documentDelegate.create(
				this.parseDateText("10/20/2010"), "Ye Olde File", ".bmp",
				"Oldey Old old");
		Date date0 = this.parseDateText("12/21/2012");
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		ProbationParoleDocumentCategory category0 =
					this.probationParoleDocumentCategoryDelegate
					.create("Different Category", true);
		
		//create
		ProbationParoleDocumentAssociation documentAssociation =
			this.probationParoleDocumentAssociationDelegate.create(
				document0, offender, date0, category0);
		
		Document document = this.documentDelegate.create(
				this.parseDateText("12/21/2012"), "File", ".file",
				"Filey File-o");
		Date date = this.parseDateText("12/21/2012");
		ProbationParoleDocumentCategory category =
					this.probationParoleDocumentCategoryDelegate
					.create("Document Category", true);
		this.probationParoleDocumentAssociationDelegate.create(
				document0, offender, date, category);
		
		//update
		documentAssociation = this.probationParoleDocumentService
				.updateProbationParoleDocumentAssociation(documentAssociation,
						document, offender, date, category);
		
	}

	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}
