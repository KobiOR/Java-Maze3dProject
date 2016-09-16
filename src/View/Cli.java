package View;

import Controller.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class Cli implements Runnable  {
    private BufferedReader in;
    private PrintWriter out;
    private HashMap<String, Command> hMap;


    public Cli(BufferedReader in, PrintWriter out, HashMap<String, Command> hMap) {
        this.in = in;
        this.out = out;
        this.hMap = hMap;
    }
    public void start() throws IOException {
        run();
    }
    @Override
    public void run() {

        while (true){
        try {

            String[] input = in.readLine().split(" ");
            if (!checkInput(input[0]))continue;
            if (input.length==1 )
                if (hMap.get(input[0]) != null && input[0].equals("exit"))
                    hMap.get(input[0]).doCommand(input[0]);
                else {displaymessage("Wrong input");continue;}

            else if(input.length>1)
                {
                input[1] = fixString(input);
                input[1] = input[1].trim();
                if (hMap.get(input[0]) != null)
                    hMap.get(input[0]).doCommand(input[1]);
                else
                    out.print("Error!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }}
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
    private boolean checkInput(String st){
        for (String s:hMap.keySet())
        {
            if(s.equals(st))return true;
        }
        displaymessage("Wrong input!");
        return false;

    }
}
