package com.pavilion.controllers;

import com.pavilion.model.Cost;
import com.pavilion.model.Material;
import com.pavilion.model.MaterialPrice;
import com.pavilion.model.Result;
import com.pavilion.model.dto.MaterialDto;
import com.pavilion.model.dto.MaterialPriceDto;
import com.pavilion.model.dto.TableData;
import com.pavilion.service.CostService;
import com.pavilion.service.MaterialPriceService;
import com.pavilion.service.MaterialService;
import com.pavilion.util.EhcacheUtil;
import com.pavilion.util.FileUtil;
import org.apache.poi.ss.usermodel.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/material")
public class MaterialController {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    MaterialService materialService;
    @Autowired
    MaterialPriceService materialPriceService;
    @Autowired
    CostService costService;

    @RequestMapping(value = "/bomQuery", method= RequestMethod.GET)
    public String bomQuery(){
        return "material/bomQuery";
    }

    @RequestMapping(value = "/bomMaintain", method= RequestMethod.GET)
    public String bomMaintain(){
        return "material/bomMaintain";
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

    @RequestMapping(value = "/calcWorkTime", method= RequestMethod.GET)
    public String calcWorkTime(Model model){
        Cost cost=costService.getFirst();
        model.addAttribute("cost",cost);
        return "material/calcWorkTime";
    }

    @RequestMapping(value = "/updateWorkTime", method= RequestMethod.POST)
    @ResponseBody
    public Result<String> updateWorkTime(Double labor, Double wear, Double management, Double sale){

        Cost cost=costService.getFirst();
        cost.setLabor(labor);
        cost.setWear(wear);
        cost.setManagement(management);
        cost.setSale(sale);

        costService.update(cost);

        return Result.success("更新成功");
    }

    @RequestMapping(value = "/getTotalPrice", method= RequestMethod.GET)
    @ResponseBody
    public Double getTotalPrice(){
        try {
            Double totalPrice = materialService.getTotalPrice();
            return totalPrice;
        }catch (Exception ex){
            ex.printStackTrace();
            return Double.valueOf(0);
        }
    }

    @RequestMapping(value = "/updateIpsQty", method= RequestMethod.POST)
    @ResponseBody
    public Double updateIpsQty(int mtlId,int ipsQty){
        try {
            Double totalPrice=Double.valueOf(0);
            int row = materialService.updateIpsQty(mtlId,ipsQty);
            if(row>0) {
                totalPrice = materialService.getTotalPrice();
            }
            return totalPrice;
        }catch (Exception ex){
            ex.printStackTrace();
            return Double.valueOf(0);
        }
    }

    @RequestMapping(value = "/add", method= RequestMethod.POST)
    @ResponseBody
    public Result<String> add(String cpscode,String cinvname,String cinvstd,String type,int ipsquantity){
        if(StringUtils.isEmptyOrWhitespace(cpscode) ||StringUtils.isEmptyOrWhitespace(cinvname) || StringUtils.isEmptyOrWhitespace(cinvstd)){
            return Result.parameterError();
        }

        if(ipsquantity<=0){
            ipsquantity=0;
        }

        Material existMtl = materialService.getByCpscode(cpscode);
        if(existMtl!=null){
            return Result.fail("子件编码[ "+existMtl.getCpscode()+" ]已存在");
        }

        Material mtl=new Material();
        mtl.setCpscode(cpscode);
        mtl.setCinvname(cinvname);
        mtl.setCinvstd(cinvstd);
        mtl.setType(type);
        mtl.setIpsquantity(ipsquantity);
        mtl.setTotalPrice(Double.valueOf(0));
        int row = materialService.add(mtl);

        if(row>0) {
            return Result.success("添加成功");
        }else{
            return Result.fail("添加失败");
        }
    }

    @RequestMapping(value = "/addPrice", method= RequestMethod.POST)
    @ResponseBody
    public Result<String> addPrice(int mtlId,int unit,Double price){
        if(mtlId<=0 || unit<=0 || price<=0){
            return Result.parameterError();
        }

        List<MaterialPrice> prices=materialPriceService.getMaterialPrices(mtlId);
        if(prices!=null && prices.stream().anyMatch(mp->mp.getUnit()==unit)){
            return Result.fail("已存在阶梯价");
        }

        MaterialPrice mp=new MaterialPrice();
        mp.setMaterialId(mtlId);
        mp.setUnit(unit);
        mp.setPrice(price);

        int row = materialPriceService.add(mp);

        if(row>0) {
            return Result.success("添加成功");
        }else{
            return Result.fail("添加失败");
        }
    }

    @RequestMapping(value = "/updateMaterial", method= RequestMethod.POST)
    @ResponseBody
    public Result<Double> updateMaterial(Material mtl,String field){
        if(StringUtils.isEmptyOrWhitespace(mtl.getCpscode()) || StringUtils.isEmptyOrWhitespace(mtl.getCinvname()) || StringUtils.isEmptyOrWhitespace(mtl.getCinvstd()) ||
                StringUtils.isEmptyOrWhitespace(mtl.getType())){
            return Result.parameterError();
        }

        if(mtl.getIpsquantity()<=0){
            mtl.setIpsquantity(0);
        }

        if(field=="cpscode") {
            Material existMtl = materialService.getByCpscode(mtl.getCpscode());
            if (existMtl != null) {
                return Result.fail("子件编码[ " + existMtl.getCpscode() + " ]已存在");
            }
        }

        int row = materialService.updateMaterial(mtl);
        if(row>0) {
            Double totalPrice = materialService.getTotalPrice();
            return Result.success("修改成功",totalPrice);
        }else{
            return Result.fail(-1,"修改失败",Double.valueOf(0));
        }
    }

    @RequestMapping(value = "/deletePrice", method= RequestMethod.POST)
    @ResponseBody
    public Result<String> deletePrice(int mtlPriceId){
        int row = materialPriceService.deleteById(mtlPriceId);
        if(row>0) {
            return Result.success("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }

    @RequestMapping(value = "/deleteMaterial", method= RequestMethod.POST)
    @ResponseBody
    public Result<String> deleteMaterial(int id){
        int row = materialService.deleteById(id);
        if(row>0) {
            return Result.success("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        logger.info("开始处理excel:"+file.getOriginalFilename());

        try {
            if (file.isEmpty()) {
                logger.error("excel文件是空的");
                return Result.fail("上传失败，因为文件是空的.");
            }

            String fileExtension=FileUtil.getFileExtension(file.getOriginalFilename());
            if(!fileExtension.endsWith("xls") && !fileExtension.endsWith("xlsx")){
                logger.error("文件格式不正确");
                return Result.fail("文件格式不正确");
            }

            //获取jar包所在的路径
            ApplicationHome home = new ApplicationHome(this.getClass());
            String uploadedFileDir=home.getDir()+"\\upload\\";
            File tmp=new File(uploadedFileDir);
            if(!tmp.exists()){
                tmp.mkdir();
            }
            String uploadedFilePath=uploadedFileDir+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+fileExtension;
            logger.info("原始文件名:"+file.getOriginalFilename()+",对应上传后的文件:"+uploadedFilePath);
            File uploadedFile=new File(uploadedFilePath);

            //先保存上传的文件
            BufferedOutputStream out = new BufferedOutputStream( new FileOutputStream(uploadedFile));
            out.write(file.getBytes());
            out.flush();
            out.close();

            //清除缓存
            EhcacheUtil.getInstance().removeAll();

            //Excel文件
            Workbook wb= WorkbookFactory.create(file.getInputStream());
            //Excel工作表
            Sheet sheet = wb.getSheetAt(0);
            Row firstRow = sheet.getRow(0);
            int lastColIndex=firstRow.getLastCellNum();
            if(lastColIndex<4){
                logger.error("无阶梯价格信息, 导入的excel格式必须严格按照模板来");
                return Result.fail("无阶梯价格信息, 导入的excel格式必须严格按照模板来");
            }

            Map<Integer , Integer> unitMap = new HashMap<Integer , Integer>();
            //处理表头信息
            for(int j=0;j<lastColIndex-1;j++){
                if(j>=4) {
                    Cell cellj=firstRow.getCell(j);
                    cellj.setCellType(CellType.STRING);
                    String unitStr = cellj.getStringCellValue().replaceFirst(".*?(\\d+).*", "$1");
                    if (StringUtils.isEmptyOrWhitespace(unitStr)) {
                        logger.error("表头列[" + (j + 1) + "]数据错误");
                        return Result.fail("表头列[" + (j + 1) + "]数据错误");
                    }
                    int unit = Integer.parseInt(unitStr);
                    unitMap.put(j,unit);
                }
            }

            //循环处理每一行数据
            for(int i=1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);

                Cell cell0 = row.getCell(0);
                cell0.setCellType(CellType.STRING);
                String cpscode = cell0.getStringCellValue();
                if(StringUtils.isEmptyOrWhitespace(cpscode)){
                    return Result.fail("上传失败,行["+i+"]子件编码不能为空");
                }

                Cell cell1 = row.getCell(1);
                cell1.setCellType(CellType.STRING);
                String type = cell1.getStringCellValue();

                Cell cell2 = row.getCell(2);
                cell2.setCellType(CellType.STRING);
                String cinvname = cell2.getStringCellValue();

                Cell cell3 = row.getCell(3);
                cell3.setCellType(CellType.STRING);
                String cinvstd = cell3.getStringCellValue();
                MaterialDto tmpMtl=new MaterialDto(0,cpscode,cinvname,cinvstd,0,type);

                List<MaterialPriceDto> tmpPrices=new ArrayList<>();
                for(int j=4;j<lastColIndex-1;j++){
                    int unit=unitMap.get(j);
                    Cell cellj=row.getCell(j);
                    cellj.setCellType(CellType.NUMERIC);
                    Double price=cellj.getNumericCellValue();
                    MaterialPriceDto tmpPrice=new MaterialPriceDto(0,0,unit,price);
                    tmpPrices.add(tmpPrice);
                }
                tmpMtl.setPrices(tmpPrices);

                EhcacheUtil.getInstance().put(tmpMtl.getCpscode(),tmpMtl);
            }

            //将内存数据批量插入到数据库中
            materialService.importExcelData();

        }catch (Exception ex){
            ex.printStackTrace();
            return Result.fail("上传失败,"+ ex.getMessage());
        }
        return Result.success("上传成功");
    }
}
