package com.like4u.aim.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/16 10:26
 */
public class ClientThreadManager {
    private static HashMap<String,ServerConnectClientThread> threads=new HashMap<>();
    public static void addClientThread(String username,ServerConnectClientThread serverConnectClientThread){
        threads.put(username, serverConnectClientThread);

    }
    public static ServerConnectClientThread getClientThread(String username){
        return threads.get(username);
    }

    public static void deleteClientThread(String username){
        threads.remove(username);
    }

    public static String getUserList(String sender){

        Set<String> userSet = threads.keySet();

        StringBuilder userList= new StringBuilder();
        for (String user:userSet){
            //如果从集合里拿出来的不是发送方自己就给他拼到用户列表里去
            if (!user.equals(sender))
                userList.append(user).append(" ");
        }
        return userList.toString();

    }
    public static ObjectOutputStream getOOSByUsername(String username){
        try {
            return new ObjectOutputStream(ClientThreadManager.getClientThread(username).getSocket().getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static ObjectInputStream getOISByUsername(String username){
        try {
            return new ObjectInputStream(ClientThreadManager.getClientThread(username).getSocket().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
