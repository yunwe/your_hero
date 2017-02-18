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
class Location {

    int x;
    int y;

    Location(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public boolean IsValid() {
        return (x >= 0 && x < 2) && (y >= 0 && y < 3);
    }

    public Location Add(Location b) {
        return new Location(x + b.x, y + b.y);
    }
}

class Game {

    public static final Location NORTH = new Location(0, -1);
    public static final Location EAST = new Location(1, 0);
    public static final Location SOUTH = new Location(0, 1);
    public static final Location WEST = new Location(-1, 0);

    private Player ashely;
//    private Item[] items = new Item[3];
    private Location place;
    public static Room[][] map = new Room[2][3];
    public boolean clrscr;
    public boolean gameend;

    private static Game obj;

    public static Game game() {
        if (obj == null) {
            obj = new Game();
        }
        return obj;
    }

    /**
     * @param args the command line arguments
     */
    private String GameEnd() {
        gameend = true;
        clrscr = true;
        return "<html><head></head><body text = \"RED\"><H3 align = center>Congratulation!!!</H3> </body></html>";
    }

    private void _init() {
        ashely = new Player();
        clrscr = false;
        gameend = false;

        Room palace = new Room("Palace", new Location(0, 0));
        Room green_forest = new Room("green_forest", new Location(0, 1));
        Room lake = new Room("lake", new Location(1, 0));
        Room wild_forest = new Room("wild_forest", new Location(1, 1));
        Room castle = new Room("castle", new Location(1, 2));
        Room cave = new Room("cave", new Location(0, 2));

        palace.setItem("knife");
        palace.setItem("gold");
        palace.setItem("axe");

        lake.setItem("island");
        lake.setItem("itemY");

        green_forest.setItem("tree");
        green_forest.setItem("bird");
        green_forest.setItem("flower");

        wild_forest.setItem("lion");
        wild_forest.setItem("itemZ");

        castle.setItem("princess");
        castle.setItem("lock");

        cave.setItem("witch");
        cave.setItem("key");

        map[0][0] = palace;
        map[0][1] = green_forest;
        map[0][2] = cave;
        map[1][0] = lake;
        map[1][1] = wild_forest;
        map[1][2] = castle;

        place = new Location(0, 0);
    }

    public String startGame() {
        Prefab._init();
        _init();
        return map[place.x][place.y].getDescription().trim();
    }

    public String Unlock() {
        String temp = map[place.x][place.y].lookItem("lock");
        if (temp == null) {
            return "There is no lock here to unlock.";
        }
        temp = ashely.lookItem("key");
        if (temp == null) {
            return "You have no key to unlock.";
        }
        ashely.setAchievement("lock");
        map[place.x][place.y].removeItem("lock");
        return "Successfully unlocked.";
    }

    public String Look(String obj) {
        if (obj.equalsIgnoreCase("pocket")) {
            if (ashely.listItems().length() <= 0) {
                return "There is nothing in your pocket!";
            }
            return "You have " + ashely.listItems() + ".";
        }
        String temp = null;
        temp = map[place.x][place.y].lookItem(obj);
        if (temp == null) {
            temp = ashely.lookItem(obj);
        }
        if (temp == null) {
            temp = "There is no object named " + obj + ".";
        }
        return temp;

    }

    public String Look() {
        return map[place.x][place.y].getDescription();
    }

    public String Drop(String obj) {
        Item itm = ashely.dropItem(obj);
        if (itm == null) {
            String s = "Invalid item.";
            if (ashely.listItems().length() > 0) {
                s += "You have " + ashely.listItems() + ".";
            }
            return s;
        }
        map[place.x][place.y].setItem(obj, itm);
        return "You dropped " + obj + ".";
    }

    public String Pick(String obj) {
        if (ashely.pocketFull()) {
            return "You've reached maximum limit. You have " + ashely.listItems() + ".\n Drop some items inorder to free up some spaces.";
        }
        Item itm = map[place.x][place.y].grabItem(obj);
        if (itm != null) {
            ashely.pickItem(obj, itm);
            return "Successfully added " + obj + ".";
        } else if (map[place.x][place.y].lookItem(obj) == null) {
            return "There is no item as " + obj + ".";
        } else {
            itm = map[place.x][place.y].copyItem(obj);
            if (itm.isMissionary()) {
                String n = ((MissionItem) itm).getRequirement();
                if (ashely.has(n) || ashely.Achieved(n)) {
                    ashely.pickItem(obj, itm);
                    map[place.x][place.y].removeItem(obj);
                    if (obj.equalsIgnoreCase("princess")) {
                        return GameEnd();
                    }
                    return "Successfully added " + obj + ".";
                }
            }
            return map[place.x][place.y].getItemAdditionalInformation(obj);
        }
    }

    public String Go(String direction) {
        Location dir;
        if (direction.equalsIgnoreCase("north")) {
            dir = NORTH;
        } else if (direction.equalsIgnoreCase("east")) {
            dir = EAST;
        } else if (direction.equalsIgnoreCase("south")) {
            dir = SOUTH;
        } else if (direction.equalsIgnoreCase("west")) {
            dir = WEST;
        } else {
            dir = new Location(-10, -10);
        }

        Location next_place = place.Add(dir);
        if (!next_place.IsValid()) {
            return "Invalid direction. There is no exit at " + direction;
        }
        clrscr = true;
        place = next_place;
        return map[place.x][place.y].getDescription();
    }

    public String Kill(String obj) {
        Item victim = map[place.x][place.y].copyItem(obj);
        if (victim == null) {
            return "There is no object named " + obj + " here to kill.";
        }
        if (!victim.killable) {
            return "You can't kill " + obj + ".";
        } else {
            if (ashely.has(((LivingThings) victim).getKillingWeapon())) {
                map[place.x][place.y].removeItem(obj);
                ashely.setAchievement(obj);
                return "Successfully killed " + obj + ".";
            } else {
                return "You can't kill " + obj + " without " + ((LivingThings) victim).getKillingWeapon() + ".";
            }
        }

    }

    public String Craft(String obj) {
        if (!Prefab.IsCraftItem(obj)) {
            return "Invalid Item Name!!!";
        }
        CraftItem ci = (CraftItem) Prefab.instantiate(obj);
        HashMap<String, Item> hm = ci.getElements();
        for (String s : hm.keySet()) {
            if (hm.get(s).IsPersistacne()) {
                if (map[place.x][place.y].lookItem(s) == null) {
                    return "There is no " + s + " in this place.";
                }
            } else {
                if (!ashely.has(s)) {
                    return "You need " + s + " to craft " + obj + ".";
                }
            }
        }
        for (String s : hm.keySet()) {
            if (hm.get(s).IsReusable()) {
                continue;
            }
            if (hm.get(s).IsPersistacne()) {
                map[place.x][place.y].removeItem(s);
            } else {
                ashely.dropItem(s);
            }
        }
        map[place.x][place.y].setItem(obj, ci);
        return "Successfully crafted " + obj;
    }

    public String help() {
        clrscr = true;
        return "Help!\n"
                + "- command must be used with the format <command> <object name>\n"
                + "_ You can get the detail information of a command by typing <command>?\n"
                + "_ For Example type 'look?'\n"
                + "- but command are not case sensitive\n"
                + "- For Example type 'look knife' to look the knife.\n"
                + "- Availabel command are:\n"
                + "\t- go\n"
                + "\t- look\n"
                + "\t- pick\n"
                + "\t- drop\n"
                + "\t- craft\n"
                + "\t- kill\n"
                + "- Some can be use with single word such as :\n"
                + "\t- look\n"
                + "\t- unlock\n"
                + "\t- clear\n"
                + "\t- help\n"
                + "\t- End";
    }

    public String detail_help(String cmd) {
        String output = "Invalid command. Please see help!";
        if (cmd.equalsIgnoreCase("look?")) {
            output = "- Type 'look <object>' to look detail of object.\n"
                    + "- For example : look knife\n"
                    + "- Type 'look' to look around the current place.\n"
                    + "- Type 'look pocket' to look what is in your pocket.";
        } else if (cmd.equalsIgnoreCase("drop?")) {
            output = "- Type 'drop <object>' to remove the object form your pocket.\n"
                    + "- For example : drop knife";
        } else if (cmd.equalsIgnoreCase("pick?")) {
            output = "- Type 'pick <object>' to pick up the object.\n"
                    + "- For example : pick knife";
        } else if (cmd.equalsIgnoreCase("go?")) {
            output = "- Type 'go <direction>' to go to that direction.\n"
                    + "- For example : go east";
        } else if (cmd.equalsIgnoreCase("kill?")) {
            output = "- Type 'kill <object>' to kill that object.\n"
                    + "- For example : kill tiger";
        } else if (cmd.equalsIgnoreCase("craft?")) {
            output = "- Type 'craft <object>' to craft the object.\n"
                    + "- For example : craft boat - to get the boat.";
        } else if (cmd.equalsIgnoreCase("unlock?")) {
            output = "- Type 'unlock' to unlock the lock.";
        } else if (cmd.equalsIgnoreCase("clear?")) {
            output = "- Type 'clear' to clear the screen.";
        } else if (cmd.equalsIgnoreCase("end?")) {
            output = "- Type 'end' to exit from the game.";
        }
        return output;
    }
    
    public String clear(){
        clrscr = true;
        return "";
    }

    public String Compile(String cmd) {
        cmd = cmd.trim();
        String c = "";
        String obj = "";
        String output = "Invalid command. Please see help!";
        int _space = cmd.indexOf(" ");
        if (_space != -1) {
            c = cmd.substring(0, _space).trim();
            obj = cmd.substring(_space + 1).trim();
            if (c.equalsIgnoreCase("look")) {
                output = Look(obj);
            } else if (c.equalsIgnoreCase("drop")) {
                output = Drop(obj);
            } else if (c.equalsIgnoreCase("pick")) {
                output = Pick(obj);
            } else if (c.equalsIgnoreCase("go")) {
                output = Go(obj);
            } else if (c.equalsIgnoreCase("kill")) {
                output = Kill(obj);
            } else if (c.equalsIgnoreCase("craft")) {
                output = Craft(obj);
            }

        } else if (cmd.endsWith("?")) {
            output = detail_help(cmd);
        } else if (cmd.equalsIgnoreCase("look")) {
            output = Look();
        } else if (cmd.equalsIgnoreCase("unlock")) {
            output = Unlock();
        } else if (cmd.equalsIgnoreCase("help")) {
            output = help();
        }else if (cmd.equals("clear")) {
            output = clear();
        } 
        else if (cmd.equals("end")) {
            System.exit(0);
        }
        return output;
    }
}
