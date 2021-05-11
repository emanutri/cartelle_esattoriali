<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento da eliminare</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
	<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Id:</dt>
				  <dd class="col-sm-9">${cartella_delete.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione:</dt>
				  <dd class="col-sm-9">${cartella_delete.descrizione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Importo:</dt>
				  <dd class="col-sm-9">${cartella_delete.importo}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato:</dt>
				  <dd class="col-sm-9">${cartella_delete.stato}</dd>
		    	</dl>
		    	
		    	
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Contribuente:</dt>
				  <dd class="col-sm-9">${cartella_delete.contribuente.cognome}${cartella_delete.contribuente.cognome} </dd>
		    	</dl>
		    
		    
		    
			    <div class='card-footer'>
			    <button type="submit" name="submit" value="submit" id="submit_${cartella_delete.id}" class="btn btn-danger link-for-modal float-right"  data-toggle="modal" data-target="#confirmOperationModal" >Invalida</button>
			        <a href="${pageContext.request.contextPath }/cartellaesattoriale/" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			</div>	
	</div>
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
	<!-- modal -->
	<div class="modal fade" id="confirmOperationModal" tabindex="-1" role="dialog" aria-labelledby="confirmOperationModalLabel" aria-hidden="true">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma Operazione</h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                Continuare con la disabilitazione della Cartella Esattoriale?
	            </div>
	            <form:form method="post" action = "execute" modelAttribute="cartella_delete">
		            <div class="modal-footer">
		        		<input type = "hidden" name = "id" id= "id" value = "${cartella_delete.id }"/>
		                <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Disabilita"  class="btn btn-danger">
		            </div>
	            </form:form>
	        </div>
	    </div>
	</div>
	
	<script type="text/javascript">
		$(".link-for-modal").click(function(){
			var callerId = $(this).attr('id').substring(7);
			$('#id').val(callerId);
		});
	</script>
</body>
</html>