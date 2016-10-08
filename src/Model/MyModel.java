package Model;

import Presenters.PropertiesLoader;
import algorithem.Demo.MazeAdapter;
import algorithms.search.*;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerators.Coordinate;
import mazeGenerators.GrowingTreeGenerator;
import mazeGenerators.Maze3d;
import mazeGenerators.SimpleMaze3dGenerator;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static mazeGenerators.Maze3dGenerator.*;


/**
 * Created by orrko_000 on 12/09/2016.
 */
public class MyModel extends Observable implements Model {

    private ExecutorService executor;
    private HashMap<String, Maze3d> mHMap = null;
    private HashMap<String[], Solution<Coordinate>> sMap;
    private Presenters.Properties p;

    public MyModel() {
       p = PropertiesLoader.getInstance().getProperties();
        executor = Executors.newFixedThreadPool(1);
        try {
            loadHashMap();
        } catch (IOException e) {
            notifyObservers("No found files to load");
        }

    }
    @Override
    public Maze3d generate3dmaze(String mName, int mHeight, int fHeight, int fWidth, String algoname) throws Exception {

        if (mHMap == null)
            mHMap = new HashMap<>();
        executor.submit(new Callable<Maze3d>() {
            @Override
            public Maze3d call() throws Exception {
                Maze3d myMaze;
                SimpleMaze3dGenerator SMG = new SimpleMaze3dGenerator();
                GrowingTreeGenerator gt = new GrowingTreeGenerator();
                try {
                    if (algoname.equals("Growin_Tree")) {
                        for (myMaze = gt.Generate(mHeight, fHeight, fWidth); !SMG.verityPathOnMaze(myMaze); myMaze = gt.Generate(mHeight, fHeight, fWidth))
                            ;
                        mHMap.put(mName, myMaze);
                    } else {
                        for (myMaze = SMG.Generate(mHeight, fHeight, fWidth); !SMG.verityPathOnMaze(myMaze); myMaze = gt.Generate(mHeight, fHeight, fWidth))
                            ;
                        mHMap.put(mName, myMaze);
                    }
                    setChanged();
                    notifyObservers("Maze " + mName + " created!");
                    setChanged();
                    notifyObservers(myMaze);

                } catch (NullPointerException e) {
                    System.out.println("invalid algorithm name");
                    e.printStackTrace();
                }
                return mHMap.get(mName);
            }

        });
        return mHMap.get(mName);
    }
    @Override
    public void solve(String mazeName, String algorithm) throws Exception {
        if (mHMap == null) {
            setChanged();
            notifyObservers("You need to create maze first!");
            return;
        }
        if (!mHMap.containsKey(mazeName)) {
            setChanged();
            notifyObservers("No found maze named:" + mazeName);
            return;
        }
        Maze3d myMaze = mHMap.get(mazeName);
        Solution<State<Coordinate>> sol;
        String[] st = {mazeName, algorithm};
        executor.submit(new Callable<Solution<Coordinate>>() {
            @Override
            public Solution<Coordinate> call() throws Exception {
                BFS<Coordinate> bfs;
                DFS<Coordinate> dfs;
                try {
                    State<Coordinate> goal = new State<Coordinate>(myMaze.getEntry());
                    Searchable<Coordinate> mts = new MazeAdapter(myMaze);
                    Solution<Coordinate> sol;
                    if (algorithm.equals("DFS")) {
                        dfs = new DFS<Coordinate>();
                        sol = dfs.search(mts);
                    } else if (algorithm.equals("BFS")) {
                        bfs = new BFS<Coordinate>();
                        sol = bfs.search(mts);
                    } else {
                        setChanged();
                        notifyObservers("No found " + algorithm + " try again please!");
                        return null;
                    }
                    setChanged();
                    notifyObservers("Maze: " + mazeName + " was solve with: " + algorithm + " algorithm");
                    setChanged();
                    notifyObservers(sol);
                    if (sMap == null)
                        sMap = new HashMap<>();
                    sMap.put(st, sol);

                } catch (NullPointerException e) {
                    System.out.println("invalid algorithm name");
                    e.printStackTrace();
                }
                return null;

            }

        });

    }
    @Override
    public String displaySolution(String mazeName) throws Exception {
        List<String[]> list = new ArrayList<>(sMap.keySet());
        String st = new String();
        boolean b = false;
        for (String[] s : list) {
            if (s[0].equals(mazeName))
                if (sMap.get(s) == null)
                    return "Solution not ready yet";
                else {
                    b = true;
                    st += s[1] + sMap.get(s).toString() + "\n";
                    setChanged();
                    notifyObservers(sMap.get(s));
                }
        }

        if (b) return st;
        else return "No found solution for:" + mazeName;


    }
    @Override
    public String display(String name) throws Exception {
        if (mHMap == null) return "You need to create maze first!\n";
        else if (mHMap.containsKey(name)) {
            setChanged();
            notifyObservers(mHMap.get(name));
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
        else {
            setChanged();
            notifyObservers("No maze named:" + name + " found\n");
        }
        return null;
    }
    @Override
    public int[][] getCrossByY(int index, String name) {
        if (mHMap.containsKey(name))
            return mHMap.get(name).getCrossSectionByY(index);
        else {
            setChanged();
            notifyObservers("No maze named:" + name + " found\n");
        }
        return null;
    }
    @Override
    public int[][] getCrossByZ(int index, String name) {
        if (mHMap.containsKey(name))
            return mHMap.get(name).getCrossSectionByZ(index);
        else {
            setChanged();
            notifyObservers("No maze named:" + name + " found\n");
        }
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
        try {
            in = new MyDecompressorInputStream(new DataInputStream(new FileInputStream(fileName)));
            byte[] b = new byte[(int) new File(fileName).length()];
            in.read(b);
            in.close();
            if (mHMap == null)
                mHMap = new HashMap<>();
            mHMap.put(mazeName, new Maze3d(b));
            setChanged();
            notifyObservers("Maze: "+mazeName+" successfully loaded!");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean saveHashMap() throws IOException {
        if (sMap == null) return false;
        File f = new File("data.zip");
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipEntry e = new ZipEntry("data.txt");
        out.putNextEntry(e);
        //out.write(((Integer)sMap.size()).toString().getBytes());
       // out.write("\r\n".getBytes());
        for (String[] s : sMap.keySet()) {
            for (String se : s) {
                out.write(se.getBytes());
                out.write("\r\n".getBytes());
            }
            out.write(sMap.get(s).toString().getBytes());
            out.write("\r\n".getBytes());
        }



        out.closeEntry();
        out.close();
        return true;

    }
    public boolean loadHashMap() throws IOException {
        unZipIt("data.zip");
        String s=new String();
        Queue<String> queue=new LinkedList<String>();
        if (sMap==null)
            sMap=new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                queue.add(line);
            }
        }
        while (!queue.isEmpty()) {
            List<State<Coordinate>> states = new ArrayList();
            String []s1=new String[]{new String(queue.poll()),new String(queue.poll())};
            String [] s3=queue.poll().split("->");
            for (String se:s3)
            {
                states.add(new State<Coordinate>(new Coordinate(se)));
            }
            sMap.put(s1,new Solution<Coordinate>(states));
        }
        return true;
    }
    public void unZipIt(String zipFile){
        byte[] buffer = new byte[1024];

        try{
            File folder = new File("");
            if(!folder.exists()){
                folder.mkdir();
            }
            ZipInputStream zis =new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){
                String fileName = ze.getName();
                File newFile = new File(fileName);
                System.out.println(ANSI_BOLD+ANSI_RED+"Data unzip : "+ newFile.getAbsoluteFile()+ANSI_RESET);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println(ANSI_BOLD+ANSI_BLUE+"The file:"+ zipFile+" was unzip successfully"+ANSI_RESET);

        }catch(IOException ex){
            notifyObservers("No files to load");
        }
    }
    public void saveProperties() {
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream("properties.xml"));
            encoder.writeObject(p);
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void changeProperties(String name, String value) throws IOException {
        if(name.equals("ChangeGenerate")){
            if(value.equals("DFS") || value.equals("SimpleMaze")){
                p.setGenerateMazeAlgorithm(value);
            }else{
                throw new IOException("Invalid Algorithm Name");
            }

        }else if(name.equals("ChangeSolve")){
            if(value.equals("BFS") || value.equals("DFS")){
                p.setSolveMazeAlgorithm(value);
            }else{
                throw new IOException("Invalid Algorithm Name");
            }
        }else{
            throw new IOException("Invalid Parameter name");
        }

        setChanged();
        notifyObservers("change_properties_notify_command " + name);
    }
}
