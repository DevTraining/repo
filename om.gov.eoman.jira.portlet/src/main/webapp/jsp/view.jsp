<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>  

<portlet:defineObjects />
<liferay-ui:error key="comm-error" message="Error in adding Comment"/>
<liferay-ui:success key="comm-add" message="Comment Added" />
<portlet:actionURL var="addComment">
    <portlet:param name="action" value="add"></portlet:param>
</portlet:actionURL>
<h2>JIRA</h2> 

<form:form name="comment" modelAttribute="comment" method="post" action="${addComment}" >
    <table>
        <tr>
            <td>
                ID
            </td>
            <td>
            <select required="required" id="<portlet:namespace />id" name="<portlet:namespace />id">
			   <c:forEach var="issue" items="${issues}" >
			      <option value="${issue.key}">[${issue.key}]  ${issue.summary}</option>
			   </c:forEach>
			</select>
            </td>
            <td><form:errors path="id" cssClass="error" /></td>
        </tr>
        <tr>
            <td>
                Comment
            </td>
            <td><textarea name="<portlet:namespace />comment">${comment.comment}</textarea><br/>
            </td>
            <td><form:errors path="comment" cssClass="error" /></td>
        </tr>
        
        <tr>
            <td colspan="3">
                
                <input type="submit" name="submit" value="Comment" />
        </tr>
    </table>
</form:form>