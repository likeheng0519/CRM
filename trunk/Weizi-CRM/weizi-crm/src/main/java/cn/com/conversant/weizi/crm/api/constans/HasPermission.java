package cn.com.conversant.weizi.crm.api.constans;

/**
 * Created by conversant on 2017/1/11.
 */
public enum HasPermission {
    NoPermission(0),
    YesPermission(1);

    private int type;

    private HasPermission(int status) {
        this.type = status;
    }

    public int getCode() {
        return type;
    }
}
