package omis.offenderrelationship.service.testng;

import java.util.Date;

import omis.exception.DuplicateEntityFoundException;
import omis.family.service.delegate.FamilyAssociationCategoryDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.offenderrelationship.service.CreateRelationshipsService;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.component.VictimAssociationFlags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

/**
 * Test association of victim.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"offenderrelationship"})
public class OffenderRelationshipAssociateVictimTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("familyAssociationCategoryDelegate")
	private FamilyAssociationCategoryDelegate familyAssociationCategoryDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	/* Services */
	@Autowired
	@Qualifier("createRelationshipsService")
	private CreateRelationshipsService createRelationshipsService;
	
	/**
	 * Tests associate victim.
	 * @throws DuplicateEntityFoundException  DuplicateEntityFoundException
	 * @throws ReflexiveRelationshipException ReflexiveRelationshipException
	 */
	@Test
	public void testAssociateVictim() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException {
		// Arrangement
		Date registerDate = new Date(1);
		Boolean packetSent = true;
		final int packetSentDateInt = 1000000;
		Date packetSentDate = new Date(packetSentDateInt);
		VictimAssociationFlags flags = new VictimAssociationFlags(true, true, 
			true, true);
		Offender offender = this.offenderDelegate.createWithoutIdentity("Wang",
			"Kevin", "Johns", "Mr.");
		Person victim = this.personDelegate.create("lastName", "firstName", 
			"middleName", "Mr.");
		
		// Action
		VictimAssociation association = this.createRelationshipsService
			.associateVictim(offender, victim, registerDate, packetSent, 
			packetSentDate, flags); 
		
		// Assertions
		assert registerDate.equals(association.getRegisterDate())
		: String.format("Wrong Register Date: #%s expected; #%s found",
			registerDate, association.getRegisterDate());
		assert packetSentDate.equals(association.getPacketSendDate())
		: String.format("Wrong Packet Sent Date: #%s expected; #%s found",
			packetSentDate, association.getPacketSendDate());
		assert packetSent.equals(association.getPacketSent())
		: String.format("Wrong Packet Sent: #%s expected; #%s found",
			packetSent.toString(), association.getPacketSent().toString());
		assert flags.getBusiness().equals(association.getFlags().getBusiness())
		: String.format("Wrong Bussiness Flag: #%s expected; #%s found",
			flags.getBusiness().toString(), 
			association.getFlags().getBusiness().toString());
		assert flags.getDocRegistered().equals(association.getFlags()
			.getDocRegistered())
		: String.format("Wrong DocRegistered Flag: #%s expected; #%s found",
			flags.getDocRegistered().toString(), 
			association.getFlags().getDocRegistered().toString());
		assert flags.getVictim().equals(association.getFlags().getVictim())
		: String.format("Wrong Victim Flag: #%s expected; #%s found",
			flags.getVictim().toString(), 
			association.getFlags().getVictim().toString());
		assert flags.getVineRegistered().equals(association.getFlags()
			.getVineRegistered())
		: String.format("Wrong VineRegistered Flag: #%s expected; #%s found",
			flags.getVineRegistered().toString(), 
			association.getFlags().getVineRegistered().toString());
		assert offender.equals((Offender) association.getRelationship()
				.getFirstPerson())
			: String.format("Wrong Offender: #%s expected; #%s found",
				offender.getOffenderNumber(), ((Offender) association
				.getRelationship().getFirstPerson()).getOffenderNumber()); 
		assert victim.equals(association.getRelationship().getSecondPerson())
		: String.format("Wrong Victim: #%s expected; #%s found",
			victim.getName(), 
			association.getRelationship().getSecondPerson().getName());
	}
	
	/**
	 * Tests duplicate victim association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate relationships exist
	 * @throws ReflexiveRelationshipException ReflexiveRelationshipException
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class, 
		ReflexiveRelationshipException.class })
	public void testDuplicateAssociateFamilyMember() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException {
		// Assignment
		Date registerDate = new Date(1);
		Boolean packetSent = true;
		final int packetSentDateInt = 1000000;
		Date packetSentDate = new Date(packetSentDateInt);
		VictimAssociationFlags flags = new VictimAssociationFlags(true, true, 
			true, true);
		Offender offender = this.offenderDelegate.createWithoutIdentity("Wang",
			"Kevin", "Johns", "Mr.");
		Person victim = this.personDelegate.create("lastName", "firstName", 
			"middleName", "Mr.");
		
		// Action
		this.createRelationshipsService.associateVictim(offender, victim, 
			registerDate, packetSent, packetSentDate, flags); 
		this.createRelationshipsService.associateVictim(offender, victim, 
			registerDate, packetSent, packetSentDate, flags); 
	}
}