package com.eduprimehub.alpha.models.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> extends BaseObject {

    private Long timestamp;
    private String id = Thread.currentThread().getName();
    private T result;
    private List<T> listResult;
    private String status;
    private Integer responseCode;
    private String responseMessage;
    private List<Error> errors = new ArrayList<>();

    private BaseResponse(Long timestamp,
                         T result,
                         List<Error> errors,
                         String status) {
        this.timestamp = timestamp;
        this.result = result;
        this.status = status;
        this.errors = errors;
    }

    public BaseResponse(T result,
                        List<Error> errors,
                        String status) {
        this(new Date().getTime(), result, errors, status);
    }

    public BaseResponse(T result,
                        String status) {
        this(result, null, status);
    }

    public BaseResponse(T result) {
        this(result, null);
    }
}
