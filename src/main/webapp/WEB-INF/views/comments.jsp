<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="row">
    <div class="col-md-10">
       
        <form id="comments" method="post" action=" <c:url value="/comments/update/${comments.id}" />">

          
                <input type="hidden" name="partialCandidat.id" value="${comments.partialCandidat.id}"/>
                <input type="hidden" name="id" value="${comments.id}"/>
                <div class="form-group">
                    Date du commentaire  :
                    <input  class="form-control" type="text" name="commentDate"  value="${comments.commentDate}"/>            
                </div>
                Commentaire
                <div class="form-group">
                    <textarea class="form-control" rows="10" name="comment" form="comments" value="">${comments.comment}</textarea>
                </div>
                <input class="btn btn-default" type="submit" value="Modifier"/>

          
        </form>
    </div>
</div>