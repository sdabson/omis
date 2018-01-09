package omis.document.io.impl;

import omis.document.dao.DocumentDao;
import omis.io.FilenameGenerator;
import omis.io.impl.AbstractFilenameGenerator;

/** Generator for document filenames.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 23, 2015)
 * @since OMIS 3.0 */
public class DocumentFilenameGenerator 
	extends AbstractFilenameGenerator 
	implements FilenameGenerator {
	private final DocumentDao documentDao;
	
	/** Constructor.
	 * @param documentDao - document dao. */
	public DocumentFilenameGenerator(final DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	/** {@inheritDoc} */
	@Override
	public String getDistinguisher() {
		return this.documentDao.findNextDocumentFilenameDistinguisher();
	}
}
