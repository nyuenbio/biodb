package com.nyuen.dxtest.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nyuen.dxtest.dao.DbsnpGrch37LogMapper;
import com.nyuen.dxtest.dao.DbsnpGrch37Mapper;
import com.nyuen.dxtest.model.DbsnpGrch37;
import com.nyuen.dxtest.Bean.PageBean;
import com.nyuen.dxtest.model.DbsnpGrch37Log;
import com.nyuen.dxtest.service.DbsnpGrch37Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DbsnpGrch37ServiceImpl implements DbsnpGrch37Service {

    @Resource
    private DbsnpGrch37Mapper dbsnpGrch37Mapper;
    @Resource
    private DbsnpGrch37LogMapper dbsnpGrch37LogMapper;

    @Override
    public PageBean getAllData(int pageIndex,int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageIndex,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<DbsnpGrch37> page = (Page<DbsnpGrch37>) dbsnpGrch37Mapper.getAllData();
        page.setTotal(dbsnpGrch37Mapper.getTotalCount());
        return new PageBean(page.getTotal(),page.getResult());
    }

    @Override
    public void updateByPrimaryKeySelective(DbsnpGrch37 dbsnpGrch37, String rsIdOldVal, Integer commonSateOldVal) {
        //根据主键编辑数据：只能编辑rsId 和 conmmonState字段
        dbsnpGrch37Mapper.updateByPrimaryKeySelective(dbsnpGrch37);
        DbsnpGrch37Log dbsnpGrch37Log = new DbsnpGrch37Log();
        dbsnpGrch37Log.setOperateTableName("dbsnp_grch37");
        if( !dbsnpGrch37.getRsId().equals(rsIdOldVal)){
            dbsnpGrch37Log.setOperateColumnName("rs_id");
            dbsnpGrch37Log.setOldData(rsIdOldVal);
            dbsnpGrch37Log.setNewData(dbsnpGrch37.getRsId());
            addLog(dbsnpGrch37Log,dbsnpGrch37);
        }
        if(commonSateOldVal.intValue() != dbsnpGrch37.getCommonState().intValue()) {
            dbsnpGrch37Log.setOperateColumnName("common_state");
            dbsnpGrch37Log.setOldData(commonSateOldVal + "");
            dbsnpGrch37Log.setNewData(dbsnpGrch37.getCommonState() + "");
            addLog(dbsnpGrch37Log,dbsnpGrch37);
        }
    }

    private void addLog(DbsnpGrch37Log dbsnpGrch37Log, DbsnpGrch37 dbsnpGrch37){
        dbsnpGrch37Log.setOperateUser("陈钰林");
        dbsnpGrch37Log.setOperateTime(new Date());
        dbsnpGrch37Log.setChr(dbsnpGrch37.getChr());
        dbsnpGrch37Log.setPos(dbsnpGrch37.getPos());
        dbsnpGrch37Log.setRef(dbsnpGrch37.getRef());
        dbsnpGrch37Log.setAlt(dbsnpGrch37.getAlt());
        dbsnpGrch37LogMapper.insertSelective(dbsnpGrch37Log);
    }

    @Override
    public void deleteByPrimaryKey(DbsnpGrch37 dbsnpGrch37) {
        //根据主键逻辑删除数据
        dbsnpGrch37Mapper.updateByPrimaryKeySelective(dbsnpGrch37);
        //添加删除记录日志
        DbsnpGrch37Log dbsnpGrch37Log = new DbsnpGrch37Log();
        dbsnpGrch37Log.setOperateTableName("dbsnp_grch37");
        dbsnpGrch37Log.setOperateColumnName("is_delete");
        dbsnpGrch37Log.setOldData("false");
        dbsnpGrch37Log.setNewData(dbsnpGrch37.getIsDelete()+"");
        addLog(dbsnpGrch37Log,dbsnpGrch37);
    }

    @Override
    public void add(DbsnpGrch37 dbsnpGrch37) {
        //增加一条数据
        dbsnpGrch37Mapper.insertSelective(dbsnpGrch37);
    }

    @Override
    public PageBean selectByCondition(Map<String, Object> params) {
        //使用Mybatis分页插件
        //PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()),Integer.parseInt(params.get("pageSize").toString()));
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        //Page<DbsnpGrch37> page = (Page<DbsnpGrch37>) dbsnpGrch37Mapper.selectByCondition(params);

        Integer startIndex = Integer.parseInt(params.get("currentPage").toString());
        params.put("currentPage", startIndex-1);
        System.out.println("\n"+params.get("currentPage").toString()+" "+params.get("pageSize").toString());

        List<DbsnpGrch37> grch37List = dbsnpGrch37Mapper.selectByCondition(params);
        Page page = new Page();


        if( (params.get("rsId") != null && !"".equals(params.get("rsId").toString()))
                || (params.get("chr") != null && !"".equals(params.get("chr").toString()))
                || (params.get("pos") != null && !"".equals(params.get("pos").toString()))
                || (params.get("alt") != null && !"".equals(params.get("alt").toString()))
                || (params.get("ref") != null && !"".equals(params.get("ref").toString())) ){
            page.setTotal(dbsnpGrch37Mapper.getTotalCountByCondition(params));
        }else {
            page.setTotal(737671234);//需手动更新初始总条数
        }
        return new PageBean(page.getTotal(),grch37List);
    }

    @Override
    public PageBean getLog(Map<String, Object> params) {
        //使用Mybatis分页插件
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()),Integer.parseInt(params.get("pageSize").toString()));
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<DbsnpGrch37Log> page = (Page<DbsnpGrch37Log>) dbsnpGrch37LogMapper.getLog(params);

        return new PageBean(page.getTotal(), page.getResult());
    }


}
