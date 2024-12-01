package io.github.dziodzi.repository;

import io.github.dziodzi.entity.MetricEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricEntryRepository extends JpaRepository<MetricEntry, Long> {
}
