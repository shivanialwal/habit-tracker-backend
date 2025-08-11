package com.productivity.habittracker.controller;

import com.productivity.habittracker.model.Habit;
import com.productivity.habittracker.model.HabitLog;
import com.productivity.habittracker.service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<Habit> create(@RequestBody Habit habit, Authentication authentication) {
        // in real world, map authenticated user -> userId
        // assume client sends userId for now or extract from token
        Habit created = habitService.createHabit(habit);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Habit>> list(@RequestParam UUID userId) {
        return ResponseEntity.ok(habitService.getHabitsForUser(userId));
    }

    @PostMapping("/{habitId}/log")
    public ResponseEntity<HabitLog> log(@PathVariable UUID habitId, @RequestParam String date, @RequestParam boolean status) {
        LocalDate d = LocalDate.parse(date);
        HabitLog log = habitService.logHabit(habitId, d, status);
        return ResponseEntity.ok(log);
    }
}
