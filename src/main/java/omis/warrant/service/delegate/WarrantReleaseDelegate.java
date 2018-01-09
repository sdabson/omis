package omis.warrant.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.region.domain.County;
import omis.warrant.dao.WarrantReleaseDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantRelease;
import omis.warrant.domain.component.ClearSignature;

/**
 * WarrantReleaseDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantReleaseDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A WarrantRelease already exists for the specified Warrant.";
	
	private final WarrantReleaseDao warrantReleaseDao;
	
	private final InstanceFactory<WarrantRelease> 
		warrantReleaseInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for WarrantReleaseDelegate
	 * @param warrantReleaseDao
	 * @param warrantReleaseInstanceFactory
	 * @param auditComponentRetriever
	 */
	public WarrantReleaseDelegate(
			final WarrantReleaseDao warrantReleaseDao,
			final InstanceFactory<WarrantRelease> 
				warrantReleaseInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.warrantReleaseDao = warrantReleaseDao;
		this.warrantReleaseInstanceFactory = warrantReleaseInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a WarrantRelease with the specified properties
	 * @param warrant - Warrant
	 * @param instructions - String
	 * @param county - Country
	 * @param facility - Facility
	 * @param releaseTimestamp - Date
	 * @param addressee - String
	 * @param clearedBy - Person
	 * @param clearedByDate - Date
	 * @return Newly created WarrantRelease
	 * @throws DuplicateEntityFoundException - When a WarrantRelease already
	 * exists with the specified Warrant
	 */
	public WarrantRelease create(final Warrant warrant,
			final String instructions, final County county,
			final Facility facility, final Date releaseTimestamp,
			final String addressee, final Person clearedBy,
			final Date clearedByDate)
					throws DuplicateEntityFoundException{
		if(this.warrantReleaseDao.find(warrant) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		WarrantRelease warrantRelease = 
				this.warrantReleaseInstanceFactory.createInstance();
		
		
		warrantRelease.setAddressee(addressee);
		warrantRelease.setClearSignature(new ClearSignature(
				clearedBy, clearedByDate));
		warrantRelease.setCounty(county);
		warrantRelease.setFacility(facility);
		warrantRelease.setInstructions(instructions);
		warrantRelease.setReleaseTimestamp(releaseTimestamp);
		warrantRelease.setWarrant(warrant);
		warrantRelease.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		warrantRelease.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.warrantReleaseDao.makePersistent(warrantRelease);
	}
	
	/**
	 * Updates a WarrantRelease with the specified properties
	 * @param warrantRelease - WarrantRelease to update
	 * @param instructions - String
	 * @param county - Country
	 * @param facility - Facility
	 * @param releaseTimestamp - Date
	 * @param addressee - String
	 * @param clearedBy - Person
	 * @param clearedByDate - Date
	 * @return Updated WarrantRelease
	 * @throws DuplicateEntityFoundException - When a WarrantRelease already
	 * exists with the specified WarrantRelease's Warrant
	 */
	public WarrantRelease update(final WarrantRelease warrantRelease,
			final String instructions, final County county,
			final Facility facility, final Date releaseTimestamp,
			final String addressee, final Person clearedBy,
			final Date clearedByDate)
					throws DuplicateEntityFoundException{
		if(this.warrantReleaseDao.findExcluding(warrantRelease.getWarrant(),
				warrantRelease) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		warrantRelease.setAddressee(addressee);
		warrantRelease.setClearSignature(new ClearSignature(
				clearedBy, clearedByDate));
		warrantRelease.setCounty(county);
		warrantRelease.setFacility(facility);
		warrantRelease.setInstructions(instructions);
		warrantRelease.setReleaseTimestamp(releaseTimestamp);
		warrantRelease.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.warrantReleaseDao.makePersistent(warrantRelease);
	}
	
	/**
	 * Removes a WarrantRelease
	 * @param warrantRelease - WarrantRelease to remove
	 */
	public void remove(final WarrantRelease warrantRelease){
		this.warrantReleaseDao.makeTransient(warrantRelease);
	}
	
	/**
	 * Returns a WarrantRelease by specified Warrant
	 * @param warrant - Warrant
	 * @return WarrantRelease found by specified Warrant
	 */
	public WarrantRelease findByWarrant(final Warrant warrant){
		return this.warrantReleaseDao.find(warrant);
	}
	
}
