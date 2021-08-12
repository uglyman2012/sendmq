package com.cp.sendmq.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.sendmq.dao.StudentDao;
import com.cp.sendmq.entity.Student;
import com.cp.sendmq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yy
 * @since 2021-08-11
 */

@CacheConfig(cacheNames = "catalog_test_id")
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public Student updateStudentById(String id) {
        return null;
    }

    @Override
    @CachePut(key = "#entity.id")
    public boolean updateById(Student entity) {
        int i = studentDao.updateById(entity);
        System.out.println("student更新==============" + i);
        return false;
    }

    /**
     * 说明 beforeInvocation=true生效,前提是不存在事务中,或没开启rediscache事务
     *
     * @param id
     * @return
     */
    @Override
    @CacheEvict(key = "#id", beforeInvocation = true)
    public boolean deleteByid(String id) {

        int i = studentDao.deleteById(id);
        System.out.println("student删除==============" + i);
        int a = 9 / 0;
        return false;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteBach(String id) {
        //int i = studentDao.deleteById(id);
        System.out.println("student删除==============" + id);
        return false;
    }
}
