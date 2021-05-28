package com.mkyong.service;

import jakarta.inject.Named;
import org.jvnet.hk2.annotations.Service;

@Service @Named("azure")
public class MessageServiceAzureImpl implements MessageService {

    @Override
    public String getHello() {
        return "Hello from Azure";
    }

}