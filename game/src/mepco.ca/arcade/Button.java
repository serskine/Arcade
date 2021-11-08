package mepco.ca.arcade;

public enum Button {
    P1_UP('w'),
    P1_DOWN('x'),
    P1_LEFT('a'),
    P1_RIGHT('d'),
    P1_TOP_1('r'),
    P1_TOP_2('t'),
    P1_TOP_3('y'),
    P1_BOTTOM_1('f'),
    P1_BOTTOM_2('g'),
    P1_BOTTOM_3('h'),

    P2_UP('8'),
    P2_DOWN('4'),
    P2_LEFT('6'),
    P2_RIGHT('2'),
    P2_TOP_1('i'),
    P2_TOP_2('o'),
    P2_TOP_3('p'),
    P2_BOTTOM_1('k'),
    P2_BOTTOM_2('l'),
    P2_BOTTOM_3(';')

    ;

    public final Character defaultKey;

    private Button(Character c) {
        this.defaultKey = c;
    }
}
