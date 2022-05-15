package tests.com.dinogame.model;

import com.dinogame.Config;
import com.dinogame.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
    GameModel model;

    @BeforeEach
    void setUp() {
        model = new GameModel();
    }

    @Test
    void checkDinoJump() {
        model.update(0, true);
        assertEquals(Config.JUMP_POWER - Config.GRAVITY, model.getHero().getVelocity().y);
    }

    @Test
    void testStartGame() {
        assertEquals(GameModel.GameState.START, model.getGameState());
        model.update(0, true);
        assertEquals(GameModel.GameState.RUN, model.getGameState());
    }
}