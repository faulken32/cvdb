<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<div div="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h2>Ajouter une annonce</h2>
        <form id="addexp" method="post">

<!--            <input type="hidden" name="id" value="${candidat.id}">-->

            <div class="form-group">
                <label for="compagny">
                    Titre
                </label>
                <input  class="form-control"  name="title" type="text" value=""/>  
            </div>                    
            <div class="form-group">
                <label for="desc">
                    Contenue
                </label>
                <textarea id="editor1" class="form-control" name="desc" form="addexp" row="10"></textarea>
            </div>
            <input  class="btn btn-default"  type="submit" value="Ok" />
        </form>
    </div>   
    <div class="col-md-1"></div>
</div>

<script src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
<script>
    $(document).ready(function () {

        CKEDITOR.replace('editor1');
    });
</script>