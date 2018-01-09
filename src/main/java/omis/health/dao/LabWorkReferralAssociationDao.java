package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralAssociation;

/**
 * Lab work referral data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 26, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkReferralAssociationDao 
	extends GenericDao<LabWorkReferralAssociation> {

	/**
	 * Returns the lab work referral association with the specified lab work 
	 * and lab work referral.
	 * 
	 * @param labWork lab work
	 * @param labWorkReferral lab work referral
	 * @return lab work referral association
	 */
	LabWorkReferralAssociation find(LabWork labWork, 
			LabWorkReferral labWorkReferral);
}