package pl.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.common.lang.Nullable;

import java.util.Date;

public record TrainingDto(@Nullable Long id, Long userId, Date startTime, Date endTime,
                          ActivityType activityType, double distance, double averageSpeed
) {}

