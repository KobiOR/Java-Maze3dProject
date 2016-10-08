package Presenters;

import java.io.Serializable;
public class Properties implements Serializable {

    private static final long serialVersionUID = 1L;
    private int numOfThreads;
    private String[] generateMazeAlgorithm;
    private String[] solveMazeAlgorithm;
    private String view;
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }
    public int getNumOfThreads() {
        return numOfThreads;
    }
    public void setNumOfThreads(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }
    public String[] getGenerateMazeAlgorithm() {
        return generateMazeAlgorithm;
    }
    public void setGenerateMazeAlgorithm(String[] generateMazeAlgorithm) {
        this.generateMazeAlgorithm = generateMazeAlgorithm;
    }
    public String[] getSolveMazeAlgorithm() {
        return solveMazeAlgorithm;
    }
    public void setSolveMazeAlgorithm(String[] solveMazeAlgorithm) {
        this.solveMazeAlgorithm = solveMazeAlgorithm;
    }

}