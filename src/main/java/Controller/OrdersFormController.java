package Controller;

import Dto.OrderDetailDto;
import Dto.OrderDto;
import Dto.Tm.ItemTm;
import Dto.Tm.OrderDetailTm;
import Dto.Tm.OrderTm;
import Dto.Tm.OrdersFormTm;
import bo.BoFactory;
import bo.custom.OrderDetailBo;
import bo.custom.OrdersBo;
import bo.custom.impl.OrderDetailBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrdersFormController {

    public JFXTreeTableView<OrdersFormTm> tblOrders;
    public TreeTableColumn colOrderID;
    public TreeTableColumn colDate;
    public TreeTableColumn colCustomerID;
    public JFXTreeTableView<OrderDetailTm> tblItems;
    public TreeTableColumn colDetailOrderId;
    public TreeTableColumn colDetailItemCode;
    public TreeTableColumn colDetailQty;
    public TreeTableColumn colDetailUnitPrice;

    OrdersBo<OrderDto,String> bo= BoFactory.getInstance().getBo(BoType.ORDER);
    OrderDetailBo detailBo= BoFactory.getInstance().getBo(BoType.ORDER_DETAIL);
    public AnchorPane ordersPane;


    public void initialize(){
        colOrderID.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
        colCustomerID.setCellValueFactory(new TreeItemPropertyValueFactory<>("custId"));
        loadOrdersTable();

        colDetailOrderId.setCellValueFactory(new TreeItemPropertyValueFactory<>("orderId"));
        colDetailItemCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colDetailQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colDetailUnitPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrice"));

        tblOrders.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSelection, newSelection) -> {
            if(newSelection!=null){
                setData(newSelection.getValue());
            }
        });
    }

    private void setData(OrdersFormTm newValue) {
        ObservableList<OrderDetailTm> detailTms = FXCollections.observableArrayList();
        try {
            if (newValue!=null) {
                List<OrderDetailDto> items = detailBo.getItems(newValue.getId());
                for (OrderDetailDto dto:items) {
                    detailTms.add(
                            new OrderDetailTm(
                                    dto.getOrderId(),
                                    dto.getItemCode(),
                                    dto.getQty(),
                                    dto.getUnitPrice()
                            )
                    );
                }
                TreeItem<OrderDetailTm> treeItem = new RecursiveTreeItem<>(detailTms, RecursiveTreeObject::getChildren);
                tblItems.setRoot(treeItem);
                tblItems.setShowRoot(false);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadOrdersTable() {
        ObservableList<OrdersFormTm> orderTms = FXCollections.observableArrayList();
        try {
            List<OrderDto> dtoList = bo.allOrders();

            for (OrderDto dto:dtoList) {
                orderTms.add(
                        new OrdersFormTm(
                                dto.getOrderId(),
                                dto.getDate(),
                                dto.getCustId()
                        )
                );
            }

            RecursiveTreeItem treeItem = new RecursiveTreeItem<>(orderTms, RecursiveTreeObject::getChildren);
            //RecursiveTreeItem treeItem = new RecursiveTreeItem<>(orderTms, RecursiveTreeObject::getChildren);
            tblOrders.setRoot(treeItem);
            tblOrders.setShowRoot(false);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ordersPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.show();
    }
}
