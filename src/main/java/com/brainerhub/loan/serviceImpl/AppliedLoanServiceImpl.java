package com.brainerhub.loan.serviceImpl;

import com.brainerhub.loan.commonutil.Constant;
import com.brainerhub.loan.commonutil.Util;
import com.brainerhub.loan.entity.AppliedLoan;
import com.brainerhub.loan.entity.User;
import com.brainerhub.loan.repository.AppliedLoanRepository;
import com.brainerhub.loan.responseDto.APIResponseBuilder;
import com.brainerhub.loan.responseDto.GenericResponse;
import com.brainerhub.loan.service.AppliedLoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AppliedLoanServiceImpl implements AppliedLoanService {

    @Autowired
    private AppliedLoanRepository appliedLoanRepository;


    @Autowired
    private Util util;

    @Override
    public GenericResponse applyLoan(AppliedLoan appliedLoan) {
        try {
            User user = util.getAuthenticationUserDetails();
            Optional<AppliedLoan> optionalAppliedLoan = appliedLoanRepository.findByUserIdAndId(user.getId(), appliedLoan.getId());
            if (optionalAppliedLoan.isPresent()) {
                return APIResponseBuilder.build(Boolean.FALSE, null, "You had a already applied this loan ");
            } else {
                appliedLoan.setStatus(Boolean.TRUE);
                appliedLoan.setIsApproved(Boolean.FALSE);
                appliedLoanRepository.save(appliedLoan);
                return APIResponseBuilder.build(Boolean.TRUE, null, "You have applied this loan success");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), Constant.EXCEPTION);
        }
    }

    @Override
    public GenericResponse getAppliedLoans() {
        try {
            User user = util.getAuthenticationUserDetails();
            List<Map<String, Objects>> appliedLoanList = appliedLoanRepository.findAllLoanByUser(user.getId());
            return APIResponseBuilder.build(appliedLoanList.size() > 0 ? Boolean.TRUE : Boolean.FALSE, appliedLoanList,
                    appliedLoanList.size() > 0 ? "Record found" : "No records found");
        } catch (Exception e) {
            log.error(e.getMessage());
            return APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), Constant.EXCEPTION);
        }
    }

    @Override
    public GenericResponse getAppliedLoanOfUser(Long appliedLoanId) {
        try {
            User user = util.getAuthenticationUserDetails();
            Map<String, Objects> appliedLoan = appliedLoanRepository.getAppliedLoanById(user.getId(), appliedLoanId);
            return APIResponseBuilder.build(appliedLoan.size() > 0 ? Boolean.TRUE : Boolean.FALSE, appliedLoan,
                    appliedLoan.size() > 0 ? "Record found" : "No records found");
        } catch (Exception e) {
            log.error(e.getMessage());
            return APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), Constant.EXCEPTION);
        }
    }

    @Override
    public GenericResponse loanApproveOrDeclined(Long appliedLoanId, Boolean status, Long userId) {
        try {
            Optional<AppliedLoan> optionalAppliedLoan = appliedLoanRepository.findByUserIdAndId(userId, appliedLoanId);
            if (optionalAppliedLoan.isPresent()) {
                AppliedLoan appliedLoan = optionalAppliedLoan.get();
                appliedLoan.setIsApproved(status);
                appliedLoan = appliedLoanRepository.save(appliedLoan);
                return APIResponseBuilder.build(Boolean.TRUE, appliedLoan.getIsApproved(), "Loan isApproved/Declined success");
            } else {
                return APIResponseBuilder.build(Boolean.FALSE, null, "No Applied Loan Found");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), Constant.EXCEPTION);
        }
    }

    @Override
    public GenericResponse getAllAppliedByAdmin() {
        try {
            List<Map<String, Objects>> appliedLoanList = appliedLoanRepository.findAllLoanByAdmin();
            return APIResponseBuilder.build(appliedLoanList.size() > 0 ? Boolean.TRUE : Boolean.FALSE, appliedLoanList,
                    appliedLoanList.size() > 0 ? "Record found" : "No records found");
        } catch (Exception e) {
            log.error(e.getMessage());
            return APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), Constant.EXCEPTION);
        }
    }
}
