/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ro.fils.smarthome.service.HelloService;

/**
 *
 * @author andre
 */
@Configuration
@ComponentScan
public class App {
    
    public static void main(String[] args){
        MainApp.main(args);
    }
    
}
