


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">

        <div class="panel panel-default">
            <div class="panel-body">
                <h1 class="">Liste des experiences</h1>       
            </div>
        </div>




        <table class="table table-striped">
            <tr>
                <th>Nom</th>
                <th>Email</th>
                <th>Fin</th>
                <th>Modifier</th>

                <!--                <th>Ajouté une exp</th>
                                 <th>List des exp</th>
                                  <th>Status</th>-->

            </tr>
            <c:forEach items="${exp}" var="experience">
                <tr>
                    
                    <td>${experience.title}</td>
                     <td>${experience.compagny}</td>
                      <td>${experience.end}</td>
                      <td>  <a class="glyphicon glyphicon-pencil" href="<c:url value="/elastic/exp/update/${experience.id}" />"></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-1"></div>


</div>