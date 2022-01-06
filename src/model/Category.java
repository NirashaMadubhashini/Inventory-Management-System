package model;


public class Category  {
  private int categoryId;
  private String categoryName;
  private String description;
  private String date;
  private String time;


    public Category(int categoryId, String categoryName, String description, String date, String time) {
        this.setCategoryId(categoryId);
        this.setCategoryName(categoryName);
        this.setDescription(description);
        this.setDate(date);
        this.setTime(time);

    }

   public Category(int categoryId,String categoryName,String description){
        this.setCategoryId(categoryId);
        this.setCategoryName(categoryName);
        this.setDescription(description);
   }

    public Category(String categoryName, String description, String date, String time) {
        this.setCategoryName(categoryName);
        this.setDescription(description);
        this.setDate(date);
        this.setTime(time);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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


}
