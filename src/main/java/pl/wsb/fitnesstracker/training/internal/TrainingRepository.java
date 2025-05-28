package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDateTime;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findAllByUserId(Long userId);

    List<Training> findAllByEndTimeAfter(LocalDateTime endTime);

    List<Training> findAllByActivityType(ActivityType activityType);
}
