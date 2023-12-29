package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "order_Id")
    String orderId;
    @Column(name = "item_Code")
    String itemCode;
}
