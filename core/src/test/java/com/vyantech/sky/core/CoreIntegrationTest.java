package com.vyantech.sky.core;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CoreTestSpringConfig.class)
public abstract class CoreIntegrationTest {

}
