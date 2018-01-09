package omis.adaaccommodation.dao;

import omis.adaaccommodation.domain.Disability;
import omis.adaaccommodation.domain.DisabilityClassificationCategory;
import omis.dao.GenericDao;
import omis.offender.domain.Offender;

/**
 * Data access object for disability.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 23, 2015)
 * @since OMIS 3.0
 */
public interface DisabilityDao 
					extends GenericDao<Disability> {

	/**
	 * Returns the disability.
	 * 
	 * @param offender offender
	 * @param description description
	 * @param disabilityClassificationCategory 
	 * disability classification category
	 * @return disability
	 */
	Disability find(Offender offender, String description,
			DisabilityClassificationCategory disabilityClassificationCategory);

	/**
	 * Returns the disability excluding the one in view.
	 * 
	 * @param offender offender
	 * @param description description
	 * @param disabilityClassificationCategory 
	 * disability classification category
	 * @param disability
	 * @return
	 */
	Disability findExcluding(Offender offender, String description,
			DisabilityClassificationCategory disabilityClassificationCategory,
			Disability disability);

}