package com.yearn.life;

import com.yearn.life.utils.CustomMultipartResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;


@Configuration
@EnableCaching
@SpringBootApplication
public class YearnApplication extends SpringBootServletInitializer {

	//注入自定义的文件上传处理类
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver()
	{
		return new CustomMultipartResolver();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(YearnApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(YearnApplication.class, args);
	}
}
