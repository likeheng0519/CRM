package cn.com.conversant.weizi.crm.api.constans;

/**
 * Created by conversant on 2017/1/11.
 */
public enum UserType {

    ADMINISTRATOR(0),
    CUSTOMER(1),
    SALES_MANAGER(2),
    SALES_REPRESENT(3);

    private int type;

    private UserType(int status) {
        this.type = status;
    }

    public int getCode() {
        return type;
    }
}
