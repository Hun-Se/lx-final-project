package com.lx.finalproject.dao;


import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lx.finalproject.dto.prp.PrpDTO;

@Repository
public class PropertyDaoImpl implements PropertyDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<PrpDTO> getAllProperties() {
        return sqlSession.selectList("com.lx.finalproject.dao.PropertyDao.getAllProperties");
    }

    @Override
    public PrpDTO getPropertyMarkerById(Long prp_pk) {
        return sqlSession.selectOne("com.lx.finalproject.dao.PropertyDao.getPropertyById", prp_pk);
    }
}
