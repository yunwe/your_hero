/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourhero;

/**
 *
 * @author Aung Khant Kyaw
 */
public class Item {

    private String description;
    private String additional_information;
    
    private boolean persistance;
    private boolean pickable = true;
    private boolean reusable = false;
    protected boolean killable = false;
    protected boolean missionary = false;
    
    public Item(){
        persistance = false;
        killable = false;
        missionary = false;
        reusable = false;
    }
    
    public Item(String d) {
        persistance = false;
        killable = false;
        missionary = false;
        reusable = false;
        description = d;
    }

    public String getAdditionalInformation() {
        return additional_information;
    }

    public void setAdditionalInformation(String additional_information) {
        this.additional_information = additional_information;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void Persistance(boolean value){
        persistance = value;
    }
    public boolean IsPersistacne(){
        return persistance;
    }
    public void Pickable(boolean value){
        pickable = value;
    }
    public boolean isPickable() {
        return pickable;
    }
    
    public boolean isKillable(){
        return killable;
    }
    
    public boolean isMissionary(){
        return missionary;
    }
    public void Reusable(boolean value){
        reusable = value;
    }
    public boolean IsReusable(){
        return reusable;
    }
}
