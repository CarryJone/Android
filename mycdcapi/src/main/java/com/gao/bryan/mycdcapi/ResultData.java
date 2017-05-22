package com.gao.bryan.mycdcapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hermes on 2016/12/16.
 */
public class ResultData<T>
{
    @SerializedName("Result")
    private String result;

    @SerializedName("MSG")
    private String message;

    @SerializedName("Data")
    @Expose
    private ArrayList<T> data = new ArrayList<>();

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public ArrayList<T> getData()
    {
        return data;
    }

    public void setData(ArrayList<T> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("ResultData{");
        sb.append("data=").append(data);
        sb.append(", result='").append(result).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

