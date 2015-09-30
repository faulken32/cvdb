<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form method="post">



    <textarea name="contends" id="editor1">
       
        <div>
            <h1>Civilit�</h1>    

            <strong>Pr�avis :</strong>
                ${candidat.preavis} mois<br>
            <strong>Nombre d'ann�e d'exp�rience :</strong>
                ${candidat.nbYearExp}<br><br>
            <strong>Mobilit� : </strong>
                ${candidat.mobilite}<br><br>
            <label>Langues</label>
                ${candidat.language}<br><br>
        </div>
        <div>
            Exp�riences
                <c:forEach items="${exp}" var="e">
                    ${e.title} <br><br>
                    ${e.compagny} <br><br>
                    ${e.start} : ${e.end}  /  ${e.duration} ans <br><br>
                    ${e.expContend}<br><br>
                Environement Technique : <br>
                    ${e.tecnoList}<br>
                </c:forEach>
        </div>

        <div>
            <h1>Education</h1>
                <c:forEach items="${school}" var="school">
                <div>
                    <label>
                        �cole
                    </label>
                    <p>
                            ${school.school}
                    </p>
                </div>
                <div>
                    <label>
                        Date
                    </label>
                    <p>
                        De ${school.start} � ${school.end}
                    </p>
                </div>
                <div>
                    <label> Titre de la formation</label>
                    <p>
                            ${school.title}
                    </p>
                </div>
                <div>
                    <label>Desciption</label>
                    <br>
                        ${school.sContend}

                </div>
                </c:forEach>

        </div>
        </html>
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
