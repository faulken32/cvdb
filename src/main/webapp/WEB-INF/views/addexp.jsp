<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<a  href="<c:url value="/elastic/get/${exp.candidatid}"/>"> retour au candidat </a>
  
<div div="row">
    <form id="addexp" method="post">
        
        <input type="hidden" name="candidatid" value="${exp.candidatid}">
        
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
        <input  class="btn btn-default"  type="submit" value="Validez" />
    </form>


</div>