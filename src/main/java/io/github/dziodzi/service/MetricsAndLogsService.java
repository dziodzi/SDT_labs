package io.github.dziodzi.service;

import io.github.dziodzi.entity.LogEntry;
import io.github.dziodzi.entity.MetricEntry;
import io.github.dziodzi.repository.LogEntryRepository;
import io.github.dziodzi.repository.MetricEntryRepository;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class MetricsAndLogsService {
    private final LogEntryRepository logEntryRepository;
    private final MetricEntryRepository metricEntryRepository;
    private final MeterRegistry meterRegistry;
    private final List<String> uuidList = new ArrayList<>();
    
    public MetricsAndLogsService(LogEntryRepository logEntryRepository,
                          MetricEntryRepository metricEntryRepository,
                          MeterRegistry meterRegistry) {
        this.logEntryRepository = logEntryRepository;
        this.metricEntryRepository = metricEntryRepository;
        this.meterRegistry = meterRegistry;
    }
    
    public String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        
        log.info("Generated UUID: {}", uuid);
        
        LogEntry logEntry = new LogEntry();
        logEntry.setRequestId(uuid);
        logEntry.setMessage("Generated UUID and logged it with MDC");
        logEntry.setTimestamp(LocalDateTime.now());
        logEntryRepository.save(logEntry);
        
        meterRegistry.counter("custom_requests_total").increment();
        MetricEntry metricEntry = new MetricEntry();
        metricEntry.setName("custom_requests_total");
        metricEntry.setValue(meterRegistry.counter("custom_requests_total").count());
        metricEntry.setTimestamp(LocalDateTime.now().toString());
        metricEntryRepository.save(metricEntry);
        
        return uuid;
    }
    
    public String generateUUIDRecursively(int depth) {
        String uuid = UUID.randomUUID().toString();
        log.info("Generated UUID at depth {}: {}", depth, uuid);
        return generateUUIDRecursively(depth + 1);
    }
    
    public void generateUUIDsIndefinitely() {
        while (true) {
            uuidList.add(UUID.randomUUID().toString());
        }
    }
}
