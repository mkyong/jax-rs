package com.mkyong.service;

import jakarta.inject.Named;
import org.jvnet.hk2.annotations.Service;

@Service @Named("aws")
public class MessageServiceAwsImpl implements MessageService {

    @Override
    public String getHello() {
        return "Hello from AWS";
    }

}