package com.msdp.order.service.sagas;

public class CreateOrderSagaData  {

	  private OrderDetails orderDetails;
	  private Long orderId;
	  private RejectionReason rejectionReason;

	  public CreateOrderSagaData(OrderDetails orderDetails) {
	    this.orderDetails = orderDetails;
	  }

	  public CreateOrderSagaData() {
	  }

	  public Long getOrderId() {
	    return orderId;
	  }

	  public OrderDetails getOrderDetails() {
	    return orderDetails;
	  }

	  public void setOrderId(Long orderId) {
	    this.orderId = orderId;
	  }

	  public void setRejectionReason(RejectionReason rejectionReason) {
	    this.rejectionReason = rejectionReason;
	  }

	  public RejectionReason getRejectionReason() {
	    return rejectionReason;
	  }
	}
