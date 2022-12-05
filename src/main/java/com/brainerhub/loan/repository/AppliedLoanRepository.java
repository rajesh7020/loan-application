package com.brainerhub.loan.repository;

import com.brainerhub.loan.entity.AppliedLoan;
import com.brainerhub.loan.entity.Loan;
import com.brainerhub.loan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface AppliedLoanRepository extends JpaRepository<AppliedLoan, Long> {

    String findAllLoanByUserQuery = "SELECT l.loanName AS loanName, l.id AS loanId, l.loanAmount AS loanAmount, " +
            "l.loanTerm As loanTerm, u.id AS userId, u.email AS email " +
            "FROM AppliedLoan ap LEFT JOIN ap.user u LEFT JOIN ap.loan l WHERE u.id = :userId";

    String findAllLoanByAdminQuery = "SELECT l.loanName AS loanName, l.id AS loanId, l.loanAmount AS loanAmount, " +
            "l.loanTerm AS loanTerm, u.id AS userId, u.email AS email " +
            "FROM AppliedLoan ap LEFT JOIN ap.user u LEFT JOIN ap.loan l";

    String findOneLoanByUserQuery = "SELECT l.loanName AS loanName, l.id AS loanId, l.loanAmount AS loanAmount, " +
            "l.loanTerm AS loanTerm, u.id AS userId, u.email AS email " +
            "FROM AppliedLoan ap LEFT JOIN ap.user u LEFT JOIN ap.loan l WHERE u.id = :userId AND ap.id =:appliedLoanId";

    Optional<AppliedLoan> findByUserIdAndId(Long userId, Long appliedLoanId);

    @Query(findAllLoanByUserQuery)
    List<Map<String, Objects>> findAllLoanByUser(Long userId);

    @Query(findOneLoanByUserQuery)
    Map<String, Objects> getAppliedLoanById(Long userId, Long appliedLoanId);

    @Query(findAllLoanByAdminQuery)
    List<Map<String, Objects>> findAllLoanByAdmin();


}
