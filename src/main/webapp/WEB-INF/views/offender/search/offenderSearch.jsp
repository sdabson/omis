<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Offender Search</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
	<link rel="stylesheet" type="text/css" href="resources/common/style/general.css"/>
	<style type="text/css">
	
	body {
	/* default font, size, weight and color */
	font-family: verdana, tahoma, arial;
	font-size: 8pt;
	font-weight: normal;
	color: #000000;
	margin-top: 0px;
	margin-left: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}


th,td, input,select,textarea,.bullet,.button,.formCaption {
	font-family: verdana, tahoma, arial;
	font-size: 8pt;
}
	
.formCaption {
	font-size: 12pt;
	padding: 4px;
	text-align: center;
	background-color:#F7F1AA;
}

.formSeperator {


}

.lbl {
	font-weight: bold;
	
}
	
	
.radioButton {

width:"8px";
background: url(radio.png) no-repeat;
vertical-align: center;
}

.footer {
	color: #999999;
	font-size: 7pt;
}
	
	
	
	 </style>
<script type="text/javascript">

var searchAction = "name";

function offenderSearch() {
		
		if (searchAction == "name") {
			document.forms[0].action="${pageContext.request.contextPath}/offender/search/showOffenderSearchResultsByName.html";
			document.forms[0].submit();
		}
		
		if (searchAction == "dob") {
			document.forms[0].action="${pageContext.request.contextPath}/offender/search/showOffenderSearchResultsByDateOfBirth.html";
			document.forms[0].submit();
		}
		
		if (searchAction == "offenderNumber") {
			document.forms[0].action="${pageContext.request.contextPath}/offender/showProfile.html?offenderNumber=" + document.getElementById("offenderNumber").value;
			document.forms[0].submit();
		}
		
		if (searchAction == "otherNumber") {
			document.forms[0].action="${pageContext.request.contextPath}/offender/search/showOffenderSearchResultsByOtherNumber.html";
			document.forms[0].submit();
		}

}

function addOffender() {
		
			document.forms[0].action="${pageContext.request.contextPath}/offender/search/addOffender.html";
			document.forms[0].submit();
}
		
		
function setSearchType(searchType) {

			document.getElementById("searchTypeName").checked = false;
			document.getElementById("searchTypeId").checked = false;
			document.getElementById("searchTypeDB").checked = false;
			document.getElementById("searchTypeON").checked = false;

	if (searchType == "lastName" || searchType == "firstName") {
			document.getElementById("searchTypeName").checked = true;
			searchAction = "name";
		} else if (searchType == "offenderNumber") {
			document.getElementById("searchTypeId").checked = true;
			searchAction = "offenderNumber";
		} else if (searchType == "dateOfBirth") {
			document.getElementById("searchTypeDB").checked = true;
			searchAction = "dob";
		} else if (searchType == "numValue" || searchType == "numberType" || searchType == "numberIssuer") {
			searchAction = "otherNumber";
			document.getElementById("searchTypeON").checked = true;
			
		}
		
}

function setDefaults() {


    searchAction = "name";
	document.getElementById("searchTypeName").checked = true;
	document.getElementById("lastName").focus();
	
}

</script>
  </head>
   	<body onload="setDefaults();">
    	<form>
   			<table width="100%" >
   				<tr>
   					<td class="formCaption" colspan="100%">Offender Search</td>
   				
   				</tr>
				<tr>
					<td class="radioButton"><input type="radio"  id="searchTypeName" value="NA"/></td>
					<td class="lbl">Last Name: </td>
					<td> <input type="text" name="offenderLastName"  id="lastName" onfocus="javascript:setSearchType(this.id)"/></td>
					<td class="lbl">First Name:</td>
					<td><input type="text" name="offenderFirstName" id="firstName"  onfocus="javascript:setSearchType(this.id)"/></td>
				</tr>
				<tr>
					<td class="radioButton"><input type="radio"  id="searchTypeId" value="ID"/></td>
					<td class="lbl">Offender Number: </td>
					<td> <input type="text" name="offenderNumber"  id="offenderNumber" onfocus="javascript:setSearchType(this.id)"/></td>
				</tr>
				<tr>
					<td class="radioButton"><input type="radio"  id="searchTypeDB"  value="DB"/></td>
					<td class="lbl">Date of Birth: </td>
					<td> <input type="text" name="dateOfBirth"  id="dateOfBirth" onfocus="javascript:setSearchType(this.id)"/></td>
				</tr>
				<tr>
					<td class="radioButton"><input type="radio"  id="searchTypeON"  value="ON" /></td>
					<td class="lbl">Other Number: </td>
					<td> <input type="text"  id="numValue"  onfocus="javascript:setSearchType(this.id)" /></td>
					<td class="lbl">Number Type:</td>
					<td>
					  <select id="numberType" name="numberType" onfocus="javascript:setSearchType(this.id)"> 
					      <option value="">...</option>
   				 		<c:forEach items="${numberTypes}" var="numberType"> 
       						<option value="${numberType.code}">${numberType.description}</option> 
  						</c:forEach> 
					  </select> 
					  </td>
					<td class="lbl">Government Source</td>
					<td>
					  <select id="numberIssuer" name="numberIssuer" onfocus="javascript:setSearchType(this.id)"> 
					      <option value="">...</option>
   				 		<c:forEach items="${numberIssuers}" var="numberIssuer"> 
       						<option value="${numberIssuer.code}">${numberIssuer.description}</option> 
  						</c:forEach> 
					  </select> 
					</td>
					</tr>
					<tr>

				</tr>
			
				<tr>
					<td style="width:2%">&nbsp;</td>
					<td><input type="button" value="Add Offender" onclick="javascript:addOffender()"/></td>
					<td><input type="submit" value="Clear"/></td>
					<td><input type="button" value="Search" onclick="javascript:offenderSearch()"/></td>
				</tr>
			</table>
			</form>
  			<div id="searchResults"> 
  			<table width="100%">
  			<tr>
   					<td class="formCaption"  colspan="100%">Offender Search Results</td>
   				
   				</tr>
   			</table>
   			</div>
  		
  		
    </body>
</html>
