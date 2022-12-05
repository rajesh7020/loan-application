package com.brainerhub.loan.service;

import com.brainerhub.loan.entity.AppliedLoan;
import com.brainerhub.loan.responseDto.GenericResponse;

public interface AppliedLoanService {

    GenericResponse applyLoan(AppliedLoan appliedLoan);

    GenericResponse getAppliedLoans();

    GenericResponse getAppliedLoanOfUser(Long appliedLoanId);

    GenericResponse loanApproveOrDeclined(Long appliedLoanId, Boolean status, Long userId);

    GenericResponse getAllAppliedByAdmin();
}
