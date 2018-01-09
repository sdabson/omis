<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Feb 9, 2015)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<c:forEach items="${facilities}" var="facility">
	<option value="${facility.id}"><c:out value="${facility.facility.name}"/></option>
</c:forEach>