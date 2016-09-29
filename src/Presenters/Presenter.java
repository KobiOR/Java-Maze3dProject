package Presenters;

import Model.Model;
import Presenters.Commands.*;
import Views.Cli;
import Views.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import static java.lang.System.exit;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class Presenter implements Observer {

    private View cView;
    private Model cModel;
    private HashMap<String,Command> hMap;

    public View getView() {
        return cView;
    }
    public Model getModel() {
        return cModel;
    }
    public Presenter(View cView, Model cModel) {
        installCommands();
        this.cView = cView;
        this.cModel = cModel;
        cView.setCli(new Cli(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true),this));

    }
    @Override
    public void update(Observable object, Object arg){
        String str=object.getClass().getName();
        switch (str)
        {
            case "Views.MazeWindow":{doCommand((String[])arg);break;}
            case "Views.Cli":{doCommand((String[])arg);break;}
            case "Model.MyModel":{cView.display(arg);break;}
            default:{exit(1);}
        }

    }
    private void installCommands()    {
        hMap=new HashMap<>();
        hMap.put("dir",new Dir(this));
        hMap.put("generate_maze",new GenerateMaze(this));
        hMap.put("display",new Display(this));
        hMap.put("display_cross_section",new DisplayCrossSection(this));
        hMap.put("save_maze",new SaveMaze(this));
        hMap.put("load_maze",new LoadMaze(this));
        hMap.put("solve",new Solve(this));
        hMap.put("display_solution",new DisplaySolution(this));
        hMap.put("exit",new Exit(this));

    }
    private void doCommand(String [] strArray){
        if (!checkInput(strArray[0])) return;
        else {
                    if (strArray.length == 1)
                        if (hMap.get(strArray[0]) != null && strArray[0].equals("exit")) {
                            hMap.get(strArray[0]).doCommand(strArray[0]);
                        }
                        else {
                            cView.display("Wrong input");
                        }

                    else if (strArray.length > 1) {
                        strArray[1] = fixString(strArray);
                        strArray[1] = strArray[1].trim();
                        if (hMap.get(strArray[0]) != null)
                            hMap.get(strArray[0]).doCommand(strArray[1]);
                        else
                            cView.display("Error!!");
                    }
                }

   }
    private String fixString(String[] str){
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
        cView.display("Wrong command!");
        return false;

    }
}
