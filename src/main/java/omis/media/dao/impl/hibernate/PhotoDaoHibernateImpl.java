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
package omis.media.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.media.dao.PhotoDao;
import omis.media.domain.Photo;

/**
 * Hibernate entity configurable data access object for photos.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public class PhotoDaoHibernateImpl
		extends GenericHibernateDaoImpl<Photo>
		implements PhotoDao {
	
	/* Query names. */
	
	private static final String FIND_PHOTO_QUERY_NAME = "findPhotoByFilename";
	
	private static final String FIND_NEXT_PHOTO_FILENAME
		= "findNextPhotoFilename";
	
	private static final String FIND_PHOTO_EXCLUDING_QUERY_NAME
		= "findPhotoByFilenameExcluding";
	
	/* Parameter names. */
	
	private static final String FILENAME_PARAM_NAME = "filename";

	private static final String EXCLUDED_PHOTOS_PARAM_NAME
		= "excludedPhotos";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * photos with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PhotoDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public String findNextFilenameDistinguisher() {
		return getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_NEXT_PHOTO_FILENAME)
				.uniqueResult().toString();
	}

	/** {@inheritDoc} */
	@Override
	public Photo find(final String filename) {
		Photo photo = (Photo) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PHOTO_QUERY_NAME)
				.setParameter(FILENAME_PARAM_NAME, filename)
				.uniqueResult();
		return photo;
	}

	/** {@inheritDoc} */
	@Override
	public Photo findExcluding(final String filename,
			final Photo... excludedPhotos) {
		Photo photo = (Photo) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PHOTO_EXCLUDING_QUERY_NAME)
				.setParameter(FILENAME_PARAM_NAME, filename)
				.setParameterList(EXCLUDED_PHOTOS_PARAM_NAME, excludedPhotos)
				.uniqueResult();
		return photo;
	}
}