package com.pavilion.service.impl;

import com.pavilion.dao.MaterialMapper;
import com.pavilion.dao.MaterialPriceMapper;
import com.pavilion.model.Material;
import com.pavilion.model.MaterialPrice;
import com.pavilion.model.dto.MaterialDto;
import com.pavilion.model.dto.MaterialPriceDto;
import com.pavilion.service.MaterialService;
import com.pavilion.util.EhcacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialMapper materialMapper;
    @Autowired
    MaterialPriceMapper materialPriceMapper;

    @Override
    public int getCount() {
        return materialMapper.getCount();
    }

    @Override
    public List<Material> getPagedMaterials(int page, int limit) {
        int offset=(page-1)*limit;
        return materialMapper.getPagedMaterials(offset,limit);
    }

    @Override
    public int getSearchCount(String cpscode,String cinvname,String cinvstd,String type) {
        return materialMapper.getSearchCount(cpscode,cinvname,cinvstd,type);
    }

    @Override
    public List<Material> getSearchPagedMaterials(int page, int limit, String cpscode,String cinvname,String cinvstd,String type) {
        int offset=(page-1)*limit;
        return materialMapper.getSearchPagedMaterials(offset,limit,cpscode,cinvname,cinvstd,type);
    }

    @Override
    public int updateIpsQty(int mtlId, int ipsQty) {
        return materialMapper.updateIpsQty(mtlId,ipsQty);
    }

    @Override
    public Double getTotalPrice() {
        return materialMapper.getTotalPrice();
    }

    @Override
    public int add(Material mtl) {
        return materialMapper.add(mtl);
    }

    @Override
    public Material getByCpscode(String cpscode) {
        return materialMapper.getByCpscode(cpscode);
    }

    @Override
    public int updateMaterial(Material mtl) {
        return materialMapper.updateByPrimaryKey(mtl);
    }

    @Override
    public int deleteById(int id) {
        return materialMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public boolean importExcelData() {
        boolean result=true;

        //先删除现有数据
        materialMapper.deleteAll();

        List<String> keys = EhcacheUtil.getInstance().getCache().getKeys();
        for (int i = 0; i < keys.size(); i++) {
            String key=keys.get(i);
            MaterialDto dto=(MaterialDto)EhcacheUtil.getInstance().get(key);
            Material mtlToAdd=new Material();
            mtlToAdd.setCpscode(dto.getCpscode());
            mtlToAdd.setCinvname(dto.getCinvname());
            mtlToAdd.setCinvstd(dto.getCinvstd());
            mtlToAdd.setType(dto.getType());
            mtlToAdd.setIpsquantity(0);
            mtlToAdd.setTotalPrice(Double.valueOf(0));
            int mtlId = add(mtlToAdd);
            if(mtlId<=0){
                throw new RuntimeException("添加失败, 材料id无效");
            }

            if(dto.getPrices()!=null && dto.getPrices().size()>0) {
                for (int j = 0; j < dto.getPrices().size(); j++) {
                    MaterialPriceDto priceDto=dto.getPrices().get(j);
                    MaterialPrice priceToAdd=new MaterialPrice();
                    priceToAdd.setMaterialId(mtlToAdd.getId());
                    priceToAdd.setUnit(priceDto.getUnit());
                    priceToAdd.setPrice(priceDto.getPrice());
                    int mtlPriceId = materialPriceMapper.add(priceToAdd);
                    if(mtlPriceId<=0){
                        throw new RuntimeException("添加失败, 阶梯价格id无效");
                    }
                }
            }
        }

        //计算总价
        materialMapper.calcTotalPrice();

        return result;
    }


}
