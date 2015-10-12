
package com.brandonjf.volleycupid.okclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Percentages {

    @SerializedName("match")
    @Expose
    private Integer match;
    @SerializedName("enemy")
    @Expose
    private Integer enemy;

    /**
     * 
     * @return
     *     The match
     */
    public Integer getMatch() {
        return match;
    }

    /**
     * 
     * @param match
     *     The match
     */
    public void setMatch(Integer match) {
        this.match = match;
    }

    /**
     * 
     * @return
     *     The enemy
     */
    public Integer getEnemy() {
        return enemy;
    }

    /**
     * 
     * @param enemy
     *     The enemy
     */
    public void setEnemy(Integer enemy) {
        this.enemy = enemy;
    }

}
