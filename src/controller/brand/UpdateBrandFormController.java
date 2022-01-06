package controller.brand;

import controller.brand.service.BrandService;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Brand;
import util.TableBrandLoadEvent;


import java.sql.SQLException;

public class UpdateBrandFormController extends BrandController implements BrandService {
    public AnchorPane UpdateBrandFormContext;
    public TextField txtUpdateBrandName;
    public int brandId;
    public Button btnUpdateBrand;

    private TableBrandLoadEvent event;

    public void UpdateBrandOnAction(ActionEvent actionEvent) {
        Brand b = new Brand(
                brandId,
                txtUpdateBrandName.getText()
        );
        try {
            if (new BrandController().updateBrand(b)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();

                Stage stage = (Stage) btnUpdateBrand.getScene().getWindow();
                stage.close();

                event.loadData();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setEvent(TableBrandLoadEvent event) {
        this.event = event;
    }

}
