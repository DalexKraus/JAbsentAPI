package at.thejano.absentapi;

import java.util.UUID;

public class JAbsentAPI {

    private static Main pluginInstance;

    JAbsentAPI(Main instance) {
        pluginInstance = instance;
    }

    /**
     * Returns the time in seconds the specified player is afk
     *
     * @param playerId The Player
     * @return The time in seconds
     */
    public static int getAFKTime(UUID playerId) {
        return pluginInstance.allAfkPlayers.get(playerId);
    }
}
