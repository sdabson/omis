<%@ page contentType="text/css" %>
/*
 * Font styling.
 *
 * <p>All font sizes should be in points (pt). The font family should only be
 * set for the body (all elements should inherit this font family). 
 *
 * Author: Stephen Abson, Ryan Johns
 * Version: 0.1.0 (Feb 22, 2013)
 * Since: OMIS 3.0
 */

/* Default font. */
body {
	font-family: Tahoma, Verdana, Helvectica, Arial;
	font-size: 9pt;
}

/* Headers. */
h1 {
	font-size: 14pt;
}
h2 {
	font-size: 13pt;
}
h3 {
	font-size: 12pt;
}
h4 {
	font-size: 11pt;
}
h5 {
	font-size: 10pt;
}

/* Links */
a {
	text-decoration: none;
}
a:hover {
	text-decoration: underline;
}

.moduleLink > a:hover, .identifyingInformation a:hover  {
	text-decoration: none;
}

/* Tabs. */
.tabbed_box h4 {
	font-size: 23px;
	letter-spacing: -1px;
}
.tabbed_box h4 small {
	letter-spacing: 0px;
	font-size: 9pt;
	font-weight: normal;
}
ul.tabs li span a, span.tab a {
	font-size: 9pt;
	font-weight: bold;
	text-decoration: none;
}
.content ul li {
	font-size: 13px;
}
.content ul li span a, span.tab a {
	text-decoration: none;
}
.content ul li span a small, span.tab a small {
	text-transform: uppercase;
	font-size: 9px;
}

/* Form. */
form.editForm {
	text-decoration: none;
}


/* Form labels and values. */
form.editForm .formLabel {
  font-size: 10pt;
  font-style: italic;
  font-weight: 600;
}

form.editForm span.error, form.searchForm span.error {
	font-size: 76%;
	text-transform: uppercase;
	font-weight: bold;	 
}


/* Index/home/container page. */
div.productName {
	letter-spacing: 50px;
	font-size: 16pt;
  font-weight: lighter;
}
span#userDetails {
	font-size: 10pt;
}

span.fieldLabel {
	font-weight: bold;
}

/* Informational or suggested input */

.defaultDisplayText {
	font-size:12px;	
}

/* dataTable filter */
.filterLabel {
	font-family: Tahoma, Verdana, Helvectica, Arial;
 	font-size: 10pt;
 	font-weight: normal;
}

/* Identifying information */
.identifyingInformation {
	font-size: 16px;
}

/* Application toolbar. */

#applicationToolbar {
	font-weight: bold;
}

/* Data tables. */

table.dataTable tbody tr td.header {
	font-weight: bold;
}

table.listTable tbody tr.activeRecord {
	font-weight: bold;
}

/* Header information. */

div.summaryHeader span.headerLabel {
	font-weight: bold;
}

/* List tables. */

table.listTable caption {
	font-family: Tahoma,Verdana,Helvectica,Arial;
	font-size: 16px;
	font-weight: bold;
}

span.header {
	font-size: 16px;
	font-weight: bold;
}

.businessMessageKey {
	color: #FF0000;
	font-size: 17px;
	max-width: 50%;
	font-style: italic;
}

.businessViolationTitle{
	font-size: 16px;
	font-weight:bold;
}