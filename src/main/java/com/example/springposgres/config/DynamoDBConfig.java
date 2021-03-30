package com.example.springposgres.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.springposgres.repository")
public class DynamoDBConfig {
    private static final String AWS_ACCESS_KEY_ID = "AWS_ACCESS_KEY_ID";
    private static final String AWS_SECRET_KEY = "AWS_SECRET_KEY";

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Autowired
    private Environment environment;

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(environment.getProperty(AWS_ACCESS_KEY_ID),
                environment.getProperty(AWS_SECRET_KEY));
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(amazonAWSCredentialsProvider())
                .build();
    }

    @Bean
    public DynamoDB dynamoDB() {
        return new DynamoDB(amazonDynamoDB());
    }

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }
}
