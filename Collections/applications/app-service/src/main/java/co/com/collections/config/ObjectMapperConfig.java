package co.com.collections.config;

import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

    @Configuration
    @ComponentScan(basePackages = "co.com.collections.usecase",
            includeFilters = {
                    @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
            },
            useDefaultFilters = false)
    public static class UseCasesConfig {
    }

    @Configuration
    @ComponentScan(basePackages = "co.com.collections.api.validator",
            includeFilters = {
                    @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Validator$")
            },
            useDefaultFilters = false)
    public static class ValidatorsConfig {
    }
}
