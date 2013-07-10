package eu.kyotoproject.kafannotator.statistics;

import eu.kyotoproject.kafannotator.TagReader;
import eu.kyotoproject.kafannotator.objects.WordTag;
import eu.kyotoproject.kafannotator.util.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: 9/7/11
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetSentenceCorrelations {
    static final String NONE = "__NOVALUE";
    static boolean DEBUG = false;
    public static int[][] buildMatrix (HashMap<Integer, ArrayList<String>> sentenceTagMap, ArrayList<String> tag1ArrayList, ArrayList<String> tag2ArrayList) {

        int [][] matrix = new int[tag1ArrayList.size()][tag2ArrayList.size()];
        /// init matrix
        for (int j = 0; j < matrix.length; j++) {
            int[] ints = matrix[j];
            for (int k = 0; k < ints.length; k++) {
                matrix[j][k] = 0;
            }
        }

        Set keySet = sentenceTagMap.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            Integer key = (Integer) keys.next();
            ArrayList<String> tags = sentenceTagMap.get(key);
         //   System.out.println(key+" has tags = " + tags);
            boolean tag1Match = false;
            boolean tag2Match = false;
            System.out.println(key+" tags = " + tags.toString());
            for (int j = 0; j < tags.size(); j++) {
                String s1 = tags.get(j);
                if (s1.equalsIgnoreCase("ID")) {
                    DEBUG = true;
                    System.out.println("s1 = " + s1);
                }
                else {
                    DEBUG = false;
                }
                int idx1 = tag1ArrayList.indexOf(s1);
                if (idx1>=0) {
                    tag1Match = true;
                    for (int k = 0; k < tags.size(); k++) {
                        if (k!=j) {
                            String s2 = tags.get(k);
                            int idx2 = tag2ArrayList.indexOf(s2);
                            if (idx2>=0) {
                                tag2Match = true;
                                matrix[idx1][idx2]++;
                                if (DEBUG) {
                                    System.out.println("idx1 ="+idx1+" and idx2 = " + idx2);
                                    System.out.println("tag1ArrayList idx1 = " + tag1ArrayList.get(idx1));
                                    System.out.println("tag2ArrayList idx2 = " + tag2ArrayList.get(idx2));
                                    System.out.println("matrix[idx1][idx2] = " + matrix[idx1][idx2]);
                                }
                            }
                            else {
                                ////belongs to tag1 list
                            }
                        }
                    }
                    if (!tag2Match) {
                        //// None of the tags belongs to tag level 2
                        int idx2 = tag2ArrayList.indexOf(NONE);
                        if (idx2>=0) {
                            matrix[idx1][idx2]++;
                            if (DEBUG) {
                                System.out.println("idx1 ="+idx1+" and idx2 = " + idx2);
                                System.out.println("tag1ArrayList idx1 = " + tag1ArrayList.get(idx1));
                                System.out.println("tag2ArrayList idx2 = " + tag2ArrayList.get(idx2));
                                System.out.println("matrix[idx1][idx2] = " + matrix[idx1][idx2]);
                            }
                        }
                        else {
                            ////WRONG INIT VALUE NONE!!!!
                        }
                    }
                }
                else {
                    ///// belongs to tag2ArrayList
                }
                //// In case none of the tags matched tag1 values
                if (!tag1Match) {
                    for (int k = 0; k < tags.size(); k++) {
                        String s2 = tags.get(k);
                        int idx2 = tag2ArrayList.indexOf(s2);
                        if (idx2>-1) {
                            idx1 = tag1ArrayList.indexOf(NONE);
                            if (idx1>=0) {
                                matrix[idx1][idx2]++;
                                if (DEBUG) {
                                    System.out.println("idx1 ="+idx1+" and idx2 = " + idx2);
                                    System.out.println("tag1ArrayList idx1 = " + tag1ArrayList.get(idx1));
                                    System.out.println("tag2ArrayList idx2 = " + tag2ArrayList.get(idx2));
                                    System.out.println("matrix[idx1][idx2] = " + matrix[idx1][idx2]);
                                }
                            }
                            else {
                                ////WRONG INIT VALUE NONE!!!!
                            }
                        }
                        else {
                            //// would be strange!!!!!!
                        }
                    }
                }
            }
        }
        return matrix;
    }

    public static void writeMatrix (FileOutputStream fosFile, int[][] matrix, ArrayList<String> tag1ArrayList, ArrayList<String> tag2ArrayList) throws IOException {
                String str = "";
                // // we write the matrix
                //// write the column names
                for (int j = 0; j < tag2ArrayList.size(); j++) {
                    String s = tag2ArrayList.get(j);
                    str += "\t"+s;
                }
                str += "\tTOTAL\n";
                fosFile.write(str.getBytes()); str = "";
                int [] columnTotals = new int[tag2ArrayList.size()];
                for (int j = 0; j < tag1ArrayList.size(); j++) {
                    String s = tag1ArrayList.get(j);
                    int rowTotal = 0;
                    str = s;
                    int[] ints = matrix[j];
                    for (int l = 0; l < ints.length; l++) {
                        int anInt = ints[l];
                        rowTotal+=anInt;
                        columnTotals[l]+=anInt;
                        str+= "\t"+anInt;
                    }
                    str +="\t"+rowTotal+"\n";
                    fosFile.write(str.getBytes()); str = "";
                }
                str = "TOTAL";
                for (int j = 0; j < columnTotals.length; j++) {
                    int columnTotal = columnTotals[j];
                    str+= "\t"+columnTotal;
                }
                str += "\n";
                fosFile.write(str.getBytes()); str = "";
    }


    public static void correlateFile (FileOutputStream fosFile, String filePath, int tag1, int tag2) throws IOException {
            String str ="\nCorrelation matrix tag level "+tag1+" and level "+tag2+"\n";
            fosFile.write(str.getBytes()); str = "";
            HashMap<Integer, ArrayList<String>> sentenceTagMap = new HashMap<Integer, ArrayList<String>>();
            ArrayList<String> tag1ArrayList = new ArrayList<String>();
            ArrayList<String> tag2ArrayList = new ArrayList<String>();
            HashMap<String, WordTag> tagsMap = TagReader.readAnnotationFileToHashMap(filePath);
            Set keySet = tagsMap.keySet();
            Iterator keys = keySet.iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                WordTag tag = tagsMap.get(key);
                int sentenceId = tag.getSentenceId();
                String tag1Value = tag.getTag(tag1).trim();
                String tag2Value = tag.getTag(tag2).trim();
                if (sentenceTagMap.containsKey(sentenceId)) {
                    ArrayList<String> tags = sentenceTagMap.get(sentenceId);
                    //// only unique and non-empty values are kept.
                    if ((tag1Value.length()>0) && (!tags.contains(tag1Value))) tags.add(tag1Value);
                    if ((tag2Value.length()>0) && (!tags.contains(tag2Value))) tags.add(tag2Value);
                    sentenceTagMap.put(sentenceId, tags);
                }
                else {
                    ArrayList<String> tags = new ArrayList<String>();
                    if (tag1Value.length()>0) tags.add(tag1Value);
                    if (tag2Value.length()>0) tags.add(tag2Value);
                    if (tags.size()>0) sentenceTagMap.put(sentenceId, tags);
                }
                if (tag1Value.length()>0) {
                    if (!tag1ArrayList.contains(tag1Value)) {
                        tag1ArrayList.add(tag1Value);
                    }
                }
                if (tag2Value.length()>0) {
                    if (!tag2ArrayList.contains(tag2Value)) {
                        tag2ArrayList.add(tag2Value);
                    }
                }
            }
            tag1ArrayList.add(NONE);
            tag2ArrayList.add(NONE);
            Collections.sort(tag1ArrayList);
            Collections.sort(tag2ArrayList);
            System.out.println("sentenceTagMap = " + sentenceTagMap.size());
            //// We fill the matrix
            int [][] matrix = buildMatrix(sentenceTagMap, tag1ArrayList, tag2ArrayList);
            writeMatrix(fosFile, matrix, tag1ArrayList, tag2ArrayList);
    }

    public static void main (String [] args) {
            String str = "";
            File tagFolder = null;
            int tag1 = 0;
            int tag2 = 0;
            String[] tagList = null;
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.equalsIgnoreCase("--folder")) {
                    if (args.length>(i+1)) {
                       tagFolder =  new File(args[i+1]);
                       if (!tagFolder.exists()) {
                           System.out.println("tagFolder does not exist = " + tagFolder.getAbsolutePath());
                           return;
                       }
                       if (!tagFolder.isDirectory()) {
                           System.out.println("tagFolder is not a directory = " + tagFolder.getAbsolutePath());
                           return;
                       }
                        System.out.println("tagFolder = " + tagFolder.getName());
                    }
                    else {
                        System.out.println("too few arguments = " + args.length);
                        System.out.println("i = " + i);
                        return;
                    }
                }
                if (arg.equalsIgnoreCase("--tag1")) {
                    if (args.length>(i+1)) {
                        try {
                            tag1 = Integer.parseInt(args[i+1]);
                            if (tag1<1 || tag1>8) {
                                System.out.println("Tag level should be an integer from 1 to 8 = " + args[i+1]);
                                return;
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            System.out.println("Tag level should be an integer from 1 to 8 = " + args[i+1]);
                            System.out.println("i = " + i);
                            return;
                        }
                    }
                    else {
                        System.out.println("too few arguments = " + args.length);
                        return;
                    }
                }
                if (arg.equalsIgnoreCase("--tag2")) {
                    if (args.length>(i+1)) {
                        try {
                            tag2 = Integer.parseInt(args[i+1]);
                            if (tag2<1 || tag2>8) {
                                System.out.println("Tag level should be an integer from 1 to 8 = " + args[i+1]);
                                return;
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            System.out.println("Tag level should be an integer from 1 to 8 = " + args[i+1]);
                            return;
                        }
                    }
                    else {
                        System.out.println("too few arguments = " + args.length);
                        System.out.println("i = " + i);
                        return;
                    }
                }
                if (arg.equalsIgnoreCase("--taglist")) {
                    if (args.length>(i+1)) {
                        tagList = args[i+1].split("-");
                    }
                    else {
                        System.out.println("too few arguments = " + args.length);
                        System.out.println("i = " + i);
                        return;
                    }
                }
            }
            try {
                System.out.println("tagFolder = " + tagFolder.getAbsolutePath());
                ArrayList<String> files = Util.makeFlatFileList(tagFolder.getAbsolutePath(), "tag");
                for (int i = 0; i < files.size(); i++) {
                    String filePath = files.get(i);
                    if (filePath.indexOf("pvda2010-zorg_R.tag")==-1) {
                        continue;
                    }
                    if (tagList==null) {
                            FileOutputStream fosFile = new FileOutputStream(filePath+".cor-"+tag1+"-"+tag2+".xls");
                            System.out.println("filePath = " + filePath);
                            str = filePath+"\n";
                            fosFile.write(str.getBytes());
                            correlateFile(fosFile,  filePath, tag1, tag2);
                            fosFile.close();
                    }
                    else {
                        FileOutputStream fosFile = new FileOutputStream(filePath+".cor.xls");
                        System.out.println("filePath = " + filePath);
                        str = filePath+"\n";
                        fosFile.write(str.getBytes());
                        for (int j = 0; j < tagList.length; j++) {
                            String s = tagList[j];
                            String [] tags = s.split(";");
                            if (tags.length==2) {
                                try {
                                    tag1 = Integer.parseInt(tags[0]);
                                    tag2 = Integer.parseInt(tags[1]);
                                    correlateFile(fosFile,  filePath, tag1, tag2);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                }
                            }
                        }
                        fosFile.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
    }
}
