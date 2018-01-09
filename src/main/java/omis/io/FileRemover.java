package omis.io;

/**
 * File remover.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 5, 2014)
 * @since OMIS 3.0
 */
public interface FileRemover {

	/**
	 * Removes a file.
	 * 
	 * @param filename file name
	 * @return whether file was removed
	 */
	boolean remove(String filename);
}