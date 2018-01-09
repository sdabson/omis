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
package omis.task.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateParameterValue;

/**
 * Data access object for task template parameter values.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
public interface TaskTemplateParameterValueDao
		extends GenericDao<TaskTemplateParameterValue> {

	/**
	 * Returns parameter values by template.
	 * 
	 * @param template template
	 * @return parameter values by template
	 */
	List<TaskTemplateParameterValue> findByTemplate(TaskTemplate template);
	
	/**
	 * Returns task template parameter value.
	 * 
	 * @param template template
	 * @param order order
	 * @param typeName type name
	 * @return task template parameter value
	 */
	TaskTemplateParameterValue find(
			TaskTemplate template, Short order, String typeName);
	
	/**
	 * Returns task template parameter value.
	 * 
	 * @param template template
	 * @param order order
	 * @param typeName type name
	 * @param excludedParameterValues excluded parameter values
	 * @return task template parameter value
	 */
	TaskTemplateParameterValue findExcluding(
			TaskTemplate template, Short order, String typeName,
			TaskTemplateParameterValue... excludedParameterValues);
}