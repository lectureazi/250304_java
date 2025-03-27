package com.grepp.greppcat.a_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TcpServer {
    
    private final int port;
    private ServerSocket serverSocket;
    
    public TcpServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void start(){
        System.out.println("system : 서버 실행");
        
        // http server 특징 stateless
        // 클라이언트의 상태를 저장하지 않는다.
        while(true){
            try(
                // 클라이언트의 최초 요청이 도착하면
                // 클라이언트와 1:1 통신을 하기 위한 소켓을 생성
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
            ){
            
                String startLine = reader.readLine();
                if(startLine == null) continue;
                
                
                
                
                
                

//                String headerLine = reader.readLine();
//                while(!headerLine.isEmpty()){
//                    System.out.println(headerLine);
//                    headerLine = reader.readLine();
//                }
                
                writer.println("HTTP/1.1 200 OK");
                writer.println("\n");
                writer.println("<h1>hello world</h1>");
                
                writer.flush();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
