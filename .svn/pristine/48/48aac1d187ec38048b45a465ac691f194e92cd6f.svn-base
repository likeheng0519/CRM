package cn.com.conversant.weizi.crm.api.constans;

/**
 * Created by conversant on 2017/1/11.
 */
public enum AppealStatus {
    /*1:等待处理 2:已经处理 3:申诉资料不全，不予处理 4:数据无误，无需更改*/
    DELETED(0),
    WAITING(1),
    DONE(2),
    NODATA(3),
    HASDATA(4);

    private int type;

    private AppealStatus(int status) {
        this.type = status;
    }

    public int getCode() {
        return type;
    }
}
