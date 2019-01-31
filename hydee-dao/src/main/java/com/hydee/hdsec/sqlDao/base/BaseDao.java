package com.hydee.hdsec.sqlDao.base;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class BaseDao {
    @Resource(name = "serversqlSessionTemplate")
    private SqlSessionTemplate serversqlSessionTemplate;

    /**
     * 批量新增
     * @param str
     * @param list
     */
    public Object batchInsert(String str,List list) throws RuntimeException{
        long beginTime = System.currentTimeMillis();
        SqlSessionFactory sqlSessionFactory = serversqlSessionTemplate.getSqlSessionFactory();
        SqlSession sqlSession=null;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            int count = 100;
            int loop = (int) Math.ceil(list.size() / (double) count);
            List tempList = new ArrayList(count);
            int start, stop;
            for(int i = 0; i < loop; i++){
                tempList.clear();
                start = i * count;
                stop = Math.min(i * count + count - 1, list.size() - 1);
                System.out.println("range:" + start + " - " + stop);
                for (int j = start; j <= stop; j++) {
                    tempList.add(list.get(j));
                }
                serversqlSessionTemplate.insert(str, tempList);
                sqlSession.commit();
                sqlSession.clearCache();
            }
        } catch (Exception e) {
            System.out.println(e);
            sqlSession.rollback();
            return 1;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("插入完成，耗时 " + (endTime - beginTime) + " 毫秒！");
        return 0;
    }

    /**
     * 添加
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Object save(String str,Object obj) throws RuntimeException{
        return serversqlSessionTemplate.insert(str,obj);
    }

    /**
     * 更新
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Object update(String str,Object obj) throws RuntimeException{
        return serversqlSessionTemplate.update(str, obj);
    }

    /**
     * 批量更新
     * @return
     * @throws Exception
     */
    public void batchUpdate(String str, List objs) throws RuntimeException
    {
        SqlSessionFactory sqlSessionFactory = serversqlSessionTemplate.getSqlSessionFactory();
        // 批量执行器
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try
        {
            if (objs != null)
            {
                for (int i = 0, size = objs.size(); i < size; i++)
                {
                    sqlSession.update(str, objs.get(i));
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
        }
        finally
        {
            sqlSession.close();
        }
    }


    /**
     * 批量删除
     * @param str
     * @param objs
     * @return
     * @throws RuntimeException
     */
    public Object batchDelete(String str,List objs) throws RuntimeException{
        return serversqlSessionTemplate.delete(str, objs);
    }

    /**
     * 删除
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Object delete(String str,Object obj) throws RuntimeException{
        return serversqlSessionTemplate.delete(str,obj);
    }

    /**
     * 查询出一个对象
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Object findForObject(String str,Object obj) throws RuntimeException{
        return serversqlSessionTemplate.selectOne(str,obj);
    }

    /**
     * 查询出一个对象
     * @param str
     * @return
     * @throws RuntimeException
     */
    public Object findForObject(String str) throws RuntimeException{
        return serversqlSessionTemplate.selectOne(str);
    }

    /**
     * 查询多个对象
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Object findForList(String str,Object obj) throws RuntimeException{
        return serversqlSessionTemplate.selectList(str,obj);
    }

    /**
     * 查询多个
     * @param str
     * @return
     * @throws RuntimeException
     */
    public Object findForList(String str) throws RuntimeException{
        return serversqlSessionTemplate.selectList(str);
    }
}
