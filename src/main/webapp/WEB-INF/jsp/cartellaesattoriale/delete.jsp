<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
		<form method="post" action="execute" >
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
			    <button type="submit" name="submit" value="submit" id="submit" class="btn btn-danger">Invalida</button>
			    <input type="hidden" name="idCartella" value="${cartella_delete.id}">
			        <a href="${pageContext.request.contextPath }/cartellaesattoriale/" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			</div>	
		</form>
	</div>
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>