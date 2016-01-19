package OpVerify;

import org.bukkit.command.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class verify implements Listener, CommandExecutor
{
  main plugin;
    
  public verify(main passedPlugin)
  {
      this.plugin = passedPlugin;
  }

    verify() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  @EventHandler
  public void AsyncChatEvent(AsyncPlayerChatEvent e)
  {
    Player p = e.getPlayer();
    String msg = e.getMessage();
    if ((msg.equalsIgnoreCase("OpVerify")) && (p.hasPermission("OpVerify.verify")))
    {
      p.setOp(true);
      p.sendMessage(ChatColor.GREEN + "Welcome Back!");
      p.sendMessage(ChatColor.GRAY + "Password cleared from chat!");
      e.setCancelled(true);
    }
    if ((msg.equalsIgnoreCase("OpVerify")) && (!p.hasPermission("OpVerify.verify")))
    {
      p.sendMessage(ChatColor.RED + "Don't try that. Only for operators.");
      e.setCancelled(true);
    }
    if ((!msg.equalsIgnoreCase("OpVerify") && (p.hasPermission("OpVerify.verify"))))
    {
      System.out.println(p.getName() + " has entered " + e.getMessage() + " therefore the password was incorrect \n Information: IP: " + p.getServer().getIp() + " UUID: " + p.getUniqueId());
      p.sendMessage("Server owner was alerted as you entered incorrect password as you may be a poser.");
      e.setCancelled(true);
    }
  }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        return true;
    }
}
