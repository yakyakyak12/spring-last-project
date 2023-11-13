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
        System.out.println("스케줄링된 작업 시작");
        // 스케줄링된 작업 내용
        System.out.println("안녕");
        userService.결재변경(user);
        System.out.println("어떤 유저의 값을 받아오지? : " + user.getNickname());
        cancelScheduledTask();
    }

    // 스케줄링된 작업 동적으로 취소
    public void cancelScheduledTask() {
        System.out.println("스케줄링된 작업 취소");
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
    }

    // 스케줄링된 작업 동적으로 시작
    public void startScheduledTask(User user) {
        System.out.println("스케줄링된 작업 동적으로 시작");

        // 예제: 한 달 뒤에 실행되도록 설정
        this.scheduledFuture = taskScheduler.schedule(() -> scheduledTask(user), new SimpleTrigger());

    }

    private static class SimpleTrigger implements Trigger {

        @Override
        public Date nextExecutionTime(TriggerContext triggerContext) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nextExecutionTime = now.plus(1, ChronoUnit.MONTHS);
            System.out.println("날짜 계산 : " + nextExecutionTime);
            // return
            // Date.from(nextExecutionTime.atZone(ZoneId.systemDefault()).toInstant());
            return Date
                    .from(LocalDateTime.now().plus(15,
                            ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant());

        }

    }

}
