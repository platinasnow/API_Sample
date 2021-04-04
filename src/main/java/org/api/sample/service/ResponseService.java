package org.api.sample.service;

import org.api.sample.model.response.CommonResponse;
import org.api.sample.model.response.ItemResponse;
import org.api.sample.model.response.ListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {


    //성공 여부만 반환
    public CommonResponse getSuccessResponse(){
        CommonResponse commonResponse = new CommonResponse();
        this.setSuccessCode(commonResponse);
        return commonResponse;
    }

    //실패
    public CommonResponse getFailedResponse(String code, String msg){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setResult(false);
        commonResponse.setCode(code);
        commonResponse.setMsg(msg);
        return commonResponse;
    }

    //성공 결과에 단건 데이터 포함
    public <T>ItemResponse getItemResponse(T data){
        ItemResponse result = new ItemResponse();
        result.setData(data);
        this.setSuccessCode(result);
        return result;
    }

    //성공 결과에 다중 데이터 포함
    public <T>ListResponse<T> getListResponse(List<T> data){
        ListResponse<T> result = new ListResponse<>();
        result.setData(data);
        this.setSuccessCode(result);
        return result;
    }

    private void setSuccessCode(CommonResponse response){
        response.setResult(true);
        response.setCode("0");
        response.setMsg("SUCCESS");
    }

}
