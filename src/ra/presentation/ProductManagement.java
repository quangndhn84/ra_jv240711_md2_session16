package ra.presentation;

import ra.bussiness.ProductBussiness;
import ra.entity.Product;

import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    public static void displayProductMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("***************PRODUCT MENU***************");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListProduct();
                    break;
            }
        } while (isExit);
    }

    public static void displayListProduct() {
        List<Product> listProducts = ProductBussiness.findAll();
        listProducts.forEach(Product::displayData);
    }
}
