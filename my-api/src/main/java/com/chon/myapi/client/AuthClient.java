package com.chon.myapi.client;

import com.chon.myapi.client.dto.AuthRequestDto;
import com.chon.myapi.client.dto.AuthResponseDto;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "authClient", url = "${auth.host}",
        configuration = AuthClient.Configuration.class)
public interface AuthClient {
    @RequestMapping(method = RequestMethod.POST, value = "/oauth2/token",  consumes = "application/x-www-form-urlencoded")
    AuthResponseDto token(@RequestBody AuthRequestDto authRequestDto);

    class Configuration {
        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }
    }
}
