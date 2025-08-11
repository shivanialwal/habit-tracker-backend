package com.productivity.habittracker.service;

import com.productivity.habittracker.model.Habit;
import com.productivity.habittracker.model.HabitLog;
import com.productivity.habittracker.repository.HabitLogRepository;
import com.productivity.habittracker.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final HabitLogRepository habitLogRepository;

    public HabitService(HabitRepository habitRepository, HabitLogRepository habitLogRepository) {
        this.habitRepository = habitRepository;
        this.habitLogRepository = habitLogRepository;
    }

    public Habit createHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public List<Habit> getHabitsForUser(UUID userId) {
        return habitRepository.findByUserId(userId);
    }

    public HabitLog logHabit(UUID habitId, LocalDate date, boolean status) {
        var opt = habitLogRepository.findByHabitIdAndDate(habitId, date);
        HabitLog log;
        if (opt.isPresent()) {
            log = opt.get();
            log.setStatus(status);
        } else {
            log = new HabitLog();
            log.setHabitId(habitId);
            log.setDate(date);
            log.setStatus(status);
        }
        return habitLogRepository.save(log);
    }
}
