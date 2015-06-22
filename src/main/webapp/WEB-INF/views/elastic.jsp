<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-10">
        <h4>Candidat </h4>


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
                        <p>Duré</p>
                        <p>${exp.start} /  ${exp.end} </p>
                    </div>
                    <div>                       
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
</div>

