package com.example.springlastproject.payment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Service;

import com.example.springlastproject.user.User;
import com.example.springlastproject.user.UserJPARepository;
import com.example.springlastproject.user.UserService;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
public class SchedulerService {

    @Autowired
    private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;
    private final UserService userService;
    private final UserJPARepository userJPARepository;

    public SchedulerService(TaskScheduler taskScheduler, UserService userService, UserJPARepository userJPARepository) {
        this.taskScheduler = taskScheduler;
        this.userService = userService;
        this.userJPARepository = userJPARepository;

    }

    // 스케줄링된 작업 시작
    public void scheduledTask(User user) {
        // 스케줄링된 작업 내용
        userService.결재변경(user);
        cancelScheduledTask();
    }

    // 스케줄링된 작업 동적으로 취소
    public void cancelScheduledTask() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
    }

    // 스케줄링된 작업 동적으로 시작
    public void startScheduledTask(User user, Payment payment) {

        // 예제: 한 달 뒤에 실행되도록 설정
        this.scheduledFuture = taskScheduler.schedule(() -> scheduledTask(user),
                new SimpleTrigger(payment.getMonths()));

    }

    private static class SimpleTrigger implements Trigger {
        private Integer months;

        public SimpleTrigger(Integer months) {
            this.months = months;
        }

        @Override
        public Date nextExecutionTime(TriggerContext triggerContext) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nextExecutionTime = now.plus(months, ChronoUnit.MONTHS);
            // return Date
            // .from(LocalDateTime.now().plus(15,
            // ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant());
            return Date.from(nextExecutionTime.atZone(ZoneId.systemDefault()).toInstant());

        }

    }

}
