<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<div div="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h2>Ajouter une annonce</h2>
        <form id="addDesc" method="post">
            <div class="form-group">
                <label for="compagny">
                    Titre
                </label>
                <input  class="form-control"  name="title" type="text" value="${jobs.title}"/>  
            </div>                    
            <div class="form-group">
                <label for="desc">
                    Contenue
                </label>
                <textarea id="editor1" class="form-control" name="desc" form="addDesc" row="10">${jobs.desc}</textarea>
            </div>

            <h2>Ajouter un critere de recherche</h2>

            <div class="form-group ">
                <label>Nom du profile recherché</label>
                <input  class="form-control"  name="profileName" type="text" value="${jobs.profileName}"/>  
            </div>
            <div class="form-group ">
                <label>Type de profile</label>
                <input  class="form-control"  name="profiType" type="text" value="${jobs.profiType}"/>
            </div>
            <div class="form-group">
                <label>Nombre d'année d'exprérience total</label>
                <input  class="form-control"  name="expTotal" type="number" value="${jobs.expTotal}"/>
            </div>
            <input  class="btn btn-default"  type="submit" value="Ok"/>
        </form>

        <c:if test="${jobs.id != null}">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".fade">
                Ajouter un critrere de recherche
            </button>
        </c:if>
        <div class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Ajouter un critrere de recherche</h4>
                    </div>

                    <form id="criteria" method="post" action="<c:url value="/client/job/criteria/updateAdd/${jobs.id}/${jobs.partialsClients.id}"  />">
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group col-md-12">
                                            <label>Technologie recherché</label>
                                            <input  class="form-control"  name="technoName" type="text" value=""/>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label>Nombre d'année</label>
                                            <input  class="form-control"  name="expDurationStart" type="number" value="" placeholder="1 to 6"/>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label>Nombre d'année</label>
                                            <input  class="form-control"  name="expDurationEnd" type="number" value="" placeholder="1 to 6"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>

                            <input class="btn btn-default" type="submit" value="OK"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <table class="table table-striped">
            <tr>
                <th>Nom</th>
                <th>Debut intervale</th>
                <th>Fin intervale</th>
                <th>Ajouter/Modifier</th>

            </tr>
            <c:forEach items="${jobs.technoCriterias}" var="c">                      
                <tr>
                    <td>${c.technoName}</td>
                    <td>${c.expDurationStart}</td>
                    <td>${c.expDurationEnd}</td>

                    <!--<td><a class="glyphicon glyphicon-plus" href="<c:url value="/client/job/update/${jobs.id}/${jobs.partialsClients.id}" />"></a></td>-->
                </tr>            
            </c:forEach>   
        </table>

    </div>   
    <div class="col-md-1"></div>
</div>

<script src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
<script>

    $(document).ready(function () {

//        location.reload();

        CKEDITOR.replace('editor1');
        $('#criteria').submit(function () {


        });

    });
</script>