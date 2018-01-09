package omis.dna.dao;

import omis.dao.GenericDao;
import omis.dna.domain.DnaSampleExemption;
import omis.offender.domain.Offender;

/**
 * Data access object for DNA sample exemptions.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 23, 2015)
 * @since OMIS 3.0
 */
public interface DnaSampleExemptionDao
		extends GenericDao<DnaSampleExemption> {

	/**
	 * Returns DNA sample exemption.
	 * 
	 * @param offender offender
	 * @return DNA sample exemption
	 */
	DnaSampleExemption find(Offender offender);
}