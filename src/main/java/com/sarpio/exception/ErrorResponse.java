package com.sarpio.exception;

import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "error")
public class ErrorResponse {

    private Date timestamp;
    private String message;
    private List<String> details;

    public ErrorResponse(Date timestamp, String message, List<String> details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
