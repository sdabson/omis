package omis.io;

/**
 * Generates a file name.
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
public interface FilenameGenerator {

	/**
	 * Sets the prefix.
	 * @param prefix prefix
	 */
	void setPrefix(String prefix);
	
	/**
	 * Sets the suffix.
	 * @param suffix suffix
	 */
	void setSuffix(String suffix);
	
	/**
	 * Sets the extension.
	 * @param extension extension
	 */
	void setExtension(String extension);
	
	/**
	 * Generates filename.
	 * @return filename
	 */
	String generate();
}