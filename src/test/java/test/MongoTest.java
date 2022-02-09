package test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class MongoTest {

    @Container
    private static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:" + System.getProperty("mongo", "5.0"));

    @Test
    void canRun(){
        assertTrue(true);
    }
}
