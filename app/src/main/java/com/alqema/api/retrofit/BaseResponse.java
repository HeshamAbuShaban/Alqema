package com.alqema.api.retrofit;

import java.util.List;

public class BaseResponse<T> {
    public boolean status;

    public String message;

    public List<T> data = null;

    public T object;
}
