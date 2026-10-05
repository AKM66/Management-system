package com.mc.library_mc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mc.library_mc.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}