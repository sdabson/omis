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
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.component.VictimAssociationFlags;
import omis.victim.service.VictimAssociationService;
import omis.victim.service.delegate.VictimAssociationDelegate;

/**
 * Tests update of victim.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"victimAssociation", "update"})
public class VictimAssociationServiceUpdateTests
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
	
	@Autowired
	@Qualifier("relationshipDelegate")
	private RelationshipDelegate relationshipDelegate;
	
	@Autowired
	@Qualifier("victimAssociationDelegate")
	private VictimAssociationDelegate victimAssociationDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("victimAssociationService")
	private VictimAssociationService victimAssociationService;
	
	/* Test methods. */
	
	public void testUpdate() throws DuplicateEntityFoundException {
		
		// Arrangements
		Offender sanchez = this.offenderDelegate.createWithoutIdentity(
				"Sanchez", "Franz", "", "");
		Person leiter = this.personDelegate.create(
				"Leiter", "Felix", "", "");
		Relationship relationship;
		try {
			relationship = this.relationshipDelegate
					.create(sanchez, leiter);
		} catch (ReflexiveRelationshipException e) {
			throw new AssertionError("Relationship exists", e);
		}
		VictimAssociation victimAssociation = this.victimAssociationDelegate
				.create(relationship, this.parseDateText("03/12/2013"), true,
						this.parseDateText("12/18/2013"),
						new VictimAssociationFlags(true, false, true, false));
		
		// Action
		Date registerDate = this.parseDateText("12/13/2013");
		Boolean packetSent = true;
		Date packetSendDate = this.parseDateText("01/02/2014");
		Boolean victimFlag = true;
		Boolean docRegisteredFlag = true;
		Boolean businessFlag = false;
		Boolean vineRegisteredFlag = false;
		victimAssociation = this.victimAssociationService
				.update(victimAssociation, registerDate, packetSent,
						packetSendDate,
						new VictimAssociationFlags(victimFlag,
								docRegisteredFlag, businessFlag,
								vineRegisteredFlag));
		
		// Assertions
		assert sanchez.equals((Offender) victimAssociation
					.getRelationship().getFirstPerson())
				: String.format("Wrong offender - %s expected; %s found",
						sanchez,
						victimAssociation.getRelationship().getFirstPerson());
		assert leiter.equals(victimAssociation.getRelationship()
					.getSecondPerson())
				: String.format("Wrong victim - %s expected; %s found",
						leiter,
						victimAssociation.getRelationship().getSecondPerson());
		assert registerDate.equals(victimAssociation.getRegisterDate())
				: String.format("Wrong register date - %s expected; %s found",
						registerDate, victimAssociation.getRegisterDate());
		assert packetSent.equals(victimAssociation.getPacketSent())
				: String.format(
						"Wrong packet sent flags - %s expected; %s found",
						packetSent, victimAssociation.getPacketSent());
		assert packetSendDate.equals(victimAssociation.getPacketSendDate())
				: String.format(
						"Wrong packet send date - %s expected; %s found",
						packetSendDate, victimAssociation.getPacketSendDate());
		assert victimFlag.equals(victimAssociation.getFlags().getVictim())
				: String.format("Wrong victim flag - %s expected; %s found",
						victimFlag, victimAssociation.getFlags().getVictim());
		assert docRegisteredFlag.equals(
					victimAssociation.getFlags().getDocRegistered())
				: String.format(
						"Wrong DOC registered flag - %s expected; %s found",
						docRegisteredFlag,
						victimAssociation.getFlags().getDocRegistered());
		assert businessFlag.equals(victimAssociation.getFlags().getBusiness())
				: String.format("Wrong business flag - %s expected; %s found",
						businessFlag,
						victimAssociation.getFlags().getBusiness());
		assert vineRegisteredFlag.equals(
				victimAssociation.getFlags().getVineRegistered())
				: String.format(
						"Wrong VINE registered flag - %s expected; %s found",
						vineRegisteredFlag,
						victimAssociation.getFlags().getVineRegistered());
	}
	
	/* Helper methods. */
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}