<jsp:include page="../include/header.jsp" />

	<div class="container">

		<div class="starter-template">
			<form action="index" method="GET">
				nom <input type="text" name="nom"> <input type="submit"
					value="ok">
			</form>

			bonjour ${nom}
		</div>

	</div>
	<!-- /.container -->

<jsp:include page="../include/footer.jsp" />
