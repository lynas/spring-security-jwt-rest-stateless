package com.lynas.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by LynAs on 20-Jan-16
 */
@EnableWebMvc
@ComponentScan("com.lynas")
public class WebConfig {

}
