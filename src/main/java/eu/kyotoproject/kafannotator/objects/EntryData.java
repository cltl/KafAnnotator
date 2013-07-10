package eu.kyotoproject.kafannotator.objects;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 5, 2010
 * Time: 4:57:19 PM
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
public class EntryData {

    private ArrayList<TagData> tagDataList;

    public EntryData () {
        tagDataList = new ArrayList<TagData>();
    }

    public ArrayList<TagData> getTagDataList() {
        return tagDataList;
    }

    public void setTagDataList(ArrayList<TagData> tagDataList) {
        this.tagDataList = tagDataList;
    }

    public void addTagData (String tag) {
        TagData tagData = new TagData (tag, 1);
        tagDataList.add(tagData);
    }
    public void upDate(String tag, int freq) {
        boolean newTag = true;
        for (int i = 0; i < tagDataList.size(); i++) {
            TagData tagData = tagDataList.get(i);
             if (tagData.getTag().equals(tag)) {
                 tagData.addFreq(freq);
              //   System.out.println("tagData.freq = " + tagData.getFreq());
                 newTag = false;                 
                 break;
             }
        }
        if (newTag) {
             TagData aTagData = new TagData();
             aTagData.setTag(tag);
             aTagData.setFreq(1);
           //  System.out.println("update aTagData.tag = " + aTagData.getTag1());
           //  System.out.println(" update aTagData.freq = " + aTagData.getFreq());
             tagDataList.add(aTagData);
        }
    }

    public void increment(String tag) {
        boolean newTag = true;
        for (int i = 0; i < tagDataList.size(); i++) {
            TagData tagData = tagDataList.get(i);
             if (tagData.getTag().equals(tag)) {
                 tagData.incrementFreq();
              //   System.out.println("tagData.freq = " + tagData.getFreq());
                 newTag = false;
                 break;
             }
        }
        if (newTag) {
             TagData aTagData = new TagData(tag, 1);
          //   System.out.println("increment aTagData.tag = " + aTagData.getTag1());
             tagDataList.add(aTagData);
        }
    }

    public void decrement(String tag) {
        for (int i = 0; i < tagDataList.size(); i++) {
            TagData tagData = tagDataList.get(i);
             if (tagData.getTag().equals(tag)) {
                 tagData.decrementFreq();;
            //     System.out.println("tagData.freq = " + tagData.getFreq());
                 break;
             }
        }
    }

    public void upDate(EntryData otherData) {
        for (int i = 0; i < otherData.tagDataList.size(); i++) {
            TagData tagData = otherData.tagDataList.get(i);
            upDate(tagData.getTag(), tagData.getFreq());
        }
    }

    public String getTopTag() {
        String tag = "";
        int topFreq = 0;
        for (int i = 0; i < tagDataList.size(); i++) {
             TagData tagData = tagDataList.get(i);
             if (tagData.getFreq()>=topFreq) {
                 topFreq = tagData.getFreq();
                 tag = tagData.getTag();
             }
        }
        return tag;
   }

   public int getTopFreq() {
       int topFreq = 0;
       for (int i = 0; i < tagDataList.size(); i++) {
             TagData tagData = tagDataList.get(i);
             if (tagData.getFreq()>=topFreq) {
                 topFreq = tagData.getFreq();
             }
        }
        return topFreq;
   }

   public int getTagFreq(String tag) {
       for (int i = 0; i < tagDataList.size(); i++) {
             TagData tagData = tagDataList.get(i);
             if (tagData.getTag().equals(tag)) {
                 return tagData.getFreq();
             }
        }
        return 0;
   }

    public int getTotalFreq() {
       int freq = 0;
       for (int i = 0; i < tagDataList.size(); i++) {
             TagData tagData = tagDataList.get(i);
             freq += tagData.getFreq();
        }
        return freq;
   }

    public String toString() {
        String tags = "";
        for (int i = 0; i < tagDataList.size(); i++) {
             TagData tagData = tagDataList.get(i);
             tags += tagData.getTag() +";"+ tagData.getFreq()+";";
        }
        return tags;
   }

    public String toXmlString() {
        String tags = "";
        for (int i = 0; i < tagDataList.size(); i++) {
            TagData tagData = tagDataList.get(i);
            tags += "\t\t\t<tagData><tag>"+ tagData.getTag() +"</tag> <freq>"+ tagData.getFreq()+"</freq></tagData>\n";
         }
         return tags;
    }
 }
