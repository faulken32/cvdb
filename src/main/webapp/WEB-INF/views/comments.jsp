<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="row">
    <div class="col-md-10">

        <form id="comments" method="post" action="/site/comments/update">

            <c:forEach items="${comments}" var="c" >
                <div class="form-group">
                    Date du commentaire  :
                    <input  class="form-control" type="text" name="commentDate"  value="${c.commentDate}"/>            
                </div>
                Commentaire
                <div class="form-group">
                    <textarea class="form-control" rows="10" name="comment" form="comments" value="">${c.comment}</textarea>
                </div>
                <input class="btn btn-default" type="submit" value="Modifier"/>

            </c:forEach>
        </form>
    </div>
</div>