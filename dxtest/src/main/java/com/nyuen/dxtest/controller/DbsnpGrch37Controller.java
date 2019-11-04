package com.nyuen.dxtest.controller;

import com.alibaba.fastjson.JSON;
import com.nyuen.dxtest.model.DbsnpGrch37;
import com.nyuen.dxtest.Bean.PageBean;
import com.nyuen.dxtest.result.Result;
import com.nyuen.dxtest.result.ResultFactory;
import com.nyuen.dxtest.service.impl.DbsnpGrch37ServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/grch37")
@CrossOrigin
public class DbsnpGrch37Controller extends BaseController{
    @Resource
    private DbsnpGrch37ServiceImpl dbsnpGrch37ServiceImpl;

    @RequestMapping("/getAllData")
    @ResponseBody
    public Result getAllData(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize){
        int pageIndex = currentPage;
        PageBean pageBean = dbsnpGrch37ServiceImpl.getAllData(pageIndex,pageSize);
        return ResultFactory.buildSuccessResult(pageBean);
    }

    @RequestMapping("/getDataByCondition")
    @ResponseBody
    public Result getDataByCondition(@RequestBody String criteria){
        Map<String, Object> condition = JSON.parseObject(criteria, Map.class);
        Object posStart = condition.get("posStart");
        Object posEnd = condition.get("posEnd");

        if(posStart != null &&  !"".equals(posStart.toString())){
            condition.put("posStart",Integer.parseInt(posStart.toString()));
        }
        if(posEnd != null && !"".equals(posEnd.toString())){
            condition.put("posEnd",Integer.parseInt(posEnd.toString()));
        }
        PageBean pageBean = dbsnpGrch37ServiceImpl.selectByCondition(condition);
        return ResultFactory.buildSuccessResult(pageBean);
    }

    /**
     * 编辑单条数据
     */
    @RequestMapping("/editData")
    @ResponseBody
    public Result editData(@RequestBody DbsnpGrch37 dbsnpGrch37, @RequestParam("rsIdOldVal") String rsIdOldVal, @RequestParam("commonSateOldVal") Integer commonSateOldVal){
        dbsnpGrch37ServiceImpl.updateByPrimaryKeySelective(dbsnpGrch37, rsIdOldVal, commonSateOldVal);

        return ResultFactory.buildSuccessResult("");
    }

    /**
     * 查看操作日志详情
     */
    @RequestMapping("/getLog")
    @ResponseBody
    public Result getLog(@RequestBody  Map<String, Object> params){

        return ResultFactory.buildSuccessResult(dbsnpGrch37ServiceImpl.getLog(params));
    }

    /**
     * 删除单条数据
     */
    @RequestMapping("/deleteData")
    @ResponseBody
    public Result deleteData(@RequestBody DbsnpGrch37 dbsnpGrch37){
        dbsnpGrch37.setIsDelete(true);
        dbsnpGrch37ServiceImpl.deleteByPrimaryKey(dbsnpGrch37);
        return ResultFactory.buildSuccessResult(0);
    }

}
