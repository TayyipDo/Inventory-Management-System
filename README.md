# Envanter Yönetim Sistemi (Spring Boot)

Bu proje, Spring Boot ve Java kullanılarak geliştirilmiş, küçük ve orta-büyük ölçekli işletmeler için hazırlanmış tam özellikli bir Envanter Yönetim Sistemi'dir. Uygulama, işletmelerin envanter takibini dijitalleştirmeyi, stok hatalarını ve veri kayıplarını azaltmayı hedeflemektedir.

Proje; Spring Boot, Spring Data JPA, Spring MVC, Thymeleaf ve MySQL teknolojileri kullanılarak katmanlı mimari (MVC) prensiplerine uygun olarak geliştirilmiştir.

## 🚀 Temel Özellikler

* **Kullanıcı Yönetimi:** Güvenli kullanıcı kayıt (`/register`) ve giriş (`/login`) sistemi.
* **Modüler Yapı:**
    * **Kategori Yönetimi:** Ürünler için dinamik kategori ekleme ve silme işlemleri.
    * **Ürün Yönetimi (Mevcut Stok):** Envanterdeki ürünleri ekleme, düzenleme, silme ve listeleme (CRUD).
    * **Alınacak Ürünler:** Satın alınması planlanan ürünlerin takibi ve "alındı" olarak işaretlenmesi.
    * **Satılmış Ürünler:** Müşteri adı ve telefon bilgisiyle birlikte satışı yapılan ürünlerin kaydı.
* **Raporlama (Dışa Aktarma):** Tüm modüllerdeki (Ürünler, Alınacaklar, Satılanlar) verilerin **PDF** ve **Excel** formatlarında dışa aktarılması.
* **Dinamik Arayüz:** `home.html` üzerinde tüm modüllere kolay erişim sağlayan bir grid menü. Verilerin listelenmesi için Thymeleaf şablon motoru kullanılmıştır.
* **API Testleri:** Tüm `GET` ve `POST` endpoint'lerinin Postman ile test edilmesi.

## 🛠️ Kullanılan Teknolojiler

Projenin geliştirilmesinde kullanılan ana teknolojiler:

* **Backend:**
    * Java 21
    * Spring Boot 3.x
    * Spring MVC
    * Spring Data JPA
    * Maven
    * Lombok
* **Frontend:**
    * Thymeleaf
    * HTML/CSS/JavaScript
* **Veritabanı:**
    * MySQL
    * (Yönetim için MySQL Workbench veya DBeaver kullanılmıştır)
* **Raporlama:**
    * Apache POI (Excel için)
    * OpenPDF (PDF için)

## ⚙️ Kurulum

1.  **Depoyu Klonlayın:**
    ```bash
    git clone [https://github.com/](https://github.com/)[kullaniciadiniz]/[depoadiniz].git
    cd [depoadiniz]
    ```

2.  **Veritabanını Ayarlayın:**
    * MySQL Workbench, DBeaver veya phpMyAdmin kullanarak yeni bir MySQL veritabanı (örn: `inventory_db`) oluşturun.
    * `src/main/resources/` klasöründe `application.properties.example` (eğer oluşturduysanız) dosyasının bir kopyasını `application.properties` olarak oluşturun.
    * `application.properties` dosyasını kendi veritabanı ayarlarınızla güncelleyin:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
    spring.datasource.username=root
    spring.datasource.password=sifreniz
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    ```

3.  **Projeyi Çalıştırın:**
    * Projeyi bir IDE (IntelliJ IDEA) üzerinden açın ve çalıştırın.
    * Veya terminal üzerinden Maven kullanarak çalıştırın:
    ```bash
    mvn spring-boot:run
    ```

4.  **Uygulamaya Erişin:**
    * Tarayıcınızdan `http://localhost:8080` adresine gidin.

## 📈 Gelecek Geliştirmeleri

Mevcut sürümde yer almayan ancak gelecek için planlanan özellikler:

* **Yetkilendirme:** Kullanıcı rollerine (Admin/User) göre yetkilendirme.
* **Arama:** Ürün filtreleme veya arama özelliği.


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Inventory Management System (Spring Boot)

This project is a full-featured Inventory Management System for small to medium-large sized businesses, developed using Spring Boot and Java. The application aims to digitize inventory tracking for businesses, reducing stock errors and data loss.

The project was developed using Spring Boot, Spring Data JPA, Spring MVC, Thymeleaf, and MySQL, following the layered MVC architecture principles.

## 🚀 Key Features

* **User Management:** Secure user registration (`/register`) and login (`/login`) system.
* **Modular Structure:**
    * **Category Management:** Dynamically add and delete product categories.
    * **Product Management (Current Stock):** Full CRUD operations (Create, Read, Update, Delete) for products in the inventory.
    * **Purchase Management (Incoming Products):** Track planned purchases and mark them as "received".
    * **Sales Management (Sold Products):** Log sold items along with customer name and phone information.
* **Reporting (Export):** Export data from all modules (Products, Purchases, Sales) to **PDF** and **Excel** formats.
* **Dynamic Interface:** A grid menu on `home.html` for easy navigation to all modules. Uses the Thymeleaf template engine for dynamically rendering data.
* **API Testing:** All `GET` and `POST` endpoints were tested using Postman.

## 🛠️ Technology Stack

The main technologies used in this project:

* **Backend:**
    * Java 21
    * Spring Boot 3.x
    * Spring MVC
    * Spring Data JPA
    * Maven
    * Lombok
* **Frontend:**
    * Thymeleaf
    * HTML/CSS/JavaScript
* **Database:**
    * MySQL
    * (Managed with MySQL Workbench or DBeaver)
* **Reporting:**
    * Apache POI (For Excel)
    * OpenPDF (For PDF)

## ⚙️ Setup

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/](https://github.com/)[yourusername]/[repositoryname].git
    cd [repositoryname]
    ```

2.  **Set Up the Database:**
    * Create a new MySQL database (e.g., `inventory_db`) using a tool like MySQL Workbench, DBeaver, or phpMyAdmin.
    * Create a copy of `application.properties.example` (if you have one) in `src/main/resources/` and name it `application.properties`.
    * Update `application.properties` with your own database configuration:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    ```

3.  **Run the Project:**
    * Open and run the project from your IDE (e.g., IntelliJ IDEA).
    * Or, run it using Maven from the terminal:
    ```bash
    mvn spring-boot:run
    ```

4.  **Access the Application:**
    * Open `http://localhost:8080` in your browser.

## 📈 Future Enhancements

Features not included in the current version but planned for future development:

* **Authorization:** Role-based authorization (Admin/User).
* **Search:** Product filtering or search functionality.
