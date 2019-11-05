package com.hfut.studentmanager.service;

import com.hfut.studentmanager.mapper.ClazzMapper;
import com.hfut.studentmanager.pojo.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    public List<Clazz> listAllClazz(){
        return clazzMapper.findAllClazz();
    }

    public boolean addClazz(Clazz clazz){
        return clazzMapper.insertClazz(clazz);
    }

    public boolean deleteClazz(Integer id){
        return clazzMapper.deleteClazz(id);
    }
}
