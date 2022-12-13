package com.example.usercrud.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@Builder
public class ResponseObject<T> {
    @Schema(description = "1: Thành công, 0: Thất bại")
    private int result;
    @Schema(description = "Mã lỗi")
    private String code;
    @Schema(description = "Thông báo")
    private String message;
    @Schema(description = "Data trả về")
    private Object data;
    public static <T> ResponseObject<T> of(T data){
        return new ResponseObject<>(1,"200", "Thanh cong", data);
    }
    public static <T> ResponseObject<T> of(T data, Integer result, String message) {
        return new ResponseObject<>(result, "400", message, data);
    }

    public static <T> ResponseObject<T> error(T data, String message) {
        return new ResponseObject<>(1, "200", message, data);
    }

    public static ResponseEntity<?> response(Object object){
        return ResponseEntity.ok(ResponseObject.of(object));
    }
}
