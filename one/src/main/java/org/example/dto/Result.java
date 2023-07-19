package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;
    public static Result ok(){
        return new Result("success", true,null,null,null);
    }
    public static Result ok(Object data){
        return new Result("success", true,null,data,null);
    }

    /**
     * 特制的ok码
     * @param code
     * @param data
     * @return
     */
    public static Result ok(Integer code,Object data){
        return new Result("success", true,null,data,null);
    }

    public static Result fail(String errorMsg){
        return new Result("fail", true,null,errorMsg,null);
    }
    public static Result fail(Integer code,String errorMsg){
        return new Result("fail", true,null,errorMsg,null);
    }

}
