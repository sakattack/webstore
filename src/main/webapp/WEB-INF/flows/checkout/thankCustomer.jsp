<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<section>
		<div class="container">
			<p>
				Your order will be delivered to you on
				<fmt:formatDate type="date"
					value="${order.shippingDetail.shippingDate}" />
				!
			</p>
			<p>Your Order Number is ${order.orderId}</p>
			<p>
				<a href="<spring:url value="/market/products" />"
					class="btn btn-primary"> <span
					class="glyphicon-hand-left glyphicon"> </span> products
				</a>
			</p>
		</div>
	</section>