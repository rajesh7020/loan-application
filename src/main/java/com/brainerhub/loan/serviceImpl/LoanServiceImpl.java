package com.brainerhub.loan.serviceImpl;

import com.brainerhub.loan.commonutil.Constant;
import com.brainerhub.loan.entity.Loan;
import com.brainerhub.loan.repository.LoanRepository;
import com.brainerhub.loan.responseDto.APIResponseBuilder;
import com.brainerhub.loan.responseDto.GenericResponse;
import com.brainerhub.loan.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public GenericResponse addLoan(Loan loan) {
        try {
            System.out.printf("Addloan");
            Loan existLoan = loanRepository.findByLoanName(loan.getLoanName());
            if (existLoan != null) {
                return APIResponseBuilder.build(Boolean.FALSE, loan.getLoanName(), "Loan already exist");
            } else {
                loan.setStatus(Boolean.TRUE);
                loan = loanRepository.save(loan);
                return APIResponseBuilder.build(Boolean.TRUE, loan, "Loan added success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), Constant.EXCEPTION);
        }
    }
}
