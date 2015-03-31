<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>  
        <h1>Liste des candidats</h1>
        
        
        <div id="container">
            
          
            <c:out value="Bonjour" /><br/>
              <c:forEach var="var" items="${candidate}">
                  <div>${var.name}</div>
            </c:forEach>
           <div class="seedstarterlist">
                <div></div>
            </div>
            
            <div></div>
        </div>
    </body>
</html>
    