<%@ page import="com.entity.*" %>

<%
	User user = (User) request.getSession().getAttribute("user");

	if(user == null)
	{
		System.out.println("Pas de session");
		response.sendRedirect("index");
	}
	
	else
	{
		System.out.println("Session retrouvée");
	}
%>

<jsp:include page="../include/header.jsp" />

<div>
	<h1>Others !</h1>
</div>

<jsp:include page="../include/footer.jsp" />
