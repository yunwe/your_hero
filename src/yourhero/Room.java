/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourhero;

import java.util.HashMap;

/**
 *
 * @author Aung Khant Kyaw
 */
class Room {

    private String name;
    
    public Location location;
    private HashMap<String, Item> items = new HashMap<>();
    
    Room(String _name, Location _location) {
        name = _name;
        location = _location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        String temp = "You are at " + name + ".\n";
        String t2 = "";
        for(String str : items.keySet()){
            t2 += str + ", ";
        }
        if( t2.length() > 2){
            t2 = t2.substring(0, t2.length() - 2);
            temp += "There are " + t2 +".\n";
        }
        Location l = location.Add(Game.NORTH);
        if(l.IsValid()) 
            temp += "North -> " + Game.game().map[l.x][l.y].getName() + "\n";
       
        l = location.Add(Game.EAST);
        if(l.IsValid()) 
            temp += "East -> " + Game.game().map[l.x][l.y].getName() + "\n";
        
        l = location.Add(Game.SOUTH);
        if(l.IsValid()) 
            temp += "South -> " + Game.game().map[l.x][l.y].getName() + "\n";
        
        l = location.Add(Game.WEST);
        if(l.IsValid()) 
            temp += "West -> " + Game.game().map[l.x][l.y].getName() + "\n";
        
        return temp;
    }

    public void setItem(String id) {
       items.put(id, Prefab.instantiate(id));
    }
    public void setItem(String id, Item obj){
        items.put(id, obj);
    }
    public Item grabItem(String id) {
        if(!items.containsKey(id)) return null;
        Item t = items.get(id);
        if( !t.isPickable()) return null;
        return items.remove(id);
    }
    public void removeItem(String id){
        items.remove(id);
    }
    public Item copyItem(String id){
        return items.get(id);
    }
    public String lookItem(String pitm){
        String str = null;
        if(!items.containsKey(pitm)) return str;
        return items.get(pitm).getDescription();
    }
    public String getItemAdditionalInformation(String itm_id){
        String str = null;
        if(!items.containsKey(itm_id)) return str;
        return items.get(itm_id).getAdditionalInformation();
    }
}
