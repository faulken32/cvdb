



<div>
    <form id="updateExp">
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
                Début
            </label>
            <input  id="start" name="start" type="date" value="${exp.start}"/>  
        </div> 
        <div>
            <label for="end">
                Fin
            </label>
            <input  id="end" name="end" type="date" value="${exp.end}"/>  
        </div>
        <div>
            <label for="expContend">
                Contenue
            </label>


            <textarea id="expContend" form="updateExp">${exp.expContend}</textarea>
        </div>
        <input type="submit" value="Validez" />
    </form>


</div>