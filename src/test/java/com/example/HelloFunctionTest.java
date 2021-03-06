package com.example;

import com.example.model.Greeting;
import com.example.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloFunctionTest {

    @Test
    public void test() {
        Greeting result = new HelloFunction().apply(new User("foo"));
        assertThat(result.getMessage()).isEqualTo("Hello, foo!\n");
    }

    @Test
    public void start() throws Exception {
        AzureSpringBootRequestHandler<User, Greeting> handler = new AzureSpringBootRequestHandler<>(
                HelloFunction.class);
        Greeting result = handler.handleRequest(new User("foo"), null);
        handler.close();
        assertThat(result.getMessage()).isEqualTo("Hello, foo!\n");
    }
}
