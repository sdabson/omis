package omis.media.io;

import omis.media.domain.Photo;

/**
 * Given a photo, retrieves the photo data.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public interface PhotoRetriever {
	
	/**
	 * Retrieve the photo data for the photo.
	 * 
	 * @param photo photo the data of which to retrieve; retrieve the photo
	 * at the file named {@code notFoundFilename} if not found; if
	 * {@code notFoundFilename} is not set or cannot be found, return an empty
	 * byte array
	 * @return photo from filename or empty byte array if no suitable
	 * file can be found
	 */
	byte[] retrieve(Photo photo);
	
	/**
	 * Retrieve the photo data for the photo.
	 * 
	 * @param filename filename of the photo the data of which to retrieve;
	 * retrieve the photo at the file named {@code notFoundFilename} if not
	 * found; if {@code notFoundFilename} is not set or cannot be found, return
	 * an empty byte array
	 * @return photo from filename or empty byte array if no suitable
	 * file can be found
	 */
	byte[] retrieve(String filename);
}