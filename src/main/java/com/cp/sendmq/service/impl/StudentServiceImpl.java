package com.cp.sendmq.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.sendmq.dao.StudentDao;
import com.cp.sendmq.entity.Student;
import com.cp.sendmq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
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
}
