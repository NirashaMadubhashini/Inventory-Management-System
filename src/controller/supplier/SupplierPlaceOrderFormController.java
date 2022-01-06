package controller.supplier;

import controller.product.ProductController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Product;
import model.Supplier;
import model.SupplierOrder;
import model.SupplierProductDetail;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.SupplierOrderTm;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupplierPlaceOrderFormController {
    public AnchorPane SupplierPlaceOrderFormContext;

    public TextField txtSupplierOrderId;
    public TextField txtSupplierOrderDate;
    public TextField txtSupplierOrderTime;

    public ComboBox<String> cmbSupplierOrderNIC;
    public TextField txtSupplierOrderName;
    public TextField txtSupplierOrderEmail;
    public TextField txtSupplierOrderDescription;

    public ComboBox<String> cmbSupplierOrderProductName;
    public TextField txtSupplierOrderProductId;
    public TextField txtSupplierOrderProductUnitPrice;
    public TextField txtSupplierOrderProductQtyOnHand;
    public TextField txtSupplierOrderProductTotal;

    public TableView<SupplierOrderTm> tblSupplierOrder;
    public TableColumn colSupplierOrderId;
    public TableColumn colSupplierOrderNIC;
    public TableColumn colSupplierOrderProductId;
    public TableColumn colSupplierOrderProductName;
    public TableColumn colSupplierOrderQtyOnHand;
    public TableColumn colSupplierOrderPrice;
    public TableColumn colSupplierOrderTotal;


    public Label lblSupplierOrderTotal;
    public TextField txtSupplierOrderProductQty;


    int cartSelectedRowForRemove = -1;


    public void initialize() throws SQLException, ClassNotFoundException {
        colSupplierOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colSupplierOrderNIC.setCellValueFactory(new PropertyValueFactory<>("supplierNIC"));
        colSupplierOrderProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colSupplierOrderProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSupplierOrderQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSupplierOrderPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSupplierOrderTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


        cmbSupplierOrderNIC.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setSupplierData(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        cmbSupplierOrderProductName.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setProductOrderData(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        tblSupplierOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });


        loadSupplierNICs();
        loadProductDetails();
        loadDateTime();
        generateOrderId();

    }

    private void generateOrderId() {
        try {
            txtSupplierOrderId.setText(new SupplierOrderController().getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDateTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtSupplierOrderDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            txtSupplierOrderTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void setProductOrderData(String newValue) throws SQLException, ClassNotFoundException {
        Product product = new ProductController().getProductById(newValue);
        if (product == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result set");
        } else {
            txtSupplierOrderProductId.setText(String.valueOf(product.getProductId()));
            txtSupplierOrderProductUnitPrice.setText(String.valueOf(product.getPrice()));
            txtSupplierOrderProductQtyOnHand.setText(String.valueOf(product.getQty()));
        }
    }

    private void loadProductDetails() throws SQLException, ClassNotFoundException {

        List<String> productNames = new ProductController()
                .getAllProductNames();
        cmbSupplierOrderProductName.getItems().addAll(productNames);
    }

    private void loadSupplierNICs() throws SQLException, ClassNotFoundException {
        List<String> supplierIds = new SupplierController()
                .getSupplierNICs();
        cmbSupplierOrderNIC.getItems().addAll(supplierIds);
    }

    private void setSupplierData(String supplierNIC) throws SQLException, ClassNotFoundException {
        Supplier s = new SupplierController().getSupplier(supplierNIC);
        if (s == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtSupplierOrderName.setText(s.getSupplierName());
            txtSupplierOrderEmail.setText((s.getEmail()));
            txtSupplierOrderDescription.setText(s.getDescription());
        }
    }

    ObservableList<SupplierOrderTm> obList = FXCollections.observableArrayList();

    public void SupplierAddToStockOnAction(ActionEvent actionEvent) {
        String orderId = txtSupplierOrderId.getText();
        String supplierName = txtSupplierOrderName.getText();
        int productId = Integer.parseInt(txtSupplierOrderProductId.getText());
        String productName = cmbSupplierOrderProductName.getValue();
        int qtyOnHand = Integer.parseInt(txtSupplierOrderProductQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtSupplierOrderProductUnitPrice.getText());
        int qtyForCustomer = Integer.parseInt(txtSupplierOrderProductQty.getText());
        double total = Double.parseDouble(txtSupplierOrderProductTotal.getText());


        SupplierOrderTm supplierOrderTm = new SupplierOrderTm(
                orderId,
                supplierName,
                productId,
                productName,
                qtyForCustomer,
                unitPrice,
                total
        );
        int rowNumber = isExists(supplierOrderTm);

        if (rowNumber == -1) {
            obList.add(supplierOrderTm);
        } else {
            SupplierOrderTm temp = obList.get(rowNumber);
            int tableQty = 0;
            double tableTotal = 0;
            if (qtyOnHand >= temp.getQty() + qtyForCustomer) {
                tableQty = temp.getQty() + qtyForCustomer;
                tableTotal = temp.getTotal() + total;
            } else {
                new Alert(Alert.AlertType.WARNING, "Qty Not Enough").show();
                tableQty = temp.getQty();
                tableTotal = temp.getTotal();
            }
            SupplierOrderTm newTm = new SupplierOrderTm(
                    temp.getOrderId(),
                    temp.getSupplierNIC(),
                    temp.getProductId(),
                    temp.getProductName(),
                    tableQty,
                    temp.getPrice(),
                    tableTotal
            );
            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblSupplierOrder.setItems(obList);
        calculateCost();
    }

    private int isExists(SupplierOrderTm tm) {
        for (int i = 0; i < obList.size(); i++) {

            if (tm.getProductId() == obList.get(i).getProductId()) {
                return i;
            }
        }
        return -1;
    }


    public void SupplierPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            ArrayList<SupplierProductDetail> productDetails = new ArrayList<>();
            double total = 0;

            for (SupplierOrderTm tempTm : obList) {
                total += tempTm.getTotal();
                productDetails.add(new SupplierProductDetail(tempTm.getProductId(), tempTm.getQty(), tempTm.getPrice(),
                        tempTm.getTotal(), txtSupplierOrderDate.getText(), txtSupplierOrderTime.getText()));
            }

            SupplierOrder supplierOrder = new SupplierOrder(
                    txtSupplierOrderId.getText(),
                    cmbSupplierOrderNIC.getValue(),
                    txtSupplierOrderDate.getText(),
                    txtSupplierOrderTime.getText(),
                    productDetails
            );
            if (new SupplierOrderController().placeOrder(supplierOrder)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
                generateOrderId();
                showSupplierReport();
                clearText();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            e.printStackTrace();
        }

    }

    private void clearText() {
        txtSupplierOrderName.setText("");
        txtSupplierOrderEmail.setText("");
        txtSupplierOrderDescription.setText("");
        cmbSupplierOrderProductName.setValue("");
        txtSupplierOrderProductId.setText("");
        txtSupplierOrderProductUnitPrice.setText("");
        txtSupplierOrderProductQtyOnHand.setText("");
        txtSupplierOrderProductQty.setText("");
        txtSupplierOrderProductTotal.setText("");

        for ( int i = 0; i<tblSupplierOrder.getItems().size(); i++) {
            tblSupplierOrder.getItems().clear();
        }

    }


    private void showSupplierReport() {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/Invoice_Supplier.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            ObservableList<SupplierOrderTm> items = tblSupplierOrder.getItems();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JRBeanArrayDataSource(items.toArray()));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }


    public void SupplierPlaceOrderRemoveOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        } else {
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblSupplierOrder.refresh();
        }
    }


    private void calculateCost() {
        double ttl = 0;
        for (SupplierOrderTm tm : obList
        ) {
            ttl += tm.getTotal();
        }
        lblSupplierOrderTotal.setText(ttl + " /= ");
    }


    public void EnterSupplierQtyOnAction(ActionEvent actionEvent) {
        try {
            int qtyOnHand = Integer.parseInt(txtSupplierOrderProductQtyOnHand.getText());
            double unitPrice = Double.parseDouble(txtSupplierOrderProductUnitPrice.getText());
            int enterQty = Integer.parseInt(txtSupplierOrderProductQty.getText());

            double total = enterQty * unitPrice;
            txtSupplierOrderProductTotal.setText(String.valueOf(total));

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Value Is Empty Try Again").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void BackSupplierPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) SupplierPlaceOrderFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
    }


}
