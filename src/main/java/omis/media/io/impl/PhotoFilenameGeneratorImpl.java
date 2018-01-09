package omis.media.io.impl;

import omis.io.FilenameGenerator;
import omis.io.impl.AbstractFilenameGenerator;
import omis.media.dao.PhotoDao;

/**
 * Generator for photo filenames.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
public class PhotoFilenameGeneratorImpl
		extends AbstractFilenameGenerator
		implements FilenameGenerator {

	private final PhotoDao photoDao;

	/**
	 * Instantiates a simple implementation of photo filename generator.
	 * 
	 * @param photoDao data access object for photos
	 */
	public PhotoFilenameGeneratorImpl(final PhotoDao photoDao) {
		this.photoDao = photoDao;
	}
	
	/** {@inheritDoc} */
	@Override
	protected String getDistinguisher() {
		return this.photoDao.findNextFilenameDistinguisher();
	}
}