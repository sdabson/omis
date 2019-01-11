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
package omis.offenseterm.web.controller.delegate.testng;

import org.testng.annotations.Test;

import omis.offenseterm.web.controller.delegate.impl.OffenseItemSortDelegateImpl;

/**
 * Tests for sort delegate for offense items.
 * 
 * <p>Disabled due to bug in delegate implementation. This test class will
 * eventually be removed with the implementation.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jun 4, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"offenseTerm", "web"}, enabled = false)
public class OffenseItemSortDelegateImplTests
		extends AbstractOffenseItemSortDelegateTests {

	/**
	 * Instantiates tests for sort delegate for offense items.
	 */
	public OffenseItemSortDelegateImplTests() {
		this.setSortDelegate(new OffenseItemSortDelegateImpl());
	}
}