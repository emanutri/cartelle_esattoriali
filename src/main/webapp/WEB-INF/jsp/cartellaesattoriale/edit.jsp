<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
	<style>
		.ui-autocomplete-loading {
			background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
		}
		.error_field {
	        color: red; 
	    }
	</style>
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<%-- se l'attributo in request ha errori --%>
		<spring:hasBindErrors  name="contribuente_list_attribute">
			<%-- alert errori --%>
			<div class="alert alert-danger " role="alert">
				Attenzione!! Sono presenti errori di validazione
			</div>
		</spring:hasBindErrors>
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica elemento</h5> 
		    </div>
		    <div class='card-body'>

					<form:form method="post" modelAttribute="cartella_attribute" action="update" novalidate="novalidate" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="descrizione">Descrizione</label>
								<spring:bind path="descrizione">
									<input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la descrizione" value="${cartella_attribute.descrizione }">
								</spring:bind>
								<form:errors  path="descrizione" cssClass="error_field" />
							</div>
							
							<div class="form-group col-md-6">
								<label for="importo">Importo</label>
								<spring:bind path="importo">
									<input type="number" class="form-control ${status.error ? 'is-invalid' : ''}" name="importo" id="importo" placeholder="Inserire l'importo" value="${cartella_attribute.importo }">
								</spring:bind>
								<form:errors  path="importo" cssClass="error_field" />
							</div>
						</div>
						
						<div class="form-row">	
							
							<label for="stato">Stato: </label>
							<spring:bind path="stato">
								<select class="form-control ${status.error ? 'is-invalid' : ''}" id="stato" name="stato" required>
							      	<c:forEach items="${stato_cartella}" var="statoItem">
							      		<option value="${statoItem}"${cartella_attribute.stato == statoItem ? 'selected' : ''}>${statoItem}</option>
							      	</c:forEach>
					    		</select>
				    		</spring:bind>
							<form:errors  path="stato" cssClass="error_field" />
							
	  						<div class="form-group col-md-6">	
	  								<label for="contribuenteSearchInput">Contribuente:</label>
	  							<spring:bind path="contribuente">
	  									<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="contribuenteSearchInput"
	  										name="contribuenteInput" value="${cartella_attribute.contribuente.nome}${empty cartella_attribute.contribuente.nome?'':' '}${cartella_attribute.contribuente.cognome}">
	  								</spring:bind>
	  								<input type="hidden" name="contribuente" id="contribuenteId" value="${cartella_attribute.contribuente.id }">
	  								<form:errors  path="contribuente" cssClass="error_field" />
		  					</div>
  						</div>
						
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
						<input type="hidden" name="idCartella" value="${cartella_attribute.id}">
					</form:form>
				
		<!-- inizio script autocomplete migliorato -->	
			<script>
                $("#contribuenteSearchInput").autocomplete({
                    source: function(request, response) {
                        $.ajax({
                            url: "${pageContext.request.contextPath }/contribuente/searchContribuentiAjax",
                            datatype: "json",
                            data: {
                                term: request.term,
                            },
                            success: function(data) {
                                response($.map(data, function(item) {
                                    return {
                                        label: item.label,
                                        value: item.value
                                    }
                                }))
                            }
                        })
                    },
                    respone: function (event, ui) {
                        $('#contribuente').val(null);
                    },
                    //quando seleziono la voce nel campo deve valorizzarsi la descrizione
                    focus: function(event, ui) {
                        $("#contribuenteSearchInput").val(ui.item.label)
                        $('#contribuente').val(ui.item.value);
                        return false
                    },

                    minLength: 2,
                    autofocus : true,
                    delay : 500,
                    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
                    select: function( event, ui ) {
                        $('#contribuenteId').val(ui.item.value);
                        //console.log($('#registaId').val())
                        return false;
                    }
                });
            </script>
					<!-- end script autocomplete -->	
					
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>