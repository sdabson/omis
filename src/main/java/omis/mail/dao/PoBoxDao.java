package omis.mail.dao;

import java.util.List;

import omis.address.domain.ZipCode;
import omis.dao.GenericDao;
import omis.mail.domain.PoBox;

/**
 * Post office box data access object.
 * 
 * @author Yidong Li
 * @version 0.1.1 (July 27, 2015)
 * @since OMIS 3.0
 * @deprecated use {@code omis.contact.domain.component.PoBox} instead
 */
@Deprecated
public interface PoBoxDao extends GenericDao<PoBox> {

	/**
	 * Returns the post office box with the specified value and zip code.
	 * 
	 * @param value value
	 * @param zipCode zip code
	 * @return post office box
	 */
	PoBox find(String value, ZipCode zipCode);
	
	/**
	 * Returns the post office box with the specified value and zip code,
	 * except if it's the specified post office box.
	 * 
	 * @param poBox post office box
	 * @param value value
	 * @param zipCode zip code
	 * @return post office box
	 */
	PoBox findExcluding(PoBox poBox, String value, ZipCode zipCode);
	
	/**
	 * Search po box by zip code
	 * 
	 * @param zipCode zip code
	 * @return a list of post office boxes
	 */
	List<PoBox> findPoBoxByZipCode(ZipCode zipCode);
}