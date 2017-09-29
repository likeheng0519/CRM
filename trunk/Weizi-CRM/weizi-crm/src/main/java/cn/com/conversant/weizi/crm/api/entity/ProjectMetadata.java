package cn.com.conversant.weizi.crm.api.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Date;

/**
 * Created by conversant on 2017/1/4.
 */
public class ProjectMetadata  implements Serializable, Comparable<ProjectMetadata>{

    private	    long	id;
    private    String	name;
    private	    int	length;
    private    Date created;
    private    Date 	updated;
    private    String	f1_title;
    private    String	f2_title;
    private    String	f3_title;
    private    String	f4_title;
    private    String	f5_title;
    private    String	f6_title;
    private    String	f7_title;
    private    String	f8_title;
    private    String	f9_title;
    private    String	f10_title;
    private    String	f11_title;
    private    String	f12_title;
    private    String	f13_title;
    private    String	f14_title;
    private    String	f15_title;
    private    String	f16_title;
    private    String	f17_title;
    private    String	f18_title;
    private    String	f19_title;
    private    String	f20_title;
    private    String	f21_title;
    private    String	f22_title;
    private    String	f23_title;
    private    String	f24_title;
    private    String	f25_title;
    private    String	f26_title;
    private    String	f27_title;
    private    String	f28_title;
    private    String	f29_title;
    private    String	f30_title;
    private    String	f31_title;
    private    String	f32_title;
    private    String	f33_title;
    private    String	f34_title;
    private    String	f35_title;
    private    String	f36_title;
    private    String	f37_title;
    private    String	f38_title;
    private    String	f39_title;
    private    String	f40_title;
    private    String	f41_title;
    private    String	f42_title;
    private    String	f43_title;
    private    String	f44_title;
    private    String	f45_title;
    private    String	f46_title;
    private    String	f47_title;
    private    String	f48_title;
    private    String	f49_title;
    private    String	f50_title;
    private    String	f51_title;
    private    String	f52_title;
    private    String	f53_title;
    private    String	f54_title;
    private    String	f55_title;
    private    String	f56_title;
    private    String	f57_title;
    private    String	f58_title;
    private    String	f59_title;
    private    String	f60_title;
    private    String	f61_title;
    private    String	f62_title;
    private    String	f63_title;
    private    String	f64_title;
    private    String	f65_title;
    private    String	f66_title;
    private    String	f67_title;
    private    String	f68_title;
    private    String	f69_title;
    private    String	f70_title;
    private    String	f71_title;
    private    String	f72_title;
    private    String	f73_title;
    private    String	f74_title;
    private    String	f75_title;
    private    String	f76_title;
    private    String	f77_title;
    private    String	f78_title;
    private    String	f79_title;
    private    String	f80_title;
    private    String	f81_title;
    private    String	f82_title;
    private    String	f83_title;
    private    String	f84_title;
    private    String	f85_title;
    private    String	f86_title;
    private    String	f87_title;
    private    String	f88_title;
    private    String	f89_title;
    private    String	f90_title;
    private    String	f91_title;
    private    String	f92_title;
    private    String	f93_title;
    private    String	f94_title;
    private    String	f95_title;
    private    String	f96_title;
    private    String	f97_title;
    private    String	f98_title;
    private    String	f99_title;
    private    String	f100_title;
    private    String	f101_title;
    private    String	f102_title;
    private    String	f103_title;
    private    String	f104_title;
    private    String	f105_title;
    private    String	f106_title;
    private    String	f107_title;
    private    String	f108_title;
    private    String	f109_title;
    private    String	f110_title;
    private    String	f111_title;
    private    String	f112_title;
    private    String	f113_title;
    private    String	f114_title;
    private    String	f115_title;
    private    String	f116_title;
    private    String	f117_title;
    private    String	f118_title;
    private    String	f119_title;
    private    String	f120_title;
    private    String	f121_title;
    private    String	f122_title;
    private    String	f123_title;
    private    String	f124_title;
    private    String	f125_title;
    private    String	f126_title;
    private    String	f127_title;
    private    String	f128_title;
    private    String	f129_title;
    private    String	f130_title;
    private    String	f131_title;
    private    String	f132_title;
    private    String	f133_title;
    private    String	f134_title;
    private    String	f135_title;
    private    String	f136_title;
    private    String	f137_title;
    private    String	f138_title;
    private    String	f139_title;
    private    String	f140_title;
    private    String	f141_title;
    private    String	f142_title;
    private    String	f143_title;
    private    String	f144_title;
    private    String	f145_title;
    private    String	f146_title;
    private    String	f147_title;
    private    String	f148_title;
    private    String	f149_title;
    private    String	f150_title;

    public int compareTo(ProjectMetadata o) {
        if (this.id>o.id) {
            return -1;
        }
        if (this.id<o.id) {
            return 1;
        }
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getF1_title() {
        return f1_title;
    }

    public void setF1_title(String f1_title) {
        this.f1_title = f1_title;
    }

    public String getF2_title() {
        return f2_title;
    }

    public void setF2_title(String f2_title) {
        this.f2_title = f2_title;
    }

    public String getF3_title() {
        return f3_title;
    }

    public void setF3_title(String f3_title) {
        this.f3_title = f3_title;
    }

    public String getF4_title() {
        return f4_title;
    }

    public void setF4_title(String f4_title) {
        this.f4_title = f4_title;
    }

    public String getF5_title() {
        return f5_title;
    }

    public void setF5_title(String f5_title) {
        this.f5_title = f5_title;
    }

    public String getF6_title() {
        return f6_title;
    }

    public void setF6_title(String f6_title) {
        this.f6_title = f6_title;
    }

    public String getF7_title() {
        return f7_title;
    }

    public void setF7_title(String f7_title) {
        this.f7_title = f7_title;
    }

    public String getF8_title() {
        return f8_title;
    }

    public void setF8_title(String f8_title) {
        this.f8_title = f8_title;
    }

    public String getF9_title() {
        return f9_title;
    }

    public void setF9_title(String f9_title) {
        this.f9_title = f9_title;
    }

    public String getF10_title() {
        return f10_title;
    }

    public void setF10_title(String f10_title) {
        this.f10_title = f10_title;
    }

    public String getF11_title() {
        return f11_title;
    }

    public void setF11_title(String f11_title) {
        this.f11_title = f11_title;
    }

    public String getF12_title() {
        return f12_title;
    }

    public void setF12_title(String f12_title) {
        this.f12_title = f12_title;
    }

    public String getF13_title() {
        return f13_title;
    }

    public void setF13_title(String f13_title) {
        this.f13_title = f13_title;
    }

    public String getF14_title() {
        return f14_title;
    }

    public void setF14_title(String f14_title) {
        this.f14_title = f14_title;
    }

    public String getF15_title() {
        return f15_title;
    }

    public void setF15_title(String f15_title) {
        this.f15_title = f15_title;
    }

    public String getF16_title() {
        return f16_title;
    }

    public void setF16_title(String f16_title) {
        this.f16_title = f16_title;
    }

    public String getF17_title() {
        return f17_title;
    }

    public void setF17_title(String f17_title) {
        this.f17_title = f17_title;
    }

    public String getF18_title() {
        return f18_title;
    }

    public void setF18_title(String f18_title) {
        this.f18_title = f18_title;
    }

    public String getF19_title() {
        return f19_title;
    }

    public void setF19_title(String f19_title) {
        this.f19_title = f19_title;
    }

    public String getF20_title() {
        return f20_title;
    }

    public void setF20_title(String f20_title) {
        this.f20_title = f20_title;
    }

    public String getF21_title() {
        return f21_title;
    }

    public void setF21_title(String f21_title) {
        this.f21_title = f21_title;
    }

    public String getF22_title() {
        return f22_title;
    }

    public void setF22_title(String f22_title) {
        this.f22_title = f22_title;
    }

    public String getF23_title() {
        return f23_title;
    }

    public void setF23_title(String f23_title) {
        this.f23_title = f23_title;
    }

    public String getF24_title() {
        return f24_title;
    }

    public void setF24_title(String f24_title) {
        this.f24_title = f24_title;
    }

    public String getF25_title() {
        return f25_title;
    }

    public void setF25_title(String f25_title) {
        this.f25_title = f25_title;
    }

    public String getF26_title() {
        return f26_title;
    }

    public void setF26_title(String f26_title) {
        this.f26_title = f26_title;
    }

    public String getF27_title() {
        return f27_title;
    }

    public void setF27_title(String f27_title) {
        this.f27_title = f27_title;
    }

    public String getF28_title() {
        return f28_title;
    }

    public void setF28_title(String f28_title) {
        this.f28_title = f28_title;
    }

    public String getF29_title() {
        return f29_title;
    }

    public void setF29_title(String f29_title) {
        this.f29_title = f29_title;
    }

    public String getF30_title() {
        return f30_title;
    }

    public void setF30_title(String f30_title) {
        this.f30_title = f30_title;
    }

    public String getF31_title() {
        return f31_title;
    }

    public void setF31_title(String f31_title) {
        this.f31_title = f31_title;
    }

    public String getF32_title() {
        return f32_title;
    }

    public void setF32_title(String f32_title) {
        this.f32_title = f32_title;
    }

    public String getF33_title() {
        return f33_title;
    }

    public void setF33_title(String f33_title) {
        this.f33_title = f33_title;
    }

    public String getF34_title() {
        return f34_title;
    }

    public void setF34_title(String f34_title) {
        this.f34_title = f34_title;
    }

    public String getF35_title() {
        return f35_title;
    }

    public void setF35_title(String f35_title) {
        this.f35_title = f35_title;
    }

    public String getF36_title() {
        return f36_title;
    }

    public void setF36_title(String f36_title) {
        this.f36_title = f36_title;
    }

    public String getF37_title() {
        return f37_title;
    }

    public void setF37_title(String f37_title) {
        this.f37_title = f37_title;
    }

    public String getF38_title() {
        return f38_title;
    }

    public void setF38_title(String f38_title) {
        this.f38_title = f38_title;
    }

    public String getF39_title() {
        return f39_title;
    }

    public void setF39_title(String f39_title) {
        this.f39_title = f39_title;
    }

    public String getF40_title() {
        return f40_title;
    }

    public void setF40_title(String f40_title) {
        this.f40_title = f40_title;
    }

    public String getF41_title() {
        return f41_title;
    }

    public void setF41_title(String f41_title) {
        this.f41_title = f41_title;
    }

    public String getF42_title() {
        return f42_title;
    }

    public void setF42_title(String f42_title) {
        this.f42_title = f42_title;
    }

    public String getF43_title() {
        return f43_title;
    }

    public void setF43_title(String f43_title) {
        this.f43_title = f43_title;
    }

    public String getF44_title() {
        return f44_title;
    }

    public void setF44_title(String f44_title) {
        this.f44_title = f44_title;
    }

    public String getF45_title() {
        return f45_title;
    }

    public void setF45_title(String f45_title) {
        this.f45_title = f45_title;
    }

    public String getF46_title() {
        return f46_title;
    }

    public void setF46_title(String f46_title) {
        this.f46_title = f46_title;
    }

    public String getF47_title() {
        return f47_title;
    }

    public void setF47_title(String f47_title) {
        this.f47_title = f47_title;
    }

    public String getF48_title() {
        return f48_title;
    }

    public void setF48_title(String f48_title) {
        this.f48_title = f48_title;
    }

    public String getF49_title() {
        return f49_title;
    }

    public void setF49_title(String f49_title) {
        this.f49_title = f49_title;
    }

    public String getF50_title() {
        return f50_title;
    }

    public void setF50_title(String f50_title) {
        this.f50_title = f50_title;
    }

    public String getF51_title() {
        return f51_title;
    }

    public void setF51_title(String f51_title) {
        this.f51_title = f51_title;
    }

    public String getF52_title() {
        return f52_title;
    }

    public void setF52_title(String f52_title) {
        this.f52_title = f52_title;
    }

    public String getF53_title() {
        return f53_title;
    }

    public void setF53_title(String f53_title) {
        this.f53_title = f53_title;
    }

    public String getF54_title() {
        return f54_title;
    }

    public void setF54_title(String f54_title) {
        this.f54_title = f54_title;
    }

    public String getF55_title() {
        return f55_title;
    }

    public void setF55_title(String f55_title) {
        this.f55_title = f55_title;
    }

    public String getF56_title() {
        return f56_title;
    }

    public void setF56_title(String f56_title) {
        this.f56_title = f56_title;
    }

    public String getF57_title() {
        return f57_title;
    }

    public void setF57_title(String f57_title) {
        this.f57_title = f57_title;
    }

    public String getF58_title() {
        return f58_title;
    }

    public void setF58_title(String f58_title) {
        this.f58_title = f58_title;
    }

    public String getF59_title() {
        return f59_title;
    }

    public void setF59_title(String f59_title) {
        this.f59_title = f59_title;
    }

    public String getF60_title() {
        return f60_title;
    }

    public void setF60_title(String f60_title) {
        this.f60_title = f60_title;
    }

    public String getF61_title() {
        return f61_title;
    }

    public void setF61_title(String f61_title) {
        this.f61_title = f61_title;
    }

    public String getF62_title() {
        return f62_title;
    }

    public void setF62_title(String f62_title) {
        this.f62_title = f62_title;
    }

    public String getF63_title() {
        return f63_title;
    }

    public void setF63_title(String f63_title) {
        this.f63_title = f63_title;
    }

    public String getF64_title() {
        return f64_title;
    }

    public void setF64_title(String f64_title) {
        this.f64_title = f64_title;
    }

    public String getF65_title() {
        return f65_title;
    }

    public void setF65_title(String f65_title) {
        this.f65_title = f65_title;
    }

    public String getF66_title() {
        return f66_title;
    }

    public void setF66_title(String f66_title) {
        this.f66_title = f66_title;
    }

    public String getF67_title() {
        return f67_title;
    }

    public void setF67_title(String f67_title) {
        this.f67_title = f67_title;
    }

    public String getF68_title() {
        return f68_title;
    }

    public void setF68_title(String f68_title) {
        this.f68_title = f68_title;
    }

    public String getF69_title() {
        return f69_title;
    }

    public void setF69_title(String f69_title) {
        this.f69_title = f69_title;
    }

    public String getF70_title() {
        return f70_title;
    }

    public void setF70_title(String f70_title) {
        this.f70_title = f70_title;
    }

    public String getF71_title() {
        return f71_title;
    }

    public void setF71_title(String f71_title) {
        this.f71_title = f71_title;
    }

    public String getF72_title() {
        return f72_title;
    }

    public void setF72_title(String f72_title) {
        this.f72_title = f72_title;
    }

    public String getF73_title() {
        return f73_title;
    }

    public void setF73_title(String f73_title) {
        this.f73_title = f73_title;
    }

    public String getF74_title() {
        return f74_title;
    }

    public void setF74_title(String f74_title) {
        this.f74_title = f74_title;
    }

    public String getF75_title() {
        return f75_title;
    }

    public void setF75_title(String f75_title) {
        this.f75_title = f75_title;
    }

    public String getF76_title() {
        return f76_title;
    }

    public void setF76_title(String f76_title) {
        this.f76_title = f76_title;
    }

    public String getF77_title() {
        return f77_title;
    }

    public void setF77_title(String f77_title) {
        this.f77_title = f77_title;
    }

    public String getF78_title() {
        return f78_title;
    }

    public void setF78_title(String f78_title) {
        this.f78_title = f78_title;
    }

    public String getF79_title() {
        return f79_title;
    }

    public void setF79_title(String f79_title) {
        this.f79_title = f79_title;
    }

    public String getF80_title() {
        return f80_title;
    }

    public void setF80_title(String f80_title) {
        this.f80_title = f80_title;
    }

    public String getF81_title() {
        return f81_title;
    }

    public void setF81_title(String f81_title) {
        this.f81_title = f81_title;
    }

    public String getF82_title() {
        return f82_title;
    }

    public void setF82_title(String f82_title) {
        this.f82_title = f82_title;
    }

    public String getF83_title() {
        return f83_title;
    }

    public void setF83_title(String f83_title) {
        this.f83_title = f83_title;
    }

    public String getF84_title() {
        return f84_title;
    }

    public void setF84_title(String f84_title) {
        this.f84_title = f84_title;
    }

    public String getF85_title() {
        return f85_title;
    }

    public void setF85_title(String f85_title) {
        this.f85_title = f85_title;
    }

    public String getF86_title() {
        return f86_title;
    }

    public void setF86_title(String f86_title) {
        this.f86_title = f86_title;
    }

    public String getF87_title() {
        return f87_title;
    }

    public void setF87_title(String f87_title) {
        this.f87_title = f87_title;
    }

    public String getF88_title() {
        return f88_title;
    }

    public void setF88_title(String f88_title) {
        this.f88_title = f88_title;
    }

    public String getF89_title() {
        return f89_title;
    }

    public void setF89_title(String f89_title) {
        this.f89_title = f89_title;
    }

    public String getF90_title() {
        return f90_title;
    }

    public void setF90_title(String f90_title) {
        this.f90_title = f90_title;
    }

    public String getF91_title() {
        return f91_title;
    }

    public void setF91_title(String f91_title) {
        this.f91_title = f91_title;
    }

    public String getF92_title() {
        return f92_title;
    }

    public void setF92_title(String f92_title) {
        this.f92_title = f92_title;
    }

    public String getF93_title() {
        return f93_title;
    }

    public void setF93_title(String f93_title) {
        this.f93_title = f93_title;
    }

    public String getF94_title() {
        return f94_title;
    }

    public void setF94_title(String f94_title) {
        this.f94_title = f94_title;
    }

    public String getF95_title() {
        return f95_title;
    }

    public void setF95_title(String f95_title) {
        this.f95_title = f95_title;
    }

    public String getF96_title() {
        return f96_title;
    }

    public void setF96_title(String f96_title) {
        this.f96_title = f96_title;
    }

    public String getF97_title() {
        return f97_title;
    }

    public void setF97_title(String f97_title) {
        this.f97_title = f97_title;
    }

    public String getF98_title() {
        return f98_title;
    }

    public void setF98_title(String f98_title) {
        this.f98_title = f98_title;
    }

    public String getF99_title() {
        return f99_title;
    }

    public void setF99_title(String f99_title) {
        this.f99_title = f99_title;
    }

    public String getF100_title() {
        return f100_title;
    }

    public void setF100_title(String f100_title) {
        this.f100_title = f100_title;
    }

    public String getF101_title() {
        return f101_title;
    }

    public void setF101_title(String f101_title) {
        this.f101_title = f101_title;
    }

    public String getF102_title() {
        return f102_title;
    }

    public void setF102_title(String f102_title) {
        this.f102_title = f102_title;
    }

    public String getF103_title() {
        return f103_title;
    }

    public void setF103_title(String f103_title) {
        this.f103_title = f103_title;
    }

    public String getF104_title() {
        return f104_title;
    }

    public void setF104_title(String f104_title) {
        this.f104_title = f104_title;
    }

    public String getF105_title() {
        return f105_title;
    }

    public void setF105_title(String f105_title) {
        this.f105_title = f105_title;
    }

    public String getF106_title() {
        return f106_title;
    }

    public void setF106_title(String f106_title) {
        this.f106_title = f106_title;
    }

    public String getF107_title() {
        return f107_title;
    }

    public void setF107_title(String f107_title) {
        this.f107_title = f107_title;
    }

    public String getF108_title() {
        return f108_title;
    }

    public void setF108_title(String f108_title) {
        this.f108_title = f108_title;
    }

    public String getF109_title() {
        return f109_title;
    }

    public void setF109_title(String f109_title) {
        this.f109_title = f109_title;
    }

    public String getF110_title() {
        return f110_title;
    }

    public void setF110_title(String f110_title) {
        this.f110_title = f110_title;
    }

    public String getF111_title() {
        return f111_title;
    }

    public void setF111_title(String f111_title) {
        this.f111_title = f111_title;
    }

    public String getF112_title() {
        return f112_title;
    }

    public void setF112_title(String f112_title) {
        this.f112_title = f112_title;
    }

    public String getF113_title() {
        return f113_title;
    }

    public void setF113_title(String f113_title) {
        this.f113_title = f113_title;
    }

    public String getF114_title() {
        return f114_title;
    }

    public void setF114_title(String f114_title) {
        this.f114_title = f114_title;
    }

    public String getF115_title() {
        return f115_title;
    }

    public void setF115_title(String f115_title) {
        this.f115_title = f115_title;
    }

    public String getF116_title() {
        return f116_title;
    }

    public void setF116_title(String f116_title) {
        this.f116_title = f116_title;
    }

    public String getF117_title() {
        return f117_title;
    }

    public void setF117_title(String f117_title) {
        this.f117_title = f117_title;
    }

    public String getF118_title() {
        return f118_title;
    }

    public void setF118_title(String f118_title) {
        this.f118_title = f118_title;
    }

    public String getF119_title() {
        return f119_title;
    }

    public void setF119_title(String f119_title) {
        this.f119_title = f119_title;
    }

    public String getF120_title() {
        return f120_title;
    }

    public void setF120_title(String f120_title) {
        this.f120_title = f120_title;
    }

    public String getF121_title() {
        return f121_title;
    }

    public void setF121_title(String f121_title) {
        this.f121_title = f121_title;
    }

    public String getF122_title() {
        return f122_title;
    }

    public void setF122_title(String f122_title) {
        this.f122_title = f122_title;
    }

    public String getF123_title() {
        return f123_title;
    }

    public void setF123_title(String f123_title) {
        this.f123_title = f123_title;
    }

    public String getF124_title() {
        return f124_title;
    }

    public void setF124_title(String f124_title) {
        this.f124_title = f124_title;
    }

    public String getF125_title() {
        return f125_title;
    }

    public void setF125_title(String f125_title) {
        this.f125_title = f125_title;
    }

    public String getF126_title() {
        return f126_title;
    }

    public void setF126_title(String f126_title) {
        this.f126_title = f126_title;
    }

    public String getF127_title() {
        return f127_title;
    }

    public void setF127_title(String f127_title) {
        this.f127_title = f127_title;
    }

    public String getF128_title() {
        return f128_title;
    }

    public void setF128_title(String f128_title) {
        this.f128_title = f128_title;
    }

    public String getF129_title() {
        return f129_title;
    }

    public void setF129_title(String f129_title) {
        this.f129_title = f129_title;
    }

    public String getF130_title() {
        return f130_title;
    }

    public void setF130_title(String f130_title) {
        this.f130_title = f130_title;
    }

    public String getF131_title() {
        return f131_title;
    }

    public void setF131_title(String f131_title) {
        this.f131_title = f131_title;
    }

    public String getF132_title() {
        return f132_title;
    }

    public void setF132_title(String f132_title) {
        this.f132_title = f132_title;
    }

    public String getF133_title() {
        return f133_title;
    }

    public void setF133_title(String f133_title) {
        this.f133_title = f133_title;
    }

    public String getF134_title() {
        return f134_title;
    }

    public void setF134_title(String f134_title) {
        this.f134_title = f134_title;
    }

    public String getF135_title() {
        return f135_title;
    }

    public void setF135_title(String f135_title) {
        this.f135_title = f135_title;
    }

    public String getF136_title() {
        return f136_title;
    }

    public void setF136_title(String f136_title) {
        this.f136_title = f136_title;
    }

    public String getF137_title() {
        return f137_title;
    }

    public void setF137_title(String f137_title) {
        this.f137_title = f137_title;
    }

    public String getF138_title() {
        return f138_title;
    }

    public void setF138_title(String f138_title) {
        this.f138_title = f138_title;
    }

    public String getF139_title() {
        return f139_title;
    }

    public void setF139_title(String f139_title) {
        this.f139_title = f139_title;
    }

    public String getF140_title() {
        return f140_title;
    }

    public void setF140_title(String f140_title) {
        this.f140_title = f140_title;
    }

    public String getF141_title() {
        return f141_title;
    }

    public void setF141_title(String f141_title) {
        this.f141_title = f141_title;
    }

    public String getF142_title() {
        return f142_title;
    }

    public void setF142_title(String f142_title) {
        this.f142_title = f142_title;
    }

    public String getF143_title() {
        return f143_title;
    }

    public void setF143_title(String f143_title) {
        this.f143_title = f143_title;
    }

    public String getF144_title() {
        return f144_title;
    }

    public void setF144_title(String f144_title) {
        this.f144_title = f144_title;
    }

    public String getF145_title() {
        return f145_title;
    }

    public void setF145_title(String f145_title) {
        this.f145_title = f145_title;
    }

    public String getF146_title() {
        return f146_title;
    }

    public void setF146_title(String f146_title) {
        this.f146_title = f146_title;
    }

    public String getF147_title() {
        return f147_title;
    }

    public void setF147_title(String f147_title) {
        this.f147_title = f147_title;
    }

    public String getF148_title() {
        return f148_title;
    }

    public void setF148_title(String f148_title) {
        this.f148_title = f148_title;
    }

    public String getF149_title() {
        return f149_title;
    }

    public void setF149_title(String f149_title) {
        this.f149_title = f149_title;
    }

    public String getF150_title() {
        return f150_title;
    }

    public void setF150_title(String f150_title) {
        this.f150_title = f150_title;
    }
}
