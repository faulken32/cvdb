<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1>Liste des annonces</h1>

        <table class="table table-striped">
            <tr>
                <th>Titre</th>
                <th>Description</th>
                <th>Critères de recherche</th>
                <th>Modifiez</th>

            </tr>
            <c:forEach items="${allJobs}" var="jobs">
                <tr>
                    <td>${jobs.title}</td>
                    <td>${jobs.desc}</td>

                    <td>${jobs.technoList}</td>

                    <td>  <a class="glyphicon glyphicon-plus" href="<c:url value="/client/job/update/${jobs.id}" />"></a></td>
                </tr>

            </c:forEach>
        </table>
    </div>
    <div class="col-md-1"></div>
</div>
