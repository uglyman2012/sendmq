package com.cp.sendmq.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.sendmq.dao.StudentDao;
import com.cp.sendmq.entity.Student;
import com.cp.sendmq.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hehaopeng
 * @since 2021-08-11
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

}
