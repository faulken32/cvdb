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
        </ul>
    </div>
</nav>