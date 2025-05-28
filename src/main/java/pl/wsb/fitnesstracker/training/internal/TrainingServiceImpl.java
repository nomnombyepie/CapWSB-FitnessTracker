package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;


    @Override
    public Optional<User> getTraining(final Long trainingId) {throw new UnsupportedOperationException("didnt found trainging");}

    public List<Training>getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getAllTrainingsForDedicatedUser(Long userId) {
        return trainingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Training> getAllFinishedTrainingsAfter(LocalDateTime afterDateTime) {
        return trainingRepository.findAllByEndTimeAfter(afterDateTime);
    }

    @Override
    public List<Training> getAllTrainingByActivityType(ActivityType activityType) {
        return trainingRepository.findAllByActivityType(activityType);
    }
}
