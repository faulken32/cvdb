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
                <input  class="form-control"  name="title" type="text" value="${jobs.title}"/>  
            </div>                    
            <div class="form-group">
                <label for="desc">
                    Contenue
                </label>
                <textarea id="editor1" class="form-control" name="desc" form="addexp" row="10">${jobs.desc}</textarea>
            </div>

            <h2>Ajouter un critere de recherche</h2>

            <div class="form-group ">
                <label>Nom du profile recherch�</label>
                <input  class="form-control"  name="profileName" type="text" value="${jobs.profileName}"/>  
            </div>
            <div class="form-group ">
                <label>Type de profile</label>
                <input  class="form-control"  name="profiType" type="text" value=""/>
            </div>
            <div class="form-group">
                <label>Nombre d'ann�e d'expr�rience total</label>
                <input  class="form-control"  name="expTotal" type="number" value=""/>
            </div>
            <div id="add" class="btn btn-default"> plus</div>
            <div class="row">
            
                <div id="list" class="col-md-4">
                    <div class="form-group">
                        <label>Technologie recherch�</label>
                        <input  class="form-control"  name="expName" type="text" value=""/>
                    </div>
                    <div class="form-group">
                        <label>Nombre d'ann�e</label>
                        <input  class="form-control"  name="expRange" type="text" value="" placeholder="1 to 6"/>
                    </div>
                </div>
            </div>
            <input  class="btn btn-default"  type="submit" value="Ok"/>

        </form>



    </div>   
    <div class="col-md-1"></div>
</div>

<script src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
<script>
    
    $(document).ready(function () {
        
         
       
        
        
        CKEDITOR.replace('editor1');
        
         $('#add').click(function () {
            $('#list').append('<div class="form-group">' +
                        '<label>Technologie recherch�</label>'+
                        '<input  class="form-control"  name="expName" type="text" value=""/>'+
                    '</div>'+
                    '<div class="form-group">'+
                        '<label>Nombre d\'ann�e</label>'+
                        '<input  class="form-control"  name="expRange" type="text" value="" placeholder="1 to 6"/>'+
                    '</div>');
        });
        
    });
</script>