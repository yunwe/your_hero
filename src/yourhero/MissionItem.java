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
public class MissionItem extends Item{
    private String requirement;
    
    public MissionItem(){
        super.missionary = true;
        super.killable = false;
        super.Pickable(false);
    }
    
    public void setRequirement(String id){
        requirement = id;
    }
    
    public String getRequirement(){
        return requirement;
    }
}
