package Controller;

import Dto.ItemDto;
import Dto.Tm.ItemTm;
import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.impl.ItemBoImpl;
import dao.custom.impl.ItemDaoImpl;
import dao.custom.ItemDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;


public class ItemFormController {

    public JFXTreeTableView<ItemTm> tblItem;
    public JFXTextField txtSearch;
    @FXML
    private BorderPane itemPane;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TreeTableColumn colCode;

    @FXML
    private TreeTableColumn colDesc;

    @FXML
    private TreeTableColumn colPrice;

    @FXML
    private TreeTableColumn colQty;

    @FXML
    private TreeTableColumn colOption;

    ItemBo<ItemDto,String> itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    public void initialize() {
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("quantityOnHand"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        loadItemTable();

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                tblItem.setPredicate(new Predicate<TreeItem<ItemTm>>() {
                    @Override
                    public boolean test(TreeItem<ItemTm> treeItem) {
                        return treeItem.getValue().getCode().contains(newValue);
                    }
                });
            }
        });

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSelection, newSelection) -> {
            if (newSelection!=null){
                setData(newSelection.getValue());
            }
        });
    }

    private void setData(ItemTm newSelection) {
        if (newSelection!=null){
            txtCode.setText(newSelection.getCode());
            txtDesc.setText(newSelection.getDescription());
            txtPrice.setText(String.valueOf(newSelection.getUnitPrice()));
            txtQty.setText(String.valueOf(newSelection.getQuantityOnHand()));
        }
    }


    private void loadItemTable() {

        ObservableList<ItemTm> itm = FXCollections.observableArrayList();

        try {
            List<ItemDto> dtoList = itemBo.allItem();
            for (ItemDto dto:dtoList){
                JFXButton btn =new JFXButton("Delete");
                ItemTm i = new ItemTm(
                        dto.getCode(),
                        dto.getDescription(),
                        dto.getUnitPrice(),
                        dto.getQuantityOnHand(),
                        btn
                );
                btn.setOnAction(ActionEvent ->{
                    deleteItem(i.getCode());
                });
                itm.add(i);
            }
            RecursiveTreeItem treeItem = new RecursiveTreeItem<>(itm, RecursiveTreeObject::getChildren);
            tblItem.setRoot(treeItem);
            tblItem.setShowRoot(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteItem(String code) {
        try {
            boolean isDeleted = itemBo.deleteItem(code);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Delete Successfully").show();
                loadItemTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        } catch (SQLException  | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        try{
            ItemDto dto = new ItemDto(
                    txtCode.getText(),
                    txtDesc.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    Integer.parseInt(txtQty.getText())
            );
            boolean isUpdated = itemBo.updateItem(dto);
            loadItemTable();
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Updated Successfully :)").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Item not Updated :(").show();
            }
            //loadItemTable();
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Select a Item").show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            //loadItemTable();
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        try{
            ItemDto item=new ItemDto(
                    txtCode.getText(),
                    txtDesc.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    Integer.parseInt(txtQty.getText())
            );

            try {
                boolean isSaved = itemBo.saveItem(item);
                if (isSaved){
                    new Alert(Alert.AlertType.INFORMATION,"Item Added Successfully").show();
                    txtCode.setText("");
                    txtDesc.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            loadItemTable();
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Enter All Details").show();
        }

    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) itemPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.show();
    }


    public void refreshButtonOnAction(ActionEvent actionEvent) {
        loadItemTable();
    }
}
