<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1>Liste des annonces</h1>
        
    
        
        <table class="table table-striped">
            <tr>
                <th>Titre</th>
                 <th>Description</th>
               
            </tr>
            <c:forEach items="${allJobs}" var="jobs">
                <tr>
                    <td>${jobs.title}</td>
                    <td>${jobs.desc}</td><!--
                    
<!--                  <td>  <a class="glyphicon glyphicon-pencil" href="<c:url value="/client/update/${client.id}" />"></a></td>
                    <td>  <a class="glyphicon glyphicon-plus" href="<c:url value="/client/job/add/${client.id}" />"></a></td>
                </tr>-->

            </c:forEach>
        </table>
    </div>
    <div class="col-md-1"></div>
</div>
