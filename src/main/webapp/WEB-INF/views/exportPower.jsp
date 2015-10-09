<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form method="post">



    <textarea name="contends" id="editor1">
        Pour obtenir les coordonnées de ce candidat répondez à ce mails 
        avec cette références  : ${candidat.id} 
        <br> les 5 premiers sont gratuit

        <div>
            <h1>Civilité</h1>    
        
                ${candidat.cvContends}
                
                
        
        </div>
       
        

      
    </textarea>

    <div class="row">

        <div class="form-group">
            <input class="btn btn-default" name="send" type="submit" />
        </div>

    </div>

</form>


<script src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
<script>
    $(document).ready(function () {

        CKEDITOR.replace('editor1');


    });
</script>
