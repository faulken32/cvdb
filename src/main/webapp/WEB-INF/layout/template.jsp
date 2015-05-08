<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
        <title><tiles:insertAttribute name="title"/> </title>
        
<!--        <link rel="stylesheet" href="<c:url value="/resources/main.css"/>" />-->
    </head>
    
      <body>
	<tiles:insertAttribute name="header"/>
        <tiles:insertAttribute name="nav"/>
	
	<div class="container">
            <tiles:insertAttribute name="body" />
	</div>
	
</body>
</html>

