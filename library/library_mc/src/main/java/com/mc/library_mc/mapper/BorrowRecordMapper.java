package com.mc.library_mc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mc.library_mc.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}