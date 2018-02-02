/*
 *  OMIS - Offender Management Information System
 *  Copyright (C) 2011 - 2017 State of Montana
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.chronologicalnote.service.impl;

import java.util.Date;
import java.util.List;

import omis.chronologicalnote.domain.ChronologicalNote;
import omis.chronologicalnote.domain.ChronologicalNoteCategory;
import omis.chronologicalnote.exception.ChronologicalNoteCategoryAssociationExistsException;
import omis.chronologicalnote.exception.ChronologicalNoteExistsException;
import omis.chronologicalnote.service.ChronologicalNoteService;
import omis.chronologicalnote.service.delegate.ChronologicalNoteCategoryAssociationDelegate;
import omis.chronologicalnote.service.delegate.ChronologicalNoteCategoryDelegate;
import omis.chronologicalnote.service.delegate.ChronologicalNoteDelegate;
import omis.offender.domain.Offender;

/**
 * Chronological note service implmentation.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @verison 0.1.0 (February 1, 2018)
 * @since OMIS 3.0
 */
public class ChronologicalNoteServiceImpl 
implements ChronologicalNoteService {
	
	/* Delegates */
	
	private ChronologicalNoteDelegate chronologicalNoteDelegate;
	private ChronologicalNoteCategoryAssociationDelegate chronologicalNoteCategoryAssociationDelegate;
	private ChronologicalNoteCategoryDelegate chronologicalNoteCategoryDelegate;
	
	/**
	 * Instantiates a chronological note service with the specified delegates.
	 * 
	 * @param chronologicalNoteDelegate chronological note delegate
	 * @param chronologicalNoteCategoryAssociationDelegate chronological note category association delegate
	 * @param chronologicalNoteCategoryDelegate chronological note category delegate
	 */
	public ChronologicalNoteServiceImpl(final ChronologicalNoteDelegate chronologicalNoteDelegate,
			final ChronologicalNoteCategoryAssociationDelegate chronologicalNoteCategoryAssociationDelegate,
			final ChronologicalNoteCategoryDelegate chronologicalNoteCategoryDelegate) {
		this.chronologicalNoteDelegate = chronologicalNoteDelegate;
		this.chronologicalNoteCategoryAssociationDelegate = chronologicalNoteCategoryAssociationDelegate;
		this.chronologicalNoteCategoryDelegate = chronologicalNoteCategoryDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public ChronologicalNote create(final Date date, final Offender offender, final String narrative)
			throws ChronologicalNoteExistsException {
		return this.chronologicalNoteDelegate.create(date, offender, narrative);
	}

	/** {@inheritDoc} */
	@Override
	public ChronologicalNote update(final ChronologicalNote note, final Date date, final String narrative)
			throws ChronologicalNoteExistsException {
		return this.chronologicalNoteDelegate.update(note, date, narrative);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final ChronologicalNote note) {
		this.chronologicalNoteDelegate.remove(note);
	}

	/** {@inheritDoc} */
	@Override
	public void associateCategory(final ChronologicalNote note, final ChronologicalNoteCategory category) {
		try {
			this.chronologicalNoteCategoryAssociationDelegate.create(note, category);
		} catch (ChronologicalNoteCategoryAssociationExistsException exception) {
			throw new IllegalArgumentException("No association exists between specified note and category");
		}
	}

	/** {@inheritDoc} */
	@Override
	public void dissociateCategory(final ChronologicalNote note, final ChronologicalNoteCategory category) {
		this.chronologicalNoteCategoryAssociationDelegate.remove(note, category);
	}

	/** {@inheritDoc} */
	@Override
	public List<ChronologicalNoteCategory> findCategories() {
		return this.chronologicalNoteCategoryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<ChronologicalNoteCategory> findAssociatedCategories(final ChronologicalNote note) {
		return this.chronologicalNoteCategoryAssociationDelegate.findAssociatedCategories(note);
	}
}
