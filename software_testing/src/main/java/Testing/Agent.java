package Testing;

import java.io.*;
import java.lang.instrument.Instrumentation;
import java.util.*;


public class Agent {
    public static void premain(String agentArgs, Instrumentation i) {
        String path = getPath() + File.separator;
        i.addTransformer(new Transformer(path));
    }

    private static String getPath() {
        int ans = 0;
        String outputPath = null;
        String rootPath = "src" + File.separator + "main" + File.separator + "java";
        File root = new File(rootPath);
        File[] list = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.equals(".DS_Store");
            }
        });
        Set<String> dirs = new HashSet<String>();
        assert list != null;
        getEveryPath(list, dirs);
        for (String path : dirs) {

            int count = countMatches(path);
            if (ans == 0 || count < ans){
                ans = count;
                outputPath = path;
            }
        }
        return outputPath;
    }

    private static void getEveryPath(File[] files, Set<String> paths) {
        for (File file : files) {
            if (file.isDirectory()) {
                getEveryPath(file.listFiles(), paths);
            } else {
                if(file.getName().endsWith(".java")){
                    String path = file.getParent();
                    path = path.substring(14);
                    paths.add(path);
                }
            }
        }
    }

    private static boolean isEmpty(String s) {
        if(s==null)
            return true;
        else if(s.length()==0)
            return true;

        return false;
    }

    private static int countMatches(String text) {
        if (isEmpty(text) || isEmpty(File.separator)) {
            return 0;
        }
        int index = 0, count = 0;
        while (true) {
            index = text.indexOf(File.separator, index);
            if (index != -1) {
                count++;
                index += File.separator.length();
            } 
            else if(index == -1) {
                break;
            }
        }
        return count;
    }

}


