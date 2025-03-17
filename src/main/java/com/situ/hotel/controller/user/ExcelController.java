package com.situ.hotel.controller.user;

import com.situ.hotel.domain.vo.TurnoverVo;
import com.situ.hotel.service.TurnoverService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class ExcelController {
    private final TurnoverService turnoverService;

    @GetMapping
    public void exportExcel(HttpServletResponse response) throws Exception {
        // 创建Excel文档
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");

        // 创建表头
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("日期");
        header.createCell(1).setCellValue("营业额");

        // 创建日期格式
        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd"));

        // 填充数据
        List<TurnoverVo> turnoverVos = turnoverService.select();
        int rowIndex = 1;
        for (TurnoverVo turnoverVo : turnoverVos) {
            XSSFRow row = sheet.createRow(rowIndex++);

            // 写入日期
            if (turnoverVo.getDate() != null) {
                row.createCell(0).setCellValue(turnoverVo.getDate()); // 确保这是一个 Date 对象
                row.getCell(0).setCellStyle(dateCellStyle); // 设置日期格式
            }

            // 获取营业额并进行类型转换
            BigDecimal turnover = turnoverVo.getTurnover();
            if (turnover != null) {
                row.createCell(1).setCellValue(turnover.doubleValue()); // 作为数字写入
            } else {
                row.createCell(1).setCellValue(0); // 如果没有值，设定为0或其他默认值
            }
        }

        // 自动调整列宽
        sheet.autoSizeColumn(0); // 调整日期列宽
        sheet.autoSizeColumn(1); // 调整营业额列宽

        // 设置响应头信息
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=turnover.xlsx");

        // 将Excel文档写入响应流中
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close(); // 关闭工作簿
    }
}