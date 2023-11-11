package com.example.springlastproject.payment;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.utils.DateUtils;
import com.example.springlastproject.user.User;
import com.example.springlastproject.user.UserJPARepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserJPARepository userJPARepository;

    public PaymentResponse.PaymentDTO 결재내역등록하기(PaymentRequest.PaymentDTO paymentDTO) {
       
        Date startDate = new Timestamp(new Date().getTime());
        Date deadlineDate = DateUtils.convertToSqlDate(LocalDate.now().plus(paymentDTO.getMonths(), ChronoUnit.MONTHS));
        System.out.println("deadlineDate : " + deadlineDate);

        Payment payment = Payment.builder()
                .name(paymentDTO.getPay())
                .amount(paymentDTO.getAmount())
                .startDate(startDate)
                .deadlineDate(deadlineDate)
                .user(User.builder().id(paymentDTO.getUserId()).build())
                .build();

        User user = userJPARepository.findById(paymentDTO.getUserId()).get();
        user.updatePaymentStatus(true);
        ;
        payment = paymentRepository.save(payment);
        return new PaymentResponse.PaymentDTO(user, payment);
    }

    public PaymentResponse.MySettingMainPage 내정보메인페이지(Integer userId) {
        Payment payment = paymentRepository.findByUserId(userId);
        return new PaymentResponse.MySettingMainPage(payment);
    }

}
