package omis.program.service.delegate;

import omis.program.dao.ProgramSupervisoryOrganizationOfferDao;

/**
 * Delegate offering programs by supervisory organization.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 13, 2016)
 * @since OMIS 3.0
 */
public class ProgramSupervisoryOrganizationOfferDelegate {

	// TODO Use DAO - SA
	@SuppressWarnings("unused")
	private final ProgramSupervisoryOrganizationOfferDao
	programSupervisoryOrganizationOfferDao;
	
	/**
	 * Delegate offering programs by supervisory organization.
	 * 
	 * @param programSupervisoryOrganizationOfferDao data access object for
	 * delegate offering programs by supervisory organization
	 */
	public ProgramSupervisoryOrganizationOfferDelegate(
			final ProgramSupervisoryOrganizationOfferDao
				programSupervisoryOrganizationOfferDao) {
		this.programSupervisoryOrganizationOfferDao
			= programSupervisoryOrganizationOfferDao;
	}
}