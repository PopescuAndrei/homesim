package ro.fils.smarthome;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.fils.smarthome.service.HelloService;

public class MainApp extends Application {

    public HelloService helloService;

    @Override
    public void start(Stage stage) throws Exception {
        initBeans();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();

        helloService.hello();
    }

    /**
     * Main app needed to run it from the spring application context
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }

    @PostConstruct
    public void initBeans() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        helloService = ctx.getBean(HelloService.class);
    }
}
