<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<a  href="<c:url value="/elastic/get/${exp.candidat.id}"/>"> retour au candidat </a>

<div div="row">
    <form id="updateExp" method="post">

        <input type="hidden" name="candiatId" value="${exp.candidat.id}">
        <div class="form-group">
            <label for="id">
                Id
            </label>
            <input class="form-control"  id="id" name="id" type="text" value="${exp.id}" readonly/>  
        </div> 
        <div  class="form-group">
            <label for="compagny">
                Compagnie
            </label>
            <input  class="form-control"  id="compagny" name="compagny" type="text" value="${exp.compagny}"/>  
        </div>
        <div class="form-group">
            <label for="title">
                Titre
            </label>
            <input  class="form-control"  id="title" name="title" type="text" value="${exp.title}"/>  
        </div> 
        <div  class="form-group">
            <label for="start">
                Début
            </label>
            <input  class="form-control"  id="start" name="start" type="text" value="${exp.start}"/>  
        </div> 
        <div  class="form-group">
            <label for="end">
                Fin
            </label>
            <input  class="form-control"  id="end" name="end" type="text" value="${exp.end}"/>  
        </div>
        <div class="form-group">
            <label for="expContend">
                Contenue
            </label>


            <textarea  class="form-control" name="expContend" form="updateExp" row="10">${exp.expContend}</textarea>
        </div>
            <c:forEach items="${exp.tecnoList}" var="techno">
            <div  class="form-group">
                <label for="">
                    technologie
                </label>
                <input  class="form-control"  id="tecno" name="tecnoList" type="text" value="${techno}"/>  
            </div>
        </c:forEach>


        <input  class="btn btn-default"  type="submit" value="Validez" />
    </form>


</div>