package JD.Threads.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket;

import JD.server.getid.ServerGetGoodsIDFromClient;  
  
/** 
 * ����Ϊ���߳��࣬���ڷ���� 
 */  
public class ServerThreadGetGoodsUrlFromClient implements Runnable {  
  
	private String rmsg;
    private Socket client = null;  
    public ServerThreadGetGoodsUrlFromClient(Socket client,String rmsg){  
        this.client = client;  
        this.rmsg=rmsg;
    }  
      
    @Override  
	public void run()
	{
		try
		{
			// ��ȡSocket���������������ͻ��˷�������
			PrintStream out = new PrintStream(client.getOutputStream());
			// ��ȡSocket�����������������մӿͻ��˷��͹���������
			BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));

			// ���մӿͻ��˷��͹���������
			String str = buf.readLine();
			
			//System.out.println("��������ȡ������"+str);
			
			if(str.equals("close"))
			{
				ServerGetGoodsIDFromClient.down=true;
			}
			
			 Thread HandleMsg=new Thread(new HandleGoodsurlMsg(str));
			    
			 HandleMsg.start();
			

			out.println(rmsg);
			
			
			
			client.close();
			
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
  
}  