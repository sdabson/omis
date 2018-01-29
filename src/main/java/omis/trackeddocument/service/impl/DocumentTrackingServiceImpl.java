/* 
* OMIS - Offender Management Information System 
* Copyright (C) 2011 - 2017 State of Montana 
* 
* This program is free software: you can redistribute it and/or modify 
* it under the terms of the GNU General Public License as published by 
* the Free Software Foundation, either version 3 of the License, or 
* (at your option) any later version. 
* 
* This program is distributed in the hope that it will be useful, 
* but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
* GNU General Public License for more details. 
* 
* You should have received a copy of the GNU General Public License 
* along with this program.  If not, see <http://www.gnu.org/licenses/>. 
*/
package omis.trackeddocument.service.impl;

import java.util.Date;
import java.util.List;

import omis.docket.domain.Docket;
import omis.docket.service.delegate.DocketDelegate;
import omis.person.domain.Person;
import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;
import omis.trackeddocument.exception.TrackedDocumentReceivalExistsException;
import omis.trackeddocument.service.DocumentTrackingService;
import omis.trackeddocument.service.delegate.TrackedDocumentCategoryDelegate;
import omis.trackeddocument.service.delegate.TrackedDocumentReceivalDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Implementation of service for tracked document tracking.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 12, 2017)
 * @since OMIS 3.0
 */
public class DocumentTrackingServiceImpl
	implements DocumentTrackingService {	
	/* Delegates. */
	private final TrackedDocumentCategoryDelegate
		trackedDocumentCategoryDelegate;
	
	private final TrackedDocumentReceivalDelegate
		trackedDocumentReceivalDelegate;
	
	private final DocketDelegate docketDelegate;
	
	private final UserAccountDelegate userAccountDelegate;
	
	/**
	 * Instantiates an implementation of service for document tracking service.
	 * 
	 * @param trackedDocumentCategoryDelegate tracked document category delegate
	 * @param trackedDocumentReceivalDelegate tracked document receival delegate
	 * @param docketDelegate docket delegate
	 * @param userAccountDelegate userAccountDelegate
	 */
	public DocumentTrackingServiceImpl(
		final TrackedDocumentCategoryDelegate trackedDocumentCategoryDelegate,
		final TrackedDocumentReceivalDelegate trackedDocumentReceivalDelegate,
		final DocketDelegate docketDelegate,
		final UserAccountDelegate userAccountDelegate) {
		this.trackedDocumentCategoryDelegate = trackedDocumentCategoryDelegate;
		this.trackedDocumentReceivalDelegate = trackedDocumentReceivalDelegate;
		this.docketDelegate = docketDelegate;
		this.userAccountDelegate = userAccountDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public TrackedDocumentReceival trackReceival(final Docket docket,
		final TrackedDocumentCategory category, final Date receivedDate, 
		final UserAccount receivedByUserAccount)
			throws TrackedDocumentReceivalExistsException {
		return this.trackedDocumentReceivalDelegate.trackReceival(docket, 
			category, receivedDate, receivedByUserAccount);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final TrackedDocumentReceival receival) {
		this.trackedDocumentReceivalDelegate.remove(receival);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeByDocket(final Docket docket) {
		this.trackedDocumentReceivalDelegate.removeByDocket(docket);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Docket> findDockets(final Person defendant) {
		return this.docketDelegate.findByPerson(defendant);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<TrackedDocumentReceival> findReceivalsByDocket(final Docket 
		docket) {
		return this.trackedDocumentReceivalDelegate.findByDocket(docket);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<TrackedDocumentCategory> findCategories() {
		return this.trackedDocumentCategoryDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount findUserAccount(final String username) {
		return this.userAccountDelegate.findByUsername(username);
	}
}