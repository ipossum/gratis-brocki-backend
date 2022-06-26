package ch.zhaw.gratisbrockibackend;

import ch.zhaw.gratisbrockibackend.utils.HasLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class GratisBrockiBackendApplication implements HasLogger {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(GratisBrockiBackendApplication.class, args);
    }

    @PostConstruct
    public void afterInit() {
        boolean hasDevProfile = Arrays.asList(env.getActiveProfiles()).contains("dev");
        boolean hasH2Database = Arrays.asList(env.getActiveProfiles()).contains("h2");
        String applicationName = env.getProperty("spring.application.name");
        String openApiInfo="";
        String h2ConsoleInfo="";
        if (hasDevProfile) {
            openApiInfo = """
         http://localhost:8080/v3/api-docs
         http://localhost:8080/v3/api-docs.yaml -> yaml file is downloaded -> https://editor.swagger.io/
         http://localhost:8080/swagger-ui.html\s
         """;
        }
        if (hasH2Database) {
            h2ConsoleInfo= "http://localhost:8080/h2-console " + "" +
                    "-> mit Generic H2 (Embedded), org.h2.Driver, jdbc:h2:mem:testdb und sa \n";
        }
        System.out.println("\nApplication [" + applicationName + "] - Enter in Browser:\nhttp://localhost:8080 \n" +
                openApiInfo +
                h2ConsoleInfo + "\n" +
                "Active Profiles: " + Arrays.toString(env.getActiveProfiles()) + "\n");
    }
}
