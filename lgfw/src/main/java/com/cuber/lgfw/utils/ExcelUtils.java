//package com.cucumber.cudemo.utils;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
///**
// * Excel 操作工具类 - 基于 Apache POI
// * 支持 .xlsx 格式文件的读写操作
// */
//public class ExcelUtils {
//    /**
//     * 创建新的 Excel 工作簿
//     * @return 新的 XSSFWorkbook 对象
//     */
//    public static Workbook createWorkbook() {
//        return new XSSFWorkbook();
//    }
//
//    /**
//     * 从文件路径读取 Excel 工作簿
//     * @param filePath 文件路径
//     * @return Workbook 对象
//     * @throws IOException 文件读取异常
//     */
//    public static Workbook readWorkbook(String filePath) throws IOException {
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            return new XSSFWorkbook(fis);
//        }
//    }
//
//    /**
//     * 从输入流读取 Excel 工作簿
//     * @param inputStream 输入流
//     * @return Workbook 对象
//     * @throws IOException 流读取异常
//     */
//    public static Workbook readWorkbook(InputStream inputStream) throws IOException {
//        return new XSSFWorkbook(inputStream);
//    }
//
//    /**
//     * 创建工作表
//     * @param workbook 工作簿对象
//     * @param sheetName 工作表名称
//     * @return 新创建的工作表
//     */
//    public static Sheet createSheet(Workbook workbook, String sheetName) {
//        return workbook.createSheet(sheetName);
//    }
//
//    /**
//     * 获取工作表
//     * @param workbook 工作簿对象
//     * @param sheetIndex 工作表索引
//     * @return 工作表对象
//     */
//    public static Sheet getSheet(Workbook workbook, int sheetIndex) {
//        return workbook.getSheetAt(sheetIndex);
//    }
//
//    /**
//     * 获取工作表
//     * @param workbook 工作簿对象
//     * @param sheetName 工作表名称
//     * @return 工作表对象
//     */
//    public static Sheet getSheet(Workbook workbook, String sheetName) {
//        return workbook.getSheet(sheetName);
//    }
//
//    /**
//     * 创建行
//     * @param sheet 工作表对象
//     * @param rowIndex 行索引
//     * @return 新创建的行
//     */
//    public static Row createRow(Sheet sheet, int rowIndex) {
//        return sheet.createRow(rowIndex);
//    }
//
//    /**
//     * 创建单元格并设置值
//     * @param row 行对象
//     * @param cellIndex 单元格索引
//     * @param value 单元格值
//     * @return 新创建的单元格
//     */
//    public static Cell createCell(Row row, int cellIndex, Object value) {
//        Cell cell = row.createCell(cellIndex);
//        setCellValue(cell, value);
//        return cell;
//    }
//
//    /**
//     * 设置单元格值
//     * @param cell 单元格对象
//     * @param value 值
//     */
//    public static void setCellValue(Cell cell, Object value) {
//        if (value == null) {
//            cell.setCellValue("");
//        } else if (value instanceof String) {
//            cell.setCellValue((String) value);
//        } else if (value instanceof Number) {
//            cell.setCellValue(((Number) value).doubleValue());
//        } else if (value instanceof Date) {
//            cell.setCellValue((Date) value);
//            // 设置日期格式
//            CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
//            CreationHelper createHelper = cell.getSheet().getWorkbook().getCreationHelper();
//            style.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//            cell.setCellStyle(style);
//        } else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean) value);
//        } else {
//            cell.setCellValue(value.toString());
//        }
//    }
//
//    /**
//     * 获取单元格值
//     * @param cell 单元格对象
//     * @return 单元格值
//     */
//    public static Object getCellValue(Cell cell) {
//        if (cell == null) {
//            return null;
//        }
//
//        switch (cell.getCellType()) {
//            case STRING:
//                return cell.getStringCellValue();
//            case NUMERIC:
//                if (DateUtil.isCellDateFormatted(cell)) {
//                    return cell.getDateCellValue();
//                } else {
//                    return cell.getNumericCellValue();
//                }
//            case BOOLEAN:
//                return cell.getBooleanCellValue();
//            case FORMULA:
//                return cell.getCellFormula();
//            case BLANK:
//                return "";
//            default:
//                return cell.toString();
//        }
//    }
//
//    /**
//     * 设置表头
//     * @param sheet 工作表
//     * @param headers 表头数组
//     */
//    public static void setHeaders(Sheet sheet, String[] headers) {
//        Row headerRow = createRow(sheet, 0);
//        for (int i = 0; i < headers.length; i++) {
//            createCell(headerRow, i, headers[i]);
//        }
//    }
//
//    /**
//     * 写入数据行
//     * @param sheet 工作表
//     * @param rowIndex 行索引
//     * @param data 数据数组
//     */
//    public static void writeRow(Sheet sheet, int rowIndex, Object[] data) {
//        Row row = createRow(sheet, rowIndex);
//        for (int i = 0; i < data.length; i++) {
//            createCell(row, i, data[i]);
//        }
//    }
//
//    /**
//     * 写入多行数据
//     * @param sheet 工作表
//     * @param dataList 数据列表
//     */
//    public static void writeRows(Sheet sheet, List<Object[]> dataList) {
//        for (int i = 0; i < dataList.size(); i++) {
//            writeRow(sheet, i + 1, dataList.get(i)); // 从第1行开始（第0行是表头）
//        }
//    }
//
//    /**
//     * 读取所有行数据
//     * @param sheet 工作表
//     * @return 所有行数据列表
//     */
//    public static List<List<Object>> readAllRows(Sheet sheet) {
//        List<List<Object>> data = new ArrayList<>();
//        for (Row row : sheet) {
//            List<Object> rowData = new ArrayList<>();
//            for (Cell cell : row) {
//                rowData.add(getCellValue(cell));
//            }
//            data.add(rowData);
//        }
//        return data;
//    }
//
//    /**
//     * 读取指定行数据
//     * @param sheet 工作表
//     * @param rowIndex 行索引
//     * @return 行数据列表
//     */
//    public static List<Object> readRow(Sheet sheet, int rowIndex) {
//        Row row = sheet.getRow(rowIndex);
//        if (row == null) return Collections.emptyList();
//
//        List<Object> rowData = new ArrayList<>();
//        for (Cell cell : row) {
//            rowData.add(getCellValue(cell));
//        }
//        return rowData;
//    }
//
//    /**
//     * 读取指定单元格数据
//     * @param sheet 工作表
//     * @param rowIndex 行索引
//     * @param cellIndex 单元格索引
//     * @return 单元格值
//     */
//    public static Object readCell(Sheet sheet, int rowIndex, int cellIndex) {
//        Row row = sheet.getRow(rowIndex);
//        if (row == null) return null;
//
//        Cell cell = row.getCell(cellIndex);
//        return getCellValue(cell);
//    }
//
//    /**
//     * 自动调整列宽
//     * @param sheet 工作表
//     */
//    public static void autoSizeColumns(Sheet sheet) {
//        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
//            sheet.autoSizeColumn(i);
//        }
//    }
//
//    /**
//     * 保存工作簿到文件
//     * @param workbook 工作簿
//     * @param filePath 文件路径
//     * @throws IOException 文件写入异常
//     */
//    public static void saveWorkbook(Workbook workbook, String filePath) throws IOException {
//        try (FileOutputStream fos = new FileOutputStream(filePath)) {
//            workbook.write(fos);
//        }
//    }
//
//    /**
//     * 将工作簿写入输出流
//     * @param workbook 工作簿
//     * @param outputStream 输出流
//     * @throws IOException 流写入异常
//     */
//    public static void writeWorkbook(Workbook workbook, OutputStream outputStream) throws IOException {
//        workbook.write(outputStream);
//    }
//
//    /**
//     * 关闭工作簿
//     * @param workbook 工作簿
//     * @throws IOException 关闭异常
//     */
//    public static void closeWorkbook(Workbook workbook) throws IOException {
//        if (workbook != null) {
//            workbook.close();
//        }
//    }
//
//    /**
//     * 创建带有表头和数据的工作簿
//     * @param sheetName 工作表名称
//     * @param headers 表头数组
//     * @param dataList 数据列表
//     * @return 创建的工作簿
//     */
//    public static Workbook createWorkbookWithData(String sheetName, String[] headers, List<Object[]> dataList) {
//        Workbook workbook = createWorkbook();
//        Sheet sheet = createSheet(workbook, sheetName);
//        setHeaders(sheet, headers);
//        writeRows(sheet, dataList);
//        autoSizeColumns(sheet);
//        return workbook;
//    }
//
//    /**
//     * 导出数据到 Excel 文件
//     * @param filePath 文件路径
//     * @param sheetName 工作表名称
//     * @param headers 表头数组
//     * @param dataList 数据列表
//     * @throws IOException 文件操作异常
//     */
//    public static void exportToExcel(String filePath, String sheetName, String[] headers, List<Object[]> dataList) throws IOException {
//        Workbook workbook = createWorkbookWithData(sheetName, headers, dataList);
//        saveWorkbook(workbook, filePath);
//        closeWorkbook(workbook);
//    }
//
//    /**
//     * 从 Excel 文件导入数据
//     * @param filePath 文件路径
//     * @param sheetIndex 工作表索引
//     * @return 数据列表
//     * @throws IOException 文件读取异常
//     */
//    public static List<List<Object>> importFromExcel(String filePath, int sheetIndex) throws IOException {
//        try (Workbook workbook = readWorkbook(filePath)) {
//            Sheet sheet = getSheet(workbook, sheetIndex);
//            return readAllRows(sheet);
//        }
//    }
//
//    /**
//     * 从 Excel 文件导入数据
//     * @param filePath 文件路径
//     * @param sheetName 工作表名称
//     * @return 数据列表
//     * @throws IOException 文件读取异常
//     */
//    public static List<List<Object>> importFromExcel(String filePath, String sheetName) throws IOException {
//        try (Workbook workbook = readWorkbook(filePath)) {
//            Sheet sheet = getSheet(workbook, sheetName);
//            return readAllRows(sheet);
//        }
//    }
//}
