package fr.formation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.formation.config.AppConfig;

public class SpringApplication {
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext yoSpring = new AnnotationConfigApplicationContext(AppConfig.class);
		yoSpring.getBeanFactory().createBean(Application.class).run(args);
		
		yoSpring.close();
	}
}
