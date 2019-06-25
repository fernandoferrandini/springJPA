<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:formatDate value="${task.dataFinalizacao.time}"
				pattern="dd/MM/yyyy" />