package omis.person.service.delegate;

import java.util.ArrayList;
import java.util.List;

import omis.person.dao.SuffixDao;
import omis.person.domain.Suffix;

/**
 * Delegate for suffixes.
 *
 * @author Sheronda Vaughn
 * @version 0.0.1 (Jan 08, 2016)
 * @since OMIS 3.0
 */
public class SuffixDelegate {
	
	/* DAOs. */
	
	private final SuffixDao suffixDao;

	/* Constructors. */
	
	/**
	 * Instantiates delegate for name suffixes.
	 * 
	 * @param suffixDao data access object for suffixes
	 */
	public SuffixDelegate(
			final SuffixDao suffixDao) {
		this.suffixDao = suffixDao;
	}

	/**
	 * Returns suffixes.
	 * 
	 * @return suffixes
	 */
	public List<Suffix> findAll() {
		return this.suffixDao.findAll();
	}

	/**
	 * Returns suffix names.
	 * 
	 * @return suffix names
	 */
	public List<String> findSuffixNames() {
		List<String> suffixNames = new ArrayList<String>();
		List<Suffix> suffixes = this.suffixDao.findAll();
		for (Suffix suffix : suffixes) {
			suffixNames.add(suffix.getName());
		}
		return suffixNames;
	}
}