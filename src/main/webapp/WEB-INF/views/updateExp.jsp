



<div>
    <form id="updateExp" method="post">
        
        <input type="hidden" name="candiatId" value="${exp.candidat.id}">
        <div>
            <label for="id">
                Id
            </label>
            <input id="id" name="id" type="text" value="${exp.id}" readonly/>  
        </div> 
        <div>
            <label for="compagny">
                Compagnie
            </label>
            <input  id="compagny" name="compagny" type="text" value="${exp.compagny}"/>  
        </div>
        <div>
            <label for="title">
                Titre
            </label>
            <input  id="title" name="title" type="text" value="${exp.title}"/>  
        </div> 
        <div>
            <label for="start">
                D�but
            </label>
            <input  id="start" name="start" type="text" value="${exp.start}"/>  
        </div> 
        <div>
            <label for="end">
                Fin
            </label>
            <input  id="end" name="end" type="text" value="${exp.end}"/>  
        </div>
        <div>
            <label for="expContend">
                Contenue
            </label>


            <textarea name="expContend" form="updateExp">${exp.expContend}</textarea>
        </div>
        <input type="submit" value="Validez" />
    </form>


</div>