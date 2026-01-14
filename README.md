# SOFT4003

Bài tập Kiểm thử phần mềm

## Tuần 1: 5/1/2026 - 11/1/2026 :

- 5/1/2026 Hoàn thành Bài tập thực hành tuần 1 : Cantunsee ; Điểm số :6130  ; Ảnh minh chứng : Screenshot 2026-01-05 163223.png

- 7/1/2026 :Student Analyzer - Unit Test with JUnit

  - Mô tả

    Chương trình phân tích điểm số học sinh:

    Đếm số học sinh đạt loại Giỏi (>= 8.0)
    Tính điểm trung bình các điểm hợp lệ (0–10)

    Dữ liệu không hợp lệ (<0 hoặc >10) sẽ bị bỏ qua.

  - Công nghệ

    - Java 21
    - Maven
    - JUnit 5

  - Cấu trúc thư mục
    unit-test/
    ├── src/main/java/analyzer
    ├── src/test/java/analyzer
    └── pom.xml
    ## Update
- Updated README on 2026-01-12
Bài tập Thực hành Kiểm thử Tự động End-to-End với Cypress
📝 Giới thiệu
Dự án này thực hiện kiểm thử tự động cho quy trình mua hàng trên trang web mẫu SauceDemo. Sử dụng framework Cypress để đảm bảo các tính năng cốt lõi của trang thương mại điện tử hoạt động ổn định.

🚀 Các kịch bản kiểm thử (Test Cases)
File kiểm thử chính: cypress/e2e/lab_test.cy.js bao gồm:

Kiểm tra Đăng nhập:

Xác minh đăng nhập thất bại khi sai thông tin (hiển thị thông báo lỗi).

Xác minh đăng nhập thành công với tài khoản standard_user.

Quản lý Giỏ hàng:

Thêm sản phẩm đầu tiên vào giỏ hàng và kiểm tra số lượng hiển thị trên Badge.

Xóa sản phẩm khỏi giỏ hàng và xác minh Badge biến mất.

Quy trình Thanh toán (Checkout):

Thực hiện luồng từ giỏ hàng -> nhập thông tin (First Name, Last Name, Zip Code) -> Tiếp tục đến trang xác nhận (Checkout: Overview).

🛠 Yêu cầu hệ thống
Node.js: Phiên bản 14 trở lên.

Cypress: v15.9.0 (hoặc phiên bản mới nhất bạn đang dùng).

📖 Hướng dẫn cài đặt và chạy
1. Cài đặt môi trường
Mở terminal tại thư mục dự án và chạy lệnh:

Bash

npm install
2. Chạy kiểm thử bằng giao diện (Cypress Open)
Bash

npx cypress open
Sau khi cửa sổ hiện lên, chọn E2E Testing -> Chrome -> lab_test.cy.js.

3. Chạy kiểm thử ở chế độ ẩn (Cypress Run)
Bash

npx cypress run
🎥 Minh chứng kết quả
Ảnh chụp màn hình: Lưu trong thư mục cypress/screenshots.

Video demo: [Nếu bạn đã quay video, hãy ghi "Đính kèm trong thư mục dự án" hoặc dán link Google Drive/YouTube vào đây].