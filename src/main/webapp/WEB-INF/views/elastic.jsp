
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



    candidat :  
    <!--        candidat-->


    <form id="candidat" method="post" action="/site/elastic/update">

        <div>           
            <input type="text" name="id"  value="${candidat.id}" readonly/>            
        </div>
        <div>
            <input type="number" name="age" value="${candidat.age}" />
        </div>
        <div>
            <input type="text" name="name" value="${candidat.name}" />
        </div>
        <div>
            <input type="text" name="phone" value="${candidat.phone}"/> 
        </div>
        <div>
            <input type="text" name="surname"  value="${candidat.surname}"/> 
        </div>
        <div>
            <input type="text" name="email"  value="${candidat.email}"/> 
        </div>
        <div>
            <textarea name="cvContends" form="candidat" value="">${candidat.cvContends}</textarea>
        </div>
        <input type="submit" value="Modifiez"/>
    </form>

    EXP :



    
    <c:forEach items="${exp}" var="exp">
        <div>
            <div>
                <a href="<c:url value="/elastic/exp/update/${exp.id}"/>">Modifiez</a>
            </div>
            <div>           
                ${exp.title}
            </div>

            <div>
                ${exp.compagny}
            </div>
            <div>
                ${exp.start}
            </div>
            <div>
                ${exp.end}
            </div>
            <div>
                ${exp.expContend}
            </div>
            <div>
                ${exp.candidatid}
            </div>


        </div>    
    </c:forEach>
    
    
    <a href="<c:url value="/elastic/exp/add/${candidat.id}"/>">Ajouter une exp </a>

    




