package com.project.transactions.domain.model.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Error format
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Error implements Serializable {
    private String code;
    private String name;
    private String description;
}
