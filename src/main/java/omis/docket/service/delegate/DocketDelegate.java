package omis.docket.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.court.domain.Court;
import omis.docket.dao.DocketDao;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;

/**
 * Delegate for dockets.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class DocketDelegate {

	private final InstanceFactory<Docket> docketInstanceFactory;
	
	private final DocketDao docketDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates delegate for dockets.
	 * 
	 * @param docketInstanceFactory instance factory for dockets
	 * @param docketDao data access object for dockets
	 * @param auditComponentRetriever audit component retriever
	 */
	public DocketDelegate(
			final InstanceFactory<Docket> docketInstanceFactory,
			final DocketDao docketDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.docketInstanceFactory = docketInstanceFactory;
		this.docketDao = docketDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates docket.
	 * 
	 * @param person person
	 * @param court court
	 * @param value value
	 * @return created docket
	 * @throws DocketExistsException if docket exists
	 */
	public Docket create(
			final Person person, final Court court, final String value)
				throws DocketExistsException {
		if (this.docketDao.find(person, court, value) != null) {
			throw new DocketExistsException("Docket exists");
		}
		Docket docket = this.docketInstanceFactory.createInstance();
		docket.setPerson(person);
		docket.setCourt(court);
		docket.setValue(value);
		docket.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		docket.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.docketDao.makePersistent(docket);
	}
	
	/**
	 * Updates docket.
	 * 
	 * @param docket docket to update
	 * @param court court
	 * @param value value
	 * @return updated docket
	 * @throws DuplicateEntityFoundException if docket exists
	 */
	public Docket update(
			final Docket docket, final Court court, final String value)
					throws DocketExistsException {
		if (this.docketDao.findExcluding(
				docket.getPerson(), court, value, docket) != null) {
			throw new DocketExistsException("Docket exists");
		}
		docket.setCourt(court);
		docket.setValue(value);
		docket.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.docketDao.makePersistent(docket);
	}
	
	/**
	 * Removes docket.
	 * 
	 * @param docket docket to remove
	 */
	public void remove(final Docket docket) {
		this.docketDao.makeTransient(docket);
	}
	
	/**
	 * Returns a list of dockets for the specified Person
	 * @param person - person
	 * @return List of dockets for the specified person
	 */
	public List<Docket> findByPerson(final Person person){
		return this.docketDao.findByPerson(person);
	}
}