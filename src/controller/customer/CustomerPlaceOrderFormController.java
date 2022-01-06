package controller.customer;

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
import model.Customer;

import model.CustomerOrder;
import model.CustomerProductDetail;
import model.Product;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CustomerOrderTm;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CustomerPlaceOrderFormController {
    public AnchorPane CustomerPlaceOrderFormContext;

    public TextField txtCustomerOrderId;
    public TextField txtCustomerOrderDate;
    public TextField txtCustomerOrderTime;


    public TextField txtCustomerOrderName;
    public TextField txtCustomerOrderMobile;

    public ComboBox<String> cmbCustomerOrderProductName;
    public TextField txtCustomerOrderProductId;
    public TextField txtCustomerOrderProductUnitPrice;
    public TextField txtCustomerOrderProductQtyOnHand;
    public TextField txtCustomerOrderProductTotal;

    public TableView<CustomerOrderTm> tblCustomerOrder;
    public TableColumn colCustomerPlaceOrderId;
    public TableColumn colCustomerPlaceOrderName;
    public TableColumn colCustomerPlaceOrderProductId;
    public TableColumn colCustomerPlaceOrderProductName;
    public TableColumn colCustomerPlaceOrderQtyOnHand;
    public TableColumn colCustomerPlaceOrderProductPrice;
    public TableColumn colCustomerPlaceOrderProductTotal;

    public Label lblCustomerOrderTotal;
    public ComboBox<String> cmbCustomerOrderNIC;
    public TextField txtCustomerOrderProductQty;


    int cartSelectedRowForRemove = -1;


    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        colCustomerPlaceOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerPlaceOrderName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerPlaceOrderProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colCustomerPlaceOrderProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colCustomerPlaceOrderQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCustomerPlaceOrderProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCustomerPlaceOrderProductTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


        cmbCustomerOrderNIC.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        cmbCustomerOrderProductName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setProductData(newValue);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tblCustomerOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });


        loadProductDetails();
        loadCustomerNICs();
        loadDateTime();
        generateOrderId();


    }


    private void setProductData(String newValue) throws SQLException, ClassNotFoundException {
        Product p1 = new ProductController().getProductById(newValue);
        if (p1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtCustomerOrderProductId.setText(String.valueOf(p1.getProductId()));
            txtCustomerOrderProductUnitPrice.setText(String.valueOf(p1.getPrice()));
            txtCustomerOrderProductQtyOnHand.setText(String.valueOf(p1.getQty()));
        }
    }

    private void loadProductDetails() throws SQLException, ClassNotFoundException {
        List<String> productNames = new ProductController()
                .getAllProductNames();
        cmbCustomerOrderProductName.getItems().addAll(productNames);
    }


    private void generateOrderId() {
        try {
            txtCustomerOrderId.setText(new CustomerOrderController().getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDateTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtCustomerOrderDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            txtCustomerOrderTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

    }

    private void loadCustomerNICs() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController()
                .getCustomerNICs();
        cmbCustomerOrderNIC.getItems().addAll(customerIds);
    }

    private void setCustomerData(String customerNIC) throws SQLException, ClassNotFoundException {
        Customer customer = new CustomerController().getCustomer(customerNIC);
        if (customer == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtCustomerOrderName.setText(customer.getName());
            txtCustomerOrderMobile.setText((customer.getMobileNumber()));
        }
    }


    ObservableList<CustomerOrderTm> obList = FXCollections.observableArrayList();

    public void CustomerPlaceOrderAddToCartOnAction(ActionEvent actionEvent) {
        String orderId = txtCustomerOrderId.getText();
        String customerName = txtCustomerOrderName.getText();
        int productId = Integer.parseInt(txtCustomerOrderProductId.getText());
        String productName = cmbCustomerOrderProductName.getValue();
        int qtyOnHand = Integer.parseInt(txtCustomerOrderProductQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtCustomerOrderProductUnitPrice.getText());
        int qtyForCustomer = Integer.parseInt(txtCustomerOrderProductQty.getText());
        double total = Double.parseDouble(txtCustomerOrderProductTotal.getText());


        CustomerOrderTm customerOrderTm = new CustomerOrderTm(
                orderId,
                customerName,
                productId,
                productName,
                qtyForCustomer,
                unitPrice,
                total

        );
        int rowNumber = isExists(customerOrderTm);

        if (rowNumber == -1) {
            obList.add(customerOrderTm);
        } else {
            CustomerOrderTm temp = obList.get(rowNumber);
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
            CustomerOrderTm newTm = new CustomerOrderTm(
                    temp.getOrderId(),
                    temp.getCustomerName(),
                    temp.getProductId(),
                    temp.getProductName(),
                    tableQty,
                    temp.getPrice(),
                    tableTotal
            );
            obList.remove(rowNumber);
            obList.add(newTm);
        }

        tblCustomerOrder.setItems(obList);
        calculateCost();

    }

    public void CustomerPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            ArrayList<CustomerProductDetail> productDetails = new ArrayList<>();
            double total = 0;

            for (CustomerOrderTm tempTm : obList) {
                total += tempTm.getTotal();
                productDetails.add(new CustomerProductDetail
                        (tempTm.getProductId(), tempTm.getQty(),
                                tempTm.getPrice(), tempTm.getTotal(), txtCustomerOrderDate.getText(), txtCustomerOrderTime.getText()));
            }

            CustomerOrder customerOrder = new CustomerOrder(
                    txtCustomerOrderId.getText(),
                    cmbCustomerOrderNIC.getValue(),
                    txtCustomerOrderDate.getText(),
                    txtCustomerOrderTime.getText(),
                    productDetails
            );
            if (new CustomerOrderController().placeOrder(customerOrder)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
                generateOrderId();
                showCustomerReport();
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
        txtCustomerOrderName.setText("");
        txtCustomerOrderMobile.setText("");
        cmbCustomerOrderProductName.setValue("");
        txtCustomerOrderProductId.setText("");
        txtCustomerOrderProductUnitPrice.setText("");
        txtCustomerOrderProductQtyOnHand.setText("");
        txtCustomerOrderProductQty.setText("");
        txtCustomerOrderProductTotal.setText("");

        for ( int i = 0; i<tblCustomerOrder.getItems().size(); i++) {
            tblCustomerOrder.getItems().clear();
        }

    }

    private void showCustomerReport() {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/Invoice2_Table_Based.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            ObservableList<CustomerOrderTm> items = tblCustomerOrder.getItems();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JRBeanArrayDataSource(items.toArray()));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private int isExists(CustomerOrderTm tm) {
        for (int i = 0; i < obList.size(); i++) {

            if (tm.getProductId() == obList.get(i).getProductId()) {
                return i;
            }
        }
        return -1;
    }


    private void calculateCost() {
        double ttl = 0;
        for (CustomerOrderTm tm : obList
        ) {
            ttl += tm.getTotal();
        }
        lblCustomerOrderTotal.setText(ttl + " /=");
    }


    public void CustomerPlaceOrderRemoveOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        } else {
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblCustomerOrder.refresh();
        }
    }


    public void EnterQtyOnAction(ActionEvent actionEvent) {

        try {
            int qtyOnHand = Integer.parseInt(txtCustomerOrderProductQtyOnHand.getText());
            double unitPrice = Double.parseDouble(txtCustomerOrderProductUnitPrice.getText());
            int enterQty = Integer.parseInt(txtCustomerOrderProductQty.getText());

            double total = enterQty * unitPrice;
            if (enterQty > qtyOnHand) {

                new Alert(Alert.AlertType.WARNING, "Qty is Not Enough Please Re Try").show();

            } else {
                txtCustomerOrderProductTotal.setText(String.valueOf(total));
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Value Is Empty Try Again").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void BackCustomerPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) CustomerPlaceOrderFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
    }


}
