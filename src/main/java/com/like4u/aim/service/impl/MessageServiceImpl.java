package com.like4u.aim.service.impl;

import com.like4u.aim.mapper.MessageMapper;
import com.like4u.aim.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/18 11:47
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
}
