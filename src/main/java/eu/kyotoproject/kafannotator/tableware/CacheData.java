package eu.kyotoproject.kafannotator.tableware;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 22, 2010
 * Time: 8:22:55 AM
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
public class CacheData {

    private int nRow;
    private String tokenId;
    private String tag;
    private Integer tagId;
    private int tagLevel;
    private Boolean status;

    public CacheData(int nRow, String tokenId, String tag, Integer tagId, Boolean status, int tagLevel) {
        this.nRow = nRow;
        this.tokenId = tokenId;
        this.tag = tag;
        this.tagId = tagId;
        this.tagLevel = tagLevel;
        this.status = status;
    }

    public String toString () {
        String str = nRow+";"+tokenId+";"+tag+";"+tagId+";"+tagLevel+";"+status;
        return str;
    }
    
    public int getnRow() {
        return nRow;
    }

    public void setnRow(int nRow) {
        this.nRow = nRow;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public int getTagLevel() {
        return tagLevel;
    }

    public void setTagLevel(int tagLevel) {
        this.tagLevel = tagLevel;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
