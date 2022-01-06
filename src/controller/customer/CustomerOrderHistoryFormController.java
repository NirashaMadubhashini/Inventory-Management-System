package controller.customer;

import controller.customer.service.CustomerOrderService;
import db.DbConnection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerOrderDetailHistory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.TableCustomerHistoryLoadEvent;
import view.tm.CustomerOrderDetailTm;



import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerOrderHistoryFormController extends CustomerOrderController implements TableCustomerHistoryLoadEvent, CustomerOrderService {
    public AnchorPane CustomerOrderHistoryContext;



    public TableView<CustomerOrderDetailTm> tblCustomerHistory;

    public TableColumn colCustomerHistoryOrderId;
    public TableColumn colCustomerHistoryCustomerNIC;
    public TableColumn collCustomerHistoryProductId;
    public TableColumn colCustomerHistoryProductName;
    public TableColumn colCustomerHistoryUnitPrice;
    public TableColumn colCustomerHistoryQtyOnHand;
    public TableColumn colCustomerHistoryTotal;
    public TableColumn colCustomerHistoryDate;
    public TableColumn colCustomerHistoryTime;

    public Button btnCustomerReport;

    public TextField txtCustomerNICSearch;
    public TextField txtCustomerProductNameSearch;


    public void initialize() {
        try {

            colCustomerHistoryOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
            colCustomerHistoryCustomerNIC.setCellValueFactory(new PropertyValueFactory<>("customerNIC"));
            collCustomerHistoryProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
            colCustomerHistoryProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
            colCustomerHistoryUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colCustomerHistoryQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colCustomerHistoryTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
            colCustomerHistoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colCustomerHistoryTime.setCellValueFactory(new PropertyValueFactory<>("time"));

            txtCustomerNICSearch.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    searchNIC(newValue);
                }
            });

            try {
                loadAllCustomersToTable(new CustomerOrderController().getAllCustomerDetailHistory());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void searchNIC(String value) {
        try {
            List<CustomerOrderDetailHistory> customers= new CustomerOrderController().searchCustomerHistoryByNIC(value);
            loadAllCustomersToTable((ArrayList<CustomerOrderDetailHistory>) customers);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }




    public void CustomerNICSearchOnAction(ActionEvent actionEvent) {
        searchNIC(txtCustomerNICSearch.getText());
    }


    private void loadAllCustomersToTable(ArrayList<CustomerOrderDetailHistory> customerOrderDetailHistories) {
        try {
            ObservableList<CustomerOrderDetailTm> tmObservableList = FXCollections.observableArrayList();

            for (CustomerOrderDetailHistory temp : customerOrderDetailHistories
            ) {

                tmObservableList.add(
                        new CustomerOrderDetailTm(temp.getOrderId(), temp.getCustomerNIC(), temp.getProductId(),
                                temp.getProductName(), temp.getUnitPrice(), temp.getQtyOnHand(), temp.getTotal(),
                                temp.getDate(), temp.getTime())
                );
            }

            tblCustomerHistory.setItems(tmObservableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void generateSql_Chart(MouseEvent mouseEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/CustomerOrderHistory.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null,DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }



    @Override
    public void loadData() {
        try {
            loadAllCustomersToTable(new CustomerOrderController().getAllCustomerDetailHistory());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void BackCustomerOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) CustomerOrderHistoryContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
    }

}
