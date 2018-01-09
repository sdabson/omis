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
package omis.supervision.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.supervision.dao.AllowedSupervisoryOrganizationChangeDao;
import omis.supervision.dao.PlacementTermChangeReasonDao;
import omis.supervision.dao.PlacementTermDao;
import omis.supervision.dao.SupervisoryOrganizationTermDao;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.exception.OffenderNotUnderSupervisionException;
import omis.supervision.exception.OffenderSupervisedByOrganizationException;
import omis.supervision.service.ChangeSupervisoryOrganizationService;

/**
 * Implementation of service to change supervisory organizations of an offender.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Dec 19, 2014)
 * @since OMIS 3.0
 */
public class ChangeSupervisoryOrganizationServiceImpl
		implements ChangeSupervisoryOrganizationService {

	private final InstanceFactory<PlacementTerm>
		placementTermInstanceFactory;
	
	private final PlacementTermDao placementTermDao;
	
	private final InstanceFactory<SupervisoryOrganizationTerm>
	supervisoryOrganizationTermInstanceFactory;
	
	private final SupervisoryOrganizationTermDao supervisoryOrganizationTermDao;

	private final PlacementTermChangeReasonDao placementTermChangeReasonDao;
	
	private final AllowedSupervisoryOrganizationChangeDao
	allowedSupervisoryOrganizationChangeDao;

	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of service to change supervisory
	 * organizations of an offender with the specified resources.
	 * 
	 * @param placementTermInstanceFactory instance factory for placement
	 * terms
	 * @param placementTermDao data access object for placement terms
	 * @param supervisoryOrganizationTermInstanceFactory instance factory
	 * for supervisory organization terms
	 * @param supervisoryOrganizationTermDao data access object for supervisory
	 * organization terms
	 * @param placementTermChangeReasonDao data access object for change
	 * reasons for placement terms
	 * @param allowedSupervisoryOrganizationChangeDao data access object for
	 * allowed supervisory organization change
	 * @param auditComponentRetriever audit component retriever
	 */
	public ChangeSupervisoryOrganizationServiceImpl(
			final InstanceFactory<PlacementTerm>
				placementTermInstanceFactory,
			final PlacementTermDao placementTermDao,
			final InstanceFactory<SupervisoryOrganizationTerm>
				supervisoryOrganizationTermInstanceFactory,
			final SupervisoryOrganizationTermDao supervisoryOrganizationTermDao,
			final PlacementTermChangeReasonDao placementTermChangeReasonDao,
			final AllowedSupervisoryOrganizationChangeDao
			allowedSupervisoryOrganizationChangeDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.placementTermInstanceFactory = placementTermInstanceFactory;
		this.placementTermDao = placementTermDao;
		this.supervisoryOrganizationTermInstanceFactory
			= supervisoryOrganizationTermInstanceFactory;
		this.supervisoryOrganizationTermDao = supervisoryOrganizationTermDao;
		this.placementTermChangeReasonDao = placementTermChangeReasonDao;
		this.allowedSupervisoryOrganizationChangeDao
			= allowedSupervisoryOrganizationChangeDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** {@inheritDoc} */
	@Override
	public PlacementTerm change(final Offender offender,
			final SupervisoryOrganization supervisoryOrganization,
			final PlacementTermChangeReason changeReason,
			final Date effectiveDate)
					throws OffenderNotUnderSupervisionException,
						OffenderSupervisedByOrganizationException {
		
		// Prevents change if offender does not have placement term on date
		PlacementTerm existingPlacementTerm = this.placementTermDao
				.findForOffenderOnDate(offender, effectiveDate);
		if (existingPlacementTerm == null) {
			throw new OffenderNotUnderSupervisionException(
					String.format("Offender %s not under supervision on %s",
							offender, effectiveDate));
		}
		
		// Prevents change if offender is already supervised by organization
		// on date
		if (existingPlacementTerm.getSupervisoryOrganizationTerm()
				.getSupervisoryOrganization().equals(supervisoryOrganization)) {
			throw new OffenderSupervisedByOrganizationException(
					String.format("Offender %s supervised by %s on %s",
							offender, supervisoryOrganization, effectiveDate));
		}
		
		// Prevents change/changes end date of existing placement term that are
		// not null
		if (DateRange.getEndDate(existingPlacementTerm.getDateRange()) != null) {
			
			// TODO Decide - disallow or change end date? SA
			throw new UnsupportedOperationException("Not yet implemented");
		} else {
			existingPlacementTerm.setDateRange(DateRange.adjustEndDate(
					existingPlacementTerm.getDateRange(), effectiveDate));
		}
		
		// Prevents change/changes change reason of existing placement term
		// that are not null
		if (existingPlacementTerm.getEndChangeReason() != null) {
			
			// TODO Decide - disallow or change change reason? SA
			throw new UnsupportedOperationException("Not yet implemented");
			
		} else {
			existingPlacementTerm.setEndChangeReason(changeReason);
		}
		
		// Saves existing placement term
		existingPlacementTerm = this.placementTermDao
				.makePersistent(existingPlacementTerm);
		
		// Prevents change/changes end date of supervisory organization term
		// that are not null
		if (DateRange.getEndDate(existingPlacementTerm
				.getSupervisoryOrganizationTerm().getDateRange()) != null) {
			
			// TODO Decide - disallow or change end date? SA
			throw new UnsupportedOperationException("Not yet implemented");
		} else {
			existingPlacementTerm.getSupervisoryOrganizationTerm()
				.setDateRange(DateRange.adjustEndDate(
						existingPlacementTerm.getSupervisoryOrganizationTerm()
							.getDateRange(), effectiveDate));
		}
		
		// Saves existing supervisory organization term
		existingPlacementTerm.setSupervisoryOrganizationTerm(
				this.supervisoryOrganizationTermDao.makePersistent(
					existingPlacementTerm.getSupervisoryOrganizationTerm()));
		
		// Creates and persists supervisory organization term
		SupervisoryOrganizationTerm supervisoryOrganizationTerm
			= this.supervisoryOrganizationTermInstanceFactory
				.createInstance();
		supervisoryOrganizationTerm.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		supervisoryOrganizationTerm.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		supervisoryOrganizationTerm.setOffender(offender);
		supervisoryOrganizationTerm.setDateRange(
				new DateRange(effectiveDate, null));
		supervisoryOrganizationTerm.setSupervisoryOrganization(
				supervisoryOrganization);
		supervisoryOrganizationTerm = this.supervisoryOrganizationTermDao
				.makePersistent(supervisoryOrganizationTerm);
		
		// Creates, persists and returns placement term
		PlacementTerm placementTerm = this.placementTermInstanceFactory
				.createInstance();
		placementTerm.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		placementTerm.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		placementTerm.setCorrectionalStatusTerm(existingPlacementTerm
				.getCorrectionalStatusTerm());
		placementTerm.setSupervisoryOrganizationTerm(
				supervisoryOrganizationTerm);
		placementTerm.setDateRange(new DateRange(effectiveDate, null));
		placementTerm.setOffender(offender);
		placementTerm.setStartChangeReason(changeReason);
		placementTerm.setLocked(false);
		return this.placementTermDao.makePersistent(placementTerm);
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization>
	findAllowedSupervisoryOrganizationsForChange(
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization) {
		return this.allowedSupervisoryOrganizationChangeDao
			.findAllowedForChange(correctionalStatus, supervisoryOrganization);
	}

	/** {@inheritDoc} */
	@Override
	public List<PlacementTermChangeReason> findChangeReasons() {
		return this.placementTermChangeReasonDao.find(true, true);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<PlacementTermChangeReason> findAllowedChangeReasons(
			final CorrectionalStatus fromCorrectionalStatus,
			final CorrectionalStatus toCorrectionalStatus) {
		return this.placementTermChangeReasonDao
				.findAllowed(fromCorrectionalStatus, toCorrectionalStatus);
	}
}