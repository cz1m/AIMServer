package com.like4u.aim.service;

import com.like4u.aim.common.utils.Utility;
import com.like4u.aim.domain.Message;
import com.like4u.aim.domain.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/19 15:34
 */
public class SendNewsToAllUser implements Runnable{
    @Override
    public void run() {

        System.out.println("请输入服务器要推送的消息");
        String news= Utility.readString(100);
        Message msg = Message.success(MessageType.MESSAGE_SEND_TO_ALL);
        msg.setSender("服务器");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd号-HH:mm:ss");
        String format = dtf.format(now);

        msg.setTime(format);
        msg.setContent(news);

        String userList = ClientThreadManager.getUserList("");

        String[] users = userList.split(" ");

        for (String user:users){
            ObjectOutputStream oos = ClientThreadManager.getOOSByUsername(user);
            try {
                oos.writeObject(msg);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
