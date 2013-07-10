package eu.kyotoproject.kafannotator;

import eu.kyotoproject.kafannotator.objects.WordTag;
import eu.kyotoproject.kafannotator.tableware.AnnotationTableModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: 3/21/11
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class TagReader {
    static final String TAGSEPARATOR="\t";



    /// version that uses a config file to know which column contain tags and what is the EVENT tag and what is the SCOPE tag
    static public ArrayList<WordTag> readAnnotationFileToArrayList (String inputFile) {
        AnnotationTableModel tModel = new AnnotationTableModel ();
        ArrayList<WordTag> tagList = new ArrayList<WordTag>();
        String error = "";
        String inputLine = "";
        int nList = 0;
        try {
          FileInputStream fis = new FileInputStream(inputFile);
          InputStreamReader isr = new InputStreamReader(fis);
          BufferedReader in = new BufferedReader(isr);
          while ((inputLine = in.readLine()) != null) {
              //System.out.println("inputLine = " + inputLine);
              nList++;
               String [] fields = inputLine.split(TAGSEPARATOR);
               if (fields.length==tModel.MAXTABLECOLUMNS) {
                      boolean done = true;
                      String id   = fields[tModel.ROWID];
                      String token = fields[tModel.ROWWORDTOKEN];
                      String tag1  = fields[tModel.ROWTAG1];
                      Integer tagId1  = Integer.parseInt(fields[tModel.ROWTAGID1]);
                      String tag2  = fields[tModel.ROWTAG2];
                      Integer tagId2  = Integer.parseInt(fields[tModel.ROWTAGID2]);
                      String tag3  = fields[tModel.ROWTAG3];
                      Integer tagId3  = Integer.parseInt(fields[tModel.ROWTAGID3]);
                      String tag4  = fields[tModel.ROWTAG4];
                      Integer tagId4  = Integer.parseInt(fields[tModel.ROWTAGID4]);
                      String tag5  = fields[tModel.ROWTAG5];
                      Integer tagId5  = Integer.parseInt(fields[tModel.ROWTAGID5]);
                      String tag6  = fields[tModel.ROWTAG6];
                      Integer tagId6  = Integer.parseInt(fields[tModel.ROWTAGID6]);
                      String tag7  = fields[tModel.ROWTAG7];
                      Integer tagId7  = Integer.parseInt(fields[tModel.ROWTAGID7]);
                      String tag8  = fields[tModel.ROWTAG8];
                      Integer tagId8  = Integer.parseInt(fields[tModel.ROWTAGID8]);
                      if (fields[tModel.ROWSTATUS].equals("true")) {
                          done = true;
                      }
                      else {
                          done = false;
                      }
                      WordTag tag = new WordTag();
                      tag.setWordToken(token);
                      tag.setTokenId(id);
                      tag.setTag1(tag1);
                      tag.setTagId1(tagId1);
                      tag.setTag2(tag2);
                      tag.setTagId2(tagId2);
                      tag.setTag3(tag3);
                      tag.setTagId3(tagId3);
                      tag.setTag4(tag4);
                      tag.setTagId4(tagId4);
                      tag.setTag5(tag5);
                      tag.setTagId5(tagId5);
                      tag.setTag6(tag6);
                      tag.setTagId6(tagId6);
                      tag.setTag7(tag7);
                      tag.setTagId7(tagId7);
                      tag.setTag8(tag8);
                      tag.setTagId8(tagId8);
                      tagList.add(tag);
               }
         }
         in.close();
        }
        catch (Exception eee) {
              error += "\nException --"+eee.getMessage();
              System.out.println(error);
              System.out.println("Line:"+nList);
              System.out.println(inputLine);
        }
        return tagList;
    }
    static public HashMap<String, WordTag> readAnnotationFileToHashMap (String inputFile) {
        AnnotationTableModel tModel = new AnnotationTableModel ();
        HashMap<String, WordTag> tagMap = new  HashMap<String, WordTag>();
        String error = "";
        String inputLine = "";
        int nList = 0;
        try {
          FileInputStream fis = new FileInputStream(inputFile);
          InputStreamReader isr = new InputStreamReader(fis);
          BufferedReader in = new BufferedReader(isr);
          while ((inputLine = in.readLine()) != null) {
              //System.out.println("inputLine = " + inputLine);
              nList++;
               String [] fields = inputLine.split(TAGSEPARATOR);
               if (fields.length==tModel.MAXTABLECOLUMNS) {
                      boolean done = true;
                      String id   = fields[tModel.ROWID];
                      String token = fields[tModel.ROWWORDTOKEN];
                      String type = fields[tModel.ROWWORDTYPE];
                      String pos = fields[tModel.ROWPOS];
                      String synset = fields[tModel.ROWSYNSET];
                      String tag1  = fields[tModel.ROWTAG1];
                      Integer tagId1  = Integer.parseInt(fields[tModel.ROWTAGID1]);
                      String tag2  = fields[tModel.ROWTAG2];
                      Integer tagId2  = Integer.parseInt(fields[tModel.ROWTAGID2]);
                      String tag3  = fields[tModel.ROWTAG3];
                      Integer tagId3  = Integer.parseInt(fields[tModel.ROWTAGID3]);
                      String tag4  = fields[tModel.ROWTAG4];
                      Integer tagId4  = Integer.parseInt(fields[tModel.ROWTAGID4]);
                      String tag5  = fields[tModel.ROWTAG5];
                      Integer tagId5  = Integer.parseInt(fields[tModel.ROWTAGID5]);
                      String tag6  = fields[tModel.ROWTAG6];
                      Integer tagId6  = Integer.parseInt(fields[tModel.ROWTAGID6]);
                      String tag7  = fields[tModel.ROWTAG7];
                      Integer tagId7  = Integer.parseInt(fields[tModel.ROWTAGID7]);
                      String tag8  = fields[tModel.ROWTAG8];
                      Integer tagId8  = Integer.parseInt(fields[tModel.ROWTAGID8]);
                      Integer order = Integer.parseInt(fields[tModel.ROWORDER]);
                      Integer sentenceId = Integer.parseInt(fields[tModel.ROWSENTENCE]);
                      if (fields[tModel.ROWSTATUS].equals("true")) {
                          done = true;
                      }
                      else {
                          done = false;
                      }
                      WordTag tag = new WordTag();
                      tag.setWordToken(token);
                      tag.setTokenId(id);
                      tag.setWordType(type);
                      tag.setPos(pos);
                      tag.setWordSynset(synset);
                      tag.setOrder(order);
                      tag.setSentenceId(sentenceId);
                      tag.setTag1(tag1);
                      tag.setTagId1(tagId1);
                      tag.setTag2(tag2);
                      tag.setTagId2(tagId2);
                      tag.setTag3(tag3);
                      tag.setTagId3(tagId3);
                      tag.setTag4(tag4);
                      tag.setTagId4(tagId4);
                      tag.setTag5(tag5);
                      tag.setTagId5(tagId5);
                      tag.setTag6(tag6);
                      tag.setTagId6(tagId6);
                      tag.setTag7(tag7);
                      tag.setTagId7(tagId7);
                      tag.setTag8(tag8);
                      tag.setTagId8(tagId8);
                      tagMap.put(tag.getTokenId(), tag);
               }
         }
         in.close();
        }
        catch (Exception eee) {
              error += "\nException --"+eee.getMessage();
              System.out.println(error);
              System.out.println("Line:"+nList);
              System.out.println(inputLine);
        }
        return tagMap;
    }

}
