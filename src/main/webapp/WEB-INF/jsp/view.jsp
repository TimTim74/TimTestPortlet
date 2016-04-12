<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Results</h2>
Looking with <strong>metaData.getValue("com.ibm.portal.Hidden")</strong><br />
Found value for "com.ibm.portal.Hidden" : ${paramHidden}<br />
<br />
Now print instead<br />
<br />
<h3>All aggregated metadata found</h3>
<table>
    <thead>
    <th width="50%">Key</th>
    <th>Value</th>
    </thead>
    <tbody>
    <c:forEach var="metaItem" items="${allMetaDataAggr}">
        <tr><td>${metaItem.key}</td><td>${metaItem.value}</td></tr>
    </c:forEach>
    </tbody>
</table>

<h3>All non-aggregated metadata found</h3>
<table>
    <thead>
    <th width="50%">Key</th>
    <th>Value</th>
    </thead>
    <tbody>
    <c:forEach var="metaItem" items="${allMetaDataNonAggr}">
        <tr><td>${metaItem.key}</td><td>${metaItem.value}</td></tr>
    </c:forEach>
    </tbody>
</table>