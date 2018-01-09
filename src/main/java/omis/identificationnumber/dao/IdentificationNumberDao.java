package omis.identificationnumber.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.identificationnumber.domain.IdentificationNumber;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.IdentificationNumberIssuer;
import omis.offender.domain.Offender;

/**
 * Data access object for identification numbers.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public interface IdentificationNumberDao
		extends GenericDao<IdentificationNumber> {

	/**
	 * Returns identification number.
	 * 
	 * <p>Returns {@code null} if not found.
	 * 
	 * @param offender offender
	 * @param issuer issuer
	 * @param category category
	 * @param value value
	 * @param issueDate issue date
	 * @param expireDate expire date
	 * @return identification number or {@code null} if not found
	 */
	IdentificationNumber find(Offender offender,
			IdentificationNumberIssuer issuer,
			IdentificationNumberCategory category,
			String value, Date issueDate, Date expireDate);

	/**
	 * Returns identification number.
	 * 
	 * <p>Returns {@code null} if not found or excluded.
	 * 
	 * @param offender offender
	 * @param issuer issuer
	 * @param category category
	 * @param value value
	 * @param issueDate issue date
	 * @param expireDate expire date
	 * @param excludedIdentificationNumbers identification numbers to exclude
	 * @return identification number or {@code null} if not found
	 */
	IdentificationNumber findExcluding(Offender offender,
			IdentificationNumberIssuer issuer,
			IdentificationNumberCategory category,
			String value, Date issueDate, Date expireDate,
			IdentificationNumber... excludedIdentificationNumbers);

	/**
	 * Returns identification numbers by offender.
	 * 
	 * @param offender offender
	 * @return identification numbers by offender
	 */
	List<IdentificationNumber> findByOffender(Offender offender);
	
	/**
	 * Returns identification number with the specified properties and between
	 * the given issued and expire date.
	 * 
	 * <p>Returns {@code null} if not found.
	 * 
	 * @param offender offender
	 * @param issuer issuer
	 * @param category category
	 * @param issueDate issue date
	 * @param expireDate expire date
	 * @return identification number or {@code null} if not found
	 */
	IdentificationNumber findWithinDates(Offender offender,
			IdentificationNumberIssuer issuer,
			IdentificationNumberCategory category,
			Date issueDate, Date expireDate);
	
	/**
	 * Returns identification number with the specified properties and between
	 * the given issued and expire date.
	 * 
	 * <p>Returns {@code null} if not found or excluded.
	 * 
	 * @param offender offender
	 * @param issuer issuer
	 * @param category category
	 * @param issueDate issue date
	 * @param expireDate expire date
	 * @param excludedIdentificationNumbers identification numbers to exclude
	 * @return identification number or {@code null} if not found
	 */
	IdentificationNumber findWithinDatesExcluding(Offender offender,
			IdentificationNumberIssuer issuer,
			IdentificationNumberCategory category,
			Date issueDate, Date expireDate,
			IdentificationNumber... excludedIdentificationNumbers);
}