package hzu1741.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hzu1741.admin.dao.CollectionDao;
import hzu1741.admin.dao.JobDao;
import hzu1741.admin.service.CollectionService;
import hzu1741.admin.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")//跨域传参
public class CollectionController
{
    @Resource
    CollectionService collectionService;

    @Resource
    JobService jobService;

    //1.添加收藏
    @RequestMapping(value = "/inCollect",method = {RequestMethod.POST})
    @ResponseBody
   String inCollection(@RequestParam(name = "userName") String userName,
                         @RequestParam(name = "jobId") String jobId)
    {

        CollectionDao collection = new CollectionDao();
        JobDao job = jobService.selectById(jobId);
        collection.setUserName(userName);
        collection.setJobId(jobId);
        collection.setCompany(job.getCompany());
        collection.setCompanyUrl(job.getCompanyUrl());
        collection.setJobPay(job.getJobPay());
        collection.setJobState(job.getJobState());
        collection.setJobTitle(job.getJobTitle());
        collection.setJobUrl(job.getJobUrl());
        collection.setReleaseTime(job.getReleaseTime());
        collection.setWorkPlace(job.getWorkPlace());

        if (collectionService.insert(collection) == 0){
            return  "error";
        }
        else{
            return "success";
        }

    }


    //2.删除收藏
    @RequestMapping(value = "/deleCollect",method = {RequestMethod.POST})
    @ResponseBody
    String deleCollection(@RequestParam (name = "userName") String userName,
                           @RequestParam(name = "jobId") String jobId)
    {
        if(collectionService.deleteByPrimaryKey(userName,jobId) == 0){
            return "error";
        }
        else {
            return "success";
        }
    }

    //3.查看收藏

    @RequestMapping(value = "/selectCollect",method = {RequestMethod.POST})
    @ResponseBody
   public Map<String ,Object > selectCollection(@RequestParam (name = "userName") String userName,
                                                @RequestParam(value="pageNo") int pageNo)
    {
        Logger logger = Logger.getLogger("seletCollection");
        logger.setLevel(Level.INFO);
        logger.info(userName);
        Map<String,Object> modelMap = new HashMap<String,Object>();

        //对 pageNo 的校验
        if(pageNo < 1){
            pageNo = 1;
        }

        /*
         * 第一个参数：第几页;
         * 第二个参数：每页获取的条数.
         */


        PageHelper.startPage(pageNo, 8);
        List<CollectionDao> collectionList = collectionService.selectByName(userName);

        PageInfo<CollectionDao> page=new PageInfo<CollectionDao>(collectionList);
        int  totalPage = page.getPages();

        modelMap.put("page", pageNo);
        modelMap.put("list", page.getList());
        modelMap.put("totalPage", totalPage);
        modelMap.put("pageSize",8 );
        return modelMap;
    }

}
