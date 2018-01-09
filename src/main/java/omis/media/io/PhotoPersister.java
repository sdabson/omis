package omis.media.io;

import java.io.File;

import omis.media.domain.Photo;

/**
 * Persists photo data.
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
public interface PhotoPersister {

	/**
	 * Persists specified photo data for specified photo.
	 * @param photo photo
	 * @param data photo data
	 * @return file
	 * @throws IllegalArgumentException if the filename of the photo is not
	 * valid (for instance, if it is a directory)
	 */
	File persist(Photo photo, byte[] data);
}