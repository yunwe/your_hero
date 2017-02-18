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
public class Prefab {
   private static HashMap<String, Item> items = new HashMap<>();
   
   public static void _init(){
        Item knife = new Item();
        knife.setDescription("It's sharp knife. You can use it as an weapon.");
        knife.Pickable(true);
        items.put("knife", knife);
        
        Item gold = new Item();
        gold.setDescription("It's glitter and yellow. It's precious.");
        gold.Pickable(true);
        items.put("gold", gold);
        
        Item axe = new Item();
        axe.setDescription("It's an axe. That's a best tool to cut trees.");
        axe.Pickable(true);
        axe.Reusable(true);
        items.put("axe", axe);
        
        Item island = new Item();
        island.setDescription("It's a beautiful island which is situated at the center of lake.");
        island.Persistance(true);
        island.Pickable(false);
        island.setAdditionalInformation("Are you crazy? You think you can pick up an island!!!");
        items.put("island", island);
        
        MissionItem itemY = new MissionItem();
        itemY.setDescription("It's a part of weaponX and you can find it on island. Find itemZ to get weaponX.");
        itemY.Pickable(false);
        itemY.setAdditionalInformation("Craft a boat first to pass through the lake.");
        itemY.setRequirement("boat");
        items.put("itemY", itemY);
        
        Item tree = new Item();
        tree.setDescription("It's a large tree. It's teak.");
        tree.setAdditionalInformation("You cann't pick up the tree but, you can cut it down with axe to get wood.");
        tree.Persistance(true);
        tree.Pickable(false);
        items.put("tree", tree);
        
        Item bird = new Item();
        bird.setDescription("It's a yellow bird. It's flying to and fro.");
        bird.Pickable(false);
        bird.setAdditionalInformation("You can't catch up bird because it's flying.");
        items.put("bird", bird);
        
        Item flower = new Item();
        flower.setDescription("It's rose and it's read.");
        flower.Pickable(true);
        items.put("flower", flower);
        
        LivingThings lion = new LivingThings();
        lion.setDescription("It's a strong lion. It's a guard for itemZ.");
        lion.Pickable(false);
        lion.setAdditionalInformation("You can't pick up a lion but, kill it to get itemZ.");
        lion.setKillingWeapon("knife");
        items.put("lion", lion);
        
        MissionItem itemZ = new MissionItem();
        itemZ.setDescription("It's a part of weaponX.");
        itemZ.Pickable(false);
        itemZ.setAdditionalInformation("Kill lion first to get itemZ.");
        itemZ.setRequirement("lion");
        items.put("itemZ", itemZ);
        
        MissionItem princess = new MissionItem();
        princess.setDescription("Hello prince, I'm waiting for you so long.");
        princess.Pickable(false);
        princess.setAdditionalInformation("You have to unlock first before saving princess.");
        princess.setRequirement("lock");
        items.put("princess", princess);
        
        Item lock = new Item();
        lock.setDescription("Find key to unlock.");
        lock.Pickable(false);
        lock.setAdditionalInformation("You can't pick up lock. Unlock it if you have key.");
        items.put("lock", lock);
        
        LivingThings witch = new LivingThings();
        witch.setDescription("Kill me if you can!!!");
        witch.Pickable(false);
        witch.setAdditionalInformation("You can't pick up a witch but you can kill her with WeaponX.");
        witch.setKillingWeapon("weaponX");
        items.put("witch", witch);
        
        MissionItem key = new MissionItem();
        key.setDescription("Key to unlock castle.");
        key.Pickable(false);
        key.setAdditionalInformation("Kill witch first to get key.");
        key.setRequirement("witch");
        items.put("key", key);
        
        CraftItem wood = new CraftItem();
        wood.setDescription("You need wood to craft boat. First pick up wood.");
        wood.Persistance(true);
        wood.Pickable(true);
        wood.setElement("axe", axe);
        wood.setElement("tree", tree);
        items.put("wood", wood);
        
        CraftItem boat = new CraftItem();
        boat.setDescription("You can't pass the lake if you don't have the boat.");
        boat.Pickable(true);
        boat.setElement("axe", axe);
        boat.setElement("wood", wood);
        items.put("boat", boat);
        
        CraftItem weaponX = new CraftItem();
        weaponX.setDescription("Powerful weapon to kill witch.");
        weaponX.Pickable(true);
        weaponX.setElement("itemY", itemY);
        weaponX.setElement("itemZ", itemZ);
        items.put("weaponX", weaponX);
   }

   public Prefab() {
    }
   
   public static boolean IsCraftItem(String id){
      if(!items.containsKey(id)) return false;
      Item i = items.get(id);
      return (i instanceof CraftItem);
   }
   public static Item instantiate(String id){
       Item itm = new Item();
       if(!items.containsKey(id)) return null;
       itm = items.get(id);
       return itm;
   } 
   
}
