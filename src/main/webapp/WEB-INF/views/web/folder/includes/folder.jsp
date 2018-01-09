<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="folder">
<a class="label" href="#"><c:out value="${folder.label}"/></a>
<div class="folderContent">
<jsp:include page="${folder.page}"/>
</div>
<div class="subfolders">
Subfolders go here.
</div>
</div>
