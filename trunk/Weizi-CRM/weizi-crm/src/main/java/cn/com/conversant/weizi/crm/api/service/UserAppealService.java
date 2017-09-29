package cn.com.conversant.weizi.crm.api.service;

import cn.com.conversant.commons.paging.PagingList;
import cn.com.conversant.weizi.crm.api.entity.UserAppeal;
import cn.com.conversant.weizi.crm.api.entity.UserPermission;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by conversant on 2017/1/10.
 */
public interface UserAppealService {
    public void addAppealAndImage(String path,MultipartFile[] files, long metadata_id, long userId, String store, String content);

    PagingList<UserAppeal> listAppeal(int pageNumber, int pageSize);

    PagingList<UserAppeal> searchAppeal(String searchItem, long projectId, int pageNumber, int pageSize);

    void changeStatus(long id, long appealId, int status);

    List<UserAppeal> appealGetByStore(String store_code);
}
