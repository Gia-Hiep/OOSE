# Spring Boot Interceptor Demo

## Features
- `HandlerInterceptor` đo thời gian xử lý mỗi request (log ra console).
- Chặn truy cập nếu chưa đăng nhập (dựa trên `HttpSession`), redirect về `/login`.
- Giao diện Thymeleaf tối giản: `/login`, `/home`, `/403`.

## Run
```bash
mvn spring-boot:run
# hoặc
mvn clean package
java -jar target/interceptor-demo-0.0.1-SNAPSHOT.jar
```

## Try
- Mở http://localhost:8080/home -> sẽ bị redirect về `/login` nếu chưa đăng nhập.
- Nhập username bất kỳ (ví dụ `admin`) -> chuyển vào `/home`.
- Xem log thời gian xử lý trong console.
