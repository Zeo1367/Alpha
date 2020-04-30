package com.eduprimehub.alpha.models.objects;


import com.eduprimehub.alpha.utils.ApplicationConstant;

import java.util.List;


/**
 * Created by Aditya
 */
public class GenericResponse<T> {

    public BaseResponse<T> createSuccessResponse(T responseObject, Integer responseCode) {
        BaseResponse<T> response = new BaseResponse();
        if (responseObject != null) {
            response.setResult(responseObject);
        }
        response.setStatus(ApplicationConstant.SUCCESS);
        if (responseCode != null) {
            response.setResponseCode(responseCode);
//            response.setResponseMessage(ResponseCodeHandler.getMessage(responseCode));
        }
        return response;

    }

    public BaseResponse<T> createErrorResponse(Integer errorCode, String errorMessage) {
        BaseResponse response = new BaseResponse();
        response.setResponseMessage(errorMessage);
        response.setResponseCode(errorCode);
        response.setStatus(ApplicationConstant.FAILURE);
        return response;
    }

    public BaseResponse<T> createErrorResponse(Integer errorCode, String errorMessage, Throwable th) {
        BaseResponse response = new BaseResponse();
        response.setResponseMessage(errorMessage);
        response.setResponseCode(errorCode);
        response.setStatus(ApplicationConstant.FAILURE);
//        response.setThrowable(th);

        return response;
    }

    public BaseResponse<T> createSuccessListResponse(List<T> responseObject) {
        BaseResponse<T> response = new BaseResponse();
        response.setListResult(responseObject);
        response.setStatus(ApplicationConstant.SUCCESS);
        return response;

    }
}
