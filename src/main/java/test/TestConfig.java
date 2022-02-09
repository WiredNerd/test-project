package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class TestConfig {

    @Autowired
    MongoTemplate mongoTemplate;
}
