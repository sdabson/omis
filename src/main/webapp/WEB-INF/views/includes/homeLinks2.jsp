<%--
 - Author: Stephen Abson
 - Author: Ryan Johns
 - Version: 0.1.1 (Nov 18, 2015)
 - Since: OMIS 3.0
 --%>
<script>

//home.js
function setUpIndex() {
	loadOffenderProfilePhotosOnDemand(document.getElementById("caseOffenderResults"));	
	
	var contents = document.getElementsByClassName("content");
	
	for(var x=0; x < contents.length; x++) {
		alert("here");
		$(contents[x]).scrollContainer({scrollX:true, 
			scrollY:false, 
			elementCss:{
				}});
	}
}
</script>
<style>
//home.css

/*
 * Home styling.
 *
 * Author: Stephen Abson
 * Author: Ryan Johns
 */

span.contentHeading {
	display: block;
    font-family: "century palantino";
    font-size: 17pt;
    letter-spacing: 1px;
    margin-bottom: 0;
    padding-bottom: 0;
    text-align: center;
    vertical-align: bottom;
    width: 100%;
	
}

ul.content {
	margin: 2px;
	padding: 0px;
	margin-top: 0px;
	display:inline-block;
	list-style-type: none;
	box-shadow: 0 4px 2px -2px gray;
    height: 211px;
    width: 97%;
}

ul.content > li {
	padding: 5px;
	margin: 4px;
	display:inline-block;
}

ul.content > li {
	list-style: none;
}

ul.content li.last {
	/*border-bottom: none;*/
}

ul.content #caseLoadItem {
	height: 200px;
}

ul.content #linksItem {
	height: 25px;
}

.searchResultsContainer {
    margin: 0;
    width: 99%;
    
}

.searchResultsContainer > .dataTables_wrapper {
	height:305px;
}


.searchResultsContainer > span {
    display: inline-block;
    float: right;
    height: 200px;
    overflow-y: scroll;
    width: 49%;
}

span.banner {
  position: relative;
  width: 125px;
  height: 175px;
  background: #A3BAE0;
  margin: 0px;
  display: inline-block;
}

span.banner img, span.banner input {
	position:relative;
	padding: 0px;
}

span.banner img {
	left:17px;
	top: 15px;
}

span.banner input {
	top: 60px;
	left: 5px;
}

span.banner span.label {
    font-style: normal;
    font-weight: bold;
    padding-top: 20px;
    position: absolute;
    bottom: 0px;
    left: 0px;
    text-align: center;
    width: 100%;
    color: #2A4E81;
    font-family: "verdana arial geneva ";
    font-size: 11pt;
    text-decoration: none;
}

span.banner:before {
  content: "";
  display: block;
  position: absolute;
  top: 175px;
  width: 100%;
  height: 21px;
  background:
  linear-gradient(45deg, #A3BAE0 33.333%, transparent 33.333%, transparent 66.667%, #A3BAE0 66.667%), linear-gradient(135deg, #A3BAE0 33.333%, transparent 33.333%, transparent 66.667%, #A3BAE0 66.667%);
  background-size: 18px 40px;
}
</style>


<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.offender.msgs.offender" var="offenderBundle"/>
<fmt:bundle basename="omis.msgs.homeLinks">
		<span class="contentHeading">Search</span>
		<ul class="content">
			
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/addressSearch.png" height="90" width="90"/>
						<span class="label">Address Search</span>
					</span>
				</a>
			</li>
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/offenderSearch.png" height="90" width="90"/>
						<span class="label">Offender Search</span>
					</span>
				</a>
			</li>
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/staffSearch.png" height="90" width="90"/>
						<span class="label">Staff Search</span>
					</span>
				</a>
			</li>
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/serviceProviderSearch.png" height="90" width="90"/>
						<span class="label">Service Provider Search</span>
					</span>
				</a>
			</li>
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/employerSearch.png" height="90" width="90"/>
						<span class="label">Employer Search</span>
					</span>
				</a>
			</li>
			
		</ul>
		<span class="contentHeading">Caseload</span>
		<ul class="content">
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/administrativeCaseloads.png" height="90" width="90"/>
						<span class="label">Administrative Caseloads</span>
					</span>
				</a>
			</li>
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/supervisoryCaseloads.png" height="90" width="90"/>
						<span class="label">Supervisory Caseloads</span>
					</span>
				</a>
			</li>
		</ul>
		<span class="contentHeading">Work Centers</span>
		<ul class="content">
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/incidentManagementSystem.png" height="90" width="90"/>
						<span class="label">Incident Management System</span>
					</span>
				</a>
			</li>
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/legalRecordsCenter.png" height="90" width="90"/>
						<span class="label">Offender Legal Records Center</span>
					</span>
				</a>
			</li>
			<li>
				<a href="#">
					<span class="banner">
						<img src="${pageContext.request.contextPath}/resources/common/images/offenderPlacementCenter.png" height="90" width="90"/>
						<span class="label">Offender Placement Center</span>
					</span>
				</a>
			</li>
		</ul>
</fmt:bundle>