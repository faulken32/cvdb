<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<nav class="navbar navbar-default">
    <div class="container-fluid">

        <ul class="nav navbar-nav">
            <li>
                <a href="<spring:url value="/"></spring:url>">
                        Home    
                    </a>
                </li>
                <li>
                    <a href="<spring:url value="/candidat"></spring:url>">
                        Ajoutez un candidat    
                    </a>
                </li>
                <li>
                    <a href="<spring:url value="/client/all"></spring:url>">
                        Clients    
                    </a>
                </li>
                <li>
                    <a href="<spring:url value="/res"></spring:url>">
                        Résultats    
                    </a>
                </li>

                <li>
                    <a href="<spring:url value="/power/res"></spring:url>">
                        Power Search
                    </a>
                </li>
                <li>
                    <a href="<spring:url value="/mail/sendToAll"></spring:url>">
                        Mail
                    </a>
                </li>
                <li>
                    <a href="<spring:url value="/valide"></spring:url>">
                    Annonces à valider
                </a>
            </li>
        </ul>
    </div>
</nav>