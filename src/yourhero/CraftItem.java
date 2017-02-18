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
public class CraftItem extends Item {
    private HashMap<String, Item> elements = new HashMap<>();

    public HashMap<String, Item> getElements() {
       return elements;
    } 
    
    public CraftItem(){
       
    }
    
    public void setElement(String id, Item obj){
        elements.put(id, obj);
    }
}
