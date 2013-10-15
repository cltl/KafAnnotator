package eu.kyotoproject.kafannotator.objects;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 5, 2010
 * Time: 4:55:12 PM
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
public class WordTag {
    private boolean mark;
    private String tokenId;
    private Integer sentenceId;
    private String wordToken;
    private String wordType;
    private String wordSynset;
    private String pos;
    private String tag1;
    private Integer tagId1;
    private String tag2;
    private Integer tagId2;
    private String tag3;
    private Integer tagId3;
    private String tag4;
    private Integer tagId4;
    private String tag5;
    private Integer tagId5;
    private String tag6;
    private Integer tagId6;
    private String tag7;
    private Integer tagId7;
    private String tag8;
    private Integer tagId8;
    private int order;

    public WordTag () {
        mark = false;
        sentenceId = 0;
        tokenId = "";
        wordToken = "";
        wordSynset = "";
        wordType = "";
        pos = "";
        tag1 = "";
        tagId1 = 0;
        tag2 = "";
        tagId2 = 0;
        tag3 = "";
        tag4 = "";
        tag5 = "";
        tag6 = "";
        tag7 = "";
        tag8 = "";
        tagId3 = 0;
        tagId4 = 0;
        tagId5 = 0;
        tagId6 = 0;
        tagId7 = 0;
        tagId8 = 0;
        order = 0;
    }

    public WordTag (Integer aSentenceId,String aTokenId, String aWord, String aWordType, String aPos, String aSynset, int anOrder) {
        mark = false;
        sentenceId = aSentenceId;
        tokenId = aTokenId;
        wordToken = aWord;
        wordType = aWordType;
        wordSynset = aSynset;
        pos = aPos;
        tag1 = "";
        tagId1 = 0;
        tag2 = "";
        tagId2 = 0;
        tag3 = "";
        tagId3 = 0;
        tag4 = "";
        tagId4 = 0;
        tag5 = "";
        tagId5 = 0;
        tag6 = "";
        tagId6 = 0;
        tag7 = "";
        tagId7 = 0;
        tag8 = "";
        tagId8 = 0;
        order = anOrder;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getWordToken() {
        return wordToken;
    }

    public void setWordToken(String wordToken) {
        this.wordToken = wordToken;
    }

    public String getTag (int level) {
        if (level==1) {
            return tag1;
        }
        else if (level==2) {
            return tag2;
        }
        else if (level==3) {
            return tag3;
        }
        else if (level==4) {
            return tag4;
        }
        else if (level==5) {
            return tag5;
        }
        else if (level==6) {
            return tag6;
        }
        else if (level==7) {
            return tag7;
        }
        else if (level==8) {
            return tag8;
        }
        else {
            return "";
        }
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public Integer getTagId2() {
        return tagId2;
    }

    public void setTagId2(Integer tagId2) {
        this.tagId2 = tagId2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public Integer getTagId3() {
        return tagId3;
    }

    public void setTagId3(Integer tagId3) {
        this.tagId3 = tagId3;
    }

    public String getTag4() {
        return tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }

    public Integer getTagId4() {
        return tagId4;
    }

    public void setTagId4(Integer tagId4) {
        this.tagId4 = tagId4;
    }

    public String getTag5() {
        return tag5;
    }

    public void setTag5(String tag5) {
        this.tag5 = tag5;
    }

    public Integer getTagId5() {
        return tagId5;
    }

    public void setTagId5(Integer tagId5) {
        this.tagId5 = tagId5;
    }

    public String getTag6() {
        return tag6;
    }

    public void setTag6(String tag6) {
        this.tag6 = tag6;
    }

    public Integer getTagId6() {
        return tagId6;
    }

    public void setTagId6(Integer tagId6) {
        this.tagId6 = tagId6;
    }

    public String getTag7() {
        return tag7;
    }

    public void setTag7(String tag7) {
        this.tag7 = tag7;
    }

    public Integer getTagId7() {
        return tagId7;
    }

    public void setTagId7(Integer tagId7) {
        this.tagId7 = tagId7;
    }

    public String getTag8() {
        return tag8;
    }

    public void setTag8(String tag8) {
        this.tag8 = tag8;
    }

    public Integer getTagId8() {
        return tagId8;
    }

    public void setTagId8(Integer tagId8) {
        this.tagId8 = tagId8;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Integer getTagId(int level) {
        if (level==1) {
            return tagId1;
        }
        else if (level==2) {
            return tagId2;
        }
        else if (level==3) {
            return tagId3;
        }
        else if (level==4) {
            return tagId4;
        }
        else if (level==5) {
            return tagId5;
        }
        else if (level==6) {
            return tagId6;
        }
        else if (level==7) {
            return tagId7;
        }
        else if (level==8) {
            return tagId8;
        }
        return -1;
    }

    public Integer getTagId1() {
        return tagId1;
    }

    public void setTagId1(Integer tagId1) {
        this.tagId1 = tagId1;
    }

    public Integer getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getWordSynset() {
        return wordSynset;
    }

    public void setWordSynset(String wordSynset) {
        this.wordSynset = wordSynset;
    }

    public ArrayList<String> matchedValues (WordTag tag2) {
        ArrayList<String> tagValues = new ArrayList<String> ();
        ArrayList<String> thisValues = this.getTagValues();
        ArrayList<String> thatValues = tag2.getTagValues();
        for (int i = 0; i < thisValues.size(); i++) {
            String s = thisValues.get(i);
            if (thatValues.contains(s)) {
                tagValues.add(s);
            }
        }
        return tagValues;
    }


    public ArrayList<String> misMatchedValues (WordTag tag2) {
        ArrayList<String> tagValues = new ArrayList<String> ();
        ArrayList<String> thisValues = this.getTagValues();
        ArrayList<String> thatValues = tag2.getTagValues();
        for (int i = 0; i < thisValues.size(); i++) {
            String s = thisValues.get(i);
            if (!thatValues.contains(s)) {
                tagValues.add(s);
            }
        }
        return tagValues;
    }


    public ArrayList<String> getTagValues () {
        ArrayList<String> tagValues = new ArrayList<String> ();
        if (tag1.length()>0) {
            tagValues.add(tag1);
        }
        if (tag2.length()>0) {
            tagValues.add(tag2);
        }
        if (tag3.length()>0) {
            tagValues.add(tag3);
        }
        if (tag4.length()>0) {
            tagValues.add(tag4);
        }
        if (tag5.length()>0) {
            tagValues.add(tag5);
        }
        if (tag6.length()>0) {
            tagValues.add(tag6);
        }
        if (tag7.length()>0) {
            tagValues.add(tag7);
        }
        if (tag8.length()>0) {
            tagValues.add(tag8);
        }
        return tagValues;
    }

    public boolean compareTag (WordTag tag2) {
        boolean match = true;
        if (!wordSynset.equals(tag2.getWordSynset())) {
           return false;
        }
        if (this.tag1.length()>0) {
            if (this.tag1.equals(tag2.tag1) ||
                this.tag1.equals(tag2.tag2) ||
                this.tag1.equals(tag2.tag3) ||
                this.tag1.equals(tag2.tag4) ||
                this.tag1.equals(tag2.tag5) ||
                this.tag1.equals(tag2.tag6) ||
                this.tag1.equals(tag2.tag7) ||
                this.tag1.equals(tag2.tag8)) {
                match = true;
            }
            else {
                return false;
            }
        }
        if (this.tag2.length()>0) {
            if (this.tag2.equals(tag2.tag1) ||
                this.tag2.equals(tag2.tag2) ||
                this.tag2.equals(tag2.tag3) ||
                this.tag2.equals(tag2.tag4) ||
                this.tag2.equals(tag2.tag5) ||
                this.tag2.equals(tag2.tag6) ||
                this.tag2.equals(tag2.tag7) ||
                this.tag2.equals(tag2.tag8)) {
                match = true;
            }
            else {
                return false;
            }
        }
        if (this.tag3.length()>0) {
            if (this.tag3.equals(tag2.tag1) ||
                this.tag3.equals(tag2.tag2) ||
                this.tag3.equals(tag2.tag3) ||
                this.tag3.equals(tag2.tag4) ||
                this.tag3.equals(tag2.tag5) ||
                this.tag3.equals(tag2.tag6) ||
                this.tag3.equals(tag2.tag7) ||
                this.tag3.equals(tag2.tag8)) {
                match = true;
            }
            else {
                return false;
            }
        }
        if (this.tag4.length()>0) {
            if (this.tag4.equals(tag2.tag1) ||
                this.tag4.equals(tag2.tag2) ||
                this.tag4.equals(tag2.tag3) ||
                this.tag4.equals(tag2.tag4) ||
                this.tag4.equals(tag2.tag5) ||
                this.tag4.equals(tag2.tag6) ||
                this.tag4.equals(tag2.tag7) ||
                this.tag4.equals(tag2.tag8)) {
                match = true;
            }
            else {
                return false;
            }
        }
        if (this.tag5.length()>0) {
            if (this.tag5.equals(tag2.tag1) ||
                this.tag5.equals(tag2.tag2) ||
                this.tag5.equals(tag2.tag3) ||
                this.tag5.equals(tag2.tag4) ||
                this.tag5.equals(tag2.tag5) ||
                this.tag5.equals(tag2.tag6) ||
                this.tag5.equals(tag2.tag7) ||
                this.tag5.equals(tag2.tag8)) {
                match = true;
            }
            else {
                return false;
            }
        }
        if (this.tag6.length()>0) {
            if (this.tag6.equals(tag2.tag1) ||
                this.tag6.equals(tag2.tag2) ||
                this.tag6.equals(tag2.tag3) ||
                this.tag6.equals(tag2.tag4) ||
                this.tag6.equals(tag2.tag5) ||
                this.tag6.equals(tag2.tag6) ||
                this.tag6.equals(tag2.tag7) ||
                this.tag6.equals(tag2.tag8)) {
                match = true;
            }
            else {
                return false;
            }
        }
        if (this.tag7.length()>0) {
            if (this.tag7.equals(tag2.tag1) ||
                this.tag7.equals(tag2.tag2) ||
                this.tag7.equals(tag2.tag3) ||
                this.tag7.equals(tag2.tag4) ||
                this.tag7.equals(tag2.tag5) ||
                this.tag7.equals(tag2.tag6) ||
                this.tag7.equals(tag2.tag7) ||
                this.tag7.equals(tag2.tag8)) {
                match = true;
            }
            else {
                return false;
            }
        }
        if (this.tag8.length()>0) {
            if (this.tag8.equals(tag2.tag1) ||
                this.tag8.equals(tag2.tag2) ||
                this.tag8.equals(tag2.tag3) ||
                this.tag8.equals(tag2.tag4) ||
                this.tag8.equals(tag2.tag5) ||
                this.tag8.equals(tag2.tag6) ||
                this.tag8.equals(tag2.tag7) ||
                this.tag8.equals(tag2.tag8)) {
                match = true;
            }
            else {
                return false;
            }
        }
        return match;
    }

    public int countTagDifference (WordTag tag2) {
        int diff = 0;
        if (!wordSynset.equals(tag2.getWordSynset())) {
           diff++;
        }
        if (this.tag1.length()>0) {
            if (this.tag1.equals(tag2.tag1) ||
                this.tag1.equals(tag2.tag2) ||
                this.tag1.equals(tag2.tag3) ||
                this.tag1.equals(tag2.tag4) ||
                this.tag1.equals(tag2.tag5) ||
                this.tag1.equals(tag2.tag6) ||
                this.tag1.equals(tag2.tag7) ||
                this.tag1.equals(tag2.tag8)) {
            }
            else {
                diff++;
            }
        }
        if (this.tag2.length()>0) {
            if (this.tag2.equals(tag2.tag1) ||
                this.tag2.equals(tag2.tag2) ||
                this.tag2.equals(tag2.tag3) ||
                this.tag2.equals(tag2.tag4) ||
                this.tag2.equals(tag2.tag5) ||
                this.tag2.equals(tag2.tag6) ||
                this.tag2.equals(tag2.tag7) ||
                this.tag2.equals(tag2.tag8)) {
            }
            else {
                diff++;
            }
        }
        if (this.tag3.length()>0) {
            if (this.tag3.equals(tag2.tag1) ||
                this.tag3.equals(tag2.tag2) ||
                this.tag3.equals(tag2.tag3) ||
                this.tag3.equals(tag2.tag4) ||
                this.tag3.equals(tag2.tag5) ||
                this.tag3.equals(tag2.tag6) ||
                this.tag3.equals(tag2.tag7) ||
                this.tag3.equals(tag2.tag8)) {
            }
            else {
                diff++;
            }
        }
        if (this.tag4.length()>0) {
            if (this.tag4.equals(tag2.tag1) ||
                this.tag4.equals(tag2.tag2) ||
                this.tag4.equals(tag2.tag3) ||
                this.tag4.equals(tag2.tag4) ||
                this.tag4.equals(tag2.tag5) ||
                this.tag4.equals(tag2.tag6) ||
                this.tag4.equals(tag2.tag7) ||
                this.tag4.equals(tag2.tag8)) {
            }
            else {
                diff++;
            }
        }
        if (this.tag5.length()>0) {
            if (this.tag5.equals(tag2.tag1) ||
                this.tag5.equals(tag2.tag2) ||
                this.tag5.equals(tag2.tag3) ||
                this.tag5.equals(tag2.tag4) ||
                this.tag5.equals(tag2.tag5) ||
                this.tag5.equals(tag2.tag6) ||
                this.tag5.equals(tag2.tag7) ||
                this.tag5.equals(tag2.tag8)) {
            }
            else {
                diff++;
            }
        }
        if (this.tag6.length()>0) {
            if (this.tag6.equals(tag2.tag1) ||
                this.tag6.equals(tag2.tag2) ||
                this.tag6.equals(tag2.tag3) ||
                this.tag6.equals(tag2.tag4) ||
                this.tag6.equals(tag2.tag5) ||
                this.tag6.equals(tag2.tag6) ||
                this.tag6.equals(tag2.tag7) ||
                this.tag6.equals(tag2.tag8)) {
            }
            else {
                diff++;
            }
        }
        if (this.tag7.length()>0) {
            if (this.tag7.equals(tag2.tag1) ||
                this.tag7.equals(tag2.tag2) ||
                this.tag7.equals(tag2.tag3) ||
                this.tag7.equals(tag2.tag4) ||
                this.tag7.equals(tag2.tag5) ||
                this.tag7.equals(tag2.tag6) ||
                this.tag7.equals(tag2.tag7) ||
                this.tag7.equals(tag2.tag8)) {
            }
            else {
                diff++;
            }
        }
        if (this.tag8.length()>0) {
            if (this.tag8.equals(tag2.tag1) ||
                this.tag8.equals(tag2.tag2) ||
                this.tag8.equals(tag2.tag3) ||
                this.tag8.equals(tag2.tag4) ||
                this.tag8.equals(tag2.tag5) ||
                this.tag8.equals(tag2.tag6) ||
                this.tag8.equals(tag2.tag7) ||
                this.tag8.equals(tag2.tag8)) {
            }
            else {
                diff++;
            }
        }
        return diff;
    }

    public void importTags (WordTag tag2) {
        if (this.wordSynset.length()==0 && tag2.wordSynset.length()>0) {
            this.wordSynset = tag2.wordSynset;
        }
        if (this.tag1.length()==0 && tag2.tag1.length()>0) {
            this.tag1 = tag2.tag1;
            this.tagId1 = tag2.tagId1;
        }
        if (this.tag2.length()==0 && tag2.tag2.length()>0) {
            this.tag2= tag2.tag2;
            this.tagId2 = tag2.tagId2;
        }
        if (this.tag3.length()==0 && tag2.tag3.length()>0) {
            this.tag3= tag2.tag3;
            this.tagId3 = tag2.tagId3;
        }
        if (this.tag4.length()==0 && tag2.tag4.length()>0) {
            this.tag4= tag2.tag4;
            this.tagId4 = tag2.tagId4;
        }
        if (this.tag5.length()==0 && tag2.tag5.length()>0) {
            this.tag5= tag2.tag5;
            this.tagId5 = tag2.tagId5;
        }
        if (this.tag6.length()==0 && tag2.tag6.length()>0) {
            this.tag6= tag2.tag6;
            this.tagId6 = tag2.tagId6;
        }
        if (this.tag7.length()==0 && tag2.tag7.length()>0) {
            this.tag7= tag2.tag7;
            this.tagId7 = tag2.tagId6;
        }
        if (this.tag8.length()==0 && tag2.tag8.length()>0) {
            this.tag8= tag2.tag8;
            this.tagId8 = tag2.tagId6;
        }
    }

    public boolean hasTagConflicts (WordTag tag2) {
        if (!this.wordSynset.equals(tag2.wordSynset)) {
            if ((this.wordSynset.length()>0) && (tag2.wordSynset.length()>0)) {
                return true;
            }
        }
        else if (!this.tag1.equals(tag2.tag1)) {
            if (!this.tag1.isEmpty() && !tag2.tag1.isEmpty()) {
                return true;
            }
        }
        else if (!this.tag2.equals(tag2.tag2)) {
            if (!this.tag2.isEmpty() && !tag2.tag2.isEmpty()) {
                return true;
            }
        }
        else if (!this.tag3.equals(tag2.tag3)) {
            if (!this.tag3.isEmpty() && !tag2.tag3.isEmpty()) {
                return true;
            }
        }
        else if (!this.tag4.equals(tag2.tag4)) {
            if (!this.tag4.isEmpty() && !tag2.tag4.isEmpty()) {
                return true;
            }
        }
        else if (!this.tag5.equals(tag2.tag5)) {
            if (!this.tag5.isEmpty() && !tag2.tag5.isEmpty()) {
                return true;
            }
        }
        else if (!this.tag6.equals(tag2.tag6)) {
            if (!this.tag6.isEmpty() && !tag2.tag6.isEmpty()) {
                return true;
            }
        }
        else if (!this.tag7.equals(tag2.tag7)) {
            if (!this.tag7.isEmpty() && !tag2.tag7.isEmpty()) {
                return true;
            }
        }
        else if (!this.tag8.equals(tag2.tag8)) {
            if (!this.tag8.isEmpty() && !tag2.tag8.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean areIdentical (WordTag tag2) {
        return (this.toString().equals(tag2.toString()));
    }

    public String toString() {
        String str = this.getTokenId()+"\t"+this.getWordToken()+"\t"+this.getWordType()+"\t"+this.getPos();
        str += "\t"+this.getWordSynset();
        str += "\t"+this.getTag1()+"\t"+this.getTagId1();
        str += "\t"+this.getTag2()+"\t"+this.getTagId2();
        str += "\t"+this.getTag3()+"\t"+this.getTagId3();
        str += "\t"+this.getTag4()+"\t"+this.getTagId4();
        str += "\t"+this.getTag5()+"\t"+this.getTagId5();
        str += "\t"+this.getTag6()+"\t"+this.getTagId6();
        str += "\t"+this.getTag7()+"\t"+this.getTagId7();
        str += "\t"+this.getTag8()+"\t"+this.getTagId8();
        str += "\t"+this.getOrder()+"\t"+"true\t"+this.getSentenceId();
        return str;
    }
}
