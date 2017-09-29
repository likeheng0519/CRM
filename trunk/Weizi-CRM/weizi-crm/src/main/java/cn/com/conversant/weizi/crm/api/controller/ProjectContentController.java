package cn.com.conversant.weizi.crm.api.controller;

import cn.com.conversant.weizi.crm.api.constans.APIRequestUrl;
import cn.com.conversant.weizi.crm.api.constans.UserType;
import cn.com.conversant.weizi.crm.api.dto.FtpStoreFieldsJson;
import cn.com.conversant.weizi.crm.api.entity.ProjectContent;
import cn.com.conversant.weizi.crm.api.entity.ProjectMetadata;
import cn.com.conversant.weizi.crm.api.entity.ProjectSearchCondition;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.mvc.UserRequired;
import cn.com.conversant.weizi.crm.api.service.*;
import cn.com.conversant.weizi.crm.commons.CommonUtil.ConverEncoding;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by conversant on 2017/1/4.
 */
@Controller
public class ProjectContentController {
    @Value("${content.storage.base}")
    private String storageBase;
    @Resource
    private UserService userService;
    @Resource
    private ContentService contentService;
    @Resource
    private MetadataService metadataService;
    @Resource
    private SearchConditionService searchConditionService;
    @Resource
    private UserAppealService userAppealService;

    private Logger logger = Logger.getLogger(ProjectContentController.class);

    static int getTotalLines(File file) throws IOException {
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }

    @RequestMapping(value = APIRequestUrl.PROJECT_CONTENT_IMPORT, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<String, String> importContent(HttpServletRequest request,
                                             @UserRequired User admin,
                                             @RequestParam(value="file_upload", required = true) MultipartFile file_upload,
                                             @RequestParam(value="metadata_id", required = true)String metadata_id){

        HashMap retmap=new HashMap<String, String>();
        //接收参数信息
        logger.info("APIRequestUrl.PROJECT_CONTENT_IMPORT:");

        final String[] FILE_HEADER = {"id","project_metadata_id","created","f1","f2","f3","f4","f5",
                "f6","f7","f8","f9","f10","f11","f12","f13","f14","f15","f16","f17","f18","f19","f20",
                "f21","f22","f23","f24","f25","f26","f27","f28","f29","f30","f31","f32","f33","f34",
                "f35","f36","f37","f38","f39","f40","f41","f42","f43","f44","f45","f46","f47","f48",
                "f49","f50","f51","f52","f53","f54","f55","f56","f57","f58","f59","f60","f61","f62",
                "f63","f64","f65","f66","f67","f68","f69","f70","f71","f72","f73","f74","f75","f76",
                "f77","f78","f79","f80","f81","f82","f83","f84","f85","f86","f87","f88","f89","f90",
                "f91","f92","f93","f94","f95","f96","f97","f98","f99","f100","f101","f102","f103",
                "f104","f105","f106","f107","f108","f109","f110","f111","f112","f113","f114",
                "f115","f116","f117","f118","f119","f120","f121","f122","f123","f124","f125",
                "f126","f127","f128","f129","f130","f131","f132","f133","f134","f135","f136",
                "f137","f138","f139","f140","f141","f142","f143","f144","f145","f146","f147",
                "f148","f149","f150"};

        List<ProjectContent> projectContentList = new ArrayList<ProjectContent>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String filename1=storageBase+String.valueOf(Calendar.getInstance().get(Calendar.HOUR)+Calendar.getInstance().get(Calendar.MINUTE)+Calendar.getInstance().get(Calendar.SECOND));
        File tempfile = null;
        OutputStream os = null;
        Reader read_one =null;
        FileOutputStream fileOutputStream=null;
        try{
            //csv转换成bean
            fileOutputStream=new FileOutputStream(filename1);
            os = new BufferedOutputStream(fileOutputStream);
            IOUtils.copy(file_upload.getInputStream(), os);
            os.flush();
            try {
                if(os!=null) {
                    IOUtils.closeQuietly(os);
                }
                fileOutputStream.close();

            }catch (IOException ioexception){
                ioexception.printStackTrace();
                logger.error(APIRequestUrl.PROJECT_CONTENT_IMPORT+"import project content data error."+":"+ioexception.getMessage());
            }
            String filecode = ConverEncoding.codeString(filename1);
            if (!filecode.equals(ConverEncoding.CODE)) {
                ConverEncoding.convert(filename1, filecode, filename1, ConverEncoding.CODE);
            }
            CSVFormat header = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
            tempfile=new File(filename1);
            int i_line=getTotalLines(tempfile);
            read_one = new FileReader(tempfile);

            Iterable<CSVRecord> recordsheader = header.parse(read_one);
            ProjectMetadata metadatacondition = new ProjectMetadata();

            for (CSVRecord record : recordsheader) {

                long count=record.size();
                int i=0;
                metadatacondition.setId(new Long(metadata_id));
                Long longNumber=new Long(count);
                metadatacondition.setLength(longNumber.intValue());
                metadatacondition.setCreated(null);
                metadatacondition.setUpdated(null);
                metadatacondition.setF1_title(record.get(i++));
                if(count-i<=0) break;
                metadatacondition.setF2_title(record.get(i++));
                if(count-i<=0) break;
                metadatacondition.setF3_title(record.get(i++));
                if(count-i<=0) break;
                metadatacondition.setF4_title(record.get(i++));
                if(count-i<=0) break;
                metadatacondition.setF5_title(record.get(i++));
                if(count-i<=0) break;
                metadatacondition.setF6_title(record.get(i++));
                if(count-i<=0) break;
                metadatacondition.setF7_title(record.get(i++));
                if(count-i<=0) break;
                metadatacondition.setF8_title(record.get(i++));
                if(count-i<=0) break;
                metadatacondition.setF9_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF10_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF11_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF12_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF13_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF14_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF15_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF16_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF17_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF18_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF19_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF20_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF21_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF22_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF23_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF24_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF25_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF26_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF27_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF28_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF29_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF30_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF31_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF32_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF33_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF34_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF35_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF36_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF37_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF38_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF39_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF40_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF41_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF42_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF43_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF44_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF45_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF46_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF47_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF48_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF49_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF50_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF51_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF52_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF53_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF54_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF55_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF56_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF57_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF58_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF59_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF60_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF61_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF62_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF63_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF64_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF65_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF66_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF67_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF68_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF69_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF70_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF71_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF72_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF73_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF74_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF75_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF76_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF77_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF78_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF79_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF80_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF81_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF82_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF83_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF84_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF85_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF86_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF87_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF88_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF89_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF90_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF91_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF92_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF93_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF94_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF95_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF96_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF97_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF98_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF99_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF100_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF101_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF102_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF103_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF104_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF105_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF106_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF107_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF108_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF109_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF110_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF111_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF112_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF113_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF114_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF115_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF116_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF117_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF118_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF119_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF120_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF121_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF122_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF123_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF124_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF125_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF126_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF127_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF128_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF129_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF130_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF131_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF132_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF133_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF134_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF135_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF136_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF137_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF138_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF139_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF140_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF141_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF142_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF143_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF144_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF145_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF146_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF147_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF148_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF149_title(record.get(i++));if(count-i<=0) break;
                metadatacondition.setF150_title(record.get(i++));if(count-i<=0) break;
                break;
            }
            read_one.close();
            ProjectMetadata metadataresult =metadataService.getMetadataByAllTitleName(metadatacondition);
            if(metadataresult==null){
                logger.error(APIRequestUrl.PROJECT_CONTENT_IMPORT+"there is no project metadata for this project content error.");
                retmap.put("status","failed");
                return retmap;
            }
            read_one = new FileReader(tempfile);
            CSVFormat content = CSVFormat.DEFAULT.withHeader(FILE_HEADER).withSkipHeaderRecord();
            Iterable<CSVRecord> recordscontent = content.parse(read_one);
            int i_temp=0;

            for (CSVRecord record : recordscontent) {
                i_temp++;
                System.out.print("i_temp: "+i_temp);
                long size=record.size();
                int i=0;
                ProjectContent item = new ProjectContent();
                item.setProject_metadata_id(metadataresult.getId());
                item.setCreated(null);

                item.setF1(record.get(i++)); if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF2(record.get(i++)); if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF3(record.get(i++)); if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF4(record.get(i++)); if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF5(record.get(i++)); if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF6(record.get(i++)); if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF7(record.get(i++)); if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF8(record.get(i++)); if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF9(record.get(i++)); if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF10(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF11(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF12(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF13(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF14(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF15(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF16(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF17(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF18(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF19(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF20(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF21(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF22(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF23(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF24(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF25(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF26(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF27(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF28(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF29(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF30(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF31(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF32(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF33(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF34(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF35(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF36(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF37(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF38(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF39(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF40(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF41(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF42(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF43(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF44(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF45(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF46(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF47(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF48(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF49(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF50(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF51(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF52(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF53(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF54(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF55(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF56(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF57(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF58(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF59(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF60(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF61(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF62(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF63(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF64(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF65(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF66(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF67(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF68(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF69(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF70(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF71(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF72(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF73(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF74(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF75(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF76(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF77(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF78(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF79(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF80(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF81(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF82(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF83(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF84(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF85(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF86(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF87(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF88(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF89(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF90(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF91(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF92(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF93(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF94(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF95(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF96(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF97(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF98(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF99(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF100(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF101(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF102(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF103(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF104(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF105(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF106(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF107(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF108(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF109(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF110(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF111(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF112(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF113(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF114(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF115(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF116(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF117(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF118(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF119(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF120(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF121(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF122(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF123(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF124(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF125(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF126(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF127(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF128(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF129(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF130(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF131(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF132(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF133(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF134(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF135(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF136(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF137(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF138(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF139(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF140(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF141(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF142(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF143(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF144(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF145(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF146(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF147(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF148(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF149(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
                item.setF150(record.get(i++));if(size-i<=0) {projectContentList.add(item); continue;}
            }
            contentService.updateContentList(new Long(metadataresult.getId()).intValue(), projectContentList);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_CONTENT_IMPORT+"import project content data error."+":"+e.getMessage());
            retmap.put("status","failed");
            return retmap;
        }finally {

            try {
                read_one.close();
            }catch (Exception e){
                e.printStackTrace();
                logger.error(APIRequestUrl.PROJECT_CONTENT_IMPORT+"close stream error."+":"+e.getMessage());
            }
            if (tempfile.isFile() && tempfile.exists()) {
                tempfile.delete();// 文件删除
            }

        }

        retmap.put("status","success");
        return retmap;
    }


    @RequestMapping(value = {APIRequestUrl.PROJECT_CONTENT_METADATA_ID}, method = RequestMethod.GET, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<Object, Object> getContentByMetadataId(HttpServletRequest request,
                                                       @UserRequired User admin,
                                                       @PathVariable(value = "metadata_id") String metadata_id,
                                                       @RequestParam(value = "field1", required = false) String field1,
                                                       @RequestParam(value = "field1_type", required = false) String field1_type,
                                                       @RequestParam(value = "field1_content", required = false) String field1_content,
                                                       @RequestParam(value = "field2", required = false) String field2,
                                                       @RequestParam(value = "field2_type", required = false) String field2_type,
                                                       @RequestParam(value = "field2_content", required = false) String field2_content,
                                                       @RequestParam(value = "field3", required = false) String field3,
                                                       @RequestParam(value = "field3_type", required = false) String field3_type,
                                                       @RequestParam(value = "field3_content", required = false) String field3_content,
                                                       @RequestParam(value = "field4", required = false) String field4,
                                                       @RequestParam(value = "field4_type", required = false) String field4_type,
                                                       @RequestParam(value = "field4_content", required = false) String field4_content,
                                                       @RequestParam(value = "field5", required = false) String field5,
                                                       @RequestParam(value = "field5_type", required = false) String field5_type,
                                                       @RequestParam(value = "field5_content", required = false) String field5_content
    ){

        String userName = admin.getUsername();
        int user_type = admin.getUserType();
        ProjectSearchCondition searchCondition = new ProjectSearchCondition();
        if(user_type== UserType.SALES_MANAGER.getCode()){
            searchCondition.setSales_manager(userName);
        }
        if(user_type== UserType.SALES_REPRESENT.getCode()&&user_type!= UserType.SALES_MANAGER.getCode()){
            searchCondition.setSales_represent(userName);
        }
        searchCondition.setProject_metadata_id(new Long(metadata_id));
        if(field1!=null&&field1_type!=null&&field1_content!=null){
            searchCondition.setField1(field1);
            searchCondition.setField1_type(new Long(field1_type).intValue());
            searchCondition.setField1_content(field1_content);

        }
        if(field2!=null&&field2_type!=null&&field2_content!=null){
            searchCondition.setField2(field2);
            searchCondition.setField2_type(new Long(field2_type).intValue());
            searchCondition.setField2_content(field2_content);

        }
        if(field3!=null&&field3_type!=null&&field3_content!=null){
            searchCondition.setField3(field3);
            searchCondition.setField3_type(new Long(field3_type).intValue());
            searchCondition.setField3_content(field3_content);

        }
        if(field4!=null&&field4_type!=null&&field4_content!=null){
            searchCondition.setField4(field4);
            searchCondition.setField4_type(new Long(field4_type).intValue());
            searchCondition.setField4_content(field4_content);

        }
        if(field5!=null&&field5_type!=null&&field5_content!=null){
            searchCondition.setField5(field5);
            searchCondition.setField5_type(new Long(field5_type).intValue());
            searchCondition.setField5_content(field5_content);

        }
        List<ProjectContent> projectContentList=null;
        //項目對應内容
        projectContentList=contentService.getListBySearchCondition(searchCondition);
        if(projectContentList==null) {
            logger.error(APIRequestUrl.PROJECT_CONTENT_METADATA_ID+"get the project content data error,when metadata id = "+metadata_id);
            return null;
        }

        Map<Object, Object> jsonmap = new HashMap<Object, Object>();
        jsonmap.put("data", projectContentList);

        return jsonmap;
    }

    @RequestMapping(value = {APIRequestUrl.PROJECT_CONTENT_ID}, method = RequestMethod.GET, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<Object, Object> getContentById(HttpServletRequest request,
                                              @UserRequired User admin,
                                              @PathVariable(value = "id") String id){

        List<ProjectContent> projectContentList=new ArrayList<ProjectContent>();
        ProjectContent projectContent=contentService.getProjectContentById(new Long(id));
        projectContentList.add(projectContent);
        Map<Object, Object> jsonmap = new HashMap<Object, Object>();
        jsonmap.put("data", projectContentList);

        return jsonmap;

    }

    @RequestMapping(value = {APIRequestUrl.PROJECT_DELETE_ID}, method = RequestMethod.GET, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<Object, Object> deleteContentById(HttpServletRequest request,
                                                 @UserRequired User admin,
                                                 @PathVariable(value = "id") String id){
        Map<Object, Object> jsonmap = new HashMap<Object, Object>();
        try {
            contentService.deleteContentByContentId(new Long(id));
        }catch (Exception e){
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_DELETE_ID+"delete project content data error."+":"+e.getMessage());
            jsonmap.put("status","failed");
            return jsonmap;
        }

        jsonmap.put("status", "success");
        return jsonmap;

    }


    @RequestMapping(value = {APIRequestUrl.PROJECT_UPDATE_ID}, method = RequestMethod.GET, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<String, String> updateContentById(HttpServletRequest request,
                                                 @UserRequired User admin,ProjectContent item){
        Map<String, String> jsonmap = new HashMap<String, String>();
        try {
            contentService.updateContentByContentProjectContent(item);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_UPDATE_ID+"update project content data error."+":"+e.getMessage());
            jsonmap.put("status","failed");
            return jsonmap;
        }

        jsonmap.put("status", "success");
        return jsonmap;

    }

    @RequestMapping(value = {APIRequestUrl.PROJECT_UPLOAD_IMAGE_APPEAL}, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<String, String> uploadAppealImage(HttpServletRequest request,
                                                 @UserRequired User admin,
                                                 @RequestParam(value="image_upload", required = false) MultipartFile[] image_upload,
                                                 @RequestParam(value="appeal_content", required = false) String appeal_content,
                                                 @RequestParam(value="store",required = true) String store,
                                                 @RequestParam(value="metadata_id",required = true) String metadata_id){
        Map<String, String> jsonmap = new HashMap<String, String>();
        long userId = admin.getId();

        try {
            String classpath = request.getSession().getServletContext().getRealPath("");
            String storePath=classpath+"/media/";
            userAppealService.addAppealAndImage(storePath,image_upload, new Long(metadata_id),userId,  store, appeal_content);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_UPLOAD_IMAGE_APPEAL+"upload appeal image and content error."+":"+e.getMessage());
            jsonmap.put("status","failed");
            return jsonmap;
        }

        jsonmap.put("status", "success");
        return jsonmap;

    }

    @RequestMapping(value = {APIRequestUrl.PROJECT_UPLOAD_APPEAL}, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<String, String> uploadAppeal(HttpServletRequest request,
                                                 @UserRequired User admin,
                                                 @RequestParam(value="appeal_content", required = false) String appeal_content,
                                                 @RequestParam(value="store",required = true) String store,
                                                 @RequestParam(value="metadata_id",required = true) String metadata_id){
        Map<String, String> jsonmap = new HashMap<String, String>();
        long userId = admin.getId();

        try {
            String classpath = request.getSession().getServletContext().getRealPath("");
            String storePath=classpath+"/media/";
            userAppealService.addAppealAndImage(storePath,null, new Long(metadata_id),userId,  store, appeal_content);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_UPLOAD_APPEAL+" upload appeal content error."+":"+e.getMessage());
            jsonmap.put("status","failed");
            return jsonmap;
        }

        jsonmap.put("status", "success");
        return jsonmap;

    }

    @RequestMapping(value = {APIRequestUrl.PROJECT_FTP_STORE}, method = RequestMethod.GET, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public FtpStoreFieldsJson getFtpByStoreId(HttpServletRequest request,
                                              @UserRequired User admin,
                                              @PathVariable(value="store_id") String store_id,
                                              @RequestParam(value = "metadata_id") String metadata_id){

        FtpStoreFieldsJson ftpStoreFieldsJson=new FtpStoreFieldsJson();
        ftpStoreFieldsJson.setStore_id(store_id);
        try {
            String classpath = request.getSession().getServletContext().getRealPath("");
//            File file_metadata=new File(classpath+"/ftp/"+metadata_id);
//            if(file_metadata.exists() && file_metadata .isDirectory()){
//                ;
//            }else {
//                file_metadata.mkdir();
//            }
            String storePath=classpath+"/ftp/"+metadata_id+"/"+store_id;
            File file = new File(storePath);
            String field1=storePath+"/"+"1";
            String field2=storePath+"/"+"2";
            String field3=storePath+"/"+"3";
            String field4=storePath+"/"+"4";
            String field5=storePath+"/"+"5";
            String field6=storePath+"/"+"6";
            File file_field1=new File(field1);
            File file_field2=new File(field2);
            File file_field3=new File(field3);
            File file_field4=new File(field4);
            File file_field5=new File(field5);
            File file_field6=new File(field6);
            if(file.exists() && file .isDirectory()){


                if(file_field1.exists() && file_field1.isDirectory()){
                    ftpStoreFieldsJson.setField1(getFileName(metadata_id,store_id,"1",file_field1));
                }else{

                }


                if(file_field2.exists() && file_field2.isDirectory()){
                    ftpStoreFieldsJson.setField2(getFileName(metadata_id,store_id,"2",file_field2));
                }else{

                }


                if(file_field3.exists() && file_field3.isDirectory()){
                    ftpStoreFieldsJson.setField3(getFileName(metadata_id,store_id,"3",file_field3));
                }else{

                }


                if(file_field4.exists() && file_field4.isDirectory()){
                    ftpStoreFieldsJson.setField4(getFileName(metadata_id,store_id,"4",file_field4));
                }else{

                }


                if(file_field5.exists() && file_field5 .isDirectory()){
                    ftpStoreFieldsJson.setField5(getFileName(metadata_id,store_id,"5",file_field5));
                }else{

                }


                if(file_field6.exists() && file_field6 .isDirectory()){
                    ftpStoreFieldsJson.setField6(getFileName(metadata_id,store_id,"6",file_field6));
                }else{

                }

            }else{
//                file.mkdirs();
//                file_field1.mkdirs();
//                file_field2.mkdirs();
//                file_field3.mkdirs();
//                file_field4.mkdirs();
//                file_field5.mkdirs();
//                file_field6.mkdirs();
            }


        }catch (Exception e){
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_FTP_STORE+" get ftp by store id about image and content error."+":"+e.getMessage());
            return null;
        }

        return ftpStoreFieldsJson;

    }

    private static List<String> getFileName(String metadate_id,String store,String number,File file){
        String path = file.getPath();
        List<String>  fileList=new ArrayList<String>();
        String [] filelist = file.list();
        for (int i = 0; i < filelist.length; i++) {
            File readfile = new File(path + "/" + filelist[i]);
            String old_name = filelist[i];
            String new_name=old_name;
            if(old_name.contains(" ")){
//                new_name=old_name.replace(" ","_");
//                File oldfile=new File(path+"/"+old_name);
//                File newfile=new File(path+"/"+new_name);
//                oldfile.renameTo(newfile);

            }

            if (!readfile.isDirectory()) {
                String url="/ftp/"+metadate_id+"/"+store+"/"+number+"/";
                try {
                    fileList.add(url+URLEncoder.encode(new_name,"UTF-8").replaceAll("\\+","%20"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileList;
    }


}
