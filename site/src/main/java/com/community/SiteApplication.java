package com.community;

import org.broadleafcommerce.common.config.EnableBroadleafSiteAutoConfiguration;
import org.broadleafcommerce.core.offer.service.OfferService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@SpringBootApplication
@EnableAutoConfiguration
public class SiteApplication {





    @Configuration
    @EnableBroadleafSiteAutoConfiguration
    public static class BroadleafFrameworkConfiguration {

        @Resource(name="weekendOfferService")
        private OfferService offerService;


        @Bean(name="blOfferService")
        public OfferService injectWeekendDiscount() {
            return offerService ;

        }

    }




    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);
    }
    
}
