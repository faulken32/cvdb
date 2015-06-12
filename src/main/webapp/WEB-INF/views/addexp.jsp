<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<a  href="<c:url value="/elastic/get/${candidat.id}"/>"> retour au candidat </a>
  
<div>
    <form id="addexp" method="post">
        
        <input type="hidden" name="candidatid" value="${candidat.id}">
        <div>
            <label for="id">
                Id
            </label>
            <input id="id" name="id" type="text" value="${candidat.id}" readonly/>  
        </div> 
        <div>
            <label for="compagny">
                Compagnie
            </label>
            <input  id="compagny" name="compagny" type="text" />  
        </div>
        <div>
            <label for="title">
                Titre
            </label>
            <input  id="title" name="title" type="text" />  
        </div> 
        <div>
            <label for="start">
                Début
            </label>
            <input  id="start" name="start" type="text" />  
        </div> 
        <div>
            <label for="end">
                Fin
            </label>
            <input  id="end" name="end" type="text"/>  
        </div>
        <div>
            <label for="expContend">
                Contenue
            </label>


            <textarea name="expContend" form="addexp">${exp.expContend}</textarea>
        </div>
        <input type="submit" value="Validez" />
    </form>


</div>