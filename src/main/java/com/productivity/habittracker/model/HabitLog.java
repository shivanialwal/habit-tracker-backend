package com.productivity.habittracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "habit_logs", uniqueConstraints = {@UniqueConstraint(columnNames = {"habit_id","date"})})
@Getter
@Setter
@NoArgsConstructor
public class HabitLog {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "habit_id", nullable = false)
    private UUID habitId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private boolean status;
}
