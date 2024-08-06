package globalEntities.enums;

public enum TransactionStatus {
    CREATED("created"),
    PROCESSING("processing"),
    VERIFY("verify"),
    SETTLE_PENDING("settle_pending"),
    FAIL("fail"),
    SUCCESS("success");

    private final String statusName;

    TransactionStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
