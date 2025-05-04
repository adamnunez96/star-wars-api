package com.anunez.conexa.star.wars;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:star_wars",
    "spring.datasource.username=admin",
    "spring.datasource.password=admin",
	"spring.datasource.driver-class-name=org.h2.Driver",
    "spring.jpa.hibernate.ddl-auto=create-drop",
	"SERVICE_URL=http://localhost:8080/v1"
})
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
