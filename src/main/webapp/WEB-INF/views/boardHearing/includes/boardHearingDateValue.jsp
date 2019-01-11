<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:formatDate value="${boardHearingDate}" var="hearingDate" pattern="MM/dd/yyyy"/>
<c:out value="${hearingDate}"/>