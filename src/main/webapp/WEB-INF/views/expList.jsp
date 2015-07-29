


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
            <input id="expId" type="hidden" value="${experience.id}"/>
            <tr>
                <th>Nom</th>
                <th>Email</th>
                <th>Fin</th>
                <th>Modifier</th>
                <th>Effacer</th>



            </tr>
            <c:forEach items="${exp}" var="experience">
                <tr>

                    <td>${experience.title}</td>
                    <td>${experience.compagny}</td>
                    <td>${experience.end}</td>
                    <td>  <a class="glyphicon glyphicon-pencil" href="<c:url value="/elastic/exp/update/${experience.id}" />"></a></td>
                    <td> <button class="glyphicon glyphicon-remove" value="${experience.id}"></button>  
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-1"></div>
</div>

<script>
    $(document).ready(function () {
        $('.glyphicon-remove').click(function () {
            var r = confirm("Sure !");
            if (r !== true) {
                return  false;
            } else {
//                var jqxhr = $.ajax('<c:url value="/exp/del/"/>' + $(this).val()).done();
//                var status = jqxhr.statusCode();
//
//                console.log(status.["responseText"]);
                $.ajax({url: "<c:url value="/exp/del/"/>" + $(this).val()}).always(function ( data) {             
                
                alert(data.responseText);
                
                window.location.reload();
                });
            }
        });
    });



</script>