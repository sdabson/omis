<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 --%>

<%--
  - Displays docket.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.docket.msgs.docket" var="docketBundle"/>
<fmt:setBundle basename="omis.court.msgs.court" var="courtBundle"/>
<p class="information">
	<span class="fieldLabel"><fmt:message key="courtNameLabel" bundle="${courtBundle}"/></span>
	<span class="fieldValue"><c:out value="${docket.court.name}"/></span>
</p>
<p class="information">
	<span class="fieldLabel"><fmt:message key="docketValueLabel" bundle="${docketBundle}"/></span>
	<span class="fieldValue"><c:out value="${docket.value}"/></span>
</p>