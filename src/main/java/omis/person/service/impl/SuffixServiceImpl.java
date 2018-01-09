package omis.person.service.impl;

import java.util.List;

import omis.person.dao.SuffixDao;
import omis.person.domain.Suffix;
import omis.person.service.SuffixService;

/**
 * Implementation of service for suffixes.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 13, 2013)
 * @since OMIS 3.0
 */
public class SuffixServiceImpl
		implements SuffixService {
	
	private final SuffixDao suffixDao;

	/**
	 * Instantiates an implementation of service for suffixes with the specified
	 * resources.
	 * 
	 * @param suffixDao data access object for suffixes
	 */
	public SuffixServiceImpl(final SuffixDao suffixDao) {
		this.suffixDao = suffixDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Suffix> findAll() {
		return this.suffixDao.findAll();
	}
}