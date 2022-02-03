package com.msdp.order.service.sagas;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Access(AccessType.FIELD)
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private OrderState state;

  @Embedded
  private OrderDetails orderDetails;

  @Enumerated(EnumType.STRING)
  private RejectionReason rejectionReason;

  @Version
  private Long version;

  public Orders() {
  }

  public Orders(OrderDetails orderDetails) {
    this.orderDetails = orderDetails;
    this.state = OrderState.PENDING;
  }

  public static Orders createOrder(OrderDetails orderDetails) {
    return new Orders(orderDetails);
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void approve() {
    this.state = OrderState.APPROVED;
  }

  public void reject(RejectionReason rejectionReason) {
    this.state = OrderState.REJECTED;
    this.rejectionReason = rejectionReason;
  }

  public OrderState getState() {
    return state;
  }

  public RejectionReason getRejectionReason() {
    return rejectionReason;
  }
}
