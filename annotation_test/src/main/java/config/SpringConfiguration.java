package config;

import com.yz.user.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {UserService.class})
public class SpringConfiguration {
}
