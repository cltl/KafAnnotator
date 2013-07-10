package eu.kyotoproject.kafannotator.statistics;

import eu.kyotoproject.kafannotator.TagReader;
import eu.kyotoproject.kafannotator.objects.WordTag;
import eu.kyotoproject.kafannotator.tableware.AnnotationTableModel;
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
public class GetGlobalStatisticsFromTagFiles {


    static public void main (String[] args) {

        try {
            String str = "";
            String tagFolder = args[0];
            FileOutputStream fos = new FileOutputStream(tagFolder+"/stats.xls");
            HashMap<String, ArrayList<TagStatistics>> resultMap = new HashMap<String, ArrayList<TagStatistics>>();
            ArrayList<String> files = Util.makeFlatFileList(tagFolder, "tag");
            for (int i = 0; i < files.size(); i++) {
                String filePath = files.get(i);
                System.out.println("filePath = " + filePath);
                String fileName = new File(filePath).getName();
                HashMap<String, WordTag> tagsMap = TagReader.readAnnotationFileToHashMap(filePath);
                HashMap<String, TagStatistics> fileresultMap = new HashMap<String, TagStatistics>();
                Set keySet = tagsMap.keySet();
                Iterator keys = keySet.iterator();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    WordTag tag = tagsMap.get(key);
                    for (int j = 0; j < AnnotationTableModel.TAGLEVELS; j++) {
                        if (fileresultMap.containsKey(tag.getTag(j))) {
                           TagStatistics stats = fileresultMap.get(tag.getTag(j));
                           stats.addTagInfo(tag, j, fileName);
                           fileresultMap.put(tag.getTag(j), stats);
                        }
                        else {
                           TagStatistics stats = new TagStatistics();
                           stats.addTagInfo(tag, j, fileName);
                           fileresultMap.put(tag.getTag(j), stats);
                        }

                    }
                }
                keySet = fileresultMap.keySet();
                keys = keySet.iterator();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    TagStatistics stats = fileresultMap.get(key);
                    if (resultMap.containsKey(key)) {
                        ArrayList<TagStatistics> statsArray = resultMap.get(key);
                        statsArray.set(i, stats);
                        resultMap.put(key, statsArray);
                    }
                    else {
                        ArrayList<TagStatistics> statsArray = new ArrayList<TagStatistics>(files.size());
                        for (int j = 0; j < files.size(); j++) {
                            TagStatistics inistats = new TagStatistics();
                            inistats.setTag(key);
                            statsArray.add(inistats);
                        }
                        statsArray.set(i, stats);
                        resultMap.put(key, statsArray);
                    }
                }
            }
            str = "Tag\t";
            for (int i = 0; i < files.size(); i++) {
                String fileName = new File(files.get(i)).getName();
                str += fileName+"\t\t\t\t\t\t\t";
            }
            str +="\n";
            for (int i = 0; i < files.size(); i++) {
                str += TagStatistics.toHeaderString();
            }
            str += "\n";
            fos.write(str.getBytes());
            Set keySet = resultMap.keySet();
            Iterator keys = keySet.iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                fos.write(key.getBytes());
                ArrayList<TagStatistics> stats = resultMap.get(key);
                for (int i = 0; i < stats.size(); i++) {
                    TagStatistics tagStatistics = stats.get(i);
                    fos.write(tagStatistics.toCsvCntString().getBytes());
                }
                str = "\n";
                fos.write(str.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
