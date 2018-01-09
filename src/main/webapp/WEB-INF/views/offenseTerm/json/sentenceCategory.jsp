<%--
  - Sentence category.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
{
	"id": ${sentenceCategory.id},
	"name": "<c:out value='${sentenceCategory.name}'/>",
	"prisonRequirement": "<c:out value='${sentenceCategory.prisonRequirement.name}'/>",
	"probationRequirement": "<c:out value='${sentenceCategory.probationRequirement.name}'/>",
	"deferredRequirement": "<c:out value='${sentenceCategory.deferredRequirement.name}'/>"
}