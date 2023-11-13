package com.example.springlastproject.payment;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.el.ELException;
import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.errors.exception.Exception403;
import com.example.springlastproject._core.utils.DateUtils;
import com.example.springlastproject.user.User;
import com.example.springlastproject.user.UserJPARepository;

import lombok.RequiredArgsConstructor;

@EnableScheduling
@Transactional
@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserJPARepository userJPARepository;
    private final SchedulerService schedulerService;

    public PaymentResponse.PaymentDTO 결재내역등록하기(PaymentRequest.PaymentDTO paymentDTO) {

        Date startDate = new Timestamp(new Date().getTime());
        Date deadlineDate = DateUtils.convertToSqlDate(LocalDate.now().plus(paymentDTO.getMonths(), ChronoUnit.MONTHS));

        Payment payment = Payment.builder()
                .name(paymentDTO.getName())
                .amount(paymentDTO.getAmount())
                .months(paymentDTO.getMonths())
                .subNumber(paymentDTO.getSubNumbar())
                .startDate(startDate)
                .deadlineDate(deadlineDate)
                .user(User.builder().id(paymentDTO.getUserId()).build())
                .build();

        // 유저 결재 정보 업데이트
        User user = userJPARepository.findById(paymentDTO.getUserId()).get();
        if (user.getPaymentStatus() == true) {
            throw new Exception400(
                    payment.getName() + "권을 사용하고 있습니다. " + payment.getDeadlineDate() + " 이후 추가 결재 하실 수 있습니다. ");
        }

        user.updatePaymentStatus(true);

        // 결재내역 최신화
        payment = paymentRepository.save(payment);

        // 구독권 만료일에 메서드 실행
        schedulerService.startScheduledTask(user, payment);

        return new PaymentResponse.PaymentDTO(user, payment);
    }

    public PaymentResponse.MySettingMainPage 내정보메인페이지(Integer userId) {
        List<Payment> payment = paymentRepository.findAllByUserId(userId);
        try {
            return new PaymentResponse.MySettingMainPage(payment);

        } catch (Exception e) {
            e.printStackTrace();
            return new PaymentResponse.MySettingMainPage(payment);
        }
    }

}
