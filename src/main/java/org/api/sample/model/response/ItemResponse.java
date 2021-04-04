package org.api.sample.model.response;

public class ItemResponse<T> extends CommonResponse{

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
