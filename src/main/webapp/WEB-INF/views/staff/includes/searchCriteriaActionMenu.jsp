<!-- 
 - Author: Sheronda Vaughn
 - Author: Sierra Haynes
 - Version: 0.1.0 (Mar 28, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.staff.msgs.staff">
	<ul>
		<li class="taskLinkItem">
			<a class="homeLink" href="${pageContext.request.contextPath}/home.html">				
				<fmt:message key="homeLinkLabel"/>
			</a>
		</li>	
		<sec:authorize access="hasRole('STAFF_ASSIGNMENT_CREATE') or hasRole('ADMIN')">
		<li class="taskLinkItem">
			<a class="createLink" href="${pageContext.request.contextPath}/staffAssignment/create.html">				
				<fmt:message key="createLinkLabel"/>
			</a>
		</li>	
		</sec:authorize>
		<sec:authorize access="hasRole('STAFF_ASSIGNMENT_LIST') or hasRole('ADMIN')">
			<li>
				<omis:reportPro reportPath="/StaffAssignments/Staff_Search" decorate="no" title="" className="newTab reportLink"><fmt:message key="staffSearchReportLinkLabel"/></omis:reportPro>
			</li>
		</sec:authorize>
		<li>
			<a class="helpLink">
				<span class="visibleLinkLabel">
					<fmt:message key="searchHelpLabel"/>
				</span>
			</a>
		</li>	
	</ul>
</fmt:bundle>