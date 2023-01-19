package com.dragonScale.business.mapper;

import com.dragonScale.business.domain.YwVideo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface YwVideoMapper {

    @Select("select * from yw_video where pk_video in (select pk_video from yw_project_video where pk_project = #{pk_project})")
    public List<YwVideo> selectYwVideoListByProject(String pkProject);

}
