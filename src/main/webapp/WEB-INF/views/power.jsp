<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        
        
        <form method="post">
            d�partement
            <input type="text" name="mobilite"/>
            text
            <input type="text" name="cvContends" />
            
            <input type="submit"/>
        </form>
    
        <div>
            R�sultat
            <c:forEach items="${candidat}" var="c">
                
                ${c.name}
                
                
            </c:forEach>
        </div>    
        
    </div>
    <div class="col-md-1"></div>
</div>    