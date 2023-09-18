package com.like4u.aim.service;

import com.baomidou.mybatisplus.core.toolkit.support.BiIntFunction;
import com.like4u.aim.domain.Message;
import com.like4u.aim.pojo.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/16 9:00
 */
@Component
@Data
public class AIMServerService {
    @Autowired
    private UserService userService;

    private Socket socket=null;
    ServerSocket serverSocket = null;

    private User user=new User();

    @PostConstruct
    public void checkLogin() {

        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("服务器正在9999端口监听");
            new Thread(new SendNewsToAllUser()).start();
            while (true) {

                socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                user = (User) ois.readObject();
                //发送消息的输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                if (userService.checkLogin(user.getUsername(), user.getPassword())) {

                    oos.writeObject(Message.success());

                    ServerConnectClientThread serverConnectClientThread =
                            new ServerConnectClientThread(socket, user.getUsername());

                    new Thread(serverConnectClientThread).start();
                    ClientThreadManager.addClientThread(user.getUsername(), serverConnectClientThread);

                } else {
                    oos.writeObject(Message.error());
                    oos.close();
                    ois.close();
                    if (socket!=null){
                        socket.close();
                    }


                }



            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {

                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
