package com.prj.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 사용할 DataBase가 설정이 완료 되면 해당 주석 해제 후 위 어노테이션 삭제
public class AwesomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwesomeApplication.class, args);
	}

}
