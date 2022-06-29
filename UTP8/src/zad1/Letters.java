package zad1;

import java.util.ArrayList;

public class Letters extends Thread {
    ArrayList<Thread> threads = new ArrayList<Thread>();
    public Letters(String s){
        for(int i = 0; i < s.length(); i++){
            String tmp = s.substring(i, i+1);
            Runnable thread= () -> {
                while(true){
                    try{
                        System.out.print(tmp);
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        break;
                    }
                }
            };
            threads.add(new Thread(thread, "Thread " + tmp));
        }
    }
    public ArrayList<Thread> getThreads(){
        return threads;
    }

}