<%@ page language="java" contentType="text/xml; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	isErrorPage="true"
	autoFlush="false"
	%>
<%@page import="org.springframework.web.multipart.MaxUploadSizeExceededException"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<result> 
<status>failure</status> 
<id></id> 
<width></width>
<height></height> 
<message>Maximum upload size of :<%=((MaxUploadSizeExceededException)exception).getMaxUploadSize()%>was exceeded.</message>
</result>
<% 
	response.setStatus(200);
	response.flushBuffer();
%>