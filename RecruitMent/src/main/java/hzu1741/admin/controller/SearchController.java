package hzu1741.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.github.pagehelper.util.StringUtil;
import hzu1741.admin.dao.JobDao;
import hzu1741.admin.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")//跨域传参
public class SearchController
{
    @Resource
    JobService jobService;

    //1.关键词搜索，输入职位名的关键信息，用正则表达式
    @RequestMapping(value = "/search",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String ,Object > searchJob(@RequestParam(name = "keyword") String keyword,
                                          @RequestParam(value="pageNo") int pageNo,
                                          Model model)
    {
        Map<String,Object> modelMap = new HashMap<String,Object>();


        //对 pageNo 的校验

        if(pageNo < 1){
            pageNo = 1;
        }

        /*
         * 第一个参数：第几页;
         * 第二个参数：每页获取的条数.
         */
        String title = "%"+keyword+"%";

        PageHelper.startPage(pageNo, 8);

        List<JobDao> jobList = jobService.selectByPrimaryKey(title);
        PageInfo<JobDao> page = new PageInfo<JobDao>(jobList);
        int  totalPage = page.getPages();
        model.addAttribute("page",page);

        modelMap.put("page", pageNo);
        modelMap.put("list", page.getList());
        modelMap.put("totalPage", totalPage);
        modelMap.put("pageSize",8 );

        return modelMap;
    }

    //2.高级搜索,传入多个字段，判断是否空值，查询
    @RequestMapping(value = "/superSearch",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String ,Object >  superSearchJob(@RequestParam(name = "place") String place,
                                 @RequestParam(name = "payRange") String payRange,
                                 @RequestParam(name = "jobState") String jobState,
                                 @RequestParam(name = "keyword") String keyword,
                                                @RequestParam(value="pageNo") int pageNo
                                 )
    {

        //对 pageNo 的校验
        if(pageNo < 1){
            pageNo = 1;
        }
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<JobDao> joblist = new ArrayList<>();


        if(StringUtil.isEmpty(place) && StringUtil.isEmpty(payRange)
                && StringUtil.isEmpty(jobState)&&StringUtil.isEmpty(keyword))
        {
            PageHelper.startPage(pageNo, 7);
            joblist = jobService.selectByPlace("%惠州%");
        }
        else {
            if(place != null){place = "%"+place+"%";}
            if(payRange != null){payRange = "%"+payRange+"%";}
            if(jobState != null){jobState = "%"+jobState+"%";}
            if(keyword != null){keyword = "%"+keyword+"%";}

            PageHelper.startPage(pageNo, 8);
            joblist = jobService.selectAll(place,payRange,jobState,keyword);
        }

        /*
         * 第一个参数：第几页;
         * 第二个参数：每页获取的条数.
         */

        PageInfo<JobDao> page = new PageInfo<>(joblist);
        int  totalPage = page.getPages();

        modelMap.put("page", pageNo);
        modelMap.put("list", page.getList());
        modelMap.put("totalPage", totalPage);
        modelMap.put("pageSize",8 );

        return modelMap;
    }
}
