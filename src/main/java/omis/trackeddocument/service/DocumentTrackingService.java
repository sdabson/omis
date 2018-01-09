package omis.trackeddocument.service;

import java.util.Date;
import java.util.List;

import omis.docket.domain.Docket;
import omis.person.domain.Person;
import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;
import omis.trackeddocument.exception.TrackedDocumentReceivalExistsException;
import omis.user.domain.UserAccount;

/**
 * Service for document tracking.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Aug 12, 2017)
 * @since OMIS 3.0
 */
public interface DocumentTrackingService {

	/**
	 * Create tracked document receival.
	 * 
	 * @param docket docket
	 * @param receivedDate received date
	 * @param category tracked document category
	 * @param receivedByUserAccount received user account
	 * @return TrackedDocumentReceival tracked document receival
	 * @throws TrackedDocumentReceivalExistsException tracked document receival
	 * exists exception
	 */
	TrackedDocumentReceival trackReceival(Docket docket, TrackedDocumentCategory
		category, Date receivedDate, UserAccount receivedByUserAccount)
		throws TrackedDocumentReceivalExistsException;
	
	/**
	 * Remove tracked document recevial.
	 * 
	 * @param receival tracked document receival
	 */
	void remove(TrackedDocumentReceival receival);
	
	/**
	 * Remove all tracked document receival by docket.
	 * @param docket docket
	 */
	void removeByDocket(Docket docket);
	
	/**
	 * Find dockets by defendant.
	 * @param defendant defendant 
	 * @return dockets
	 */
	List<Docket> findDockets(Person defendant);
	
	/**
	 * Returns a list of tracked document receival.
	 * @param docket docket
	 * @return a list of tracked document receival
	 */
	List<TrackedDocumentReceival> findReceivalsByDocket(Docket docket);
	
	/**
	 * Finds all tracked document category.
	 * 
	 * @return a list of tracked document category
	 */
	List<TrackedDocumentCategory> findCategories();
	
	/**
	 * Finds user account.
	 * 
	 * @return a user account
	 */
	UserAccount findUserAccount();
}