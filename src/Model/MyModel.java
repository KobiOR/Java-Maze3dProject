package Model;

import algorithem.Demo.MazeAdapter;
import algorithms.search.*;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerators.Coordinate;
import mazeGenerators.GrowingTreeGenerator;
import mazeGenerators.Maze3d;
import mazeGenerators.SimpleMaze3dGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class MyModel extends Observable implements Model {

    private HashMap<String, Maze3d> mHMap = null;
    private HashMap<String[],Solution<Coordinate>> sMap;

    @Override
    public Maze3d generate3dmaze(String mName, int mHeight, int fHeight, int fWidth, String algoname) throws Exception {

        if (mHMap == null)
            mHMap = new HashMap<>();

        Callable<Maze3d> thread = new Callable<Maze3d>() {
            @Override
            public Maze3d call() throws Exception {
                Maze3d myMaze;
                SimpleMaze3dGenerator SMG=new SimpleMaze3dGenerator();
                GrowingTreeGenerator gt=new GrowingTreeGenerator();
                try {
                    if (algoname.equals("GrowingTree")) {
                        for(myMaze = gt.Generate(mHeight, fHeight, fWidth); !SMG.verityPathOnMaze(myMaze); myMaze = gt.Generate(mHeight, fHeight, fWidth));
                        mHMap.put(mName,myMaze);
                    }
                    else { for(myMaze = gt.Generate(mHeight, fHeight, fWidth); !SMG.verityPathOnMaze(myMaze); myMaze = gt.Generate(mHeight, fHeight, fWidth));
                        mHMap.put(mName,myMaze);
                    }
                } catch (NullPointerException e) {
                    System.out.println("invalid algorithm name");
                    e.printStackTrace();
                }
                System.out.println("");
                setChanged();notifyObservers("Maze " + mName + " created!");
                return mHMap.get(mName);
            }

        };thread.call();



        return mHMap.get(mName);
    }
    @Override
    public void solve(String mazeName, String algorithm) throws Exception {
        if (mHMap == null) {setChanged();notifyObservers("You need to create maze first!");return;}
        if (!mHMap.containsKey(mazeName)) {setChanged();notifyObservers("No found maze named:"+mazeName);return;}
        Maze3d myMaze=mHMap.get(mazeName);
        Solution<Coordinate> sol;
        String[] st ={mazeName,algorithm};
        Callable<Solution<Coordinate>> thread = new Callable<Solution<Coordinate>>() {
            @Override
            public Solution<Coordinate> call() throws Exception {
                BFS<Coordinate>  bfs;
                DFS<Coordinate> dfs;
                try {
                    State<Coordinate> goal = new State<Coordinate>(myMaze.getEntry());
                    Searchable<Coordinate> mts = new MazeAdapter(myMaze);
                    Solution<Coordinate> sol;
                    if(algorithm.equals("DFS")) {
                        dfs = new DFS<Coordinate>();
                        sol = dfs.search(mts);
                    }
                    else if(algorithm.equals("BFS")) {
                        bfs = new BFS<Coordinate>();
                        sol = bfs.search(mts);
                    }
                    else
                    {
                        setChanged();notifyObservers("No found " +algorithm+" try again please!");
                        return  null;
                    }
                    setChanged();notifyObservers("Maze: " + mazeName + " was solve with: " + algorithm +" algorithm");
                    if (sMap==null)
                        sMap=new HashMap<>();
                    sMap.put(st,sol);

                   }
                catch (NullPointerException e) {
                    System.out.println("invalid algorithm name");
                    e.printStackTrace();
                }
                return null;

            }

        };
        thread.call();

    }
    @Override
    public String displaySolution(String mazeName) throws Exception {
        List<String []> list= new ArrayList<>(sMap.keySet());
        String st=new String();
        boolean b=false;
        for (String [] s:list)
        {if(s[0].equals(mazeName))
                if(sMap.get(s)==null)
                    return "Solution not ready yet";
                else { b=true;
                    st+=s[1]+sMap.get(s).toString() +"\n";
               }
        }
          if(b) return st;
             else return "No found solution for:"+ mazeName;




    }
    @Override
    public String display(String name) throws Exception {
        if (mHMap == null) return "You need to create maze first!\n";
        else if (mHMap.containsKey(name)) {
                    return mHMap.get(name).getMazeString();
        } else
            return "No maze named:" + name + " found\n";
    }
    @Override
    public long fileSize(String name) throws Exception {
        return 0;
    }
    @Override
    public int[][] getCrossByX(int index, String name) {

        if (mHMap.containsKey(name))
            return mHMap.get(name).getCrossSectionByX(index);
        else {setChanged();notifyObservers("No maze named:" + name + " found\n");}
        return null;
    }
    @Override
    public int[][] getCrossByY(int index, String name) {
        if (mHMap.containsKey(name))
            return mHMap.get(name).getCrossSectionByY(index);
        else {setChanged();notifyObservers("No maze named:" + name + " found\n");}
        return null;
    }
    @Override
    public int[][] getCrossByZ(int index, String name) {
        if (mHMap.containsKey(name))
            return mHMap.get(name).getCrossSectionByZ(index);
        else {setChanged();notifyObservers("No maze named:" + name + " found\n");}
        return null;
    }
    @Override
    public int mazeSize(String name) throws Exception {
        return 0;
    }
    @Override
    public String dir(String path) throws NullPointerException {
        File f = new File(path);
        String[] str = f.list();
        String listpath = "";

        for (int i = 0; i < str.length; i++)
            listpath += str[i] + '\n'; //getting one line down in order it will be more
        //comfortable for the user/client to read.
        return listpath;

    }
    @Override
    public boolean saveMaze(String fileName, String mazeName) {
        MyCompressorOutputStream e = null;
        if (mHMap != null && mHMap.containsKey(mazeName)) try {
            e = new MyCompressorOutputStream(new FileOutputStream(fileName));
            e.write(mHMap.get(mazeName).toByteArray());
            return true;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean loadMaze(String fileName, String mazeName) throws IOException {
        MyDecompressorInputStream in = null;
        try
        {
            in = new MyDecompressorInputStream(new DataInputStream(new FileInputStream(fileName)));
            byte[] b=new byte[(int) new File(fileName).length()];
            in.read(b);
            in.close();
            if(mHMap==null)
                mHMap=new HashMap<>();
            mHMap.put(mazeName,new Maze3d(b));
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}