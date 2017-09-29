package cn.com.conversant.weizi.crm.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by conversant on 2017/1/17.
 */
public class FtpStoreFieldsJson implements Serializable {
    private String store_id;
    private List<String> field1=new ArrayList<String>();
    private List<String> field2=new ArrayList<String>();
    private List<String> field3=new ArrayList<String>();
    private List<String> field4=new ArrayList<String>();
    private List<String> field5=new ArrayList<String>();
    private List<String> field6=new ArrayList<String>();


    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public List<String> getField1() {
        return field1;
    }

    public void setField1(List<String> field1) {
        this.field1 = field1;
    }

    public List<String> getField2() {
        return field2;
    }

    public void setField2(List<String> field2) {
        this.field2 = field2;
    }

    public List<String> getField3() {
        return field3;
    }

    public void setField3(List<String> field3) {
        this.field3 = field3;
    }

    public List<String> getField4() {
        return field4;
    }

    public void setField4(List<String> field4) {
        this.field4 = field4;
    }

    public List<String> getField5() {
        return field5;
    }

    public void setField5(List<String> field5) {
        this.field5 = field5;
    }

    public List<String> getField6() {
        return field6;
    }

    public void setField6(List<String> field6) {
        this.field6 = field6;
    }
}
