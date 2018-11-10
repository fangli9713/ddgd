package com.fangln.dd.init.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class M2MReceive extends Thread {

    private Socket s;
    
    
    public M2MReceive(Socket s) {
        this.s = s;
    }
 
    @Override
    public void run() {
        try {
 
            //构建输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
 
            //不断的接收信息
            while(true){
 
                String info = null;
 
                //接收信息
                if((info=br.readLine()) != null){
                    System.out.println(info);
                }
 
            }
 
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
