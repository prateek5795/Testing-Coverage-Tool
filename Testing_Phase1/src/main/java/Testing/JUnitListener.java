package Testing;

import java.io.*;
import java.util.*;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/* JUnitListener captures the start and end events of each JUnit test method, overriding it to get the log as per given format */
public class JUnitListener extends RunListener {
	
	static int case_num = 0;
    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
        CollectionCoverage.map = new HashMap<Integer, String>();
        CollectionCoverage.suite = new TreeMap<Integer, HashMap<String, List<Integer>>>();
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        StringBuilder path = new StringBuilder();
        path.append("Output");
        try{
            File dir = new File(path.toString());
            if(!dir.exists()){
                dir.mkdir();
            }
        path.append(File.separator);
        path.append("output.txt");
            File file = new File(path.toString());
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuilder sb = new StringBuilder();
            for (int testNumber : CollectionCoverage.suite.keySet()) {
            	String testName = "Test: " + " " + CollectionCoverage.map.get(testNumber);
                sb.append(testName + "\n");
                HashMap<String, List<Integer>> cases = CollectionCoverage.suite.get(testNumber);

                for (String className : cases.keySet()) {
                    for(int i : cases.get(className)){
                        sb.append(className + " : " + i + "\n");
                    }
                }
            }
            bw.write(sb.toString());
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
        case_num += 1;
        String s = "" + description.getClassName() + ":" + description.getMethodName() + "";
        CollectionCoverage.map.put(case_num, s);
        CollectionCoverage.cases = new HashMap<String, List<Integer>>();
    }

    @Override
    public void testFinished(Description description) throws Exception {
        CollectionCoverage.suite.put(case_num,CollectionCoverage.cases);
        super.testFinished(description);

    }

}
