<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>

    <c:choose>
        <c:when test="${res != '' || res != null } ">
            Erreur  : ${res}
        </c:when>
        <c:when test="${res == null } ">
         
            Mail envoyer
        </c:when>
        <c:otherwise>
            <form method="POST">
                <input name="send" type="checkbox" />

                <c:choose>
                    <c:when test="${candidat != null}">
                        Voulez vous envoyer le mail de rappel de statut ${candidat.name} ? 
                    </c:when>
                    <c:otherwise>
                        Voulez vous envoyer le mail de rappel de statut à tous les candidats ? 
                    </c:otherwise>
                </c:choose>
                <br>
                <br>
                <input type="submit" value="Valider"/>
            </form>

            <c:forEach items="${missing}" var="m">
                ${m.name}        
            </c:forEach>
        </c:otherwise>
    </c:choose>





</div>