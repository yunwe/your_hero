/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourhero;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Aung Khant Kyaw
 */
public class Player {

    private HashMap<String, Item> items = new HashMap<>();
    private ArrayList<String> achievements = new ArrayList<>();
    
    private static final int MAX = 3;

    public boolean pocketFull(){
        if(items.size() >= MAX) return true;
        return false;
    }
    public void pickItem(String id, Item itm) {
        if(items.size() >= MAX) return;
        items.put(id, itm);
    }
    
    public Item dropItem(String id){
        if(!items.containsKey(id)) return null;
        return items.remove(id); 
    }
    
    public String listItems(){
        String str = "\n";
        for(String s : items.keySet())
            str += "\t" + "- " + s + "\n";
        str = str.substring(0, str.length() - 1);
        return str;
    }
    
    public boolean has(String id) {
        return items.containsKey(id);
    }
    
    public String lookItem(String pitm){
        String str = null;
        if(!items.containsKey(pitm)) return str;
        return items.get(pitm).getDescription();
    }
    
    public void setAchievement(String a){
        achievements.add(a);
    }
    
    public boolean Achieved(String a){
        for(String s : achievements){
            if(s.equalsIgnoreCase(a)) return true;
        }
        return false;
    }

}
