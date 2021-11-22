package com.example.JDBC.JDBChomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceInitializationMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.shell.SpringShellAutoConfiguration;
import org.springframework.shell.jline.JLineShellAutoConfiguration;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@SpringBootApplication()
public class JdbChomeworkApplication {

	public static void main(String[] args) {
		/*String[] disabledCommands = {"--spring.shell.interactive.enabled=false ",
									 "--spring.shell.script.spring.shell.script=false"};
		String[] fullArgs = StringUtils.concatenateStringArrays(args, disabledCommands);*/

		SpringApplication.run(JdbChomeworkApplication.class, args);
	}

}