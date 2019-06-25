<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<link type="text/css" href="resources/css/tasks.css" rel="stylesheet" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Editar task - ${task.id}</h2>
<form action="editatask" method="post">
	<input type="hidden" name="id" value="${task.id}" />
	<textarea name="descricao" rows="5" cols="100">
	${task.descricao}</textarea>
	<br />Finalizada? <input type="checkbox" name="finalizada"
		value="true" ${task.finalizada?  'checked' : ''} />
	<br />Data de Finalização<br />
	<input type="text" name="dataFinalizacao"
		value="<fmt:formatDate 
		value="${task.dataFinalizacao.time}"
		pattern="dd/MM/yyyy"/>"/>
	<input type="submit" value="Editar" />
</form>
</body>
</html>