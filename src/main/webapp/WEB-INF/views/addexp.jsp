<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<a  href="<c:url value="/elastic/get/${exp.partialCandidat.id}"/>"> retour au candidat </a>
  
<div div="row">
    <form id="addexp" method="post" action="<c:url value="/elastic/exp/add/${exp.partialCandidat.id}"/>">
        
        <input type="hidden" name="candidatid" value="${exp.partialCandidat.id}">
        
        <div class="form-group">
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


            <textarea  class="form-control" name="expContend" form="addexp" row="10">${exp.expContend}</textarea>
        </div>
        
        <div class="containerT">
                <h2>Technologie</h2>
                <div class="form-group">                    
                    <div class="btn btn-default" id="add">Ajouter</div>
                </div>
                <c:forEach items="${exp.tecnoList}" var="techno">
                    <div class="form-group">                    
                        <input  class="form-control"  id="tecno" name="tecnoList" type="text" value=""/>  
                    </div>
                </c:forEach>
            </div>
        <input  class="btn btn-default"  type="submit" value="Validez" />
    </form>
        <script>
    $(document).ready(function () {
        $('#add').click(function () {
            $('.containerT').append('<input class="form-control" type="text" name="tecnoList" />');
        });
    });
</script>

</div>