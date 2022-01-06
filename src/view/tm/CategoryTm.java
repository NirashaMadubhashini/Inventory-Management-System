package view.tm;

import javafx.scene.control.Button;

public class CategoryTm {
    private int categoryId;
    private String categoryName;
    private String description;
    private String date;
    private String time;
    private Button update;
     private Button delete;

    public CategoryTm(int categoryId, String categoryName, String description, String date, String time) {
    }

    public CategoryTm(int categoryId,String categoryName, String description, String date, String time, Button update,Button delete) {
        this.setCategoryId(categoryId);
        this.setCategoryName(categoryName);
        this.setDescription(description);
        this.setDate(date);
        this.setTime(time);
        this.setUpdate(update);
        this.setDelete(delete);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
