<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h4>Candidat</h4>
        <c:choose>
            <c:when test="${noCandidat != true}"> 
                <p> profiled  : ${candidat.profiled} <br></p>
                <p>Auto profiled  : ${candidat.autoMaticProfiled}</p>



                <form id="candidat" method="post" action="/site/elastic/update">
                    <div class="form-group">
                        Date d'entré dans la base  :
                        <input  class="form-control" type="text" name="enterDate"  value="${candidat.enterDate}" readonly/>            
                    </div>

                    <div class="form-group">
                        Nombre d'année d'exprérience (champs calculé)
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
                        prénom
                        <input class="form-control" type="text" name="surname"  value="${candidat.surname}"/> 
                    </div>
                    <div class="form-group">
                        email
                        <input class="form-control" type="text" name="email"  value="${candidat.email}"/> 
                    </div>
                    <div class="form-group">
                        préavis en mois 
                        <input class="form-control" type="text" name="preavis"  value="${candidat.preavis}"/> 
                    </div>
                    <div class="form-group">
                        mobilité par département
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
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapsecv" aria-expanded="false" aria-controls="collapsecv">
                        Afficher Cv
                    </button>
                    <div id="collapsecv" class="form-group collapse">
                        <textarea class="form-control" rows="20" name="cvContends" form="candidat" value="">${candidat.cvContends}</textarea>
                    </div>
                    <input class="btn btn-default" type="submit" value="Modifiez"/>
                </form>

                <h4>Exp : </h4>        
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExp" aria-expanded="false" aria-controls="collapseExp">
                    Afficher Exp
                </button>

                <div id="collapseExp" class="collapse">              
                    <a href="<c:url value="/elastic/exp/add/${candidat.id}"/>">Ajouter une exp </a>
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
                                    <p>Duré</p>
                                    <p>${exp.duration}</p>
                                    <p>${exp.start} /  ${exp.end} </p>
                                </div>
                                <div>
                                    <c:forEach items="${exp.tecnoList}" var="techno">
                                        <span>${techno}</span>

                                    </c:forEach>   
                                </div>
                                <div>
                                    <h4>Contenue</h4>
                                    <div class="form-group">
                                        <pre>
                                            ${exp.expContend}
                                        </pre>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>

                <h2>Commentaire</h2>
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseComments" aria-expanded="false" aria-controls="collapseComments">
                    Afficher les commentaires
                </button>
                <div id="collapseComments" class="collapse">
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
                            <pre>
                                ${comments.comment}
                            </pre>
                        </div>
                        <a href="<c:url value="/comments/get/${comments.id}"/>">Modifier</a>
                    </c:forEach>
                </div>

                <h2>Formations</h2>
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseSchool" aria-expanded="false" aria-controls="collapseSchool">
                    Afficher les formations
                </button>

                <div id="collapseSchool" class="collapse">
                    <div>
                        <a href="<c:url value="/school/add/${candidat.id}" />">Ajouter une formartion</a>
                    </div>
                    <c:forEach items="${school}" var="school">

                        <div>
                            <label>
                                école
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
                                De ${school.start} à ${school.end}
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
                            <pre>
                                ${school.sContend}
                            </pre>
                        </div>
                        <a href="<c:url value="/school/get/${school.id}"/>">Modifier</a>
                    </c:forEach>
                </div>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h2>Pas de candidat trouver pour cette id</h2>
    </c:otherwise>  
</c:choose>


<div class="col-md-1"></div>
<script>
    $(document).ready(function () {

        $('#add').click(function () {
            $('.containerM').append('<input class="form-control" type="text" name="mobilite" />');
        });


    });
</script>
