<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<div div="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h2>Ajouter une �cole</h2>
        <form id="addexp" method="post" action="<c:url value="/school/add" />">

            <input type="hidden" name="id" value="${candidat.id}">

            <div class="form-group">
                <label for="compagny">
                    Ecole
                </label>
                <input  class="form-control"  id="school" name="school" type="text" value=""/>  
            </div>
            <div class="form-group">
                <label for="title">
                    Titre
                </label>
                <input  class="form-control"  id="title" name="title" type="text" value=""/>  
            </div> 
            <div  class="form-group">
                <label for="start">
                    D�but
                </label>
                <input  class="form-control"  id="start" name="start" type="text" value=""/>  
            </div> 
            <div  class="form-group">
                <label for="end">
                    Fin
                </label>
                <input  class="form-control"  id="end" name="end" type="text" value=""/>  
            </div>
            <div class="form-group">
                <label for="sContend">
                    Contenue
                </label>


                <textarea  class="form-control" name="sContend" form="addexp" row="10"></textarea>
            </div>
            <input  class="btn btn-default"  type="submit" value="Validez" />
        </form>
    </div>   
    <div class="col-md-1"></div>
</div>