package br.com.salao.exceptions;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ErrorDetails implements Serializable {

    private String timestamp;
    private Integer status;
    private String error;
}
