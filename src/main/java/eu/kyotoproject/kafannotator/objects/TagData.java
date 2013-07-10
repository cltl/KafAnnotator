package eu.kyotoproject.kafannotator.objects;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 10, 2010
 * Time: 9:28:25 AM
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
public class TagData {
        private String tag;
        private int freq;

        public TagData() {
            this.tag = "";
            this.freq = 0;
        }

        public TagData(String aTag, int aFreq) {
            this.tag = aTag;
            this.freq = aFreq;
        }

    public String getTag() {
        return tag;
    }

    public void setTag(String aTag) {
        this.tag = aTag;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int aFreq) {
        this.freq = aFreq;
    }

    public void addFreq(int aFreq) {
        this.freq += aFreq;
    }

    public void incrementFreq() {
        this.freq++;
    }

    public void decrementFreq() {
        this.freq--;
    }
}
