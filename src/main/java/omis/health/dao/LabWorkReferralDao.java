package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.OffenderAppointmentAssociation;

/**
 * Lab work referral data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 7, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkReferralDao extends GenericDao<LabWorkReferral> {

	/**
	 * Returns the lab work referral with the specified offender 
	 * appointment association.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 * @return lab work referral
	 */
	LabWorkReferral find(
			OffenderAppointmentAssociation offenderAppointmentAssociation);
	
	/**
	 * Returns the lab work referral with the specified offender appointment 
	 * association, unless that lab work referral matches the specified lab work
	 * referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param offenderAppointmentAssociation offender appointment association
	 * @return lab work referral
	 */
	LabWorkReferral findExcluding(LabWorkReferral labWorkReferral, 
			OffenderAppointmentAssociation offenderAppointmentAssociation);
}