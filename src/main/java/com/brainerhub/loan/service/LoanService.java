package com.brainerhub.loan.service;

import com.brainerhub.loan.entity.Loan;
import com.brainerhub.loan.responseDto.GenericResponse;

public interface LoanService {

    GenericResponse addLoan(Loan loan);
}
