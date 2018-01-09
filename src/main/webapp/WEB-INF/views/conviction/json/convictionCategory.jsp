<%--
  - Conviction category for convictions.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
{
	"id": ${convictionCategory.id},
	"name": "<c:out value='${convictionCategory.name}'/>",
	"prison": ${convictionCategory.prison},
	"probation": ${convictionCategory.probation},
	"deferred": ${convictionCategory.deferred}
}
