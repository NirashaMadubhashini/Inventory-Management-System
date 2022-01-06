package model;

public class Brand {
    private int brandId;
    private int brandCategoryId;
    private String brandName;
    private String date;
    private String time;

    public Brand(int brandId, int brandCategoryId, String brandName, String date, String time) {
        this.brandId = brandId;
        this.brandCategoryId = brandCategoryId;
        this.brandName = brandName;
        this.date = date;
        this.time = time;
    }

    public Brand(String brandName, String date, String time){
      this.setBrandName(brandName);
      this.setDate(date);
      this.setTime(time);
    }



    public Brand(int brandId, String brandName) {
        this.setBrandId(brandId);
        this.setBrandName(brandName);
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getBrandCategoryId() {
        return brandCategoryId;
    }

    public void setBrandCategoryId(int brandCategoryId) {
        this.brandCategoryId = brandCategoryId;
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



}
