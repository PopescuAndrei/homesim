package ro.fils.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.fils.smarthome.view.StartFrame;

@SpringBootApplication
public class Application extends SpringApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        StartFrame frame = new StartFrame(context);
        frame.setVisible(true);
    }

}
