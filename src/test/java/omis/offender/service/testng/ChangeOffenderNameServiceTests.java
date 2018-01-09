package omis.offender.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.AlternativeOffenderNameService;
import omis.offender.service.ChangeOffenderNameService;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.AlternativeNameCategory;
import omis.person.service.delegate.AlternativeNameCategoryDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * ChangeOffenderNameServiceTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 9, 2017)
 *@since OMIS 3.0
 *
 */
@Test(groups = "offender")
public class ChangeOffenderNameServiceTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("changeOffenderNameService")
	private ChangeOffenderNameService changeOffenderNameService;
	
	@Autowired
	@Qualifier("alternativeOffenderNameService")
	private AlternativeOffenderNameService alternativeOffenderNameService;
	
	@Autowired
	@Qualifier("alternativeNameCategoryDelegate")
	private AlternativeNameCategoryDelegate alternativeNameCategoryDelegate;
	
	@Test
	public void testChange() throws DuplicateEntityFoundException{
		final String oldLastName = "Oldman";
		final String oldFirstName = "Orwell";
		final String oldMiddleName = "Batman";
		final String oldSuffix = "III";
		
		final String newLastName = "Newman";
		final String newFirstName = "Norman";
		final String newMiddleName = "Snowman";
		final String newSuffix = "IV";
		final Date effectiveDate = this.parseDateText("03/09/2017");
		final AlternativeNameCategory previousNameCategory = this
				.alternativeNameCategoryDelegate
				.create("A Category", "Prev Category", (short) 0, true);
		
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				oldLastName, oldFirstName, oldMiddleName, oldSuffix);
		
		offender = this.changeOffenderNameService
				.change(offender, newLastName, newFirstName, newMiddleName,
						newSuffix, effectiveDate, previousNameCategory);
		
		final List<AlternativeNameAssociation> alternativeNameAssociations =
				this.alternativeOffenderNameService
				.findAssociations(offender);
		
		assert newLastName.equals(offender.getName().getLastName())
		: String.format("Wrong last name: %s found; %s expected",
				offender.getName().getLastName(), newLastName);
		assert newFirstName.equals(offender.getName().getFirstName())
		: String.format("Wrong first name: %s found; %s expected",
				offender.getName().getFirstName(), newFirstName);
		assert newMiddleName.equals(offender.getName().getMiddleName())
		: String.format("Wrong middle name: %s found; %s expected",
				offender.getName().getMiddleName(), newMiddleName);
		assert newSuffix.equals(offender.getName().getSuffix())
		: String.format("Wrong suffix: %s found; %s expected",
				offender.getName().getSuffix(), newSuffix);
		assert effectiveDate.equals(alternativeNameAssociations.get(0)
				.getDateRange().getEndDate())
		: String.format("Wrong effective date: %s found; %s expected",
				alternativeNameAssociations.get(0).getDateRange().getEndDate(),
				effectiveDate);
		assert previousNameCategory.equals(alternativeNameAssociations.get(0)
				.getCategory())
		: String.format("Wrong previous category: %s found; %s expected",
				alternativeNameAssociations.get(0).getCategory().getName(),
				previousNameCategory.getName());
		
		assert oldLastName.equals(alternativeNameAssociations.get(0).getName()
				.getLastName())
		: String.format("Wrong last name in association: %s found; %s expected",
				alternativeNameAssociations.get(0).getName().getLastName(),
				oldLastName);
		assert oldFirstName.equals(alternativeNameAssociations.get(0).getName()
				.getFirstName())
		: String.format("Wrong first name in association: %s found; %s expected",
				alternativeNameAssociations.get(0).getName().getFirstName(),
				oldFirstName);
		assert oldMiddleName.equals(alternativeNameAssociations.get(0).getName()
				.getMiddleName())
		: String.format("Wrong middle name in association: %s found; %s expected",
				alternativeNameAssociations.get(0).getName().getMiddleName(),
				oldMiddleName);
		assert oldSuffix.equals(alternativeNameAssociations.get(0).getName()
				.getSuffix())
		: String.format("Wrong suffix in association: %s found; %s expected",
				alternativeNameAssociations.get(0).getName().getSuffix(),
				oldSuffix);
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		}
		catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}
