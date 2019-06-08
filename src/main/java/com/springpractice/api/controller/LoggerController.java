package com.springpractice.api.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logger")
public class LoggerController {

	@PostMapping("/{logLevel}")
    public void changeAllLogLevel(@PathVariable String logLevel) {
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        Collection<Logger> loggers = loggerContext.getLoggers();
        loggers.stream().forEach(l -> l.setLevel(Level.toLevel(logLevel)));
    }

    @PostMapping("/{logName}/{logLevel}")
    public ResponseEntity<Object> changeOneLogLevel(@PathVariable String logName, @PathVariable String logLevel) {
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        Configurator.setLevel(logName, Level.toLevel(logLevel));
        return ResponseEntity.ok().body(LogManager.getContext(false).getLogger(logName).getName() +
                " level -> " + LogManager.getContext().getLogger(logName).getLevel());
    }

    @GetMapping("")
    public List<String> getAllLogLevel() {
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        Collection<Logger> loggers = loggerContext.getLoggers();
        return loggers.stream().map(l -> l.getName() + " -> " + l.getLevel()).collect(Collectors.toList());
    }
}
