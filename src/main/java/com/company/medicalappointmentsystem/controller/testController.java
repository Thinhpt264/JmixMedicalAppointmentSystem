package com.company.medicalappointmentsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class testController {
    @GetMapping("test")
    @ResponseBody
    public String test() {
        return """
                <html>
                    <head><title>Test</title></head>
                    <body>
                        <h1>Xin chào từ controller!</h1>
                        <p>Đây là nội dung HTML thuần từ server.</p>
                    </body>
                </html>
                """;
    }

}
