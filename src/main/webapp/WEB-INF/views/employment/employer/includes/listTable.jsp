<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Phone Number</th>
			<th>Address</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="employer" items="${employerList}">
			<tr>
				<td>${employer.id}</td>
				<td>${employer.name}</td>
				<td>${employer.telephoneNumber.areaCode} ${employer.telephoneNumber.number}</td>
				<td>${employer.address.value} ${employer.address.city} ${empty employer.address.state ? employer.address.state.name.concat(", ") : ""} ${employer.address.zipCode} </td>
			</tr>
		</c:forEach>
	</tbody>
</table>