package com.cp.sendmq.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cp.sendmq.entity.Student;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hehaopeng
 * @since 2021-08-11
 */
public interface StudentService extends IService<Student> {

    Student updateStudentById(String id);

    boolean deleteByid(String id);

    boolean deleteBach(String id);
}
