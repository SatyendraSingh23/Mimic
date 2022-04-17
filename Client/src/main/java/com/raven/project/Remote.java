/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.project;

/**
 *
 * @author Satyendra
 */
import java.awt.Robot;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
	
class Hold extends Thread
{
	private static int currentKey;
	private boolean status=false;
	private int key;
	private static Robot r; 
	static
	{
		try
		{	
			currentKey=-1;
			r=new Robot();
		}catch(Exception e)
		{

		}
	}

	Hold(){ }
	private long beg;
	Hold(int key)
	{
		Thread.getAllStackTraces().keySet().forEach((t) -> {
			if(t.getName().equals("key")) t.stop();
		});
		this.setName("key");
		this.key=key;
		this.status=true;

		this.beg=System.currentTimeMillis();
	}
	public void doStop()
	{
		this.status=false;
	}
    @Override
	public void run()
	{

		int i=1;
		while(System.currentTimeMillis()-this.beg<200);
		{
			if(i==1)r.keyPress(this.key);
			i=0;
		}
		while(this.status)
		{
			r.keyPress(this.key);
			try
			{
				Thread.sleep(50);
			}catch (Exception e)
			{
			r.keyRelease(this.key);				
			}
		}
	r.keyRelease(this.key);	
	}
}
interface ClientStatus
{
   abstract void onClientLoggedIn(String name,String address);
   abstract void onClientLoggedOut(String name,String address);
}

public class Remote extends Thread 
{       
    
        private final  java.util.List<ClientStatus> listeners;
        private static  Remote remote=new Remote();
        static int prevY=0,mousewheel=0;
	static long start;
	static Robot robot;
	static Socket s;
	static ServerSocket ss;
	static InputStreamReader isr;
	static BufferedReader br;
	static String message,read;
	static String arr[];
	static int clientHeight,clientWidth;
	static double height,width;
        static boolean isConnectionSuccessfull=false,interruptOccured=false;
        boolean isConnected =false;
        static String clientName;
        static String clientIpAddress;
        
        @Override
        public boolean isInterrupted()
        {
            return Remote.interruptOccured;
        }
        private Remote()
        {
            this.listeners = new ArrayList<>();
        }
        public void addListener(ClientStatus toAdd)
        {
            listeners.add(toAdd);
        }
        
        public static Remote getInstance()
        {
            if(Remote.s!=null && Remote.s.isClosed())
            {
                Remote.remote=new Remote();
            }
            Remote.interruptOccured=false;
            return Remote.remote;
        }
        private  synchronized void setLoggedIn(boolean x)
        {
            Remote.isConnectionSuccessfull=x;
            for(ClientStatus c:listeners)c.onClientLoggedIn(Remote.clientName,Remote.clientIpAddress);

        }
        private  void setClientName(String name)
        {
            Remote.clientName=name;
        }

        private  void setClientIp(String Ip)
        {
            Remote.clientIpAddress=Ip;
        }
        public void stopConnection()
        {
            if(remote.isConnected)
            {
            try
            {
                remote.isConnected=false;
                        
                remote.ss.close();
                remote.s.close();
                remote.isr.close();
                remote.br.close();

                remote.closeConnection();
            }catch(Exception e)
            {
                
            }
            }
            
        }

    /**
     *
     */
        private synchronized void closeConnection()
        {
            try
            {
                for(ClientStatus c:listeners)c.onClientLoggedOut(remote.clientName,remote.clientIpAddress);
            }catch(Exception e)
            {
                
            }

        }
    @Override
    public synchronized void run()
	{

		float x,y;
		try
		{
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			width = screenSize.getWidth();
			height = screenSize.getHeight();
			robot=new Robot();
			ss = new ServerSocket(7034);

                        s=ss.accept();

                        isConnected =true;
                        remote.setClientIp(s.getInetAddress().getHostAddress());
                        remote.setClientName(s.getInetAddress().getHostName());
                        remote.setLoggedIn(true);
                        isr=new InputStreamReader(s.getInputStream());
			br=new BufferedReader(isr);
			message=br.readLine();
			if(message!=null && message.contains("@")){
				arr=message.split("@");
				clientHeight=Integer.parseInt(arr[0]);
				clientWidth=Integer.parseInt(arr[1]);
                        }
//                    Hold robot.keyPress();    
                    while(isConnected)  
        		{
                            read=br.readLine();
                            System.out.println("READ "+read);                                
                            start = System.currentTimeMillis();
                            if(read!=null)message=read;
                                if( message.contains("@"))
                                {
                                    arr=message.split("@");
                                    System.out.println(arr[0]+","+arr[1]);
                                    clientHeight=(int)Float.parseFloat(arr[0]);
                                    clientWidth=(int)Float.parseFloat(arr[1]);

                                }
                                else if(message.equalsIgnoreCase("lcd"))robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				else if(message.equalsIgnoreCase("MD"))robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				else if(message.equalsIgnoreCase("mu"))robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				else if(message.equalsIgnoreCase("lcu"))robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				else if(message.equalsIgnoreCase("rcd"))robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
				else if(message.equalsIgnoreCase("rcu"))robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
				else if(message.equalsIgnoreCase("mcu"))robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
				else if(message.equalsIgnoreCase("lcu"))robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
				else if(message.startsWith("drag"))
				{
					arr=message.split(",");
					y=(int)Float.parseFloat(arr[1]);
					// y=(int)((y/clientHeight)*height);
					mousewheel=1;
					if(y<prevY)mousewheel=-1;
					prevY=(int)y;
					robot.mouseWheel((int)1);
				}
				else if(message.contains(",") && message.length()>1)
				{
					arr=message.split(",");
					x=(int)Float.parseFloat(arr[0]);
					y=(int)Float.parseFloat(arr[1]);
					x=(int)((x/clientWidth)*width);
					y=(int)((y/clientHeight)*height);
					robot.mouseMove((int)x,(int)y);
				}

				else if(message.equals("a"))
				{	
					robot.keyPress(KeyEvent.VK_A);
					
				} 
		        else if(message.equals("b"))
		        {
		        	robot.keyPress(KeyEvent.VK_B);
		        	
		        } 
		        else if(message.equals("c"))
		        {
		        	robot.keyPress(KeyEvent.VK_C);
		        	
		        } 
		        else if(message.equals("d"))
		        {
		        	robot.keyPress(KeyEvent.VK_D);
		        	
		        } 
		        else if(message.equals("e"))
		        {
		        	robot.keyPress(KeyEvent.VK_E);
		        	
		        } 
		        else if(message.equals("f"))
		        {
		        	robot.keyPress(KeyEvent.VK_F);
		        	
		        } 
		        else if(message.equals("g"))
		        {
		        	robot.keyPress(KeyEvent.VK_G);
		        	
		        } 
		        else if(message.equals("h"))
		        {
		        	robot.keyPress(KeyEvent.VK_H);
		        	
		        } 
		        else if(message.equals("i"))
		        {
		        	robot.keyPress(KeyEvent.VK_I);
		        	
		        } 
		        else if(message.equals("j"))
		        {
		        	robot.keyPress(KeyEvent.VK_J);
		        	
		        } 
		        else if(message.equals("k"))
		        {
		        	robot.keyPress(KeyEvent.VK_K);
		        	
		        } 
		        else if(message.equals("l"))
		        {
		        	robot.keyPress(KeyEvent.VK_L);
		        	
		        } 
		        else if(message.equals("m"))
		        {
		        	robot.keyPress(KeyEvent.VK_M);
		        	
		        } 
		        else if(message.equals("n"))
		        {
		        	robot.keyPress(KeyEvent.VK_N);
		        	
		        } 
		        else if(message.equals("o"))
		        {
		        	robot.keyPress(KeyEvent.VK_O);
		        	
		        } 
		        else if(message.equals("p"))
		        {
		        	robot.keyPress(KeyEvent.VK_P);
		        	
		        } 
		        else if(message.equals("q"))
		        {
		        	robot.keyPress(KeyEvent.VK_Q);
		        	
		        } 
		        else if(message.equals("r"))
		        {
		        	robot.keyPress(KeyEvent.VK_R);
		        	
		        } 
		        else if(message.equals("s"))
		        {
		        	robot.keyPress(KeyEvent.VK_S);
		        	
		        } 
		        else if(message.equals("t"))
		        {
		        	robot.keyPress(KeyEvent.VK_T);
		        	
		        } 
		        else if(message.equals("u"))
		        {
		        	robot.keyPress(KeyEvent.VK_U);
		        	
		        } 
		        else if(message.equals("v"))
		        {
		        	robot.keyPress(KeyEvent.VK_V);
		        	
		        } 
		        else if(message.equals("w"))
		        {
		        	robot.keyPress(KeyEvent.VK_W);
		        	
		        } 
		        else if(message.equals("x"))
		        {
		        	robot.keyPress(KeyEvent.VK_X);
		        	
		        } 
		        else if(message.equals("y"))
		        {
		        	robot.keyPress(KeyEvent.VK_Y);
		        	
		        } 
		        else if(message.equals("z")) 
		        {
		        	robot.keyPress(KeyEvent.VK_Z);
		        	
		        } 
		        else if(message.equals("`")) 
		        {
		        	robot.keyPress(KeyEvent.VK_BACK_QUOTE);
		        	
		        } 
		        else if(message.equals("0")) 
		        {
		        	robot.keyPress(KeyEvent.VK_0);
		        	
		        }
		        else if(message.equals("1")) 
		        {
		        	robot.keyPress(KeyEvent.VK_1);
		        	
		        }
		        else if(message.equals("2")) 
		        {
		        	robot.keyPress(KeyEvent.VK_2);
		        	
		        }
		        else if(message.equals("3")) 
		        {
		        	robot.keyPress(KeyEvent.VK_3);
		        	
		        }
		        else if(message.equals("4")) 
		        {
		        	robot.keyPress(KeyEvent.VK_4);
		        	
		        }
		        else if(message.equals("5")) 
		        {
		        	robot.keyPress(KeyEvent.VK_5);
		        	
		        }
		        else if(message.equals("6")) 
		        {
		        	robot.keyPress(KeyEvent.VK_6);
		        	
		        }
		        else if(message.equals("7")) 
		        {
		        	robot.keyPress(KeyEvent.VK_7);
		        	
		        }
		        else if(message.equals("8")) 
		        {
		        	robot.keyPress(KeyEvent.VK_8);
		        	
		        }
		        else if(message.equals("9")) 
		        {
		        	robot.keyPress(KeyEvent.VK_9);
		        	
		        }
		        else if(message.equals("-")) 
		        {
		        	robot.keyPress(KeyEvent.VK_MINUS);
		        	
		        } 
		        else if(message.equals("=")) 
		        {
		        	robot.keyPress(KeyEvent.VK_EQUALS);
		        	
		        } 
		        else if(message.equals("tab")) 
		        {
		        	robot.keyPress(KeyEvent.VK_TAB);
		        	
		        } 
		        else if(message.equals("enter")) 
		        {
		        	robot.keyPress(KeyEvent.VK_ENTER);
		        	
		        } 
		        else if(message.equals("[")) 
		        {
		        	robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
		        	
		        } 
		        else if(message.equals("]")) 
		        {
		        	robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
		        	
		        } 
		        else if(message.equals("\\")) 
		        {
		        	robot.keyPress(KeyEvent.VK_BACK_SLASH);
		        	
		        } 
		        else if(message.equals(";")) 
		        {
		        	robot.keyPress(KeyEvent.VK_SEMICOLON);
		        	
		        } 
		        else if(message.equals("'")) 
		        {
		        	robot.keyPress(KeyEvent.VK_QUOTE);
		        	
		        } 
		        else if(message.equals("COMMA")) 
		        {
		        	robot.keyPress(KeyEvent.VK_COMMA);
		        	
		        } 
		        else if(message.equals(".")) 
		        {
		        	robot.keyPress(KeyEvent.VK_PERIOD);
		        	
		        } 
		        else if(message.equals("/")) 
		        {
		        	robot.keyPress(KeyEvent.VK_SLASH);
		        	
		        } 
		        else if(message.equals(" ")) 
		        {
		        	robot.keyPress(KeyEvent.VK_SPACE);
		        	
		        }
		        else if(message.equals("alt")) 
		        {
					robot.keyPress(KeyEvent.VK_ALT);
		        }
		        else if(message.equals("shift")) 
		        {
		        	robot.keyPress(KeyEvent.VK_SHIFT);
		        }
		        else if(message.equals("caps")) 
		        {
					robot.keyPress(KeyEvent.VK_CAPS_LOCK);
		        }
		        else if(message.equals("back")) 
		        {
		        	robot.keyPress(KeyEvent.VK_BACK_SPACE);
		        	
		        }
		        else if(message.equals("del")) 
		        {
		        	robot.keyPress(KeyEvent.VK_DELETE);
		        	
		        }
		        else if(message.equals("ptr")) 
		        {
					robot.keyPress(KeyEvent.VK_PRINTSCREEN);
		        }
		        else if(message.equals("f12")) 
		        {
					robot.keyPress(KeyEvent.VK_F12);
		        }
		        else if(message.equals("f11")) 
		        {
					robot.keyPress(KeyEvent.VK_F11);
		        }
		        else if(message.equals("f10")) 
		        {
					robot.keyPress(KeyEvent.VK_F10);
		        }
		        else if(message.equals("f9")) 
		        {
					robot.keyPress(KeyEvent.VK_F9);
		        }
		        else if(message.equals("f8")) 
		        {
					robot.keyPress(KeyEvent.VK_F8);
		        }
		        else if(message.equals("f7")) 
		        {
					robot.keyPress(KeyEvent.VK_F7);
		        }
		        else if(message.equals("f6")) 
		        {
					robot.keyPress(KeyEvent.VK_F6);
		        }
		        else if(message.equals("f5")) 
		        {
					robot.keyPress(KeyEvent.VK_F5);
		        }
		        else if(message.equals("f4")) 
		        {
					robot.keyPress(KeyEvent.VK_F4);
		        }
		        else if(message.equals("f3")) 
		        {
					robot.keyPress(KeyEvent.VK_3);
		        }
		        else if(message.equals("f2")) 
		        {
					robot.keyPress(KeyEvent.VK_F2);
		        }
		        else if(message.equals("f1")) 
		        {
					robot.keyPress(KeyEvent.VK_F1);
		        }
		        else if(message.equals("esc")) 
		        {
					robot.keyPress(KeyEvent.VK_ESCAPE);
		        }
		        else if(message.equals("win")) 
		        {
					robot.keyPress(KeyEvent.VK_WINDOWS);
		        }
		        else if(message.equals("up")) 
		        {
		        	robot.keyPress(KeyEvent.VK_UP);
		        	
		        }
		        else if(message.equals("left")) 
		        {
		        	robot.keyPress(KeyEvent.VK_LEFT);
		        	
		        }
		        else if(message.equals("down")) 
		        {
		        	robot.keyPress(KeyEvent.VK_DOWN);
		        	
		        }
		        else if(message.equals("right")) 
		        {
		        	robot.keyPress(KeyEvent.VK_RIGHT);
		        	
		        }
		        else if(message.equals("ins")) 
		        {
					robot.keyPress(KeyEvent.VK_INSERT);
		        }
		        else if(message.equals("ctrl")) 
		        {
					robot.keyPress(KeyEvent.VK_CONTROL);
		        }

		        //-------------- RELEASING EVENTS ----------------------------//
				else if(message.equals("U-a"))
		        {	
					
		        	 
		        	robot.keyRelease(KeyEvent.VK_A);
		        }		        
		        else if(message.equals("U-b"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_B);
		        } 
		        else if(message.equals("U-c"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_C);
		        } 
		        else if(message.equals("U-d"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_D);
		        } 
		        else if(message.equals("U-e"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_E);
		        } 
		        else if(message.equals("U-f"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F);
		        } 
		        else if(message.equals("U-g"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_G);
		        } 
		        else if(message.equals("U-h"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_H);
		        } 
		        else if(message.equals("U-i"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_I);
		        } 
		        else if(message.equals("U-j"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_J);
		        } 
		        else if(message.equals("U-k"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_K);
		        } 
		        else if(message.equals("U-l"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_L);
		        } 
		        else if(message.equals("U-m"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_M);
		        } 
		        else if(message.equals("U-n"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_N);
		        } 
		        else if(message.equals("U-o"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_O);
		        } 
		        else if(message.equals("U-p"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_P);
		        } 
		        else if(message.equals("U-q"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_Q);
		        } 
		        else if(message.equals("U-r"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_R);
		        } 
		        else if(message.equals("U-s"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_S);
		        } 
		        else if(message.equals("U-t"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_T);
		        } 
		        else if(message.equals("U-u"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_U);
		        } 
		        else if(message.equals("U-v"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_V);
		        } 
		        else if(message.equals("U-w"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_W);
		        } 
		        else if(message.equals("U-x"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_X);
		        } 
		        else if(message.equals("U-y"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_Y);
		        } 
		        else if(message.equals("U-z"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_Z);
		        } 
		        else if(message.equals("U-`"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
		        } 
		        else if(message.equals("U-0"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_0);
		        } 
		        else if(message.equals("U-1"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_1);
		        } 
		        else if(message.equals("U-2"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_2);
		        } 
		        else if(message.equals("U-3"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_3);
		        } 
		        else if(message.equals("U-4"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_4);
		        } 
		        else if(message.equals("U-5"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_5);
		        } 
		        else if(message.equals("U-6"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_6);
		        } 
		        else if(message.equals("U-7"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_7);
		        } 
		        else if(message.equals("U-8"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_8);
		        } 
		        else if(message.equals("U-9"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_9);
		        } 
		        else if(message.equals("U--"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_MINUS);
		        } 
		        else if(message.equals("U-="))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_EQUALS);
		        } 
		        else if(message.equals("U-tab"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_TAB);
		        } 
		        else if(message.equals("U-enter"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_ENTER);
		        } 
		        else if(message.equals("U-["))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
		        } 
		        else if(message.equals("U-]"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
		        } 
		        else if(message.equals("U-\\"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_BACK_SLASH);
		        } 
		        else if(message.equals("U-;"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_SEMICOLON);
		        } 
		        else if(message.equals("U-'"))
		        {	
				
		        		
		        	robot.keyRelease(KeyEvent.VK_QUOTE);
		        } 
		        else if(message.equals("U-COMMA"))
		        {	
				
		        		
		        	robot.keyRelease(KeyEvent.VK_COMMA);
		        } 
		        else if(message.equals("U-."))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_PERIOD);
		        } 
		        else if(message.equals("U-/"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_SLASH);
		        } 
		        else if(message.equals("U- "))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_SPACE);
		        } 
		        else if(message.equals("U-alt"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_ALT);

		        } 
		        else if(message.equals("U-shift"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_SHIFT);

		        } 
		        else if(message.equals("U-caps"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_CAPS_LOCK);

		        } 
		        else if(message.equals("U-back"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		        } 
		        else if(message.equals("U-del"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_DELETE);
		        } 
		        else if(message.equals("U-ptr"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_PRINTSCREEN);

		        } 
		        else if(message.equals("U-f12"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F12);

		        } 
		        else if(message.equals("U-f11"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F11);

		        } 
		        else if(message.equals("U-f10"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F10);

		        } 
		        else if(message.equals("U-f9"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F9);

		        } 
		        else if(message.equals("U-f8"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F8);

		        } 
		        else if(message.equals("U-f7"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F7);

		        } 
		        else if(message.equals("U-f6"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F6);

		        } 
		        else if(message.equals("U-f5"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F5);
 
		        } 
		        else if(message.equals("U-f4"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F4);

		        } 
		        else if(message.equals("U-f3"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F3);

		        } 
		        else if(message.equals("U-f2"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F2);
		        } 
		        else if(message.equals("U-f1"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_F1);
		        } 
		        else if(message.equals("U-esc"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_ESCAPE);
		        } 
		        else if(message.equals("U-win"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_WINDOWS);
		        } 
		        else if(message.equals("U-up"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_UP);
		        } 
		        else if(message.equals("U-left"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_LEFT);
		        } 
		        else if(message.equals("U-down"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_DOWN);
		        } 
		        else if(message.equals("U-right"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_RIGHT);
		        } 
		        else if(message.equals("U-ins"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_INSERT);	
		        }			        	
				else if(message.equals("U-ctrl"))
		        {	
					
		        		
		        	robot.keyRelease(KeyEvent.VK_CONTROL); 
		        }	   	
			else if(message.equalsIgnoreCase("exit"))
			{
                            throw new Exception("------------------------Exception Aya");
                            
			}

			}

		}catch(Exception e)
		{
                    Remote.interruptOccured=true;
                    remote.stopConnection();
		}
	}

}
