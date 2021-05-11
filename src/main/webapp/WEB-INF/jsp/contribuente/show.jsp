<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
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
				  <dd class="col-sm-9">${show_contribuente_attr.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9">${show_contribuente_attr.nome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9">${show_contribuente_attr.cognome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data Di Nascita:</dt>
				  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_contribuente_attr.dataDiNascita}" /></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
				  <dd class="col-sm-9">${show_contribuente_attr.codiceFiscale}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Indirizzo: </dt>
				  <dd class="col-sm-9">${show_contribuente_attr.indirizzo}</dd>
		    	</dl>
		    	
		    	<!-- info Cartele -->
		    	<jsp:useBean id="utility" class="it.prova.gestionecartelle.utility.UtilityCustom"/>
		    	<p>
				  <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
				    Info Cartelle Contribuente
				  </a>
				</p>
				<div class="collapse" id="collapseExample">
				  <div class="card card-body">
				  	<dl class="row">
					  <dt class="col-sm-3 text-right">Tot. Importo Cartelle:</dt>
					  <dd class="col-sm-9">${utility.sommaTotCartelle(show_contribuente_attr)}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Tot. Concluso e Pagato:</dt>
					  <dd class="col-sm-9">${utility.sommaCartelleConclusePagate(show_contribuente_attr)}</dd>
				   	</dl>
				   	<c:if test="${utility.sommaCartelleContenzioso(show_contribuente_attr)!=0.0}">
				   	<dl class="row">
				 		<dt class="col-sm-3 text-right text-danger">Tot. in Contenzioso:</dt>
				  		<dd class="col-sm-9 text-danger">${utility.sommaCartelleContenzioso(show_contribuente_attr)}</dd>
		    		</dl>
				    </c:if>
				  </div>
				</div>
				<!-- end info Cartelle -->
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="${pageContext.request.contextPath }/contribuente/" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>