<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="row">
    <div class="col-md-10">

        <form id="comments" method="post" action="">

          
               
               
                <div class="form-group">
                   Nom du candidat
                    <input  class="form-control" type="text" name="name"/>            
                </div>
                Cv 
                <div class="form-group">
                    <textarea class="form-control" rows="10" name="cvCondents" form="comments" ></textarea>
                </div>
                <input class="btn btn-default" type="submit" value="Ajouter"/>

          
        </form>
    </div>
</div>