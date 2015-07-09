<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-10">
        <h4>Candidat </h4>


        <p> profiled  : ${candidat.profiled} <br></p>
        <p>Auto profiled  : ${candidat.autoMaticProfiled}</p>



        <form id="candidat" method="post" action="/site/elastic/update">
            <div class="form-group">
                Date d'entr� dans la base  :
                <input  class="form-control" type="text" name="enterDate"  value="${candidat.enterDate}" readonly/>            
            </div>

            <div class="form-group">
                Nombre d'ann�e d'expr�rience (champs calcul�)
                <input  class="form-control" type="number" name="nbYearExp"  value="${candidat.nbYearExp}" readonly/>            
            </div>


            <div class="form-group">           
                <input  class="form-control" type="text" name="id"  value="${candidat.id}" readonly/>            
            </div>

            <div class="form-group">
                age
                <input class="form-control" type="number" name="age" value="${candidat.age}" />
            </div>

            <div class="form-group">
                nom
                <input class="form-control" type="text" name="name" value="${candidat.name}" />
            </div>
            <div class="form-group">
                tel
                <input class="form-control" type="text" name="phone" value="${candidat.phone} "/> 
            </div>
            <div class="form-group">
                pr�nom
                <input class="form-control" type="text" name="surname"  value="${candidat.surname}"/> 
            </div>
            <div class="form-group">
                email
                <input class="form-control" type="text" name="email"  value="${candidat.email}"/> 
            </div>
            <div class="form-group">
                pr�avis en mois 
                <input class="form-control" type="text" name="preavis"  value="${candidat.preavis}"/> 
            </div>
            <div class="form-group">
                mobilit� par d�partement
                <div class="btn btn-default" id="add">Ajouter</div>
            </div>
            <div class="containerM">

                <c:forEach items="${candidat.mobilite}" var="mobil">

                    <div class="form-group">

                        <input class="form-control" type="text" name="mobilite" value="${mobil}"/> 
                        <!--<button class="btn btn-default" id="del" value="effacer">effacer</button>-->
                    </div>
                </c:forEach>
            </div>   
            <div class="form-group">
                <textarea class="form-control" rows="10" name="cvContends" form="candidat" value="">${candidat.cvContends}</textarea>
            </div>
            <input class="btn btn-default" type="submit" value="Modifiez"/>
        </form>

        <h4>Exp : </h4>        


        <c:forEach items="${exp}" var="exp">
            <div class="row">
                <div class="col-md-10 withBorder">
                    <div>
                        <a href="<c:url value="/elastic/exp/update/${exp.id}"/>">Modifiez</a>
                    </div>
                    <div>
                        <p><b>Titre</b></p>
                        <p> ${exp.title} </p>
                    </div>
                    <div>
                        <p><b>Company</b></p>
                        <p>
                            ${exp.compagny}
                        </p>
                    </div>
                    <div>
                        <p>Dur�</p>
                        <p>${exp.start} /  ${exp.end} </p>
                    </div>
                    <div>
                        <c:forEach items="${exp.tecnoList}" var="techno">
                            <span>${techno}</span>

                        </c:forEach>   
                    </div>
                    <div>
                        <p><b>Contenue</b></p>
                        <p>
                            ${exp.expContend}
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>

        <a href="<c:url value="/elastic/exp/add/${candidat.id}"/>">Ajouter une exp </a>
    </div>
    <div class="col-md-10">
        <h2>Commentaire</h2>
        <div>
            <a href="<c:url value="/comments/add/${candidat.id}"/>">Ajouter un commentaire</a>
        </div>
        <c:forEach items="${comments}" var="comments">

            <div>
                <label>
                    Date du commentaire :
                </label>
                <p>
                    ${comments.commentDate}
                </p>
            </div>
            <div>
                <label> Commentaire :</label>
                <p>
                    ${comments.comment}
                </p>
            </div>
            <a href="<c:url value="/comments/get/${comments.id}"/>">Modifier</a>
        </c:forEach>
    </div>
</div>
<script>
    $(document).ready(function () {

        $('#add').click(function () {
            $('.containerM').append('<input class="form-control" type="text" name="mobilite" />');
        });


    });
</script>
