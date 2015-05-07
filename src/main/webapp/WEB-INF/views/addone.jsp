<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>  
        
         <h1>ajoutez un candidat</h1>
         
         <a href="/site" >Home</a>
        <c:choose>
            <c:when test="${succes == true}">
                Submit 
            </c:when>
            <c:otherwise>
                 <div id="container">
            <div id="form">
                <form method="post">
                    <p>
                        <label>Nom</label>
                        <input type="text" name="name"/>
                    </p>
                    <p>
                        <label>Prémon</label>
                        <input type="text" name="surname"/>
                    </p>
                    <p>
                        <label>Adresse Email</label>
                        <input type="text" name="email"/>
                    </p>
                    <p>
                        <label>Date de naissance</label>
                        <input type="datetime" name="birthDate"/>
                    </p>
                    <p>
                        <label>Adresse postale</label>
                        <input type="text" name="adress"/>
                    </p>
                    <p>
                        <label>Ville</label>
                        <input type="text" name="city"/>
                    </p>
                    <input type="submit" value="Envoyer">
                </form>

            </div>  
        </div>
            </c:otherwise>
        </c:choose>
      </body>
</html>
