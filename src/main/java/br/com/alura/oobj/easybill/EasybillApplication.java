package br.com.alura.oobj.easybill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EasybillApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasybillApplication.class, args);
	}

}
