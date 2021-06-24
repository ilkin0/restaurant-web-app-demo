package com.ilkinmehdiyev.restaurantwebappdemo;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Config.class)
@AutoConfigureMockMvc
abstract public class TestsConfiguration {
}
