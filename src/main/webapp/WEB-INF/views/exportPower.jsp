<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form method="post">



    <textarea name="contends" id="editor1">
     
           <div>
            <h2>Civilité</h2>    
            
           <p>${candidat.name} ${candidat.surname} </p>            
           <p>${candidat.email}</p> 
           <p>${candidat.phone}</p> 
           <p>${candidat.cvContends}</p>                               
        
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
