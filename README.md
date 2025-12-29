# abBankTest

---

## 1. Setup Instructions

1. **Clone the repository**

```bash
git clone https://github.com/s2light/abBankTest.git
```
2. Project Structure
```
src/
├── main/
│   └── java/                
└── test/
    ├── java/                 # Mã nguồn kiểm thử
    │   ├── pages/            # Page Objects (định nghĩa element UI)
    │   ├── runners/          # Các file cấu hình để chạy Test (Test Runner)
    │   ├── stepdefinitions/  # Code xử lý logic cho từng bước trong file feature
    │   └── utils/            # Các hàm bổ trợ dùng chung (Actions, Helpers)
    └── resources/            # Tài nguyên phục vụ kiểm thử
        ├── features/         # Kịch bản kiểm thử viết bằng Gherkin
        │   ├── api/          # Các kịch bản kiểm thử API
        │   └── web/          # Các kịch bản kiểm thử giao diện Web
        └── serenity.conf     # File cấu hình môi trường và WebDriver
```
3. How to Run UI Tests
```
mvn clean verify -Dtest=UITestSuite
```
4 How to Run Api Tests
```
mvn clean verify -Dtest=ApiTestSuite
```
5.Html Report: [target/site/serenity/index.html]()

6.[Watch Test Run Video](ABBank.mp4)