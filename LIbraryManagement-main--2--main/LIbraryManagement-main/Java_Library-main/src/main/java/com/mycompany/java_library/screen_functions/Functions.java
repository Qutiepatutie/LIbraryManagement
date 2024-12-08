package com.mycompany.java_library.screen_functions;


public class Functions{

    public static void clear_screen(int time_milli) throws InterruptedException{
        Thread.sleep(time_milli);
        System.out.print("\033c");
    }
    
}
