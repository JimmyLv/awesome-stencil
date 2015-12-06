package info.jimmylv.www.config;

import info.jimmylv.www.controller.HealthController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HealthController.class);
    }
}
