package com.example.springlastproject.payment;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.print.attribute.standard.Chromaticity;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.utils.DateUtils;
import com.example.springlastproject.payment.PaymentRequest.PaymentDTO;
import com.example.springlastproject.readingbook.ReadingBookResponse.readingbookDTO;
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
        System.out.println("startDate : " + startDate);
        Date deadlineDate = DateUtils.convertToSqlDate(LocalDate.now().plus(1, ChronoUnit.MONTHS));
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

}
