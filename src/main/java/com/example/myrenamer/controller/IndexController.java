package com.example.myrenamer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    @GetMapping("search")
    @ResponseBody
    public Map<String, Object> search(String path) {
        File localFilePath = new File(path);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", Arrays.stream(localFilePath.list()).sorted().toArray(String[]::new));
        return result;
    }

    @PostMapping("preview")
    @ResponseBody
    public Map<String, Object> preview(@RequestParam("fileList[]") String[] fileList, String regex, String replacement) {
        String[] replaceResult = {};
        try {
            replaceResult = Arrays.stream(fileList).map(file -> file.replaceAll(regex, replacement)).toArray(String[]::new);
        } catch (Exception e) {
            System.out.println("正则表达式错误");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", replaceResult);
        return result;
    }
}
