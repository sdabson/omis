package omis.victim.service.testng;

import java.beans.PropertyEditor;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.component.VictimAssociationFlags;
import omis.victim.service.VictimAssociationService;

/**
 * Tests creation of victims.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"victimAssociation", "create"})
public class VictimAssociationServiceAssociateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;

	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("victimAssociationService")
	private VictimAssociationService victimAssociationService;
	
	/* Test methods. */
	
	/**
	 * Tests association of victim to offender.
	 * 
	 * @throws DuplicateEntityFoundException if victim association exists
	 * @throws ReflexiveRelationshipException if offender is victim 
	 */
	public void testAssociate()
			throws DuplicateEntityFoundException,
				ReflexiveRelationshipException {
		
		// Arrangements
		Offender greene = this.offenderDelegate
				.createWithoutIdentity("Greene", "Dominic", "", "");
		Person mathis = this.personDelegate
				.create("Mathis", "Rene", "", "");
		Date registeredDate = this.parseDateText("02/11/2014");
		Boolean packetSent = true;
		Date packetSendDate = this.parseDateText("03/01/2014");
		VictimAssociationFlags flags = new VictimAssociationFlags();
		flags.setBusiness(true);
		flags.setDocRegistered(false);
		flags.setVictim(true);
		flags.setVineRegistered(true);
		
		// Action
		VictimAssociation victimAssociation = this.victimAssociationService
				.associate(greene, mathis, registeredDate, packetSent,
						packetSendDate, flags);
		
		// Assertions
		assert victimAssociation.getRelationship().getFirstPerson()
			.equals(greene)
				: String.format("Wrong offender - %s expected; %s found",
					greene,
					victimAssociation.getRelationship().getFirstPerson());
		assert victimAssociation.getRelationship().getSecondPerson()
			.equals(mathis)
				: String.format("Wrong victim - %s expected; %s found",
					mathis,
					victimAssociation.getRelationship().getSecondPerson());
		assert registeredDate.equals(victimAssociation.getRegisterDate())
				: String.format("Wrong register date - %s expected; %s found",
						registeredDate, victimAssociation.getRegisterDate());
		assert packetSendDate.equals(victimAssociation.getPacketSendDate())
				: String.format(
						"Wrong packet send date - %s expected; %s found",
						packetSendDate, victimAssociation.getPacketSendDate());
		assert flags.getBusiness().equals(
					victimAssociation.getFlags().getBusiness())
				: String.format("Wrong business flag - %s expected; %s found",
						flags.getBusiness(),
						victimAssociation.getFlags().getBusiness());
		assert flags.getDocRegistered().equals(
					victimAssociation.getFlags().getDocRegistered())
				: String.format(
						"Wrong DOC registered flag - %s expected; %s found",
						flags.getDocRegistered(),
						victimAssociation.getFlags().getDocRegistered());
		assert flags.getVictim().equals(
					victimAssociation.getFlags().getVictim())
				: String.format("Wrong victim flag - %s expected; %s found",
						flags.getVictim(),
						victimAssociation.getFlags().getVictim());
		assert flags.getVineRegistered().equals(
					victimAssociation.getFlags().getVineRegistered())
				: String.format(
						"Wrong VINE registered flag - %s expected; %s found",
						flags.getVineRegistered(),
						victimAssociation.getFlags().getVineRegistered());
	}
	
	/* Helpers. */
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}