package eu.kyotoproject.kafannotator.objects;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kyoto
 * Date: 10/11/12
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class TagSet {

    private ArrayList<String> tokenIds;
    private String phrase;
    private String label;

    public TagSet() {
        this.phrase = "";
        this.label = "";
        this.tokenIds = new ArrayList<String>();
    }

    public String getPhrase() {
        return phrase.trim();
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public void extendPhrase(String phrase) {
        this.phrase += " "+phrase;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<String> getTokenIds() {
        return tokenIds;
    }

    public void setTokenIds(ArrayList<String> tokenIds) {
        this.tokenIds = tokenIds;
    }

    public void addTokenIds(String tokenId) {
        if (!this.tokenIds.contains(tokenId)) {
            this.tokenIds.add(tokenId);
        }
    }


}
