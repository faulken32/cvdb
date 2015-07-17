<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>  
     
        
        
   
            
             <h1>Liste des candidats</h1>
      <iframe src="http://localhost:5601/#/visualize/edit/techno-exp?embed&_a=(filters:!(),linked:!f,query:(query_string:(analyze_wildcard:!t,query:'*')),vis:(aggs:!((id:'1',params:(field:duration),schema:metric,type:sum),(id:'2',params:(field:tecnoList,order:desc,orderBy:'1',size:200),schema:segment,type:terms),(id:'3',params:(field:partialCandidat.name,order:desc,orderBy:'1',size:5),schema:group,type:terms)),listeners:(),params:(addLegend:!t,addTimeMarker:!f,addTooltip:!t,defaultYExtents:!f,mode:stacked,scale:linear,setYExtents:!f,shareYAxis:!t,times:!(),yAxis:()),type:histogram))&_g=()" height="600" width="800"></iframe>
        <iframe src="http://localhost:5601/#/dashboard?embed&_a=(filters:!(),panels:!((col:1,columns:!(_id,name,surname,email,age,mobilite),id:all-candidat,row:1,size_x:12,size_y:2,sort:!(_score,desc),type:search)),query:(query_string:(query:'*')),title:'New%20Dashboard')&_g=()" height="600" width="800"></iframe>   
        </div>
    </body>
</html>
    