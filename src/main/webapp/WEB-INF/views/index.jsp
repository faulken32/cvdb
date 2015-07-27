<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   


<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">

        <div class="panel panel-default">
            <div class="panel-body">
                <h1 class="">Liste des candidats</h1>       
            </div>
        </div>




        <table class="table table-striped">
            <tr>
                <th>Nom</th>
                <th>Email</th>
                <th>Téléphone</th>

                <th>Ajouté une exp</th>
                <th>List des exp</th>
                <th>Status</th>

            </tr>
            <c:forEach items="${candidat}" var="candidat">
                <tr>
                    <td> 
                        <a class="" href="<c:url value="/elastic/get/${candidat.id}" />">${candidat.name}</a></td>
                    <td> ${candidat.email}</td>
                    <td>  ${candidat.phone}   </td>
                    <td>   <a class="glyphicon glyphicon-plus" href="<c:url value="/elastic/exp/add/${candidat.id}" />"></a></td>
                    <td>   <a class="glyphicon glyphicon-plus" href="<c:url value="/exp/all/${candidat.id}" />"></a></td>
                    <td>  ${candidat.status}   </td>
                </tr>

            </c:forEach>
        </table>
    </div>
    <div class="col-md-1"></div>


</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <iframe src="http://localhost:5601/#/dashboard/all?embed&_g=(refreshInterval:(display:Off,pause:!f,section:0,value:0),time:(from:'2010-07-29T07:36:17.657Z',mode:absolute,to:'2013-10-12T04:29:25.443Z'))&_a=(filters:!(),panels:!((col:1,id:techno-exp,row:1,size_x:12,size_y:4,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'*')),title:all)" height="600" width="1200"></iframe>
    </div>
    <div class="col-md-1"></div>
</div>