package omis.detainernotification.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.detainernotification.dao.DetainerActivityCategoryDao;
import omis.detainernotification.dao.DetainerTypeDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerActivityCategory;
import omis.detainernotification.domain.DetainerInterpretation;
import omis.detainernotification.domain.DetainerJurisdictionCategory;
import omis.detainernotification.domain.DetainerType;
import omis.detainernotification.domain.DetainerWarrantCancellationReason;
import omis.detainernotification.domain.DetainerWarrantProcessingStatus;
import omis.detainernotification.domain.Direction;
import omis.detainernotification.domain.InterstateAgreementDetainer;
import omis.detainernotification.domain.InterstateAgreementInitiatedByCategory;
import omis.detainernotification.domain.InterstateDetainerActivity;
import omis.detainernotification.service.DetainerService;
import omis.detainernotification.service.delegate.DetainerDelegate;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * DetainerNotificationRemoveTests.java
 * 
 *@author Annie Jacques 
 *@author Joel NOrris
 *@version 0.1.1 (May 26, 2017)
 *@since OMIS 3.0
 *
 */
public class DetainerNotificationRemoveTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("detainerService")
	private DetainerService detainerService;
	
	@Autowired
	private DetainerDelegate detainerDelegate;
	
	@Autowired
	private DetainerActivityCategoryDao detainerActivityCategoryDao;
	
	@Autowired
	@Qualifier("detainerActivityCategoryInstanceFactory")
	private InstanceFactory<DetainerActivityCategory>
		detainerActivityCategoryInstanceFactory;
	
	@Autowired
	private DetainerTypeDao detainerTypeDao;
	
	@Autowired
	@Qualifier("detainerTypeInstanceFactory")
	private InstanceFactory<DetainerType> detainerTypeInstanceFactory;
	
	@Autowired
	private OffenderDelegate offenderDelegate;
	
	@Test
	public void testRemove() throws DuplicateEntityFoundException{
		
		/* Detainer Properties */
		
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Man", "Old", "Guy", "Dude");
		
		DetainerType detainerType =
				this.detainerTypeInstanceFactory.createInstance();
		
		detainerType.setName("DetainerType");
		detainerType.setValid(true);
		detainerType.setSortOrder((short)0);
		detainerType.setInterpretation(DetainerInterpretation.NOTIFICATION);
		detainerType = this.detainerTypeDao.makePersistent(detainerType);
		
		/* Interstate Agreement Activity Properties */
		
		DetainerActivityCategory category =
				this.detainerActivityCategoryInstanceFactory.createInstance();
		
		category.setDescription("ActivityCategory");
		category.setName("ActivityCategoryName");
		category.setReceivable(true);
		category.setSendable(false);
		
		category = this.detainerActivityCategoryDao
				.makePersistent(category);
		
		/* Detainer Service Create Methods */
		
		final Detainer detainer = this.detainerService.create(
				offender, "altOff", "description",
				"number", detainerType, null,
				DetainerJurisdictionCategory.FEDERAL,
				this.parseDateText("01/01/2001"),
				this.parseDateText("01/01/2001"), "warrantNumber");
		
		final InterstateAgreementDetainer interstateAgreementDetainer = 
				this.detainerService.createInterstateAgreementDetainer(
						detainer, this.parseDateText("01/01/2001"),
						InterstateAgreementInitiatedByCategory.PROSECUTOR);
		
		final Document document = this.detainerService.createDocument(
				this.parseDateText("01/01/2001"), "file", ".doc", "fileTitle");
		final DocumentTag documentTag = this.detainerService.createDocumentTag(
				document, "tagName");
		final InterstateDetainerActivity interstateDetainerActivity =
				this.detainerService.createDetainerActivity(
						interstateAgreementDetainer,
						this.parseDateText("01/01/2001"), Direction.RECEIVED,
						document, category);
		
		final DetainerWarrantProcessingStatus detainerWarrantProcessingStatus =
				this.detainerService.createDetainerWarrantProcessingStatus(
						detainer, this.parseDateText("01/01/2001"), null, null,
						null, this.parseDateText("01/01/2001"), true, false,
						this.parseDateText("01/01/2001"),
						DetainerWarrantCancellationReason.DROPPED_BY_AGENCY,
						"refusedToSignComment", "waiverRequiredComment",
						"facilityName");
		
		/* Removal And Assertions */
		
		this.detainerService.removeDocumentTag(documentTag);
		assert !this.detainerService.findDocumentTagsByDocument(document)
			.contains(documentTag)
				: "Document Tag was not removed.";
		
		this.detainerService.removeDetainerActivity(interstateDetainerActivity);
		assert !this.detainerService
			.findDetainerActivityByInterstateAgreementDetainer(
					interstateAgreementDetainer)
			.contains(interstateDetainerActivity)
				: "Interstate Detainer Activity was not removed.";
			
		this.detainerService.removeInterstateAgreementDetainer(
				interstateAgreementDetainer);
		assert this.detainerService
			.findInterstateAgreementDetainerByDetainer(detainer) == null
				: "Interstate Agreement Detainer was not removed.";
		
		this.detainerService.removeDetainerWarrantProcessingStatus(
				detainerWarrantProcessingStatus);
		assert this.detainerService
			.findDetainerWarrantProcessingStatusByDetainer(detainer) == null
				: "Detainer Warrant Processing Status was not removed.";
		
		this.detainerService.remove(detainer);
		assert !this.detainerDelegate.findByOffender(offender).contains(detainer)
			: "Detainer was not removed.";
	}
	

	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
	
}
