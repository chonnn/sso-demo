package com.chon.myapi.controller;

import com.chon.myapi.dto.DemoDto;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/demo")
public class DemoController {
    @GetMapping(value = "/")
    public @ResponseBody
    DemoDto demo() {
        DemoDto demoDto = new DemoDto();
        demoDto.setName("Name from DemoController");

        return demoDto;
    }
}
