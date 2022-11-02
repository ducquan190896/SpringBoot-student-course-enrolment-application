package com.quan.gradepractice.Exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private LocalDateTime localDateTime;
    private Throwable throwable;
    @Override
    public String toString() {
        return "ErrorResponse [message=" + message + ", localDateTime=" + localDateTime + ", throwable=" + throwable
                + "]";
    }

    
}
