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
 - Author: Ryan Johns
 - Author: Josh Divine
 - Version: 0.1.1 (Aug 8, 2018)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.courtdocument.msgs.document">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/courtCase/document/list.html?offender=${offenderSummary.id}">
			<span>
				<fmt:message key="courtDocumentCountLabel">
					<fmt:param value="${courtDocumentCount}"/>
				</fmt:message>
			</span>
		</a>
	</div>
</fmt:bundle>