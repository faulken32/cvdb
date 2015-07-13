<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<a  href="<c:url value="/elastic/get/${school.partialCandidat.id}"/>"> retour au candidat </a>

<div div="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <form id="updateSchool" method="post" action="<c:url value="/school/update" />">
           
            
            <input type="hidden" name="id" value="${school.id}"/>
            <input type="hidden" name="partialCandidat.id" value="${school.partialCandidat.id}"/>
            
            <div class="form-group">
                <label for="title">
                    Titre
                </label>
                <input  class="form-control"  id="title" name="title" type="text" value="${school.title}"/>  
            </div> 
            <div  class="form-group">
                <label for="start">
                    Début
                </label>
                <input  class="form-control"  id="start" name="start" type="text" value="${school.start}"/>  
            </div> 
            <div  class="form-group">
                <label for="end">
                    Fin
                </label>
                <input  class="form-control"  id="end" name="end" type="text" value="${school.end}"/>  
            </div>
            <div class="form-group">
                <label for="updateSchool">
                    Contenue
                </label>

                <textarea class="form-control" name="sContend" form="updateSchool" row="20">${school.sContend}</textarea>
            </div>
            <input  class="btn btn-default"  type="submit" value="Validez" />
        </form>
    </div>
    <div class="col-md-1"></div>
</div>