<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>  

<portlet:defineObjects />
<liferay-ui:error key="username-isnot-match" message="Username should have from 10 to 15 with One Capital"></liferay-ui:error>  
<liferay-ui:error key="age-isnot-match" message="Age is from (21 to 50)"></liferay-ui:error>  
<html>
<style type="text/css">
    .data, .data td {
        border-collapse: collapse;
        width: 100%;
        border: 1px solid #aaa;
        margin: 2px;
        padding: 2px;
    }
    .data th {
        font-weight: bold;
        background-color: #5C82FF;
        color: white;
    }
</style>
<h2>User Manager</h2>
<portlet:actionURL var="addUserURL">
    <portlet:param name="action" value="add"></portlet:param>
</portlet:actionURL>
<portlet:actionURL var="deleteUserURL">
    <portlet:param name="action" value="delete"></portlet:param>
</portlet:actionURL>
<portlet:actionURL var="updateUserURL">
    <portlet:param name="action" value="update"></portlet:param>
</portlet:actionURL>
<form:form name="users" modelAttribute="users" method="post" action="${addUserURL}" >
    <table>
        <tr>
            <td>
                Username
            </td>
            <td><input type="text" name="<portlet:namespace />username" value="${users.username}"/></td>
            <td><form:errors path="username" cssClass="error" /></td>
          
        </tr>
        <tr>
            <td>
                Age
            </td>
            <td><input type="text" name="<portlet:namespace />age" value="${users.age}"/></td>
            <td><form:errors path="age" cssClass="error" /></td>
        </tr>
        
        <tr>
            <td colspan="3">
                
                <input type="submit" value="addUser"/>
        </tr>
    </table>
</form:form>


<h3>Users</h3>
<c:if test="${!empty listUsers}">
    <table class="data">
        <tr>
            <th>Name</th>
            <th>Age</th>
            <th>#</th>
            <th>#</th>
        </tr>
        <c:forEach items="${listUsers}" var="users">
            <tr>
                <td>${users.username}</td>
                <td>${users.age}</td>
                <td><a href="${deleteUserURL}&<portlet:namespace />username=${users.username}">delete</a></td>
                <td><a href="${updateUserURL}&<portlet:namespace />username=${users.username}">update</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


</body>
</html>
