<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form method="post">



    <textarea name="contends" id="editor1">
       
        <div>
            <h2>Civilité</h2>    

            <strong>Préavis :</strong>
                ${candidat.preavis} mois<br>
            <strong>Nombre d'année d'expérience :</strong>
                ${candidat.nbYearExp}<br><br>
            <strong>Mobilité : </strong>
                ${candidat.mobilite}<br><br>
            <strong>Langues</strong>
                ${candidat.language}<br><br>
        </div>
        <div>
            <strong>   Expériences </strong>
                <c:forEach items="${exp}" var="e">
                  Entreprise :  ${e.compagny} <br><br>
                  Titre : ${e.title} <br><br>
                    
                    ${e.start} : ${e.end}  durré  ${e.duration} ans <br><br>
                    ${e.expContend}<br><br>
                Environement Technique : <br>
                    ${e.tecnoList}<br>
                    <hr />
                </c:forEach>
        </div>

        <div>
            <h1>Education</h1>
                <c:forEach items="${school}" var="school">
                <div>
                    <strong>  école </strong>                  
                    <p>
                            ${school.school}
                    </p>
                </div>
                <div>
                      <strong>Date </strong>                  
                    <p>
                        De ${school.start} à ${school.end}
                    </p>
                </div>
                <div>
                    <strong> Titre de la formation</strong>
                    <p>
                            ${school.title}
                    </p>
                </div>
                <div>
                    <strong>Desciption</strong>
                    <br>
                        ${school.sContend}

                </div>
                </c:forEach>

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
