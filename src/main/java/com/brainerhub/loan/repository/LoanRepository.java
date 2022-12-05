package com.brainerhub.loan.repository;

import com.brainerhub.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Loan findByLoanName(String loanName);
}
