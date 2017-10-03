package com.pavilion.controllers;

import com.pavilion.model.Material;
import com.pavilion.model.MaterialPrice;
import com.pavilion.model.dto.MaterialDto;
import com.pavilion.model.dto.MaterialPriceDto;
import com.pavilion.model.dto.TableData;
import com.pavilion.service.MaterialPriceService;
import com.pavilion.service.MaterialService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    MaterialService materialService;
    @Autowired
    MaterialPriceService materialPriceService;

    @RequestMapping(value = "/bomQuery", method= RequestMethod.GET)
    public String bomQuery(){
        return "material/bomQuery";
    }

    @RequestMapping(value="/query", method = RequestMethod.GET)
    @ResponseBody
    public TableData<List<MaterialDto>> queryMaterials(int page, int limit, String cpscode,String cinvname,String cinvstd,String type){
        TableData<List<MaterialDto>> data=new TableData<List<MaterialDto>>();

        int count=0;
        List<MaterialDto> mtlDtos;

        count = materialService.getSearchCount(cpscode,cinvname,cinvstd,type);
        List<Material> mtls = materialService.getSearchPagedMaterials(page, limit,cpscode,cinvname,cinvstd,type);
        mtlDtos = new ModelMapper().map(mtls, new TypeToken<List<MaterialDto>>() {
        }.getType());

        data.setCount(count);
        data.setData(mtlDtos);
        return data;
    }

    @RequestMapping(value = "/viewPrice", method= RequestMethod.GET)
    public String viewPrice(int materialId, Model model){
        model.addAttribute("materialId",materialId);

        return "material/viewPrice";
    }

    @RequestMapping(value = "/getPriceInfo", method= RequestMethod.GET)
    @ResponseBody
    public TableData<List<MaterialPriceDto>> getPriceInfo(int materialId){
        TableData<List<MaterialPriceDto>> data= new TableData<>();

        List<MaterialPrice> mtls = materialPriceService.getMaterialPrices(materialId);
        List<MaterialPriceDto> priceDtos = new ModelMapper().map(mtls, new TypeToken<List<MaterialPriceDto>>() {
        }.getType());

        data.setData(priceDtos);
        return data;
    }


}
