<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        
        
        <form method="post">
            département
            <input type="text" name="mobilite"/>
            text
            <input type="text" name="cvContends" />
            
            <input type="submit"/>
        </form>
    
        <div>
            Résultat
            <c:forEach items="${candidat}" var="c">
                
                ${c.name}
                
                
            </c:forEach>
        </div>    
        
    </div>
    <div class="col-md-1"></div>
</div>    