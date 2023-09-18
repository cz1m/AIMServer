package com.like4u.aim.common;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/15 17:16
 */
public class AIMClientView {
    /**
     * 显示主菜单
     * */

    private boolean loop =true;



    private void mainMenu(){

        while (loop){

            System.out.println("欢迎使用局域网通讯系统AIM");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
        }
    }
}
