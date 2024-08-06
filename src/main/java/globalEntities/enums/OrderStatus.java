package globalEntities.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum OrderStatus {
    CREATED("created", false),
    PROCESSING("processing", false),
    TREE_DS_VERIFY("3ds_verify", false),
    SETTLE_PENDING("settle_pending", false),
    AUTH_OK("auth_ok", false),
    AUTH_FAILED("auth_failed", true),
    VOID_OK("void_ok", true),
    APPROVED("approved", true),
    DECLINED("declined", true),
    SETTLE_OK("settle_ok", true),
    PARTIAL_SETTLED("partial_settled", true),
    REFUNDED("refunded", true);

    public String getName() {
        return name;
    }

    public boolean getIsFinalState() {
        return isFinalState;
    }

    private final String name;
    private final boolean isFinalState;

    OrderStatus(String name, boolean isFinalState) {
        this.name = name;
        this.isFinalState = isFinalState;
    }

    public static OrderStatus getOrderStatusByName(String name) {
        return Arrays.stream(OrderStatus.values())
                .filter(orderStatus -> orderStatus.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Order status name '" + name + "' is missing in OrderStatus enum."));
    }
}
