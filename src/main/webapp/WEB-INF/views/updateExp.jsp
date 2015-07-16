<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<a  href="<c:url value="/elastic/get/${exp.partialCandidat.id}"/>"> retour au candidat </a>

<div div="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <form id="updateExp" method="post">
            <input type="hidden" name="candiatId" value="${exp.partialCandidat.id}">
            <div class="form-group">
                <label for="id">
                    Id
                </label>
                <input class="form-control"  id="id" name="id" type="text" value="${exp.id}" readonly/>  
            </div> 
            <div  class="form-group">
                <label for="compagny">
                    Compagnie
                </label>
                <input  class="form-control"  id="compagny" name="compagny" type="text" value="${exp.compagny}"/>  
            </div>
            <div class="form-group">
                <label for="title">
                    Titre
                </label>
                <input  class="form-control"  id="title" name="title" type="text" value="${exp.title}"/>  
            </div> 
            <div  class="form-group">
                <label for="start">
                    Début
                </label>
                <input  class="form-control"  id="start" name="start" type="text" value="${exp.start}"/>  
            </div> 
            <div  class="form-group">
                <label for="end">
                    Fin
                </label>
                <input  class="form-control"  id="end" name="end" type="text" value="${exp.end}"/>  
            </div>
            <div class="form-group">
                <label for="expContend">
                    Contenue
                </label>

                <textarea class="form-control" name="expContend" form="updateExp" row="20">${exp.expContend}</textarea>
            </div>


            <div class="containerT">
                <h2>Technologie</h2>
                <div class="form-group">                    
                    <div class="btn btn-default" id="add">Ajouter</div>
                </div>
                <c:forEach items="${exp.tecnoList}" var="techno">
                    <div class="form-group">                    
                        <input  class="form-control"  id="tecno" name="tecnoList" type="text" value="${techno}"/>  
                    </div>
                </c:forEach>
            </div>

            <input  class="btn btn-default"  type="submit" value="Validez" />
        </form>
    </div>
    <div class="col-md-1"></div>
</div>
<script>
    $(document).ready(function () {
        $('#add').click(function () {
            $('.containerT').append('<input class="form-control" type="text" name="tecnoList" />');
        });
    });
</script>