package View;

import Presenters.Command;
import Presenters.Presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class Cli extends Observable {
    private BufferedReader in;
    private PrintWriter out;


    public Cli(BufferedReader in, PrintWriter out, Presenter p) {
        this.in = in;
        this.out = out;
        addObserver(p);
    }
    public void start() throws IOException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String[] input = in.readLine().split(" ");
                        setChanged();
                        notifyObservers(input);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });thread.start();
    }
    public void displaymessage(String str){
        out.println(str);
        out.flush();
    }
    public String fixString(String[] str){
        String strCmd="";
        for (int i = 1; i <str.length ; i++)
            strCmd=strCmd +" "+str[i];
        return strCmd;

    }

}
