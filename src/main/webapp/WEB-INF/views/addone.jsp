<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1>ajoutez un candidat</h1>

<c:choose>
    <c:when test="${succes == true}">
        Submit 
    </c:when>
    <c:when test="${errorMsg == true}">

        Candidats déja présent dans la base!
    </c:when>    
    <c:otherwise>
        <div id="container">
            <div id="form">
                <form method="post"  enctype="multipart/form-data">

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
                    <p>
                        <label>Téléphone</label>
                        <input type="text" name="telephone"/>
                    </p>
                    <p>
                        <label>Cv</label>
                        <input type="file" name="cv"/>
                    </p>

                    <input type="submit" value="Envoyer">
                </form>
            </div>  
        </div>
    </c:otherwise>
</c:choose>

