package com.jason.didiserver.controller;

import com.jason.didiserver.pojo.Path;
import com.jason.didiserver.pojo.PathInfo;
import com.jason.didiserver.pojo.Result;
import com.jason.didiserver.pojo.Search;
import com.jason.didiserver.service.PathService;
import com.jason.didiserver.utils.JWTUtil;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/path")
public class PathController {

    @Autowired
    private PathService pathService;

    @PostMapping("/carriage")
    public Result<List<Path>> findPathById(@RequestHeader(name = "Authorization") String token, @RequestParam(required = false) Integer id) {
        Map<String, Object> map = JWTUtil.parseToken(token);
        if (id == null) {
            Integer id2 = (Integer) map.get("id");
            List<Path> ls = pathService.findPathById(id2);
            return Result.success(ls);
        } else {
            List<Path> ls = pathService.findPathById(id);
            return Result.success(ls);
        }
    }


    //shit
    @GetMapping
    public Result<List<Path>> getPathById(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> map = JWTUtil.parseToken(token);
        Integer id2 = (Integer) map.get("id");
        List<Path> ls = pathService.findPathById(id2);
        return Result.success(ls);
    }

    @PostMapping("/update")
    public Result updatePath(@RequestBody List<Path> pathList) {

        pathService.updatePath(pathList);
        return Result.success();
    }

    @PostMapping("/search")
    public Result<List<PathInfo>> searchPathInfo(@RequestBody Search search) {
        List<PathInfo> ls = pathService.searchPathInfo(search);
        return Result.success(ls);
    }

}
