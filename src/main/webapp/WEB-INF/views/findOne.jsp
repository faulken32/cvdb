<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>  
        <h1>Candidats</h1>
        
        
        <div id="container">
            
          
          
           <c:out value="${candidate.name}"/>
                      
           <div>${candidate.adress}</div>
           <div>${candidate.city}</div>
          
           <div>${candidate.email}</div>
           <div>${candidate.birthDate}</div>
           
           
        </div>
    </body>
</html>
    