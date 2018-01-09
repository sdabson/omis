package omis.program.service.delegate;

import omis.program.dao.ProgramLocationOfferDao;

/**
 * Delegate offering program at facility.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 13, 2016)
 * @since OMIS 3.0
 */
public class ProgramLocationOfferDelegate {

	// TODO Use DAO - SA
	@SuppressWarnings("unused")
	private final ProgramLocationOfferDao programLocationOfferDao;
	
	/**
	 * Instantiates delegate offering program at location.
	 * 
	 * @param programLocationOfferDao data access object offering program
	 * at location
	 */
	public ProgramLocationOfferDelegate(
			final ProgramLocationOfferDao programLocationOfferDao) {
		this.programLocationOfferDao = programLocationOfferDao;
	}
}