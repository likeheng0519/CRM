package cn.com.conversant.weizi.crm.api.controller;

import cn.com.conversant.weizi.crm.api.constans.APIRequestUrl;
import cn.com.conversant.weizi.crm.api.constans.UserType;
import cn.com.conversant.weizi.crm.api.entity.ProjectMetadata;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.mvc.UserRequired;
import cn.com.conversant.weizi.crm.api.service.ContentService;
import cn.com.conversant.weizi.crm.api.service.MetadataService;
import cn.com.conversant.weizi.crm.api.service.SearchConditionService;
import cn.com.conversant.weizi.crm.api.service.UserService;
import cn.com.conversant.weizi.crm.commons.CommonUtil.ConverEncoding;
import cn.com.conversant.weizi.crm.commons.CommonUtil.FileCharsetDetector;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conversant on 2017/1/4.
 */
@Controller
public class ProjectMetadataController {
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

    private Logger logger = Logger.getLogger(ProjectMetadataController.class);


    @RequestMapping(value = APIRequestUrl.PROJECT_METADATA_IMPORT, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<String, String> importMetadate(HttpServletRequest request,
                                             @UserRequired User admin,
                                             @RequestParam(value="file_upload", required = true) MultipartFile file_upload,
                                             @RequestParam(value="metadata_name", required = true) String metadata_name) {
        HashMap retmap=new HashMap<String, String>();
        //接收参数信息
        logger.info("APIRequestUrl.PROJECT_METADATA_IMPORT:");

        long userId = admin.getId();

        final String[] FILE_HEADER = {"id","name","length","created","updated",
                "f1_title","f2_title","f3_title","f4_title","f5_title",
                "f6_title","f7_title","f8_title","f9_title","f10_title",
                "f11_title","f12_title","f13_title","f14_title","f15_title",
                "f16_title","f17_title","f18_title","f19_title","f20_title",
                "f21_title","f22_title","f23_title","f24_title","f25_title",
                "f26_title","f27_title","f28_title","f29_title","f30_title",
                "f31_title","f32_title","f33_title","f34_title","f35_title",
                "f36_title","f37_title","f38_title","f39_title","f40_title",
                "f41_title","f42_title","f43_title","f44_title","f45_title",
                "f46_title","f47_title","f48_title","f49_title","f50_title",
                "f51_title","f52_title","f53_title","f54_title","f55_title",
                "f56_title","f57_title","f58_title","f59_title","f60_title",
                "f61_title","f62_title","f63_title","f64_title","f65_title",
                "f66_title","f67_title","f68_title","f69_title","f70_title",
                "f71_title","f72_title","f73_title","f74_title","f75_title",
                "f76_title","f77_title","f78_title","f79_title","f80_title",
                "f81_title","f82_title","f83_title","f84_title","f85_title",
                "f86_title","f87_title","f88_title","f89_title","f90_title",
                "f91_title","f92_title","f93_title","f94_title","f95_title",
                "f96_title","f97_title","f98_title","f99_title","f100_title",
                "f101_title","f102_title","f103_title","f104_title","f105_title",
                "f106_title","f107_title","f108_title","f109_title","f110_title",
                "f111_title","f112_title","f113_title","f114_title","f115_title",
                "f116_title","f117_title","f118_title","f119_title","f120_title",
                "f121_title","f122_title","f123_title","f124_title","f125_title",
                "f126_title","f127_title","f128_title","f129_title","f130_title",
                "f131_title","f132_title","f133_title","f134_title","f135_title",
                "f136_title","f137_title","f138_title","f139_title","f140_title",
                "f141_title","f142_title","f143_title","f144_title","f145_title",
                "f146_title","f147_title","f148_title","f149_title","f150_title"
            };

        Calendar cal=Calendar.getInstance();
        int y,m,d,h,mi,s;
        y=cal.get(Calendar.YEAR);
        m=cal.get(Calendar.MONTH);
        d=cal.get(Calendar.DATE);
        h=cal.get(Calendar.HOUR_OF_DAY);
        mi=cal.get(Calendar.MINUTE);
        s=cal.get(Calendar.SECOND);
        String filename1=storageBase+String.valueOf(y)+String.valueOf(m)+String.valueOf(d)+String.valueOf(h)+String.valueOf(mi)+String.valueOf(s)+".csv";
        CSVFormat format = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        File tempfile =null;
        OutputStream os = null;
        Reader in =null;
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
            tempfile=new File(filename1);
            in = new FileReader(tempfile);
            Iterable<CSVRecord> records = format.parse(in);

            ProjectMetadata item = new ProjectMetadata();

            for (CSVRecord record : records) {
                long count=record.size();
                int i=0;
                item.setName(metadata_name);
                Long longNumber=new Long(count);
                item.setLength(longNumber.intValue());
                item.setCreated(null);
                item.setUpdated(null);
                item.setF1_title(record.get(i++));
                if(count-i<=0) break;
                item.setF2_title(record.get(i++));
                if(count-i<=0) break;
                item.setF3_title(record.get(i++));
                if(count-i<=0) break;
                item.setF4_title(record.get(i++));
                if(count-i<=0) break;
                item.setF5_title(record.get(i++));
                if(count-i<=0) break;
                item.setF6_title(record.get(i++));
                if(count-i<=0) break;
                item.setF7_title(record.get(i++));
                if(count-i<=0) break;
                item.setF8_title(record.get(i++));
                if(count-i<=0) break;
                item.setF9_title(record.get(i++));if(count-i<=0) break;
                item.setF10_title(record.get(i++));if(count-i<=0) break;
                item.setF11_title(record.get(i++));if(count-i<=0) break;
                item.setF12_title(record.get(i++));if(count-i<=0) break;
                item.setF13_title(record.get(i++));if(count-i<=0) break;
                item.setF14_title(record.get(i++));if(count-i<=0) break;
                item.setF15_title(record.get(i++));if(count-i<=0) break;
                item.setF16_title(record.get(i++));if(count-i<=0) break;
                item.setF17_title(record.get(i++));if(count-i<=0) break;
                item.setF18_title(record.get(i++));if(count-i<=0) break;
                item.setF19_title(record.get(i++));if(count-i<=0) break;
                item.setF20_title(record.get(i++));if(count-i<=0) break;
                item.setF21_title(record.get(i++));if(count-i<=0) break;
                item.setF22_title(record.get(i++));if(count-i<=0) break;
                item.setF23_title(record.get(i++));if(count-i<=0) break;
                item.setF24_title(record.get(i++));if(count-i<=0) break;
                item.setF25_title(record.get(i++));if(count-i<=0) break;
                item.setF26_title(record.get(i++));if(count-i<=0) break;
                item.setF27_title(record.get(i++));if(count-i<=0) break;
                item.setF28_title(record.get(i++));if(count-i<=0) break;
                item.setF29_title(record.get(i++));if(count-i<=0) break;
                item.setF30_title(record.get(i++));if(count-i<=0) break;
                item.setF31_title(record.get(i++));if(count-i<=0) break;
                item.setF32_title(record.get(i++));if(count-i<=0) break;
                item.setF33_title(record.get(i++));if(count-i<=0) break;
                item.setF34_title(record.get(i++));if(count-i<=0) break;
                item.setF35_title(record.get(i++));if(count-i<=0) break;
                item.setF36_title(record.get(i++));if(count-i<=0) break;
                item.setF37_title(record.get(i++));if(count-i<=0) break;
                item.setF38_title(record.get(i++));if(count-i<=0) break;
                item.setF39_title(record.get(i++));if(count-i<=0) break;
                item.setF40_title(record.get(i++));if(count-i<=0) break;
                item.setF41_title(record.get(i++));if(count-i<=0) break;
                item.setF42_title(record.get(i++));if(count-i<=0) break;
                item.setF43_title(record.get(i++));if(count-i<=0) break;
                item.setF44_title(record.get(i++));if(count-i<=0) break;
                item.setF45_title(record.get(i++));if(count-i<=0) break;
                item.setF46_title(record.get(i++));if(count-i<=0) break;
                item.setF47_title(record.get(i++));if(count-i<=0) break;
                item.setF48_title(record.get(i++));if(count-i<=0) break;
                item.setF49_title(record.get(i++));if(count-i<=0) break;
                item.setF50_title(record.get(i++));if(count-i<=0) break;
                item.setF51_title(record.get(i++));if(count-i<=0) break;
                item.setF52_title(record.get(i++));if(count-i<=0) break;
                item.setF53_title(record.get(i++));if(count-i<=0) break;
                item.setF54_title(record.get(i++));if(count-i<=0) break;
                item.setF55_title(record.get(i++));if(count-i<=0) break;
                item.setF56_title(record.get(i++));if(count-i<=0) break;
                item.setF57_title(record.get(i++));if(count-i<=0) break;
                item.setF58_title(record.get(i++));if(count-i<=0) break;
                item.setF59_title(record.get(i++));if(count-i<=0) break;
                item.setF60_title(record.get(i++));if(count-i<=0) break;
                item.setF61_title(record.get(i++));if(count-i<=0) break;
                item.setF62_title(record.get(i++));if(count-i<=0) break;
                item.setF63_title(record.get(i++));if(count-i<=0) break;
                item.setF64_title(record.get(i++));if(count-i<=0) break;
                item.setF65_title(record.get(i++));if(count-i<=0) break;
                item.setF66_title(record.get(i++));if(count-i<=0) break;
                item.setF67_title(record.get(i++));if(count-i<=0) break;
                item.setF68_title(record.get(i++));if(count-i<=0) break;
                item.setF69_title(record.get(i++));if(count-i<=0) break;
                item.setF70_title(record.get(i++));if(count-i<=0) break;
                item.setF71_title(record.get(i++));if(count-i<=0) break;
                item.setF72_title(record.get(i++));if(count-i<=0) break;
                item.setF73_title(record.get(i++));if(count-i<=0) break;
                item.setF74_title(record.get(i++));if(count-i<=0) break;
                item.setF75_title(record.get(i++));if(count-i<=0) break;
                item.setF76_title(record.get(i++));if(count-i<=0) break;
                item.setF77_title(record.get(i++));if(count-i<=0) break;
                item.setF78_title(record.get(i++));if(count-i<=0) break;
                item.setF79_title(record.get(i++));if(count-i<=0) break;
                item.setF80_title(record.get(i++));if(count-i<=0) break;
                item.setF81_title(record.get(i++));if(count-i<=0) break;
                item.setF82_title(record.get(i++));if(count-i<=0) break;
                item.setF83_title(record.get(i++));if(count-i<=0) break;
                item.setF84_title(record.get(i++));if(count-i<=0) break;
                item.setF85_title(record.get(i++));if(count-i<=0) break;
                item.setF86_title(record.get(i++));if(count-i<=0) break;
                item.setF87_title(record.get(i++));if(count-i<=0) break;
                item.setF88_title(record.get(i++));if(count-i<=0) break;
                item.setF89_title(record.get(i++));if(count-i<=0) break;
                item.setF90_title(record.get(i++));if(count-i<=0) break;
                item.setF91_title(record.get(i++));if(count-i<=0) break;
                item.setF92_title(record.get(i++));if(count-i<=0) break;
                item.setF93_title(record.get(i++));if(count-i<=0) break;
                item.setF94_title(record.get(i++));if(count-i<=0) break;
                item.setF95_title(record.get(i++));if(count-i<=0) break;
                item.setF96_title(record.get(i++));if(count-i<=0) break;
                item.setF97_title(record.get(i++));if(count-i<=0) break;
                item.setF98_title(record.get(i++));if(count-i<=0) break;
                item.setF99_title(record.get(i++));if(count-i<=0) break;
                item.setF100_title(record.get(i++));if(count-i<=0) break;
                item.setF101_title(record.get(i++));if(count-i<=0) break;
                item.setF102_title(record.get(i++));if(count-i<=0) break;
                item.setF103_title(record.get(i++));if(count-i<=0) break;
                item.setF104_title(record.get(i++));if(count-i<=0) break;
                item.setF105_title(record.get(i++));if(count-i<=0) break;
                item.setF106_title(record.get(i++));if(count-i<=0) break;
                item.setF107_title(record.get(i++));if(count-i<=0) break;
                item.setF108_title(record.get(i++));if(count-i<=0) break;
                item.setF109_title(record.get(i++));if(count-i<=0) break;
                item.setF110_title(record.get(i++));if(count-i<=0) break;
                item.setF111_title(record.get(i++));if(count-i<=0) break;
                item.setF112_title(record.get(i++));if(count-i<=0) break;
                item.setF113_title(record.get(i++));if(count-i<=0) break;
                item.setF114_title(record.get(i++));if(count-i<=0) break;
                item.setF115_title(record.get(i++));if(count-i<=0) break;
                item.setF116_title(record.get(i++));if(count-i<=0) break;
                item.setF117_title(record.get(i++));if(count-i<=0) break;
                item.setF118_title(record.get(i++));if(count-i<=0) break;
                item.setF119_title(record.get(i++));if(count-i<=0) break;
                item.setF120_title(record.get(i++));if(count-i<=0) break;
                item.setF121_title(record.get(i++));if(count-i<=0) break;
                item.setF122_title(record.get(i++));if(count-i<=0) break;
                item.setF123_title(record.get(i++));if(count-i<=0) break;
                item.setF124_title(record.get(i++));if(count-i<=0) break;
                item.setF125_title(record.get(i++));if(count-i<=0) break;
                item.setF126_title(record.get(i++));if(count-i<=0) break;
                item.setF127_title(record.get(i++));if(count-i<=0) break;
                item.setF128_title(record.get(i++));if(count-i<=0) break;
                item.setF129_title(record.get(i++));if(count-i<=0) break;
                item.setF130_title(record.get(i++));if(count-i<=0) break;
                item.setF131_title(record.get(i++));if(count-i<=0) break;
                item.setF132_title(record.get(i++));if(count-i<=0) break;
                item.setF133_title(record.get(i++));if(count-i<=0) break;
                item.setF134_title(record.get(i++));if(count-i<=0) break;
                item.setF135_title(record.get(i++));if(count-i<=0) break;
                item.setF136_title(record.get(i++));if(count-i<=0) break;
                item.setF137_title(record.get(i++));if(count-i<=0) break;
                item.setF138_title(record.get(i++));if(count-i<=0) break;
                item.setF139_title(record.get(i++));if(count-i<=0) break;
                item.setF140_title(record.get(i++));if(count-i<=0) break;
                item.setF141_title(record.get(i++));if(count-i<=0) break;
                item.setF142_title(record.get(i++));if(count-i<=0) break;
                item.setF143_title(record.get(i++));if(count-i<=0) break;
                item.setF144_title(record.get(i++));if(count-i<=0) break;
                item.setF145_title(record.get(i++));if(count-i<=0) break;
                item.setF146_title(record.get(i++));if(count-i<=0) break;
                item.setF147_title(record.get(i++));if(count-i<=0) break;
                item.setF148_title(record.get(i++));if(count-i<=0) break;
                item.setF149_title(record.get(i++));if(count-i<=0) break;
                item.setF150_title(record.get(i++));if(count-i<=0) break;

            }
            //list导入DB
            long metadata_id=metadataService.addMetadata(userId,item);
            String classpath = request.getSession().getServletContext().getRealPath("");
            String storePath=classpath+"/ftp/"+metadata_id;
//            File file = new File(storePath);
//            if(file.exists() && file .isDirectory()){
//                ;
//            }else{
//                file.mkdirs();
//            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_METADATA_IMPORT+"import project meta data error."+":"+e.getMessage());
            retmap.put("status","failed");
            return retmap;
        }finally {
            try {
                in.close();
            }catch (Exception e){
                e.printStackTrace();
                logger.error(APIRequestUrl.PROJECT_METADATA_IMPORT+"close stream error."+":"+e.getMessage());
            }
            if (tempfile.isFile() && tempfile.exists()) {
               tempfile.delete();// 文件删除
            }

        }

        retmap.put("status","success");

        return retmap;
    }


    @RequestMapping(value = {APIRequestUrl.PROJECT_METADATA_METADATA_ID}, method = RequestMethod.GET, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public ProjectMetadata getMetadataByMetadataId(HttpServletRequest request,
                                                   @UserRequired User admin,
                                                   @PathVariable(value = "metadata_id") String metadata_id){
        if(metadata_id==null){
            return null;
        }
        ProjectMetadata projectMetadata=null;
        projectMetadata=metadataService.getMetadataById(new Long(metadata_id));

        return  projectMetadata;
    }

    @RequestMapping(value = {APIRequestUrl.PROJECT_METADATA_NAMES_USER_NAME}, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public List<ProjectMetadata>  getMetadatasByUserId(HttpServletRequest request,
                                                       @UserRequired User admin,
                                                       @RequestParam(value = "user_id") long user_id){
        List<ProjectMetadata> list=null;
        if(admin.getUserType() == UserType.ADMINISTRATOR.getCode()){
            user_id = admin.getId();
        }
        list=metadataService.getListMetadataByUserId(user_id);

        return  list;
    }

    @RequestMapping(value = {APIRequestUrl.PROJECT_METADATA_CONTENT_ID}, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public ProjectMetadata  getMetadatasByContentId(HttpServletRequest request,
                                                    @UserRequired User admin,
                                                    @RequestParam(value = "content_id") String content_id){
        if(content_id==null){
            return null;
        }
        ProjectMetadata projectMetadata=null;
        projectMetadata=metadataService.getMetadataByContentId(new Long(content_id));

        return  projectMetadata;
    }



    @RequestMapping(value = APIRequestUrl.PROJECT_METADATA_DELETE, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<String, String> deleteMetadate(HttpServletRequest request,
                                              @UserRequired User admin,
                                              @PathVariable(value = "metadata_id") String metadata_id) {
        HashMap retmap = new HashMap<String, String>();

        if(new Long(metadata_id)==0){
            retmap.put("status", "failed");
            return retmap;
        }

        try {
            metadataService.deleteMetadataById(new Long(metadata_id));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_METADATA_DELETE + "delete project meta data error[" + metadata_id + "]:" + e.getMessage());
            retmap.put("status", "failed");
            return retmap;


        }
        retmap.put("status", "success");

        return retmap;
    }

    @RequestMapping(value = APIRequestUrl.PROJECT_METADATA_UPDATE_NAME, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<String, String> updateMetadateName(HttpServletRequest request,
                                                  @UserRequired User admin,
                                                  @RequestParam(value = "new_name",required = true) String new_name,
                                                  @RequestParam(value = "metadata_id",required = true) String metadata_id) {
        HashMap retmap = new HashMap<String, String>();

        if(new Long(metadata_id)==0||new_name==null){
            retmap.put("status", "failed");
            return retmap;
        }

        try {
            metadataService.updateMetadataName(new Long(metadata_id), new_name);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_METADATA_UPDATE_NAME + "update project meta data error[" + metadata_id + "]:" + e.getMessage());
            retmap.put("status", "failed");
            return retmap;
        }

        retmap.put("status", "success");
        return retmap;
    }

    }
