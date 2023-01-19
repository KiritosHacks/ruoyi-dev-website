package com.dragonScale.business.service.impl;

import com.dragonScale.business.domain.YwProject;
import com.dragonScale.business.mapper.ProjectMapper;
import com.dragonScale.business.service.IYwProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class ProjectServiceImpl implements IYwProjectService {

    @Autowired
    ProjectMapper projectMapper;
    @Override
    public YwProject selectYwProjectByPkProject(String pkProject) {
        return null;
    }

    @Override
    public List<YwProject> selectYwProjectList(YwProject ywProject) {
        return null;
    }


    public List<YwProject> selectProjectList() {
        return projectMapper.selectYwProjectList();
    }

    @Override
    public int insertYwProject(YwProject ywProject) {
        return 0;
    }

    @Override
    public int updateYwProject(YwProject ywProject) {
        return 0;
    }

    @Override
    public int deleteYwProjectByPkProjects(String[] pkProjects) {
        return 0;
    }

    @Override
    public int deleteYwProjectByPkProject(String pkProject) {
        return 0;
    }
}
