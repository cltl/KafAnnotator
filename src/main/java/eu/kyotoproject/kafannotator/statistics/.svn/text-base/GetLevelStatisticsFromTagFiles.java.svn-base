package eu.kyotoproject.kafannotator.statistics;

import eu.kyotoproject.kafannotator.TagReader;
import eu.kyotoproject.kafannotator.objects.WordTag;
import eu.kyotoproject.kafannotator.util.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: 9/2/11
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetLevelStatisticsFromTagFiles {


    static  public String addToResultMap (HashMap<String, ArrayList<TagStatistics>> resultMap, HashMap<String, TagStatistics> fileresultMap, int nFiles, int file) {
        String totalString = "";
        int nTagIds = 0;
        int nTokens = 0;
        int nSentences = 0;
        int nConstituents = 0;
        int nTypes = 0;
        int nCoReferences = 0;

        Set keySet = fileresultMap.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            TagStatistics stats = fileresultMap.get(key);
            nTagIds += stats.getTagIds().size();
            nTokens += stats.getTokenIds().size();
            nSentences += stats.getSentenceIds().size();
            nTypes += stats.getTypeIds().size();
            nCoReferences += stats.getNCoReferences();
            nConstituents = stats.getConstituentIds().size();
            if (resultMap.containsKey(key)) {
                ArrayList<TagStatistics> statsArray = resultMap.get(key);
                statsArray.set(file, stats);
                resultMap.put(key, statsArray);
            }
            else {
                ArrayList<TagStatistics> statsArray = new ArrayList<TagStatistics>(nFiles);
                for (int j = 0; j < nFiles; j++) {
                    TagStatistics inistats = new TagStatistics();
                    inistats.setTag(key);
                    statsArray.add(inistats);
                }
                statsArray.set(file, stats);
                resultMap.put(key, statsArray);
            }
        }
        totalString = nTagIds+"\t"+nTokens+"\t"+nTypes+"\t"+nConstituents+"\t"+nSentences+"\t" +nCoReferences+"\t\t";
        return totalString;
    }

    static public void addToFileResultMap (HashMap<String, TagStatistics> resultMap, WordTag tag, int level, String fileName) {
        if (tag.getTagId(level)>0) {
/*
            if (tag.getTag(level).equalsIgnoreCase("positief") ||
                tag.getTag(level).equalsIgnoreCase("negatief") ||
                tag.getTag(level).equalsIgnoreCase("neutraal")
                    ) {
                if (level!=6) {
                    System.out.println("level = " + level);
                    System.out.println("\ttag.getTag(level) = " + tag.getTag(level));
                    System.out.println("\ttag.getTokenId() = " + tag.getTokenId());
                    System.out.println("\tfileName = " + fileName);
                }
            }
*/
            if (resultMap.containsKey(tag.getTag(level))) {
               TagStatistics stats = resultMap.get(tag.getTag(level));
               stats.addTagInfo(tag, level, fileName);
               resultMap.put(tag.getTag(level), stats);
            }
            else {
               TagStatistics stats = new TagStatistics();
               stats.addTagInfo(tag, level, fileName);
               stats.setNDocs(1);
               resultMap.put(tag.getTag(level), stats);
            }
        }
    }

    static void writeResultMapToFile (FileOutputStream fos, HashMap<String, ArrayList<TagStatistics>> resultMap, ArrayList<String> files, int tagLevel, String totalString) {
        try {
            String str = "Tag level"+tagLevel+"\t"+"Total"+"\t\t\t\t\t\t\t";
            for (int i = 0; i < files.size(); i++) {
                String fileName = new File(files.get(i)).getName();
                str += fileName+"\t\t\t\t\t\t\t";
            }
            str +="\n";
            str += TagStatistics.toHeaderString();
            for (int i = 0; i < files.size(); i++) {
                str += TagStatistics.toHeaderString();
            }
            str += "\n";
            fos.write(str.getBytes());

            TagStatistics totalTotal = new TagStatistics();
            Set keySet = resultMap.keySet();
            Iterator keys = keySet.iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                fos.write(key.getBytes());
                String substr = "";
                TagStatistics total = new TagStatistics();
                ArrayList<TagStatistics> stats = resultMap.get(key);
                for (int s = 0; s < stats.size(); s++) {
                    TagStatistics tagStatistics = stats.get(s);
                    substr += tagStatistics.toCsvCntString();
                    //System.out.println(key+" tagStatistics.toCsvCntString() for file " + s+":"+ tagStatistics.toCsvCntString());
                    total.addToTagStatistics(tagStatistics);
                    totalTotal.addToTagStatistics(tagStatistics);
                }
                str = total.toCsvCntString()+substr+"\n";
                fos.write(str.getBytes());
            }

            str = "TOTAL"+totalTotal.toCsvCntString()+"\t"+totalString+"\n\n";
            fos.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    static public void main (String[] args) {

        try {
            String tagFolder = args[0];
            FileOutputStream fos = new FileOutputStream(tagFolder+"/stats-levels.xls");
            HashMap<String, ArrayList<TagStatistics>> resultMap1 = new HashMap<String, ArrayList<TagStatistics>>();
            HashMap<String, ArrayList<TagStatistics>> resultMap2 = new HashMap<String, ArrayList<TagStatistics>>();
            HashMap<String, ArrayList<TagStatistics>> resultMap3 = new HashMap<String, ArrayList<TagStatistics>>();
            HashMap<String, ArrayList<TagStatistics>> resultMap4 = new HashMap<String, ArrayList<TagStatistics>>();
            HashMap<String, ArrayList<TagStatistics>> resultMap5 = new HashMap<String, ArrayList<TagStatistics>>();
            HashMap<String, ArrayList<TagStatistics>> resultMap6 = new HashMap<String, ArrayList<TagStatistics>>();
            HashMap<String, ArrayList<TagStatistics>> resultMap7 = new HashMap<String, ArrayList<TagStatistics>>();
            HashMap<String, ArrayList<TagStatistics>> resultMap8 = new HashMap<String, ArrayList<TagStatistics>>();
            String t1 = "";
            String t2 = "";
            String t3 = "";
            String t4 = "";
            String t5 = "";
            String t6 = "";
            String t7 = "";
            String t8 = "";
            ArrayList<String> files = Util.makeFlatFileList(tagFolder, "tag");
            for (int i = 0; i < files.size(); i++) {
                String filePath = files.get(i);
                System.out.println("filePath = " + filePath);
                String fileName = new File(filePath).getName();
                HashMap<String, TagStatistics> fileresultMap1 = new HashMap<String, TagStatistics>();
                HashMap<String, TagStatistics> fileresultMap2 = new HashMap<String, TagStatistics>();
                HashMap<String, TagStatistics> fileresultMap3 = new HashMap<String, TagStatistics>();
                HashMap<String, TagStatistics> fileresultMap4 = new HashMap<String, TagStatistics>();
                HashMap<String, TagStatistics> fileresultMap5 = new HashMap<String, TagStatistics>();
                HashMap<String, TagStatistics> fileresultMap6 = new HashMap<String, TagStatistics>();
                HashMap<String, TagStatistics> fileresultMap7 = new HashMap<String, TagStatistics>();
                HashMap<String, TagStatistics> fileresultMap8 = new HashMap<String, TagStatistics>();
                HashMap<String, WordTag> tagsMap = TagReader.readAnnotationFileToHashMap(filePath);
                Set keySet = tagsMap.keySet();
                Iterator keys = keySet.iterator();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    WordTag tag = tagsMap.get(key);
                    addToFileResultMap(fileresultMap1, tag, 1, fileName);
                    addToFileResultMap(fileresultMap2, tag, 2, fileName);
                    addToFileResultMap(fileresultMap3, tag, 3, fileName);
                    addToFileResultMap(fileresultMap4, tag, 4, fileName);
                    addToFileResultMap(fileresultMap5, tag, 5, fileName);
                    addToFileResultMap(fileresultMap6, tag, 6, fileName);
                    addToFileResultMap(fileresultMap7, tag, 7, fileName);
                    addToFileResultMap(fileresultMap8, tag, 8, fileName);
                }
                t1 += addToResultMap(resultMap1, fileresultMap1, files.size(), i);
                t2 += addToResultMap(resultMap2, fileresultMap2, files.size(), i);
                t3 += addToResultMap(resultMap3, fileresultMap3, files.size(), i);
                t4 += addToResultMap(resultMap4, fileresultMap4, files.size(), i);
                t5 += addToResultMap(resultMap5, fileresultMap5, files.size(), i);
                t6 += addToResultMap(resultMap6, fileresultMap6, files.size(), i);
                t7 += addToResultMap(resultMap7, fileresultMap7, files.size(), i);
                t8 += addToResultMap(resultMap8, fileresultMap8, files.size(), i);
            }
            writeResultMapToFile(fos, resultMap1, files, 1, t1);
            writeResultMapToFile(fos, resultMap2, files, 2, t2);
            writeResultMapToFile(fos, resultMap3, files, 3, t3);
            writeResultMapToFile(fos, resultMap4, files, 4, t4);
            writeResultMapToFile(fos, resultMap5, files, 5, t5);
            writeResultMapToFile(fos, resultMap6, files, 6, t6);
            writeResultMapToFile(fos, resultMap7, files, 7, t7);
            writeResultMapToFile(fos, resultMap8, files, 8, t8);
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
