package mepco.ca.arcade;

import java.util.Optional;

public enum ArcadeButton {
    P1_UP(38),                  // 38 'up arrow'
    P1_RIGHT(39),               // 39 'right arrow'
    P1_DOWN(40),                // 40 'down arrow'
    P1_LEFT(37),                // 37 'left arrow'
    P1_COIN_SLOT(67),           // 67 'c'
    P1_SELECT(53),              // 53 '5'
    P1_START(49),               // 49 '1'
    P1_TOP_1(17),               // 17 'left ctrl'
    P1_TOP_2(18),               // 18 'left alt'
    P1_TOP_3(32),               // 32 'space bar'
    P1_BOTTOM_1(16),            // 16 'left shift'
    P1_BOTTOM_2(90),            // 90 'z'
    P1_BOTTOM_3(88),            // 88 'x'
    P2_UP(82),                  // 82 'r'
    P2_RIGHT(71),               // 71 'g'
    P2_DOWN(70),                // 70 'f'
    P2_LEFT(68),                // 68 'd'
    P2_COIN_SLOT(53),           // 53 '5'
    P2_SELECT(54),              // 54 '6'
    P2_START(50),               // 50 '2'
    P2_TOP_1(65),               // 65 'a'
    P2_TOP_2(83),               // 83 's'
    P2_TOP_3(81),               // 81 'q'
    P2_BOTTOM_1(87),            // 87 'w'
    P2_BOTTOM_2(69),            // 69 'e'
    P2_BOTTOM_3(91),            // 91 '['
    LEFT_FLIPPER(51),           // 51 '3'
    RIGHT_FLIPPER(52),          // 52 '4'
    ESCAPE(27)                  // 27 'esc'
    ;

    public final int code;

    private ArcadeButton(int code) {
        this.code = code;
    }

    public static Optional<ArcadeButton> find(int code) {
        for(int i=0; i<ArcadeButton.values().length; i++) {
            ArcadeButton button = ArcadeButton.values()[i];
            if (button.code == code) {
                return Optional.of(button);
            }
        }
        return Optional.empty();
    }


}
