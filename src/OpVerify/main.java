package OpVerify;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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
    this.getConfig().addDefault("password", "password123");
    this.getConfig().options().copyDefaults(true);
    saveConfig();
    System.out.println("[OpVerify] Do not use on FreeOP servers.");
    System.out.println("[OpVerify] Successfully enabled.");
    getServer().getPluginManager().registerEvents(new verify(), this);
    
    PluginManager pm = getServer().getPluginManager();
    pm.addPermission(this.playerPermission);
  }
  
  @Override
  public void onDisable() 
  {
      saveConfig();
  }
  
  
  String password = getConfig().getString("password");
  @EventHandler
  public void onPlayerJoinEvent(PlayerJoinEvent e)
  {
      Player p = e.getPlayer();
      
      if (p.isOp() && p.hasPermission("OpVerify.verify"))
      {
          p.sendMessage("Welcome back " + p.getName() + " please follow the instructions below!");
          Bukkit.dispatchCommand(p, "OpVerify");
      }
  }
  
  @EventHandler
  public void AsyncChatEvent(AsyncPlayerChatEvent e)
  {
      Player p = e.getPlayer();
      String msg = e.getMessage();
      if (!p.isOp())
      {
          p.setOp(false);
          return;
      }
      if (!msg.equalsIgnoreCase(password));
      e.setCancelled(true);
      if (p.isOp() && (p.hasPermission("OpVerify.verify")))
      {
          p.setOp(false);
          Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "OpVerify" + ChatColor.GRAY + "]" + " " + ChatColor.RED + p.getName() + " was kicked as they entered the wrong password!");
          p.kickPlayer(ChatColor.RED + "You have entered: " + ChatColor.YELLOW + msg + ChatColor.RED + " and it was incorrect you are an imposer.");
      }
  }
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
