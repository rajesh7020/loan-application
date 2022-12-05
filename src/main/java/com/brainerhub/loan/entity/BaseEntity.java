package com.brainerhub.loan.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    private Boolean status;
}
