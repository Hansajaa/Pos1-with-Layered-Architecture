package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Item {

    @OneToMany(mappedBy = "item")
    List<OrderDetail> orderDetailList=new ArrayList<>();

    @Id
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;

    public Item(String code, String description, double unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }
}
