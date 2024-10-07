package com.lx.finalproject.dao;


import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.lx.finalproject.vo.PropertyVo;

@Repository
public class PropertyDaoImpl implements PropertyDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<PropertyVo> getAllProperties() {
        return sqlSession.selectList("com.lx.finalproject.dao.PropertyDao.getAllProperties");
    }

    @Override
    public PropertyVo getPropertyById(Long prp_pk) {
        return sqlSession.selectOne("com.lx.finalproject.dao.PropertyDao.getPropertyById", prp_pk);
    }
}
