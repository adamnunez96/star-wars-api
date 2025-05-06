package com.anunez.conexa.star.wars;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:star_wars",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "SERVICE_URL=http://localhost:8080/v1",
    "DATABASE_IP=localhost",
    "application.security.jwt.secret-key=ZjUzOWRmZDE2ZjgzNWYwYjljZWU0Y2NkYTdiY2JhYWMxYTZiMGZmZDFkNDBhY2EzMGUxZTQ3MjRkMWFkMjVjZA==",
    "application.security.jwt.expiration=1800000"
})
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
