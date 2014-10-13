<%@ page import="com.entity.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>

	<c:when test="${user!=null}">
		<jsp:include page="../include/header.jsp" />
		<div>
			<h1>Bienvenue ${user.name} !</h1>
		</div>
		<jsp:include page="../include/footer.jsp" />
	</c:when>
	
	<c:otherwise>
		<c:redirect url="index"/>
	</c:otherwise>
	
</c:choose>