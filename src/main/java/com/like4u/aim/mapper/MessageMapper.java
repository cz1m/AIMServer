package com.like4u.aim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.like4u.aim.domain.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/18 11:46
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
