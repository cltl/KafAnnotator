package eu.kyotoproject.kafannotator.sensetags;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Nov 4, 2010
 * Time: 4:05:16 PM
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
public class SynsetNode {
    private String synset;
    private String synsetId;
    private int nDescendants;
    private ArrayList<String> children;

    public SynsetNode() {
        this.synset = "";
        this.synsetId = "";
        this.nDescendants = 0;
        this.children = new ArrayList<String>();
    }

    public String getSynsetId() {
        return synsetId;
    }

    public void setSynsetId(String synsetId) {
        this.synsetId = synsetId;
    }

    public String getSynset() {
        return synset;
    }

    public void setSynset(String synset) {
        this.synset = synset;
    }

    public int getnDescendants() {
        return nDescendants;
    }

    public void setnDescendants(int nDescendants) {
        this.nDescendants = nDescendants;
    }

    public ArrayList<String> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<String> children) {
        this.children = children;
    }

    public void addChildren(String child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        return "SynsetNode{" +
                "synset='" + synset + '\'' +
                ", nDescendants=" + nDescendants +
                ", children=" + children +
                '}';
    }
}
