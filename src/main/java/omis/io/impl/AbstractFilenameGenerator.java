package omis.io.impl;

import omis.io.FilenameGenerator;

/**
 * Abstract implementation of filename generator.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
public abstract class AbstractFilenameGenerator
		implements FilenameGenerator {

	private String prefix;
	
	private String suffix;

	private String extension;

	/** {@inheritDoc} */
	@Override
	public void setPrefix(final String prefix) {
		this.prefix = prefix;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setExtension(final String extension) {
		this.extension = extension;
	}
	
	/** {@inheritDoc} */
	@Override
	public String generate() {
		return (this.prefix != null ? this.prefix : "")
				+ this.getDistinguisher()
				+ (this.suffix != null ? this.suffix : "")
				+ (this.extension != null ? "." + this.extension : "");
	}
	
	/**
	 * Returns a value used to distinguish files.
	 * @return value used to distinguish files
	 */
	protected abstract String getDistinguisher();
}