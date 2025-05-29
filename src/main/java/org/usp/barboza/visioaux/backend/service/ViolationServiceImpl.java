package org.usp.barboza.visioaux.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usp.barboza.visioaux.backend.entity.UiViolationModel;
import org.usp.barboza.visioaux.backend.entity.Violation;
import org.usp.barboza.visioaux.backend.repository.ViolationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ViolationServiceImpl implements ViolationService {

    private ViolationRepository violationRepository;

    /**
     * If you want to use another evaluator, change this line of code.
     */
    private final ViolationEvaluator eval = new ViolationEvaluatorImpl();

    @Autowired
    public ViolationServiceImpl(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    @Override
    public List<Violation> findAll() {
        return violationRepository.findAll();
    }

    @Override
    public List<Violation> findViolationIdenticalTo(Violation violation) {
        return violationRepository.findViolationIdenticalTo(
                violation.getActivityName(),
                violation.getViolationType(),
                violation.getDeveloperMessage(),
                violation.getDeviceId()
        );
    }

    @Override
    public Violation findById(int id) {
        Violation violation;
        Optional<Violation> result = violationRepository.findById(id);

        if (result.isPresent()) {
            violation = result.get();
        } else {
            throw new RuntimeException("Couldn't find message with id: " + id);
        }

        return violation;
    }

    @Override
    public Violation save(Violation violation) {
        return violationRepository.save(violation);
    }

    @Override
    public void deleteById(int id) {
        violationRepository.deleteById(id);
    }

    @Override
    public List<UiViolationModel> findAllByPriority() {
        List<Violation> violations = violationRepository.findAll();
        List<UiViolationModel> violationsByPriority = eval.sortByPriority(violations);
        return violationsByPriority;
    }
}
