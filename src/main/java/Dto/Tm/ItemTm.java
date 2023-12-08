package Dto.Tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private String Code;
    private String description;
    private double unitPrice;
    private int quantityOnHand;
    private JFXButton btn;
}
