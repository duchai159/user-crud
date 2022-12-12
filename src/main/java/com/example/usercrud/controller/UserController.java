package com.example.usercrud.controller;

import com.example.usercrud.dto.UserDto;
import com.example.usercrud.service.UserService;
import com.example.usercrud.swaggerDto.ResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Thêm user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Thêm thành công",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "result": 1,
                                        "code": "200",
                                        "message": "thêm thành công",
                                        "data": null
                                    }
                                    """))}),
            @ApiResponse(responseCode = "400",
                    description = "Request không hợp lệ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "result": 0,
                                        "code": "400",
                                        "message": "Request không hợp lệ",
                                        "data": null
                                    }
                                    """))})
    })
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDto userDto) {
        userService.saveUser(userDto);
        ResponseObject responseObject = new ResponseObject(1, "200", "success", null);
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @Operation(summary = "Trả về user theo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tìm thấy",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "result": 0,
                                        "code": "200",
                                        "message": "User tồn tại",
                                        "data": {
                                            "id": 3,
                                            "name": "long",
                                            "age": 20,
                                            "email": "long@"
                                    }
                                    """))}),
            @ApiResponse(responseCode = "404",
                    description = "Không tìm thấy",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "result": 0,
                                        "code": "404",
                                        "message": "Không tìm thấy",
                                        "data": null
                                    }
                                    """))})
    })
    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        ResponseObject responseObject = new ResponseObject(0, "200", "User tồn tại", userService.getUserById(id));
        return ResponseEntity.ok().body(responseObject);
    }

    @Operation(summary = "Xóa user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Xóa thành công",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "result": 1,
                                        "code": "200",
                                        "message": "Xóa thành công",
                                        "data": null
                                    }
                                    """))}),
            @ApiResponse(responseCode = "404",
                    description = "Không tìm thấy",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "result": 0,
                                        "code": "404",
                                        "message": "Không tìm thấy",
                                        "data": null
                                    }
                                    """))})
    })
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        ResponseObject responseObject = new ResponseObject(1, "200", "Xóa thành công", null);
        return ResponseEntity.ok().body(responseObject);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        userService.updateUserById(userDto);
        return ResponseEntity.ok().body(userDto);
    }
}
