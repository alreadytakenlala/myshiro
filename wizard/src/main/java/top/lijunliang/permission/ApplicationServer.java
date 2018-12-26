package top.lijunliang.permission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("top.lijunliang.permission.dao")
@ServletComponentScan(basePackages = {"top.lijunliang.permission.filter"})
public class ApplicationServer
{
    public static void main(String[] args)
    {
        SpringApplication.run(ApplicationServer.class, args);
    }
}
