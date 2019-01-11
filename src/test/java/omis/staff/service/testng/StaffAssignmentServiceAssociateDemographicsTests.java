package omis.staff.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.demographics.domain.EyeColor;
import omis.demographics.domain.HairColor;
import omis.demographics.domain.Height;
import omis.demographics.domain.MaritalStatus;
import omis.demographics.domain.PersonDemographics;
import omis.demographics.domain.Race;
import omis.demographics.domain.Weight;
import omis.demographics.domain.component.PersonAppearance;
import omis.demographics.domain.component.PersonPhysique;
import omis.demographics.service.delegate.EyeColorDelegate;
import omis.demographics.service.delegate.HairColorDelegate;
import omis.demographics.service.delegate.MaritalStatusDelegate;
import omis.demographics.service.delegate.PersonDemographicsDelegate;
import omis.demographics.service.delegate.RaceDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.staff.service.StaffAssignmentService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to associate demographics.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"staff", "service"})
public class StaffAssignmentServiceAssociateDemographicsTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("eyeColorDelegate")
	private EyeColorDelegate eyeColorDelegate;
	
	@Autowired
	@Qualifier("hairColorDelegate")
	private HairColorDelegate hairColorDelegate;
	
	@Autowired
	@Qualifier("raceDelegate")
	private RaceDelegate raceDelegate;
	
	@Autowired
	@Qualifier("maritalStatusDelegate")
	private MaritalStatusDelegate maritalStatusDelegate;
	
	@Autowired
	@Qualifier("personDemographicsDelegate")
	private PersonDemographicsDelegate personDemographicsDelegate;

	/* Service */
	@Autowired
	@Qualifier("staffAssignmentService")
	private StaffAssignmentService staffAssignmentService;
	
	/* Test methods. */

	/**
	 * Tests the association of demographics.
	 * 
	 */
	public void testDemographicsAssociation() {
		// Arrangements
		Person staffMember = this.personDelegate.create("Smith", "John", "Ryan", "Mr.");
		EyeColor eyeColor = this.eyeColorDelegate.create("Brown", true);
		HairColor hairColor = this.hairColorDelegate.create("Black", true);
		Height height = new Height(6,0);
		Weight weight = new Weight(200);

		// Action
		PersonDemographics personDemographics
		= this.staffAssignmentService.associateDemographics(
		staffMember, eyeColor, hairColor, height, weight);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("appearance.eyeColor", eyeColor)
			.addExpectedValue("appearance.hairColor", hairColor)
			.addExpectedValue("physique.height", height)
			.addExpectedValue("physique.weight", weight)
			.addExpectedValue("person", staffMember)
			.performAssertions(personDemographics);
	}
	
	/**
	 * Tests the association of existing demographics.
	 * 
	 */
	public void testExistingDemographicsAssociation() {
		// Arrangements
		Person staffMember = this.personDelegate.create(
			"Smith", "John", "Ryan", "Mr.");
		EyeColor eyeColor = this.eyeColorDelegate.create(
			"Brown", true);
		HairColor hairColor = this.hairColorDelegate.create(
			"Black", true);
		Height height = new Height(6,0);
		Weight weight = new Weight(200);
		PersonPhysique physique = new PersonPhysique();
		physique.setHeight(height);
		physique.setWeight(weight);
		PersonAppearance appearance = new PersonAppearance();
		appearance.setEyeColor(eyeColor);
		appearance.setHairColor(hairColor);
		Race race = this.raceDelegate.create("Asian", true);
		MaritalStatus marialStatus= this.maritalStatusDelegate
			.create("Married", true);
		this.personDemographicsDelegate.create(staffMember,
			appearance, race, false, physique, null, marialStatus,
			null);
		
		// Action
		PersonDemographics personDemographics
			= this.staffAssignmentService.associateDemographics(
			staffMember, null, null, null, null);
		
		//Assertion
		assert this.personDemographicsDelegate.find(staffMember)
			== null : "Person demographics 1 was not removed.";
		assert personDemographics == null : "Person demographics 2"
			+ "was not removed.";
	}
	
	/**
	 * Tests the update of existing demographics.
	 * 
	 */
	public void testUpdateDemographicsAssociation() {
		// Arrangements
		Person staffMember = this.personDelegate.create(
			"Smith", "John", "Ryan", "Mr.");
		EyeColor eyeColor = this.eyeColorDelegate.create(
			"Brown", true);
		HairColor hairColor = this.hairColorDelegate.create(
			"Black", true);
		Height height = new Height(6,0);
		Weight weight = new Weight(200);
		PersonPhysique physique = new PersonPhysique();
		physique.setHeight(height);
		physique.setWeight(weight);
		PersonAppearance appearance = new PersonAppearance();
		appearance.setEyeColor(eyeColor);
		appearance.setHairColor(hairColor);
		Race race = this.raceDelegate.create("Asian", true);
		MaritalStatus marialStatus= this.maritalStatusDelegate
			.create("Married", true);
		this.personDemographicsDelegate.create(staffMember,
			appearance, race, false, physique, null, marialStatus,
			null);
		EyeColor updatedEyeColor = this.eyeColorDelegate.create(
			"Blue", true);
		HairColor updatedHairColor = this.hairColorDelegate.create(
			"Brown", true);
		Height updatedHeight = new Height(5,10);
		Weight updatedWeight = new Weight(180);
		
		// Action
		PersonDemographics personDemographics
			= this.staffAssignmentService.associateDemographics(
			staffMember, updatedEyeColor, updatedHairColor,
			updatedHeight, updatedWeight);
		
		//Assertion
		PropertyValueAsserter.create()
		.addExpectedValue("appearance.eyeColor", updatedEyeColor)
		.addExpectedValue("appearance.hairColor", updatedHairColor)
		.addExpectedValue("physique.height", updatedHeight)
		.addExpectedValue("physique.weight", updatedWeight)
		.performAssertions(personDemographics);
	}
}