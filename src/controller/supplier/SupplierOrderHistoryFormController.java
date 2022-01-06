package controller.supplier;

import controller.supplier.service.SupplierOrderService;

import db.DbConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.SupplierOrderDetailHistory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.TableSupplierHistoryLoadEvent;
import view.tm.SupplierOrderDetailTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierOrderHistoryFormController extends SupplierOrderController implements TableSupplierHistoryLoadEvent, SupplierOrderService {
    public AnchorPane SupplierOrderHistoryContext;


    public TableView<SupplierOrderDetailTm> tblSupplierHistory;

    public TableColumn colSupplierHistoryOrderId;
    public TableColumn colSupplierHistorySupplierNIC;
    public TableColumn colSupplierHistoryProductId;
    public TableColumn colSupplierHistoryProductName;
    public TableColumn colSupplierHistoryUnitPrice;
    public TableColumn colSupplierHistoryQtyOnHand;
    public TableColumn colSupplierHistoryTotal;
    public TableColumn colSupplierHistoryDate;
    public TableColumn colSupplierHistoryTime;

    public TextField txtSupplierNICSearch;



    public void initialize() {
        try {

            colSupplierHistoryOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
            colSupplierHistorySupplierNIC.setCellValueFactory(new PropertyValueFactory<>("supplierNIC"));
            colSupplierHistoryProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
            colSupplierHistoryProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
            colSupplierHistoryUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colSupplierHistoryQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colSupplierHistoryTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
            colSupplierHistoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colSupplierHistoryTime.setCellValueFactory(new PropertyValueFactory<>("time"));


            txtSupplierNICSearch.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    searchSupplierNIC(newValue);
                }

            });



            try {
                loadAllSupplierToTable(new SupplierOrderController().getAllSupplierDetailHistory());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void SupplierNICSearchOnAction(ActionEvent actionEvent) {
        searchSupplierNIC(txtSupplierNICSearch.getText());
    }
    private void searchSupplierNIC(String value) {
        try {
            List<SupplierOrderDetailHistory> suppliers = new SupplierOrderController().searchSupplierHistoryByNIC(value);
            loadAllSupplierToTable((ArrayList<SupplierOrderDetailHistory>)suppliers);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    private void loadAllSupplierToTable(ArrayList<SupplierOrderDetailHistory> supplierOrderDetailHistories) {
        try {
            ObservableList<SupplierOrderDetailTm> tmObservableList = FXCollections.observableArrayList();

            for (SupplierOrderDetailHistory temp : supplierOrderDetailHistories
            ) {
                tmObservableList.add(
                        new SupplierOrderDetailTm(temp.getOrderId(), temp.getSupplierNIC(), temp.getProductId(),
                                temp.getProductName(), temp.getUnitPrice(), temp.getQtyOnHand(), temp.getTotal(),
                                temp.getDate(), temp.getTime())
                );
            }
            tblSupplierHistory.setItems(tmObservableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void SupplierShowReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/SupplierHistory.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    @Override
    public void loadData() {
        try {
            loadAllSupplierToTable(new SupplierOrderController().getAllSupplierDetailHistory());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void BackSupplierOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) SupplierOrderHistoryContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
    }

}
