package com.example.usercrud.swaggerDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseObject {
    @Schema(description = "1: Thành công, 2: Thất bại")
    private int result;
    @Schema(description = "Mã lỗi")
    private String code;
    @Schema(description = "Thông báo")
    private String message;
    @Schema(description = "Data trả về")
    private Object data;
}
