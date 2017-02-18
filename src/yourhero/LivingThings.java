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
public class LivingThings extends Item {
    private String weapon;
    
    public LivingThings(){
        super.killable = true;
        super.missionary = false;
    }
    
    public void setKillingWeapon(String id){
        weapon = id;
    }
    
    public String getKillingWeapon(){
        return weapon;
    }
}
