package omis.dna.service.delegate;

import java.util.List;

import omis.dna.dao.DnaSampleExemptionReasonDao;
import omis.dna.domain.DnaSampleExemptionReason;

/**
 * Delegate for DNA sample exemption reasons.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 26, 2015)
 * @since OMIS 3.0
 */
public class DnaSampleExemptionReasonDelegate {

	/* DAOs. */
	
	private final DnaSampleExemptionReasonDao dnaSampleExemptionReasonDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for DNA sample exemption reasons.
	 * 
	 * @param dnaSampleExemptionReasonDao data access object for DNA sample
	 * exemption reasons
	 */
	public DnaSampleExemptionReasonDelegate(
			final DnaSampleExemptionReasonDao dnaSampleExemptionReasonDao) {
		this.dnaSampleExemptionReasonDao = dnaSampleExemptionReasonDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns DNA sample exemption reasons. 
	 * 
	 * @return DNA sample exemption reasons
	 */
	public List<DnaSampleExemptionReason> findAll() {
		return this.dnaSampleExemptionReasonDao.findAll();
	}
}