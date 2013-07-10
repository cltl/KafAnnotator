package eu.kyotoproject.kafannotator.statistics;

import eu.kyotoproject.kafannotator.objects.WordTag;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: 9/1/11
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TagStatistics {
    /*
	inhoud	ingevuld	niveau	gekoppeld aan
tag1	WP/ID/MR	altijd	per zin	attitude (als pos of neg of neutraal)
tag2	wij-verwijzing	soms	per woord(groep)	niet relevant
tag3	rollen	soms	per woord(groep)	niet relevant
tag4	coreference	soms	per woord(groep)	twee woord(groep)en zijn via IDS aan elkaar gekoppeld
tag5	subissue	min. 1 maal per zin	per zin	attitude (als andere waarde dan pos-neg-neutraal)
tag6	attitude	min. 1 maal per zin	per zin	gekopped aan tag1 (als pos/neg/neutraal) resp. tag 5 (als beperken, handhaven, etc/)
tag7	vervallen
tag8	hoofdissue	altijd	per zin	niet relevant



			per zin = komt een of meer keren per zin voor maar is niet gekoppeld aan bepaalde woordenm
			per woord(groep) = is gekoppeld aan woorden en kan dus meerdere keren per zin voorkomen
		altijd = staat bij ieder woord ingevuld (maar meestal hetzelfde per zin).
		min. 1 per zin= staat niet bij ieder woord ingevuld, maar komt per zin wel minstens 1 keer voor



N.B. Overzichten zijn handig per tag / per zin (dus hoeveel zinnen komen met betreffende tag voor) en van tag1+tag6  en tag5 + tag6 gezamenlijk

     */

    private String tag;
    private ArrayList<String> tagIds;
    private ArrayList<String> tokenIds;
    private ArrayList<String> typeIds;
    private ArrayList<String> constituentIds;
    private ArrayList<String> sentenceIds;

    private int nDocs;
    private int nCoReferences; //// same id but different tags

    public TagStatistics() {
        this.tag = "";
        this.tagIds = new ArrayList<String>();
        this.tokenIds = new ArrayList<String>();
        this.typeIds = new ArrayList<String>();
        this.constituentIds = new ArrayList<String>();
        this.sentenceIds = new ArrayList<String>();
        this.nCoReferences = 0;
        this.nDocs = 0;
    }

    public void addToTagStatistics (TagStatistics stat) {
        this.tagIds.addAll(stat.tagIds);
        this.tokenIds.addAll(stat.tokenIds);
        this.typeIds.addAll(stat.typeIds);
        this.sentenceIds.addAll(stat.sentenceIds);
        this.constituentIds.addAll(stat.constituentIds);
        this.nCoReferences +=stat.nCoReferences;
        this.nDocs += stat.nDocs;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public int getNDocs() {
        return nDocs;
    }

    public void setNDocs(int nDocs) {
        this.nDocs = nDocs;
    }

    public int getNCoReferences() {
        return nCoReferences;
    }

    public void setNCoReferences(int nCoReferences) {
        this.nCoReferences = nCoReferences;
    }

    public ArrayList<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(ArrayList<String> tagIds) {
        this.tagIds = tagIds;
    }

    public void addTagIds(String tagId) {
        if (!this.tagIds.contains(tagId)) {
            this.tagIds.add(tagId);
        }
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

    public ArrayList<String> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(ArrayList<String> typeIds) {
        this.typeIds = typeIds;
    }

    public void addTypeIds(String typeId) {
        if (!this.typeIds.contains(typeId)) {
            this.typeIds.add(typeId);
        }
    }

    public ArrayList<String> getConstituentIds() {
        return constituentIds;
    }

    public void setConstituentIds(ArrayList<String> constituentIds) {
        this.constituentIds = constituentIds;
    }

    public void addConstituentIds(String constituentId) {
        if (!this.constituentIds.contains(constituentId)) {
            this.constituentIds.add(constituentId);
        }
    }

    public ArrayList<String> getSentenceIds() {
        return sentenceIds;
    }

    public void setSentenceIds(ArrayList<String> sentenceIds) {
        this.sentenceIds = sentenceIds;
    }

    public void addSentenceIds(String sentenceId) {
        if (!this.sentenceIds.contains(sentenceId)) {
            this.sentenceIds.add(sentenceId);
        }
    }

    public void addTagInfo (WordTag tag, int tagLevel, String fileName) {
        this.tag = tag.getTag(tagLevel);
        this.addTagIds(fileName+"#"+tag.getTagId(tagLevel));
        this.addTokenIds(fileName+"#"+tag.getTokenId());
        this.addSentenceIds((fileName+"#"+tag.getSentenceId()));
    }

    public String toString () {
        String str = "";
        return str;
    }

    static public String toHeaderString () {
        String str = "\tTag ids\tTokens\tTypes\tConstituents\tSentences\tNr. Corefs\tNr docs";
        return str;
    }
    public String toCsvString () {
        String str = tag
                + "\t"+this.tagIds.size()
                + "\t"+this.tokenIds.size()
                + "\t"+this.typeIds.size()
                + "\t"+this.constituentIds.size()
                + "\t"+this.sentenceIds.size()
                + "\t"+this.getNCoReferences()
                + "\t"+this.getNDocs()+"\n";
        return str;
    }

    public String toCsvCntString () {
        String str = "\t"+this.tagIds.size()
                + "\t"+this.tokenIds.size()
                + "\t"+this.typeIds.size()
                + "\t"+this.constituentIds.size()
                + "\t"+this.sentenceIds.size()
                + "\t"+this.getNCoReferences()
                + "\t"+this.getNDocs();
        return str;
    }
}
