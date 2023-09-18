package com.like4u.aim.service;

import com.like4u.aim.domain.Message;
import com.like4u.aim.domain.MessageType;
import com.like4u.aim.service.impl.MessageServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/16 10:11
 */

@Data
@Slf4j

public class ServerConnectClientThread implements Runnable{

    private Socket socket;
    private String username;
    

    public ServerConnectClientThread(Socket socket,String username){
        this.socket=socket;
        this.username=username;
    }


    @Override
    public void run() {


        while (true){

            try {
                if (socket.getInputStream().available() > 0){
                System.out.println("服务端与客户端"+username+"保持通信");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg =(Message) ois.readObject();


                /**
                 * 正常来说应该把MessageType这个属性改成枚举类
                 * 获取全部在线用户
                 * */
                if (msg.getMessageType().equals(MessageType.MESSAGE_GET_USER_LIST)){
                    System.out.println("用户"+msg.getSender()+"请求获取全部在线用户...");

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    Message userList = Message.success(ClientThreadManager.getUserList(msg.getSender()),MessageType.MESSAGE_USER_LIST);
                    userList.setSender("Server");
                    userList.setGetter(msg.getSender());
                    oos.writeObject(userList);


                }else if (msg.getMessageType().equals(MessageType.MESSAGE_CLIENT_EXIT)){
                    /**
                     *用户登出
                     * */
                    ClientThreadManager.deleteClientThread(username);

                    socket.close();
                    System.out.println("用户"+username+"已登出");
                    break;
                }else if (msg.getMessageType().equals(MessageType.MESSAGE_COMMON)){
                    /**
                     * 服务器收到建立通讯的消息建立发送方和接收方的信息桥梁
                     * 首先获取目标对象的socket流
                     * */

                    System.out.println(msg.getTime()+msg.getSender()+"请求向"+msg.getGetter()+"发送"+msg.getContent());
                    ObjectOutputStream oos = ClientThreadManager.getOOSByUsername(msg.getGetter());

                    oos.writeObject(msg);

                }else if (msg.getMessageType().equals(MessageType.MESSAGE_SEND_TO_ALL)){

                    /**
                     * 群发消息
                     * */
                    String userList = ClientThreadManager.getUserList(msg.getSender());
                    String[] users = userList.split(" ");
                    for (String user:users){
                        ObjectOutputStream oos = ClientThreadManager.getOOSByUsername(user);
                        oos.writeObject(msg);
                    }

                }else if (msg.getMessageType().equals(MessageType.MESSAGE_FILE)){

                    ObjectOutputStream oos = ClientThreadManager.getOOSByUsername(msg.getGetter());

                    log.info("{}请求向{}发送一个文件",msg.getSender(),msg.getGetter());

                    oos.writeObject(msg);
                }



                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
