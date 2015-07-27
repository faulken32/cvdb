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
        <div class="form-group">
            <label> Technologie séparé par des virgules</label>
            <input class="form-control" type="text" name="technoListblock" />
        </div>
     
        <input  class="btn btn-default"  type="submit" value="Validez" />
    </form>
    

</div>