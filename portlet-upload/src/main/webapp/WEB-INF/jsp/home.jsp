<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<portlet:defineObjects/>
 
<h1><small>Image Upload</small></h1>
 
<!-- File Upload -->
 
<portlet:actionURL var="fileUploadURL">
         <portlet:param name="formAction" value="fileUpload" />     
</portlet:actionURL>

<form:form name="fileUploader" commandName="springFileVO" method="post"
          action="${fileUploadURL}"  enctype="multipart/form-data">
          
	<c:out value="${springFileVO.message}" />
	<img align="left" src="<%=renderRequest.getContextPath()%>/<c:out value="${springFileVO.serverImg}" />"/>
	<img align="left" src="<%=renderRequest.getContextPath()%>/icon.png"/> 
	<label> Select a File</label>
  	<form:input path="fileData" type="file"/>
	<button type="submit">Submit</button>    
</form:form>