package com.example.springlastproject.payment;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.springlastproject.user.User;

@Service
public class SchedulerService {

    @Autowired
    private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;

    public SchedulerService(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    // 스케줄링된 작업 시작
    public void scheduledTask() {
        System.out.println("스케줄링된 작업 시작");
        // 스케줄링된 작업 내용
        System.out.println("안녕");

        // cancelScheduledTask();
    }

    // 스케줄링된 작업 동적으로 취소
    // public void cancelScheduledTask() {
    // System.out.println("스케줄링된 작업 취소");
    // if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
    // scheduledFuture.cancel(true);
    // }
    // }

    // 스케줄링된 작업 동적으로 시작
    public void startScheduledTask() {
        System.out.println("스케줄링된 작업 동적으로 시작");

        // 예제: 한 달 뒤에 실행되도록 설정
        this.scheduledFuture = taskScheduler.schedule(this::scheduledTask, new SimpleTrigger());

    }

    private static class SimpleTrigger implements Trigger {

        @Override
        public Date nextExecutionTime(TriggerContext triggerContext) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nextExecutionTime = now.plus(1, ChronoUnit.MONTHS);
            System.out.println("날짜 계산 : " + nextExecutionTime);
            return Date.from(nextExecutionTime.atZone(ZoneId.systemDefault()).toInstant());
            // return Date
            // .from(LocalDateTime.now().plus(10,
            // ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant());

        }

    }
}
