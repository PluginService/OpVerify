package OpVerify;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main
  extends JavaPlugin
{
  public Permission playerPermission = new Permission("OpVerify.use", "OpVerify.verify");
  
  @Override
  public void onEnable()
  {
      System.out.println("[OpVerify] Do not use on FreeOP servers.");
    System.out.println("[OpVerify] Successfully enabled.");
    getServer().getPluginManager().registerEvents(new verify(), this);
    
    PluginManager pm = getServer().getPluginManager();
    pm.addPermission(this.playerPermission);
  }
  
  @Override
  public void onDisable() {}
  
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if ((cmd.getName().equalsIgnoreCase("OpVerify")) && ((sender instanceof Player)) && (p.hasPermission("OpVerify.verify")))
    {
      p.sendMessage(ChatColor.GREEN + "|------- OpVerify Help -------|");
      p.sendMessage(ChatColor.AQUA + "Write the password to get OP!");
      p.sendMessage(ChatColor.GREEN + "|---- Type password below ----|");
    }
    if ((cmd.getName().equalsIgnoreCase("OpVerify")) && ((sender instanceof Player)) && (!p.hasPermission("OpVerify.verify"))) {
      p.sendMessage(ChatColor.RED + "No Permission.");
    }
    return true;
  }
}
