<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Ricerca elementi</h5> 
		    </div>
		    <div class='card-body'>

				<form method="post" action="list" >
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Descrizione: </label>
							<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" >
						</div>
						
						<div class="form-group col-md-6">
							<label>Importo: </label>
							<input type="number" name="importo" id="importo" class="form-control" placeholder="Inserire l'importo" >
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="regista.id">Contribuente</label>
						    <select class="form-control" id="contribuente.id" name="contribuente.id">
						    	<option value=""> -- Selezionare una voce -- </option>
						      	<c:forEach items="${contribuenti_list_attribute }" var="contribuenteItem">
						      		<option value="${contribuenteItem.id}">${contribuenteItem.nome } ${contribuenteItem.cognome }</option>
						      	</c:forEach>
						    </select>
						</div>	
	  					
							<div class="form-group col-md-3">
						<label for="stato">Stato: </label>
							<select class="form-control" id="stato" name="stato">
							<option value=""> -- Selezionare una voce -- </option>
						      	<c:forEach items="${stato_cartella}" var="statoItem">
						      		<option value="${statoItem}">${statoItem}</option>
						      	</c:forEach>
					    	</select>
						</div>	
						
					</div>
						
					<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					<input class="btn btn-outline-warning" type="reset" value="Ripulisci">

					<a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath }/cartellaesattoriale/insert">Add New</a>
					
				</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>