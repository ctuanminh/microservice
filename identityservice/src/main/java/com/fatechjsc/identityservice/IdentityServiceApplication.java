package com.fatechjsc.identityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication //Nơi sẽ khỏi chạy ứng dụng
@EnableDiscoveryClient // Enable security
public class IdentityServiceApplication {

	//Spring khởi chạy tạo ra Container có tên ApplicationContext
	//Đặt tên các Dependency là Bean.
	//Làm sao biết đâu là Dependency để tạo Bean? Trong spring có
	//@Annotation @Component đánh dấu trên class để spring biết đó là một Bean
	//Khi khởi chạy, Spring dò tìm các class có đánh dấu là @Component.Khi thấy nó sẽ
	//tạo ra 1 instance và đưa vào Context để quản lý.
	public static void main(String[] args) {
		SpringApplication.run(IdentityServiceApplication.class, args);
	}

}
