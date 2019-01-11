/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.media.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.media.dao.PhotoDao;
import omis.media.domain.Photo;
import omis.media.exception.PhotoExistsException;

/**
 * Photo delegate.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Sept. 21, 2018)
 * @since OMIS 3.0
 */
public class PhotoDelegate {

	/* Data access objects. */
	private PhotoDao photoDao;
	
	/* Instance factories. */
	private InstanceFactory<Photo> photoInstanceFactory;
	
	/* Helpers. */
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	public PhotoDelegate(final PhotoDao photoDao,
			final AuditComponentRetriever auditComponentRetriever,
			final InstanceFactory<Photo> photoInstanceFactory) {
		this.photoDao = photoDao;
		this.auditComponentRetriever = auditComponentRetriever;
		this.photoInstanceFactory = photoInstanceFactory;
	}
	
	/**
	 * Creates a photo with the specified filename and date.
	 * 
	 * @param filename filename
	 * @param date date
	 * @return newly created photo
	 * @throws PhotoExistsException Thrown when a duplicate photo is found.
	 */
	public Photo create(final String filename, final Date date)
			throws PhotoExistsException{
		if (this.photoDao.find(filename) != null) {
			throw new PhotoExistsException("Photo exists.");
		}
		Photo photo = photoInstanceFactory.createInstance();
		photo.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		photo.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		photo.setDate(date);
		photo.setFilename(filename);
		return this.photoDao.makePersistent(photo);
	}
	
	/**
	 * Updates photo.
	 * 
	 * @param photo photo to update.
	 * @param photoDate photo date
	 * @param filename filename
	 * @return updated photo
	 * @throws PhotoExistsException if photo exists
	 */
	public Photo update(
		final Photo photo, Date photoDate, final String filename)
			throws PhotoExistsException {
		if (this.photoDao.findExcluding(filename, photo) != null) {
			throw new PhotoExistsException("Photo exists");
		}
		photo.setDate(photoDate);
		photo.setFilename(filename);
		return this.photoDao.makePersistent(photo);
	}
	
	/**
	 * Removes the specified photo.
	 * 
	 * @param photo photo
	 */
	public void remove(final Photo photo) {
		this.photoDao.makeTransient(photo);
	}
	
	/**
	 * Find the specified photo by file name.
	 * 
	 * @param fileName file name
	 * @return photo photo
	 */
	public Photo findByFileName(final String fileName) {
		return this.photoDao.find(fileName);
	}
}