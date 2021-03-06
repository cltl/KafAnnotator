package eu.kyotoproject.kafannotator.triple;

import eu.kyotoproject.kafannotator.TagReader;
import eu.kyotoproject.kafannotator.objects.TagSet;
import eu.kyotoproject.kafannotator.objects.WordTag;
import eu.kyotoproject.kafannotator.tableware.AnnotationTableModel;
import vu.tripleevaluation.objects.Triple;

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
 * Date: Aug 26, 2010
 * Time: 9:51:14 AM
 * To change this template use File | Settings | File Templates.
 * This file is part of KafAnnotator.

    KafAnnotator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    KafAnnotator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with KafAnnotator.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * TODO
 * - use hierarchical tags in the Triple cfg
 * - use empty main in Triple cfg
 */

public class TagToTriples {

    static final String TAGSEPARATOR="\t";



    /// version that uses a config file to know which column contain tags and what is the EVENT tag and what is the SCOPE tag

    /**
     * Takes a config file stored
     * @param config
     * @param inputFile
     * @return
     */
    static public ArrayList<Triple> loadAnnotationFile (TripleConfig config, String inputFile) {
        AnnotationTableModel tModel = new AnnotationTableModel ();
        ArrayList<Triple> TripleArray = new ArrayList<Triple>();
        ArrayList<WordTag> tagList = TagReader.readAnnotationFileToArrayList(inputFile);
      //  System.out.println("tagList.size() = " + tagList.size());
        ArrayList<String> eventLabels = config.getEventLabels();
        for (int i = 0; i < config.pairs.size(); i++) {
            TripleConfig.LevelPair levelPair = config.pairs.get(i);
          //  System.out.println("levelPair.toString() = " + levelPair.toString());
            HashMap<Integer,HashMap<Integer, ArrayList<WordTag>>> tagMap = new HashMap<Integer,HashMap<Integer, ArrayList<WordTag>>>();
            for (int t = 0; t < tagList.size(); t++) {
                WordTag tag = tagList.get(t);
                if ((tag.getTagId(levelPair.getScopeLevel())>0) &&
                        (tag.getTagId(levelPair.getTagLevel())>0) &&
                        (tag.getTag(levelPair.getTagLevel()).length()>0) &&
                        (tag.getTag(levelPair.getScopeLevel()).length()>0)) {
/*
                     System.out.println("levelPair.getScopeLevel() = " + levelPair.getScopeLevel());
                     System.out.println("levelPair.getTagLevel() = " + levelPair.getTagLevel());
*/
                    if ((tag.getTag(levelPair.getScopeLevel()).equals(levelPair.getScopeLabel()) &&
                        (!tag.getTag(levelPair.getTagLevel()).isEmpty()))) {

                        addTagsToTagMap(tagMap, tag, tag.getTagId(levelPair.getScopeLevel()), tag.getTagId(levelPair.getTagLevel()));
                    }
                }
            }
        //    System.out.println("tagMap.size() = " + tagMap.size());
            addTagsToTripleArray(TripleArray, tagMap, levelPair, eventLabels);
        //    System.out.println("TripleArray = " + TripleArray.size());
        }
        for (int i = 0; i < config.flats.size(); i++) {
            Integer level = config.flats.get(i);
            HashMap<Integer, ArrayList<WordTag>> tagMap = new HashMap<Integer, ArrayList<WordTag>>();
            for (int t = 0; t < tagList.size(); t++) {
                WordTag tag = tagList.get(t);
                if ((tag.getTagId(level)>0)) {
                    addTagsToFlatTagMap(tagMap, tag, tag.getTagId(level));
                }
            }
            addFlatTagsToTripleArray(TripleArray, tagMap, level, eventLabels);
        }
        return TripleArray;
    }

    static boolean hasTag (ArrayList<WordTag> tags, WordTag tag) {
        for (int i = 0; i < tags.size(); i++) {
            WordTag wordTag = tags.get(i);
            if (wordTag.getTokenId().endsWith(tag.getTokenId())) {
                return true;
            }
        }
        return false;
    }

    static void addTagsToTagMap (HashMap<Integer,HashMap<Integer, ArrayList<WordTag>>> tagMap, WordTag tag, Integer tagScopeId, Integer tagId) {
       // System.out.println("tagScopeId = " + tagScopeId);
        
        if (tagMap.containsKey(tagScopeId)) {
            HashMap<Integer, ArrayList<WordTag>> roleMap = tagMap.get(tagScopeId);
            if (roleMap.containsKey(tagId)) {
                ArrayList<WordTag> tags = roleMap.get(tagId);
                if (!hasTag(tags, tag)) {
                    tags.add(tag);
                    roleMap.put(tagId, tags);
                }
            }
            else {
                ArrayList<WordTag> tags = new ArrayList<WordTag>();
                tags.add(tag);
                roleMap.put(tagId, tags);
            }
            tagMap.put(tagScopeId, roleMap);
        }
        else {
            HashMap<Integer, ArrayList<WordTag>> roleMap = new HashMap<Integer, ArrayList<WordTag>>();
            ArrayList<WordTag> tags = new ArrayList<WordTag>();
            tags.add(tag);
            roleMap.put(tagId, tags);
            tagMap.put(tagScopeId, roleMap);
        }

    }

    //// Version that uses a config to define which tag levels belong to events and Triples and which indicate scope

    /**
     *
     * @param TripleArray
     * @param tagMap    HashMap with the SCOPE annotation ID as the key and a HashMap as a the object
     *                  The Object HashMap has the tagId of the annotation as the key and an array of tags
     *                  that all have that same tag Id
     * @param pair
     */
    static void addTagsToTripleArray (ArrayList<Triple> TripleArray,
                                       HashMap<Integer,HashMap<Integer, ArrayList<WordTag>>> tagMap,
                                       TripleConfig.LevelPair pair,
                                       ArrayList<String> eventLabels) {
        //System.out.println("pair.toString() = " + pair.toString());
        Set keySet = tagMap.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            Integer key = (Integer) keys.next();
         //   System.out.println("key = " + key);
            /// a key represents the ID of a SCOPE TAG
            HashMap<Integer, ArrayList<WordTag>> roleMap = tagMap.get(key);
           // System.out.println("all annotations within the scope, grouped by TagId roleMap.size() = " + roleMap.size());
            //// Some of the annotations within the scope have event status, all the others will be participants
            //// In case none has event status, we will create a dummy event

            /// Build up the eventIds connected to the SCOPE iterating over all annotations within the scope
            ArrayList<TagSet> eventIds = new ArrayList<TagSet>();
            ArrayList<TagSet> participantIds = new ArrayList<TagSet>();
            Set keySubSet = roleMap.keySet();
            Iterator subKeys = keySubSet.iterator();
            boolean HASEVENTS = false;
            while (subKeys.hasNext()) {
                Integer subKey = (Integer) subKeys.next();
                TagSet tagSet = new TagSet();
                //// The subKey represents a tagId within the scope
                ArrayList<WordTag> tags = roleMap.get(subKey);
                //// Tags is a set of annotations (Tags) with the same tagId
                for (int i = 0; i < tags.size(); i++) {
                    WordTag tag =  tags.get(i);
                    tagSet.addTokenIds(tag.getTokenId());
                    tagSet.extendPhrase(tag.getWordToken());
                    tagSet.setLabel(tag.getTag(pair.getTagLevel()));
                    if (tag.getTag(pair.getTagLevel()).equals(pair.getEventLabel())) {
                      //  System.out.println("WE HAVE A MATCH FOR AN EVENT");
                        // WE ADD THE TOKEN ID to the EVENT IDS
                        eventIds.add(tagSet);
                    }
                    ///// in case we match all within a sentence, in that case the event tag in the config should read "sentence"
                    // and we do not care about the relations
                    else if (pair.getEventLabel().equalsIgnoreCase("sentence")) {
                        eventIds.add(tagSet);
                    }
                    if (!eventLabels.contains(tag.getTag((pair.getTagLevel())))) {
                        //// this must be a participant
                        participantIds.add(tagSet);
                    }
                    else {
                        /// this token is covered by at least one of the event labels and it is not the event label considered
                        /// these events could be covered by another TripleConfig pair
                        HASEVENTS = true;
                    }
                }
            }
          //  System.out.println("eventIds = " + eventIds.size());
            if (eventIds.size()>0) {
                for (int i = 0; i < eventIds.size(); i++) {
                    TagSet eventTagSet = eventIds.get(i);
                    if (participantIds.size()==0) {
                        /// there are only events labelled and no participants
                        /// we create a Triple with dummy participant
                        Triple Triple = new Triple();
                        Triple.setTripleId(key.toString()); /// SCOPE annotation ID is used to identify the Triple
                        Triple.setElementFirstIds(eventTagSet.getTokenIds());
                        Triple.setElementFirstLabel(eventTagSet.getLabel());
                        Triple.setElementFirstComment(eventTagSet.getPhrase());
                        Triple.setElementSecondLabel("DUMMY");


                        if (!pair.getRelation().isEmpty()) {
                            if (pair.getRelation().equalsIgnoreCase("e")) {
                               Triple.setRelation(eventTagSet.getLabel());
                            }
                            else if (pair.getRelation().equalsIgnoreCase("p")) {
                                /// since there is no participant, we set it to the scope label
                                Triple.setRelation(pair.getScopeLabel());
                            }
                            else {
                                //// if not p or e and not empty, we assume that somebody set the relation explicitly in the Triple.cfg file.
                                Triple.setRelation(pair.getRelation());
                            }
                        }
                        else {
                            /// no relation information in the config pair and no participants so we fall back on the scope tag
                            Triple.setRelation(pair.getScopeLabel());
                        }

                        addTriple(TripleArray,Triple);

                    }
                    else {
                        for (int j = 0; j < participantIds.size(); j++) {
                            TagSet participantTagSet = participantIds.get(j);
                            Triple Triple = new Triple();
                            Triple.setTripleId(key.toString()); /// SCOPE annotation ID is used to identify the Triple
                            Triple.setElementFirstIds(eventTagSet.getTokenIds());
                            Triple.setElementFirstComment(eventTagSet.getPhrase());
                            Triple.setElementFirstLabel(eventTagSet.getLabel());
                            Triple.setElementSecondIds(participantTagSet.getTokenIds());
                            Triple.setElementSecondLabel(participantTagSet.getLabel());
                            Triple.setElementSecondComment(participantTagSet.getPhrase());


                            if (!pair.getRelation().isEmpty()) {
                                if (pair.getRelation().equalsIgnoreCase("e")) {
                                    Triple.setRelation(eventTagSet.getLabel());
                                }
                                else  if (pair.getRelation().equalsIgnoreCase("p")) {
                                    Triple.setRelation(participantTagSet.getLabel());
                                }
                                else {
                                    Triple.setRelation(pair.getRelation());
                                }
                            }
                            else {
                                Triple.setRelation(pair.getScopeLabel());
                            }

                            addTriple(TripleArray,Triple);
                        }
                    }

                }
            }
            else {
                /////* THERE CAN BE TWO REASONS FOR THE FACT THAT THERE ARE NO EVENT-IDS:
                //     - Another TripleConfig pair is related to the annotation (it will be handled in the next iteration)
                //     - There is none

                if (!HASEVENTS) {
                    //// IN THIS CASE THE ANNOTATIONS IN THE SCOPE ARE NOT COVERED BY ANY EVENT TAG!!!!
                    //// IN THAT CASE WE USE A DUMMY EVENT!

                    for (int j = 0; j < participantIds.size(); j++) {
                        TagSet participantTagSet = participantIds.get(j);
                        Triple Triple = new Triple();
                        Triple.setTripleId(key.toString()); /// SCOPE annotation ID is used to identify the Triple
                        Triple.setElementFirstComment("DUMMY");
                        Triple.setElementSecondIds(participantTagSet.getTokenIds());
                        Triple.setElementSecondComment(participantTagSet.getPhrase());
                        Triple.setElementSecondLabel(participantTagSet.getLabel());
                        if (!pair.getRelation().isEmpty()) {
                            if (pair.getRelation().equalsIgnoreCase("p")) {
                                Triple.setRelation(participantTagSet.getLabel());
                            }
                            else if (pair.getRelation().equalsIgnoreCase("e")) {
                                Triple.setRelation(pair.getScopeLabel());
                            }
                            else {
                                    Triple.setRelation(pair.getRelation());
                            }
                        }
                        else {
                            Triple.setRelation(pair.getScopeLabel());
                        }
                        addTriple(TripleArray,Triple);
                    }
                }

            }
        }
    }


    //// HashMap used to create Triples from flat annotations, the label represents the relation and the tokens the event IDS
    static void addTagsToFlatTagMap (HashMap<Integer, ArrayList<WordTag>> tagMap, WordTag tag, Integer tagId) {
        // System.out.println("tagScopeId = " + tagScopeId);

        if (tagMap.containsKey(tagId)) {
            ArrayList<WordTag> tags = tagMap.get(tagId);
            if (!hasTag(tags, tag)) {
                    tags.add(tag);
                    tagMap.put(tagId, tags);
            }
        }
        else {
            ArrayList<WordTag> tags = new ArrayList<WordTag>();
            tags.add(tag);
            tagMap.put(tagId, tags);
        }
    }


    /**
     *
     * @param TripleArray
     * @param tagMap    HashMap with the SCOPE annotation ID as the key and a HashMap as a the object
     *                  The Object HashMap has the tagId of the annotation as the key and an array of tags
     *                  that all have that same tag Id
     */
    static void addFlatTagsToTripleArray (ArrayList<Triple> TripleArray,
                                       HashMap<Integer,ArrayList<WordTag>> tagMap,
                                       Integer level,
                                       ArrayList<String> eventLabels) {
        //System.out.println("pair.toString() = " + pair.toString());
        Set keySet = tagMap.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            Integer key = (Integer) keys.next();
         //   System.out.println("key = " + key);
            /// a key represents the ID of a TAG
            Triple Triple = new Triple();
            Triple.setTripleId(key.toString());

            /// We get all the tags with the same ID.
            ArrayList<WordTag> tags = tagMap.get(key);
            String comment = "";
            /// We use the wordform ids to fill the event IDs in the Triple and the words to make the comment
            for (int i = 0; i < tags.size(); i++) {
                WordTag wordTag = tags.get(i);
                comment += wordTag.getWordToken()+" ";
                Triple.addElementFirstIds(wordTag.getTokenId());
                if (Triple.getRelation().isEmpty()) {
                    Triple.setRelation(wordTag.getTag(level));
                }
            }
            Triple.setElementFirstComment(comment.trim());
            addTriple(TripleArray,Triple);
        }
    }


    static void addTriple (ArrayList<Triple> TripleArray, Triple Triple) {
        boolean isNew = true;
        for (int i = 0; i < TripleArray.size(); i++) {
            Triple Triple1 = TripleArray.get(i);
            if (Triple.isEqual(Triple1)) {
                isNew = false;
                break;
            }
        }
        if (isNew) {
            TripleArray.add(Triple);
        }
    }

/*    static void saveTriplesToFile (String output, ArrayList<Triple> tripleList) {
        try {
            FileOutputStream fos = new FileOutputStream(output);
            String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";
            str += "<Triples>\n";
            fos.write(str.getBytes());
            for (int i = 0; i < tripleList.size(); i++) {
                Triple Triple =  tripleList.get(i);
                str = Triple.toString();
                fos.write(str.getBytes());
                //System.out.println(Triple.toString());
            }
            str = "</Triples>\n";
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }*/

    static public void saveTriplesToFile (String output, ArrayList<Triple> tripleList, TripleConfig config) {
        try {
            FileOutputStream fos = new FileOutputStream(output);
            String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";
            str += "<triples>\n";
            fos.write(str.getBytes());
            for (int i = 0; i < tripleList.size(); i++) {
                Triple Triple =  tripleList.get(i);
                if (config.relationTable.containsKey(Triple.getRelation())) {
                    ArrayList<String> mappings = config.relationTable.get(Triple.getRelation());
                    for (int j = 0; j < mappings.size(); j++) {
                        String relation = mappings.get(j);
                        Triple.setRelation(relation);
                        str = Triple.toString();
                        fos.write(str.getBytes());
                    }
                }
                else {
                    str = Triple.toString();
                    fos.write(str.getBytes());
                }
                //System.out.println(Triple.toString());
            }
            str = "</triples>\n";
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    static public void main(String [] args) {
        String configFile = "";
        String tagFolder = "";
        String tagFile = "";
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equalsIgnoreCase("--tag-file") && args.length-1>i) {
                tagFile = args[i+1];
            }
            else if (arg.equalsIgnoreCase("--tag-folder") && args.length-1>i) {
                tagFolder = args[i+1];
            }
            else if (arg.equalsIgnoreCase("--config-file") && args.length-1>i) {
                configFile = args[i+1];
            }
        }
        System.out.println("tagFile = " + tagFile);
        System.out.println("tagFolder = " + tagFolder);
        System.out.println("configFile = " + configFile);
       // TripleConfig conf = new TripleConfig("resources/Triple.cfg");
        TripleConfig conf = new TripleConfig(configFile);
        System.out.println("conf.relationTable.size() = " + conf.relationTable.size());
        if (!tagFolder.isEmpty()) {
            ArrayList<File> files = makeFlatFileList(tagFolder, ".tag");
            for (int i = 0; i < files.size(); i++) {
                tagFile = files.get(i).getAbsolutePath();
                System.out.println("tagFile = " + tagFile);
                ArrayList<Triple> tripleList =  loadAnnotationFile(conf,tagFile);
               // System.out.println("tripleList.size() = " + tripleList.size());
                String output = tagFile+".trp";
                saveTriplesToFile(output, tripleList,conf);            }
        }
        if (!tagFile.isEmpty()) {
            System.out.println("tagFile = " + tagFile);
            String output = new File(tagFile).getAbsolutePath()+".trp";
            ArrayList<Triple> tripleList =  loadAnnotationFile(conf,tagFile);
            saveTriplesToFile(output, tripleList, conf);
        }
    }

    static public ArrayList<File> makeFlatFileList(String inputPath, String extension) {
        ArrayList<File> acceptedFileList = new ArrayList<File>();
        File[] theFileList = null;
        File lF = new File(inputPath);
        if ((lF.canRead()) && lF.isDirectory()) {
            theFileList = lF.listFiles();
            for (int i = 0; i < theFileList.length; i++) {
                if (theFileList[i].isFile()) {
                    if (theFileList[i].getAbsolutePath().endsWith(extension)) {
                        acceptedFileList.add(theFileList[i]);
                    }
                }
            }
        }
        return acceptedFileList;
    }


}