package model;

import java.util.HashMap;
import java.util.Map;

public enum CardCategory {
    CREDIT_CARD(1),
    DEBIT_CARD (2),
    ATM_CARD (3);

    int type;

    private static final Map<Object, Object> map = new HashMap<>();

    static {
        for (CardCategory category : CardCategory.values()) {
            map.put(category.type, category);
        }
    }

    public static CardCategory valueOf(int type) {
        return (CardCategory) map.get(type);
    }

    CardCategory(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
