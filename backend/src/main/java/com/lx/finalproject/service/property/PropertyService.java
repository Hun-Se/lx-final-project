package com.lx.finalproject.service.property;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lx.finalproject.dao.property.PropertyDAO;
import com.lx.finalproject.vo.PrpVO;

@Service
public class PropertyService {

    @Autowired
    private PropertyDAO dao;

    public List<PrpVO> getAllPropertiesByAgent(int agentPk) {
        return dao.getAllPropertiesByAgent(agentPk);
    }

    public PrpVO getPropertyById(int prpPk) {
        return dao.getPropertyById(prpPk);
    }

    public void saveProperty(PrpVO property) {
    	dao.saveProperty(property);
    }

    public void updateProperty(PrpVO property) {
    	dao.updateProperty(property);
    }

    public void deleteProperty(int prpPk) {
    	dao.deleteProperty(prpPk);
    }
}
