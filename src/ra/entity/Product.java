package ra.entity;

import ra.bussiness.CategoriesBussiness;

import java.util.Scanner;

public class Product implements ICommerce {
    private String productId;
    private String productName;
    private int catalogId;

    public Product() {
    }

    public Product(String productId, String productName, int catalogId) {
        this.productId = productId;
        this.productName = productName;
        this.catalogId = catalogId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public void inputData(Scanner scanner) {
        //Nhập dữ liệu cho product
    }

    @Override
    public void displayData() {
        System.out.printf("Mã SP: %s - Tên SP: %s - Tên DM: %s\n", this.productId, this.productName, CategoriesBussiness.findById(this.catalogId).getCatalogName());
    }
}
