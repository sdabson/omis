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
package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.LabWorkCategory;

/**
 * Data access object for lab work category.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Nov. 8, 2018)
 * @since OMIS 3.0
 */
public interface LabWorkCategoryDao 
	extends GenericDao<LabWorkCategory> {
	 /**
	 * Returns the existing lab work category.
	 * 
	 * @param name name
	 * 
	 * @return lab work;
	 */	
	 LabWorkCategory findExisting(String name);
	//No additional functionality.
}