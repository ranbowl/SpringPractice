package com.springpractice.api.service;

import java.io.IOException;

public interface MqListnerService {
    void listner(String message) throws IOException;
}
