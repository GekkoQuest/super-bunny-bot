package quest.gekko.superbunnybot;

public class SuperBunnyBotLauncher {

    public static void main(String[] args) {
        final SuperBunnyBot bot = new SuperBunnyBot();
        bot.registerListeners();
        bot.registerServices();
        bot.registerCommands();
        bot.start();
    }

}