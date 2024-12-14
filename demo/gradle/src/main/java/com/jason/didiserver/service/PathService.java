package com.jason.didiserver.service;

import com.jason.didiserver.pojo.Path;
import com.jason.didiserver.pojo.PathInfo;
import com.jason.didiserver.pojo.Search;

import java.util.List;

public interface PathService {
    List<Path> findPathById(Integer id);

    void updatePath(List<Path> ls);

    List<PathInfo> searchPathInfo(Search search);
}
