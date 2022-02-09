package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@Testcontainers
class TestConfigTest {

    @Container
    private static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:" + System.getProperty("mongo", "5.0"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected MongoDatabaseFactory mongoDatabaseFactory;

    @Test
    void canRun() throws IOException {
        System.out.println(mongoDBContainer.getContainerInfo().getConfig().getImage());

        Files.write(Paths.get("output.txt"),
                mongoDBContainer.getContainerInfo().getConfig().getImage().getBytes());
    }

    @Test
    void getMongoTemplate(){
        var testConfig = new TestConfig();
        testConfig.mongoTemplate = mongoTemplate;

        assertNotNull(testConfig.getMongoTemplate());
    }
}