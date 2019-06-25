<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<td>${task.id}</td>
<td>${task.descricao}</td>
<td>Finalizada</td>
<td><fmt:formatDate value="${task.dataFinalizacao.time}"
				pattern="dd/MM/yyyy" /></td>
<td><a href="excluitask?id=${task.id}">Excluir</a></td>
<td><a href="buscartask?id=${task.id}">Editar</a></td>