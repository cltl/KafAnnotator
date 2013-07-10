package eu.kyotoproject.kafannotator.interannotator;

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
 * Date: 3/21/11
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergeAnnotations {

     static public TreeSet sortOnConfidence (ArrayList<String> list) {
        TreeSet sorter = new TreeSet(
                new Comparator() {
                    public int compare(Object a, Object b) {
                        String itemA = (String) a;
                        String itemB = (String) b;
                        Long nA = new File(itemA).lastModified();
                        Long nB = new File(itemB).lastModified();
                        if (nB.equals(nA)) // We force equal frequencies to be inserted
                        {
                            return -1;
                        } else {
                            return (nA.compareTo(nB));
                        }
                    }
                }

        );
        for (int i = 0; i < list.size(); i++) {
            String filePath = list.get(i);
            sorter.add(filePath);
        }
        return sorter;
    }



    static public TreeSet sortWordTokenId (HashMap<String, WordTag> tagsMap) {
        TreeSet sorter = new TreeSet(
                new Comparator() {
                    public int compare(Object a, Object b) {
                        WordTag itemA = (WordTag) a;
                        WordTag itemB = (WordTag) b;
                        Integer nA = itemA.getOrder();
                        Integer nB = itemB.getOrder();
                        if (nB.equals(nA)) // We force equal frequencies to be inserted
                        {
                            return -1;
                        } else {
                            return (nA.compareTo(nB));
                        }
                    }
                }

        );
        Set keySet = tagsMap.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            WordTag tag = tagsMap.get(key);
            sorter.add(tag);
        }
        return sorter;
    }

    static public void main(String [] args) {
        try {
            String tagFolder = args[0];
            String mergedTagFile = tagFolder+"/"+"merged.tag";
            String conflictTagFile = tagFolder+"/"+"conflicts.tag";
            FileOutputStream fos = new FileOutputStream(mergedTagFile);
            FileOutputStream fosConflict = new FileOutputStream(conflictTagFile);
            ArrayList<String> files = Util.makeFlatFileList(tagFolder, "tag");
            HashMap<String, WordTag> mergedTagMap = new HashMap<String, WordTag>();
            HashMap<String, WordTag> conflictTagMap = new HashMap<String, WordTag>();
            TreeSet filesPerDate = sortOnConfidence(files);
            Iterator nextFile = filesPerDate.iterator();
            while (nextFile.hasNext()) {
                String filePath = (String) nextFile.next();
                System.out.println("filePath = " + filePath);
                HashMap<String, WordTag> newTagsMap = TagReader.readAnnotationFileToHashMap(filePath);
                System.out.println("mergedTagMap = " + mergedTagMap.size());
                System.out.println("newTagsMap.size() = " + newTagsMap.size());
                Set keySet = newTagsMap.keySet();
                Iterator keys = keySet.iterator();
                while (keys.hasNext()) {
                    String tokenId = (String) keys.next();
                    WordTag newTag = newTagsMap.get(tokenId);
                    if (mergedTagMap.containsKey(tokenId)) {
                        /// we already have this tag in the mergedTagFile
                        WordTag mergedTag = mergedTagMap.get(tokenId);
                        if (mergedTag.areIdentical(newTag)) {
                            ///// we do nothing and a re happy
                        }
                        else {
                            if (mergedTag.hasTagConflicts(newTag)) {
                                /// we have a problem
                                System.out.println("we have a problem newTag = " + newTag);
                                System.out.println("mergedTag = " + mergedTag);
                                System.out.println("############");
                                String code = new File(filePath).getName()+"#"+tokenId;
                                conflictTagMap.put(code, newTag);
                            }
                            else {
                                System.out.println("We are merging two tags:");
                                System.out.println("\tmergedTag = " + mergedTag);
                                System.out.println("\tnewTag = " + newTag);
                                mergedTag.importTags(newTag);
                                System.out.println("\t new mergedTag = " + mergedTag);
                                System.out.println("############");
                                mergedTagMap.put(tokenId, mergedTag);
                            }
                        }
                    }
                    else {
                       //// this token was not tagged before so we simply add it
                       //System.out.println("this token was not tagged before newTag = " + newTag);
                       // System.out.println("############");
                       mergedTagMap.put(tokenId, newTag);
                    }
                }

            }
            System.out.println("mergedTagMap = " + mergedTagMap.size());
            TreeSet mergedSet =  sortWordTokenId(mergedTagMap);
            Iterator keys = mergedSet.iterator();
            while (keys.hasNext()) {
                WordTag tag = (WordTag) keys.next();
                String str = tag.toString();
                fos.write(str.getBytes());
            }
/*
            Set keySet = mergedTagMap.keySet();
            Iterator keys = keySet.iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                WordTag tag = mergedTagMap.get(key);
                String str = tag.toString();
                fos.write(str.getBytes());
            }
*/
            fos.close();
            System.out.println("conflictTagMap = " + conflictTagMap.size());
            Set keySet = conflictTagMap.keySet();
            keys = keySet.iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                WordTag tag = conflictTagMap.get(key);
                String str = key+"\t"+tag.toString();
                fosConflict.write(str.getBytes());
            }
            fosConflict.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
