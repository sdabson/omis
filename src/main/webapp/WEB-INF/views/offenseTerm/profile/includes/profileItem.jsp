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
 - Author: Josh Divine
 - Version: 0.1.0 (May 17, 2017)
 - Since: OMIS 3.0 
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.offenseterm.msgs.offenseTerm">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/offenseTerm/list.html?person=${offenderSummary.id}">
			<span>
    			<fmt:message key="offenseTermCountLabel">
    				<fmt:param value="${offenseTermCount}"/>
    			</fmt:message>
    		</span>
    	</a>
    </div>
</fmt:bundle>