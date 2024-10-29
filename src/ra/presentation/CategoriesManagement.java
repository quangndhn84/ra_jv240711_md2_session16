package ra.presentation;

import ra.bussiness.CategoriesBussiness;
import ra.entity.Categories;

import java.util.List;
import java.util.Scanner;

public class CategoriesManagement {
    public static void displayMenuCategories(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************CATEGORIES MENU**************");
            System.out.println("1. Danh sách danh mục");
            System.out.println("2. Thêm mới danh mục");
            System.out.println("3. Cập nhật danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Thống kê danh mục theo mã danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListCategories();
                    break;
                case 2:
                    createCategories(scanner);
                    break;
                case 3:
                    updateCategories(scanner);
                    break;
                case 4:
                    deleteCategories(scanner);
                    break;
                case 5:
                    statiticCategories(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }

    public static void displayListCategories() {
        List<Categories> listCategories = CategoriesBussiness.findAll();
        listCategories.stream().forEach(System.out::println);
    }

    public static void createCategories(Scanner scanner) {
        Categories catalogNew = new Categories();
        System.out.println("Nhập vào tên danh mục:");
        catalogNew.setCatalogName(scanner.nextLine());
        System.out.println("Nhập vào mô tả danh mục:");
        catalogNew.setDescription(scanner.nextLine());
        System.out.println("Nhập vào trạng thái danh mục:");
        catalogNew.setStatus(Boolean.parseBoolean(scanner.nextLine()));
        boolean result = CategoriesBussiness.save(catalogNew);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.err.println("Thêm mới thất bại");
        }
    }

    public static void updateCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        //1. Lấy thông tin danh mục cần cập nhật từ database
        Categories catalogUpdate = CategoriesBussiness.findById(catalogId);
        if (catalogUpdate != null) {
            //2. Cho phép sửa dữ liệu trên catalogUpdate
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên danh mục");
                System.out.println("2. Cập nhật mô tả danh mục");
                System.out.println("3. Cập nhật trạng thái danh mục");
                System.out.println("4. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        catalogUpdate.setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        catalogUpdate.setDescription(scanner.nextLine());
                        break;
                    case 3:
                        catalogUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-4");
                }
            } while (isExit);
            //3. Cập nhật dữ liệu vào database
            boolean result = CategoriesBussiness.update(catalogUpdate);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Cập nhật thất bại");
            }
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void deleteCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa");
        int catalogId = Integer.parseInt(scanner.nextLine());
        Categories catalogDelete = CategoriesBussiness.findById(catalogId);
        if (catalogDelete != null) {
            boolean result = CategoriesBussiness.delete(catalogId);
            if (result) {
                System.out.println("Xóa danh mục thành công");
            } else {
                System.err.println("Xóa danh mục thất bại");
            }
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void statiticCategories(Scanner scanner) {
        System.out.println("Nhập trạng thái danh mục cần thống kê");
        boolean catalogStatus = Boolean.parseBoolean(scanner.nextLine());
        int cntCategories = CategoriesBussiness.staticticCategories(catalogStatus);
        System.out.printf("Có %d danh mục có trạng thái là %b\n", cntCategories, catalogStatus);
    }
}
