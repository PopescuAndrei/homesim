package ro.fils.smarthome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class Application extends javafx.application.Application {

    @Autowired
    Config config;

    private static String[] savedArgs;
    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        savedArgs = args;
        launch(Application.class, args);
    }

    @Override
    public void init() throws Exception {
        applicationContext = SpringApplication.run(getClass(), savedArgs);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
    }

}
