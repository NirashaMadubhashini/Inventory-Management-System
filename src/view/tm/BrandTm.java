package view.tm;

import javafx.scene.control.Button;

public class BrandTm {
     private int brandId;
     private String categoryName;
     private String brandName;
     private String date;
     private String time;
     private Button update;
     private Button delete;



    public BrandTm(int brandId, String categoryName, String brandName, String date, String time, Button update, Button delete) {
        this.setBrandId(brandId);
        this.setCategoryName(categoryName);
        this.setBrandName(brandName);
        this.setDate(date);
        this.setTime(time);
        this.setUpdate(update);
        this.setDelete(delete);
    }


    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
