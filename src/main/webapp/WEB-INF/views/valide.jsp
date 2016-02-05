


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">

        <div class="panel panel-default">
            <div class="panel-body">
                <h1 class="">Liste des announces à Valider</h1>       
            </div>
        </div>




        <table class="table table-striped">
            <!--<input id="expId" type="hidden" value="${experience.id}"/>-->
            <tr>
                <th>Titre</th>                
                <th>Nom du client</th>                              
            </tr>
            <c:forEach items="${jobsList}" var="a">
                <tr>
                    <td><a href="<c:url value="/client/job/update/${a.id}/${a.partialsClients.id}" />">${a.title}</a></td>                   
                    <td>${a.partialsClients.name}</td>                                       
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-1"></div>
</div>

