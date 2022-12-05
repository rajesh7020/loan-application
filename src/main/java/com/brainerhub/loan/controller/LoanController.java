package com.brainerhub.loan.controller;

import com.brainerhub.loan.entity.AppliedLoan;
import com.brainerhub.loan.responseDto.GenericResponse;
import com.brainerhub.loan.service.AppliedLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
@RequestMapping("/user")
public class LoanController {

    @Autowired
    private AppliedLoanService appliedLoanService;

    @PostMapping("/applyLoan")
    public ResponseEntity<GenericResponse> applyLoanByUser(@Validated @RequestBody AppliedLoan appliedLoan) {
        return ResponseEntity.ok(appliedLoanService.applyLoan(appliedLoan));
    }

    @GetMapping("/getAppliedLoans")
    public ResponseEntity<GenericResponse> getAppliedLoans() {
        return ResponseEntity.ok(appliedLoanService.getAppliedLoans());
    }

    @GetMapping("/getAppliedLoanUser/{appliedLoanId}")
    public ResponseEntity<GenericResponse> getAppliedLoanOfUser(@PathVariable Long appliedLoanId) {
        return ResponseEntity.ok(appliedLoanService.getAppliedLoanOfUser(appliedLoanId));
    }
}
