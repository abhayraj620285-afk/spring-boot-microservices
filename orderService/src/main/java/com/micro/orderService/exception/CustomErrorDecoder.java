package com.micro.orderService.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeInvisTypeAnnos;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if(response.status()==404){
            return new RuntimeException("Product Not Found");
        }
        return new RuntimeException("Generic Error "+ response.status());
    }
}
