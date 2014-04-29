<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>  

<portlet:defineObjects />


Welcome to <b>zabbix</b> Monitoring System.

<p>RAM: ${ram}</p> 
<p> Disk: ${disk}</p>
<p> CPU: ${cpu}</p>
<p> VMS: ${vms}</p>
