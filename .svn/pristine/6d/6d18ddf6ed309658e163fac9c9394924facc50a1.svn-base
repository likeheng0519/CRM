package cn.com.conversant.weizi.crm.api.service.impl;

import cn.com.conversant.commons.db.SQLHelper;
import cn.com.conversant.commons.paging.PagingArrayList;
import cn.com.conversant.commons.paging.PagingList;
import cn.com.conversant.weizi.crm.api.constans.APIRequestUrl;
import cn.com.conversant.weizi.crm.api.constans.AppealStatus;
import cn.com.conversant.weizi.crm.api.dao.UserAppealDao;
import cn.com.conversant.weizi.crm.api.dao.UserPermissionDao;
import cn.com.conversant.weizi.crm.api.entity.UserAppeal;
import cn.com.conversant.weizi.crm.api.entity.UserPermission;
import cn.com.conversant.weizi.crm.api.service.UserAppealService;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by conversant on 2017/1/10.
 */
@Service("userAppealService")
public class UserAppealServiceImpl implements UserAppealService {

    @Value("${content.storage.base}")
    private String storageBase;

    @Resource
    private UserAppealDao userAppealDao;

    private Logger logger = Logger.getLogger(UserAppealServiceImpl.class);

    @Transactional
    public void addAppealAndImage(String path,MultipartFile[] image_upload, long metadata_id, long userId, String store, String content) {
        UserAppeal userAppeal=new UserAppeal();
        userAppeal.setProject_metadata_id(metadata_id);
        userAppeal.setAuthor(userId);
        userAppeal.setContent(content);
        userAppeal.setId(0);
        userAppeal.setStore_code(store);
        userAppeal.setStatus(AppealStatus.WAITING.getCode());
        if(image_upload != null) {
            for (int i = 0; i < image_upload.length; i++) {

                if (!image_upload[i].isEmpty()) {
                    OutputStream os = null;
                    FileOutputStream fileOutputStream = null;
                    String fileName = image_upload[i].getOriginalFilename();
                    Calendar cal = Calendar.getInstance();
                    int y, m, d, h, mi, s;
                    y = cal.get(Calendar.YEAR);
                    m = cal.get(Calendar.MONTH);
                    d = cal.get(Calendar.DATE);
                    h = cal.get(Calendar.HOUR_OF_DAY);
                    mi = cal.get(Calendar.MINUTE);
                    s = cal.get(Calendar.SECOND);
                    String subpath = userId + "_" + store + "_" + y + m + d + h + mi + s + i + ""
                            + fileName.substring(fileName.lastIndexOf("."));

                    File tempfile = new File(path + subpath);

                    try {
                        fileOutputStream = new FileOutputStream(tempfile);
                        os = new BufferedOutputStream(fileOutputStream);
                        IOUtils.copy(image_upload[i].getInputStream(), os);
                        os.flush();
                        fileOutputStream.close();
                        os.close();
                        setImagePath(userAppeal, subpath);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error(APIRequestUrl.PROJECT_UPLOAD_IMAGE_APPEAL + "upload appeal image and content error." + ":" + e.getMessage());
                    }
                }
            }
        }
        //添加申訴記錄
        userAppealDao.addUserAppeal(userAppeal);

    }

    @Transactional
    public PagingList<UserAppeal> listAppeal(int pageNumber, int pageSize) {
        int offset = -1;

        if (pageSize > 0) {
            offset = (pageNumber - 1) * pageSize;
        }

        int totalCount = userAppealDao.getAppealTotalCount();
        List<UserAppeal> appealList = userAppealDao.list(offset, pageSize);

        return new PagingArrayList<UserAppeal>(pageNumber, pageSize, totalCount, appealList);
    }

    @Transactional
    public PagingList<UserAppeal> searchAppeal(String searchItem, long projectId, int pageNumber, int pageSize) {
        int offset = -1;

        if (pageSize > 0) {
            offset = (pageNumber - 1) * pageSize;
        }

        String realSearchKey = SQLHelper.escapeQueryChars(searchItem);

        int totalCount = userAppealDao.getSearchTotalCount(realSearchKey, projectId);
        List<UserAppeal> userList = userAppealDao.searchAppeal(realSearchKey, projectId, offset, pageSize);

        return new PagingArrayList<UserAppeal>(pageNumber, pageSize, totalCount, userList);
    }

    @Transactional
    public void changeStatus(long operatorId, long appealId, int status) {
        UserAppeal oldAppeal = userAppealDao.getUserAppealById(appealId);
        if(oldAppeal==null){
            return;
        }
        oldAppeal.setOperator(operatorId);
        oldAppeal.setStatus(status);
        userAppealDao.changeStatus(oldAppeal);
    }

    private static void setImagePath(UserAppeal userAppeal, String path){

        if(userAppeal.getImage1()==null){
            userAppeal.setImage1("/media/"+path);
        }else if(userAppeal.getImage2()==null){
            userAppeal.setImage2("/media/"+path);
        }else if(userAppeal.getImage3()==null){
            userAppeal.setImage3("/media/"+path);
        }else if(userAppeal.getImage4()==null){
            userAppeal.setImage4("/media/"+path);
        }else   if(userAppeal.getImage5()==null){
            userAppeal.setImage5("/media/"+path);
        }else   if(userAppeal.getImage6()==null){
            userAppeal.setImage6("/media/"+path);
        }else   if(userAppeal.getImage7()==null){
            userAppeal.setImage7("/media/"+path);
        }else   if(userAppeal.getImage8()==null){
            userAppeal.setImage8("/media/"+path);
        }else   if(userAppeal.getImage9()==null){
            userAppeal.setImage9("/media/"+path);
        }else   if(userAppeal.getImage10()==null){
            userAppeal.setImage10("/media/"+path);
        }

    }

    @Transactional
    public List<UserAppeal> appealGetByStore(String store_code) {
        if(store_code==null){
            return null;
        }
        return userAppealDao.getUserAppealByStoreCode(store_code);
    }
}
