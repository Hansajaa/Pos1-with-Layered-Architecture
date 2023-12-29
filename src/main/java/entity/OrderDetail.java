package entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class OrderDetail {


    @EmbeddedId
    OrderDetailKey orderDetailKey;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_Id")
    Orders orders;

    @ManyToOne
    @MapsId("itemCode")
    @JoinColumn(name = "item_Code")
    Item item;

    private int qty;
    private double unitPrice;
}
