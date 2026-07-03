package com.maranhao.turismoapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private int status;
    private String mensagem;
    private LocalDateTime timestamp;
    private List<String> erros;

}
