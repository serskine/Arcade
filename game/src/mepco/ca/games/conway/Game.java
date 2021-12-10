package mepco.ca.games.conway;

import mepco.ca.util.Logger;

public class Game implements Runnable {
    public static final int ARG_PROPERTIES_FILE = 0;

    public static final Game INSTANCE = new Game();

    public static void main(String[] args) {
        if (args.length>0) {
            INSTANCE.setPropertiesFile(args[ARG_PROPERTIES_FILE]);
        }
        try {
            INSTANCE.run();
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }
    //-------------------------------------------------//
    private String propertiesFile;

    public Game() {
    }

    @Override
    public void run() {
        init();
    }

    public void init() {

    }

    public String getPropertiesFile() {
        return propertiesFile;
    }

    public void setPropertiesFile(String propertiesFile) {
        this.propertiesFile = propertiesFile;

    }
}
