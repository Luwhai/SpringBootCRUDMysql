![register page](https://github.com/Luwhai/SpringBootCRUDMysql/blob/main/pic2.png)
![login page](https://github.com/Luwhai/SpringBootCRUDMysql/blob/main/pic3.png)
![list page](https://github.com/Luwhai/SpringBootCRUDMysql/blob/main/pic4.png)

# SpringBootCRUDMysql
这是一个简单的CRUD 应用

## Features
- 基本的MySQL CRUD 操作
- Spring Boot 和 Thymeleaf的 MVC 架构
- JPA and Hibernate 
- 前端使用Bootstrap 5
- 分页
- 登录拦截器
- dark theme
- keyword搜索
- swagger

## 使用技术
- **Java**
- **Spring Boot**
- **JPA & Hibernate**
- **MySQL**
- **Thymeleaf**
- **Bootstrap**
- **swagger**

## 必要条件
- Java 17 or later
- Spring Boot 3.3
- Maven 3.6+ (optional)
- MySQL Server
- IntelliJ IDEA (or any preferred IDE)

## Application Structure
- Controller: Handles HTTP requests.
- Model: Represents the entities mapped to the database.
- Repository: Interfaces for JPA queries.
- Service: Contains the business logic.

## Endpoints
- GET /users: 显示所有用户.
- POST /users/register: 注册新用户.
- GET /users/edit/{id}: 编辑指定用户.
- POST //users/save: 保存新用户或者编辑指定用户
- GET /users/delete/{id}: 删除指定用户.

## Project Setup

### 1. Clone the Repository

### 2. Configure database
src/main/resources/application.properties
    
### 3.Build and Run the Project

### 4.Access the Application
    http://localhost:8080

### 5.访问 Swagger UI
	OpenAPI JSON 文档：http://localhost:8080/v3/api-docs

    Swagger UI 界面：http://localhost:8080/swagger-ui.html


