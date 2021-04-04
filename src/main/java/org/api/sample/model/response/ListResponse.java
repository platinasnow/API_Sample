package org.api.sample.model.response;

import java.util.List;

public class ListResponse<T> extends CommonResponse{

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
