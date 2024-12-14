package com.jason.didiserver.service.impl;

import com.jason.didiserver.mapper.PathMapper;
import com.jason.didiserver.pojo.Path;
import com.jason.didiserver.pojo.PathInfo;
import com.jason.didiserver.pojo.Search;
import com.jason.didiserver.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathServiceImpl implements PathService {

    @Autowired
    private PathMapper pathMapper;

    @Override
    public List<Path> findPathById(Integer id) {
        return pathMapper.findPathById(id);
    }

    @Override
    public void updatePath(List<Path> ls) {
        ls.forEach(path -> {
            if (path.getId() != null) {
                // 如果 id 不为 null，则执行更新操作
                pathMapper.updatePath(path.getLocation(), path.getCarriage(), path.getUserId(), path.getId());
            } else {
                // 如果 id 为 null，则执行插入操作
                pathMapper.insertPath(path.getLocation(), path.getCarriage(), path.getUserId());
            }
        });
    }

    @Override
    public List<PathInfo> searchPathInfo(Search search) {
        return pathMapper.findPathBySearch(search);
    }
}
