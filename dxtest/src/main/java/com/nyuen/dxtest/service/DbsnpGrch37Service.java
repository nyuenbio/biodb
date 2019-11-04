package com.nyuen.dxtest.service;

import com.nyuen.dxtest.model.DbsnpGrch37;
import com.nyuen.dxtest.Bean.PageBean;
import com.nyuen.dxtest.model.DbsnpGrch37Log;

import java.util.List;
import java.util.Map;

public interface DbsnpGrch37Service {
    public PageBean getAllData(int pageIndex, int pageSize);

    public void updateByPrimaryKeySelective(DbsnpGrch37 dbsnpGrch37, String rsIdOldVal, Integer commonSateOldVal);

    public void deleteByPrimaryKey(DbsnpGrch37 dbsnpGrch37);

    public void add(DbsnpGrch37 dbsnpGrch37);

    //条件查询
    public PageBean selectByCondition(Map<String,Object> params);

    //查看操作日志详情
    public PageBean getLog(Map<String, Object> params);

}
