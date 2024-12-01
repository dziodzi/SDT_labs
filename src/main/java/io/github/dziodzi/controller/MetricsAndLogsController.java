package io.github.dziodzi.controller;

import io.github.dziodzi.service.MetricsAndLogsService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MetricsAndLogsController {
    private final MetricsAndLogsService MetricsAndLogsService;
    
    public MetricsAndLogsController(MetricsAndLogsService MetricsAndLogsService) {
        this.MetricsAndLogsService = MetricsAndLogsService;
    }
    
    @GetMapping
    public String generateUUID() {
        String uuid = MetricsAndLogsService.generateUUID();
        MDC.put("requestId", uuid);
        log.info("Generated UUID and logged it with MDC");
        return "Generated UUID: " + uuid;
    }
    
    @GetMapping("/stack-overflow")
    public String uuidStackOverflow() {
        return MetricsAndLogsService.generateUUIDRecursively(0);
    }
    
    @GetMapping("/uuid-out-of-memory")
    public void generateUUIDsIndefinitely() {
        MetricsAndLogsService.generateUUIDsIndefinitely();
    }
}
