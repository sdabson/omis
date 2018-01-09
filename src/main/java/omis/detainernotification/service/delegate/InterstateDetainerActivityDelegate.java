package omis.detainernotification.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.dao.InterstateDetainerActivityDao;
import omis.detainernotification.domain.DetainerActivityCategory;
import omis.detainernotification.domain.Direction;
import omis.detainernotification.domain.InterstateAgreementDetainer;
import omis.detainernotification.domain.InterstateDetainerActivity;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * InterstateDetainerActivityDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class InterstateDetainerActivityDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Interstate Detainer Activity already exists with given activity"
			+ " date and document for specified interstate agreement detainer.";
	
	private final InterstateDetainerActivityDao interstateDetainerActivityDao;
	
	private final InstanceFactory<InterstateDetainerActivity> 
		interstateDetainerActivityInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for InterstateDetainerActivityDelegate
	 * @param interstateDetainerActivityDao
	 * @param interstateDetainerActivityInstanceFactory
	 * @param auditComponentRetriever
	 */
	public InterstateDetainerActivityDelegate(
			final InterstateDetainerActivityDao interstateDetainerActivityDao,
			final InstanceFactory<InterstateDetainerActivity> 
				interstateDetainerActivityInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.interstateDetainerActivityDao = interstateDetainerActivityDao;
		this.interstateDetainerActivityInstanceFactory =
				interstateDetainerActivityInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an InterstateDetainerActivity with specified parameters
	 * @param interstateAgreementDetainer - InterstateAgreementDetainer
	 * @param activityDate - Date
	 * @param direction - Direction
	 * @param document - Document
	 * @param category - DetainerActivityCategory
	 * @return Newly created InterstateDetainerActivity
	 * @throws DuplicateEntityFoundException - When an InterstateDetainerActivity
	 * already exists with given InterstateAgreementDetainer, ActivityDate, and
	 * Document
	 */
	public InterstateDetainerActivity create(
			final InterstateAgreementDetainer interstateAgreementDetainer,
			final Date activityDate, final Direction direction,
			final Document document, final DetainerActivityCategory category)
			throws DuplicateEntityFoundException{
		if(this.interstateDetainerActivityDao.find(interstateAgreementDetainer,
				activityDate, document) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		InterstateDetainerActivity interstateDetainerActivity = 
				this.interstateDetainerActivityInstanceFactory.createInstance();
		
		interstateDetainerActivity.setActivityDate(activityDate);
		interstateDetainerActivity.setCategory(category);
		interstateDetainerActivity.setDirection(direction);
		interstateDetainerActivity.setDocument(document);
		interstateDetainerActivity.setInterstateAgreementDetainer(
				interstateAgreementDetainer);
		interstateDetainerActivity.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		interstateDetainerActivity.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.interstateDetainerActivityDao.makePersistent(
				interstateDetainerActivity);
	}
	
	/**
	 * Updates an InterstateDetainerActivity with specified parameters
	 * @param interstateDetainerActivity - InterstateDetainerActivity to update
	 * @param activityDate - Date
	 * @param direction - Direction
	 * @param document - Document
	 * @param category - DetainerActivityCategory
	 * @return Updated InterstateDetainerActivity
	 * @throws DuplicateEntityFoundException - When an InterstateDetainerActivity
	 * already exists with given InterstateAgreementDetainer, ActivityDate, and
	 * Document
	 */
	public InterstateDetainerActivity update(
			final InterstateDetainerActivity interstateDetainerActivity,
			final Date activityDate, final Direction direction,
			final Document document, final DetainerActivityCategory category)
			throws DuplicateEntityFoundException{
		if(this.interstateDetainerActivityDao.findExcluding(
				interstateDetainerActivity.getInterstateAgreementDetainer(),
				activityDate, document, interstateDetainerActivity) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		interstateDetainerActivity.setActivityDate(activityDate);
		interstateDetainerActivity.setCategory(category);
		interstateDetainerActivity.setDirection(direction);
		interstateDetainerActivity.setDocument(document);
		interstateDetainerActivity.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.interstateDetainerActivityDao.makePersistent(
				interstateDetainerActivity);
	}
	
	/**
	 * Removes an InterstateDetainerActivity
	 * @param interstateDetainerActivity - InterstateDetainerActivity to remove
	 */
	public void remove(
			final InterstateDetainerActivity interstateDetainerActivity){
		this.interstateDetainerActivityDao.makeTransient(
				interstateDetainerActivity);
	}
	
	/**
	 * Returns a list of InterstateDetainerActivities found by specified
	 * InterstateAgreementDetainer
	 * @param interstateAgreementDetainer - InterstateAgreementDetainer
	 * @return List of InterstateDetainerActivities found by specified
	 * Detainer
	 */
	public List<InterstateDetainerActivity> findByInterstateAgreementDetainer(
			final InterstateAgreementDetainer interstateAgreementDetainer) {
		return this.interstateDetainerActivityDao
				.findByInterstateAgreementDetainer(interstateAgreementDetainer);
	}
	
	
}
