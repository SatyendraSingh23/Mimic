package com.raven.Sender;

import android.app.Activity;
import android.content.Intent;

import com.raven.remoteapp.MainActivity;

import java.io.PrintWriter;
import java.net.Socket;

public class Sender  extends Activity {
    private static final Sender sender=new Sender();
    Socket s;
    PrintWriter pw;
    String ip;
    boolean isSuccessfull=false;

    private Sender()
    {
    }
    public void Connect(String ip)
    {

        if(!ip.isEmpty()) {
            this.ip=ip;
            try {
                this.s = new Socket(this.ip, 7034);
                this.pw = new PrintWriter(this.s.getOutputStream());
                this.isSuccessfull = true;

            } catch (Exception e) {
                e.printStackTrace();
                this.isSuccessfull = false;
            }
        }
    }

    public boolean isConnectionSuccessfull()
    {
        return this.isSuccessfull;
    }
    public static Sender getInstance()
    {
        return sender;
    }
    public void send(String str)
    {

        try {
//
            this.pw.println(str);
            this.pw.flush();
//
        }catch (Exception e)
        {
            this.isSuccessfull=false;
        }

    }
    public void exit()
    {
        try
        {
            this.pw.write("exit");
            this.pw.close();
            this.s.close();
        }catch (Exception e)
        {

        }
    }

     public void sendDimension(String s) {
        try
        {
            this.pw.println(s);
            this.pw.flush();
        }catch (Exception e)
        {
            this.isSuccessfull=false;
        }
    }
}
