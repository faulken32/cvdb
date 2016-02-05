



<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div div="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <form id="addexp" method="post" action="<c:url value="/mail"/>">

            <input type="hidden" name="id" value="${mail.id}">

            <div class="form-group">
                <label for="compagny">
                    adresse d'envoi
                </label>
                <input  class="form-control"  id="from" name="from" type="text" value="${mail.from}"/>  
            </div>

            <div class="form-group">
                <label for="contend">
                    Contenue
                </label>


                <textarea  id="editor1" class="form-control" name="contends" form="addexp" row="10">${mail.contends}</textarea>
            </div>

            <input  class="btn btn-default"  type="submit" value="Validez" />
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