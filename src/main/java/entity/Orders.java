package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Orders {

    @Id
    private String id;
    private String date;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    List<OrderDetail> orderDetailList=new ArrayList<>();

    public Orders(String id, String date) {
        this.id = id;
        this.date = date;
    }
}
