package com.brainerhub.loan.adminController;

import com.brainerhub.loan.entity.Loan;
import com.brainerhub.loan.responseDto.GenericResponse;
import com.brainerhub.loan.service.AppliedLoanService;
import com.brainerhub.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AppliedLoanController {

    @Autowired
    private AppliedLoanService appliedLoanService;

    @Autowired
    private LoanService loanService;

    @GetMapping("/allAppliedLoan")
    public ResponseEntity<GenericResponse> getAllAppliedByAdmin() {
        return ResponseEntity.ok(appliedLoanService.getAllAppliedByAdmin());
    }

    @GetMapping("/loanApproveOrDeclined/{appliedLoanId}/user/{userId}/status/{status}")
    public ResponseEntity<GenericResponse> loanApproveOrDeclined(@PathVariable Long appliedLoanId,
                                                                 @PathVariable Boolean status,
                                                                 @PathVariable Long userId) {
        return ResponseEntity.ok(appliedLoanService.loanApproveOrDeclined(appliedLoanId, status, userId));
    }

    @PostMapping("/addLoan")
    public ResponseEntity<GenericResponse> addLoanByAdmin(@Validated @RequestBody Loan loan) {
        System.out.println("AddLon Controller");
        return ResponseEntity.ok(loanService.addLoan(loan));
    }
}
