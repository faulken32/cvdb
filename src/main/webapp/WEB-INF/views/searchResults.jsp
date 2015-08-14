<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-1"></div>
<div class="col-md-10">
    <h4>Résultats de recheche</h4>
    <c:forEach items="${candidat}" var="clist">
        <div class="row jumbotron">
            <div class="col-md-7 panel panel-body">
                ${clist.key.partialsClients.name}
            
            </div>
            <div class="col-md-1"></div>
            <div class="col-md-4">
                <c:forEach items="${clist.value}" var="c">
                    <div class="row">
                        ${c.name}
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
<div class="col-md-1"></div>


