<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1>Liste des clients</h1>
        
       <a class="glyphicon glyphicon-plus" href="<c:url value="/client/add" />"></a>
        
        <table class="table table-striped">
            <tr>
                <th>Nom</th>
                <th>Email</th>
                <th>Téléphone</th>
                <th>Mise à jour</th>
                <th>Annonces</th>
            </tr>
            <c:forEach items="${client}" var="client">
                <tr>
                    <td>${client.name}</td>
                    <td>${client.email}</td>
                    <td>${client.phone}</td>
                    <td>  <a class="glyphicon glyphicon-pencil" href="<c:url value="/client/update/${client.id}" />"></a></td>
                    <td>  <a class="glyphicon glyphicon-plus" href="<c:url value="/client/job/add/${client.id}" />"></a></td>
                    <td>  <a class="glyphicon glyphicon-plus" href="<c:url value="/client/job/all/${client.id}" />"></a></td>
                </tr>

            </c:forEach>
        </table>
    </div>
    <div class="col-md-1"></div>
</div>
